package au.edu.unsw.assi.marketclient;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import au.edu.unsw.assi.soapservice.ImportDownloadFaultMsg;
import au.edu.unsw.assi.soapservice.ImportMarketDataRequest;
import au.edu.unsw.assi.soapservice.ImportMarketDataResponse;
import au.edu.unsw.assi.soapservice.Service1;
import au.edu.unsw.assi.soapservice.Service1ImplService;
import au.edu.unsw.soacourse.marketdatautilservice.MarketDataUtilService;
import au.edu.unsw.soacourse.marketdatautilservice.MarketDataUtilServiceImplService;
import au.edu.unsw.soacourse.marketdatautilservice.SummaryMarketDataFaultMsg;
import au.edu.unsw.soacourse.marketdatautilservice.SummaryMarketDataRequest;
import au.edu.unsw.soacourse.marketdatautilservice.SummaryMarketDataResponse;

public class Result2Command implements Command {

	private au.edu.unsw.soacourse.marketdatautilservice.ObjectFactory factoryMarketDataUtil;
	private au.edu.unsw.assi.soapservice.ObjectFactory factoryImportDownload;

	public Result2Command() {
		super();
		factoryMarketDataUtil = new au.edu.unsw.soacourse.marketdatautilservice.ObjectFactory();
		factoryImportDownload = new au.edu.unsw.assi.soapservice.ObjectFactory();
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		URL wsdlLocation1 = new URL("http://localhost:8080/ImportDownloadService/Service1?wsdl");
		URL wsdlLocation2 = new URL("http://localhost:8080/MarketDataUtilService/MarketDataUtilService?wsdl");
		Service1 importDownloadService = new Service1ImplService(wsdlLocation1).getService1ImplPort();
		MarketDataUtilService convertSummaryService = new MarketDataUtilServiceImplService(wsdlLocation2)
				.getMarketDataUtilServiceImplPort();
		ImportMarketDataRequest wsRequest1 = factoryImportDownload.createImportMarketDataRequest();
		ImportMarketDataResponse wsResponse1 = factoryImportDownload.createImportMarketDataResponse();
		SummaryMarketDataRequest wsRequest2 = factoryMarketDataUtil.createSummaryMarketDataRequest();
		SummaryMarketDataResponse wsResponse2 = factoryMarketDataUtil.createSummaryMarketDataResponse();
		String text;

		wsRequest1.setSec(request.getParameter("sec").trim());
		String stdate_tmp = request.getParameter("startdate").trim();
		String eddate_tmp = request.getParameter("enddate").trim();
		if (!stdate_tmp.matches(".+/.+/.+") || !eddate_tmp.matches(".+/.+/.+")) {
			stdate_tmp = "a/a/a";
			eddate_tmp = "b/b/b";
		}
		String[] stdate = stdate_tmp.split("\\/");
		String[] eddate = eddate_tmp.split("\\/");
		wsRequest1.setStartDate(stdate[1] + "-" + stdate[0] + "-" + stdate[2]);
		wsRequest1.setEndDate(eddate[1] + "-" + eddate[0] + "-" + eddate[2]);
		wsRequest1.setDataSource(request.getParameter("url").trim());

		try {
			wsResponse1 = importDownloadService.importMarketData(wsRequest1);
		} catch (ImportDownloadFaultMsg e) {
			request.setAttribute("errorMsg", e.getLocalizedMessage());
			request.setAttribute("faultMsg", e.getFaultInfo().getFaultMessage());
			request.setAttribute("faultType", e.getFaultInfo().getFaultType());
			e.printStackTrace();

			String tmpdir = System.getProperty("catalina.home");
			request.setAttribute("empty", "0");
			ArrayList<String> setIdList = new ArrayList<String>();
			String path = tmpdir + "/webapps/ROOT";
			File f = new File(path);
			if (!f.exists()) {
				// System.out.println(path + " not exists");
				request.setAttribute("empty", "1");
				return "/error.jsp";
			}
			File[] fileList = f.listFiles();
			for (int i = 0; i < fileList.length; ++i) {
				String name = fileList[i].getName();
				// System.out.println(name);
				if (name.matches("ass1\\-[0-9a-z]{7}\\.csv")) {
					System.out.println(name);
					setIdList.add(name.substring(0, 12));
				}
			}
			if (setIdList.isEmpty()) {
				request.setAttribute("empty", "1");
				return "/error.jsp";
			}
			request.setAttribute("setList", setIdList);
			return "/error.jsp";
		}
		System.out.println(wsResponse1.getEventSetId());
		wsRequest2.setEventSetId(wsResponse1.getEventSetId());

		try {
			wsResponse2 = convertSummaryService.summaryMarketData(wsRequest2);
		} catch (SummaryMarketDataFaultMsg e) {
			request.setAttribute("errorMsg", e.getLocalizedMessage());
			request.setAttribute("faultMsg", e.getFaultInfo().getFaultMessage());
			request.setAttribute("faultType", e.getFaultInfo().getFaultType());
			e.printStackTrace();

			String tmpdir = System.getProperty("catalina.home");
			request.setAttribute("empty", "0");
			ArrayList<String> setIdList = new ArrayList<String>();
			String path = tmpdir + "/webapps/ROOT";
			File f = new File(path);
			if (!f.exists()) {
				// System.out.println(path + " not exists");
				request.setAttribute("empty", "1");
				return "/error.jsp";
			}
			File[] fileList = f.listFiles();
			for (int i = 0; i < fileList.length; ++i) {
				String name = fileList[i].getName();
				// System.out.println(name);
				if (name.matches("ass1\\-[0-9a-z]{7}\\.csv")) {
					System.out.println(name);
					setIdList.add(name.substring(0, 12));
				}
			}
			if (setIdList.isEmpty()) {
				request.setAttribute("empty", "1");
				return "/error.jsp";
			}
			request.setAttribute("setList", setIdList);
			return "/error.jsp";
		}

		text = "The final market data file has " + wsResponse2.getNumberOfLines()
				+ " lines data with the time period from " + wsResponse2.getStartDate() + " to "
				+ wsResponse2.getEndDate() + ". Its Event Set ID is " + wsResponse2.getEventSetId()
				+ " and its Currency is " + wsResponse2.getCurrencyCode() + ". The Security Code of this file is "
				+ wsResponse2.getSec();
		System.out.println(text);
		request.setAttribute("output", text);
		String tmpdir = System.getProperty("catalina.home");
		request.setAttribute("empty", "0");
		ArrayList<String> setIdList = new ArrayList<String>();
		String path = tmpdir + "/webapps/ROOT";
		File f = new File(path);
		if (!f.exists()) {
			// System.out.println(path + " not exists");
			request.setAttribute("empty", "1");
			return "/IndexPlusResult.jsp";
		}
		File[] fileList = f.listFiles();
		for (int i = 0; i < fileList.length; ++i) {
			String name = fileList[i].getName();
			// System.out.println(name);
			if (name.matches("ass1\\-[0-9a-z]{7}\\.csv")) {
				System.out.println(name);
				setIdList.add(name.substring(0, 12));
			}
		}
		if (setIdList.isEmpty()) {
			request.setAttribute("empty", "1");
			return "/IndexPlusResult.jsp";
		}
		request.setAttribute("setList", setIdList);
		return "/IndexPlusResult.jsp";
	}

}
