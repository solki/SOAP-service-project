package au.edu.unsw.assi.marketclient;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import au.edu.unsw.soacourse.marketdatautilservice.ConvertMarketDataFaultMsg;
import au.edu.unsw.soacourse.marketdatautilservice.ConvertMarketDataRequest;
import au.edu.unsw.soacourse.marketdatautilservice.ConvertMarketDataResponse;
import au.edu.unsw.soacourse.marketdatautilservice.MarketDataUtilService;
import au.edu.unsw.soacourse.marketdatautilservice.MarketDataUtilServiceImplService;
import au.edu.unsw.soacourse.marketdatautilservice.SummaryMarketDataFaultMsg;
import au.edu.unsw.soacourse.marketdatautilservice.SummaryMarketDataRequest;
import au.edu.unsw.soacourse.marketdatautilservice.SummaryMarketDataResponse;

public class Result3Command implements Command {

	private au.edu.unsw.soacourse.marketdatautilservice.ObjectFactory factoryMarketDataUtil;
	
	public Result3Command() {
		super();
		factoryMarketDataUtil = new au.edu.unsw.soacourse.marketdatautilservice.ObjectFactory();
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		URL wsdlLocation = new URL("http://localhost:8080/MarketDataUtilService/MarketDataUtilService?wsdl");
		MarketDataUtilService convertSummaryService = new MarketDataUtilServiceImplService(wsdlLocation)
				.getMarketDataUtilServiceImplPort();
		
		ConvertMarketDataRequest wsRequest1 = factoryMarketDataUtil.createConvertMarketDataRequest();
		ConvertMarketDataResponse wsResponse1 = factoryMarketDataUtil.createConvertMarketDataResponse();
		SummaryMarketDataRequest wsRequest2 = factoryMarketDataUtil.createSummaryMarketDataRequest();
		SummaryMarketDataResponse wsResponse2 = factoryMarketDataUtil.createSummaryMarketDataResponse();
		String text;
		
		wsRequest1.setEventSetId(request.getParameter("eventSetId").trim());
		wsRequest1.setTargetCurrency(request.getParameter("currency").trim());
		
		try {
			wsResponse1 = convertSummaryService.convertMarketData(wsRequest1);
		} catch (ConvertMarketDataFaultMsg e) {
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
