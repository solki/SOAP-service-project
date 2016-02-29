package au.edu.unsw.soacourse.demoapp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;

import au.edu.unsw.soacourse.marketdatautilservice.*;
import au.edu.unsw.soacourse.soapcurrencyconvert.*;
import au.edu.unsw.assi.soapservice.*;

/**
 * Servlet implementation class MarketDataDemoApp
 */
@WebServlet("/MarketDataDemoApp")
public class MarketDataDemoApp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private au.edu.unsw.soacourse.soapcurrencyconvert.ObjectFactory factoryCurrencyConvert;
	private au.edu.unsw.soacourse.marketdatautilservice.ObjectFactory factoryMarketDataUtil;
	private au.edu.unsw.assi.soapservice.ObjectFactory factoryImportDownload;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MarketDataDemoApp() {
		super();

		factoryCurrencyConvert = new au.edu.unsw.soacourse.soapcurrencyconvert.ObjectFactory();
		factoryMarketDataUtil = new au.edu.unsw.soacourse.marketdatautilservice.ObjectFactory();
		factoryImportDownload = new au.edu.unsw.assi.soapservice.ObjectFactory();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String destJsp = "error.jsp";

		if (request.getParameter("action").equals("import")) {
			destJsp = processImportRequest(request, response);
		} else if (request.getParameter("action").equals("download")) {
			destJsp = processDownloadRequest(request, response);
		} else if (request.getParameter("action").equals("yahoo")) {
			destJsp = processYahooRequest(request, response);
		} else if (request.getParameter("action").equals("convert")) {
			destJsp = processConvertRequest(request, response);
		} else if (request.getParameter("action").equals("summary")) {
			destJsp = processSummaryRequest(request, response);
		}


		RequestDispatcher rd = request.getRequestDispatcher(destJsp);
		rd.forward(request, response);

	}

	private String processImportRequest(HttpServletRequest request,
			HttpServletResponse response) throws MalformedURLException {
		
		URL wsdlLocation;
		wsdlLocation = 
				new URL("http://localhost:8080/ImportDownloadService/Service1?wsdl");
		
		Service1 importService = 
				new Service1ImplService(wsdlLocation).getService1ImplPort();
		
		ImportMarketDataRequest wsRequest = 
				factoryImportDownload.createImportMarketDataRequest();
		ImportMarketDataResponse wsResponse =
				factoryImportDownload.createImportMarketDataResponse();
		
		wsRequest.setSec(request.getParameter("sec").trim());
		wsRequest.setStartDate(
				getDate(request.getParameter("startdate").trim()));
		wsRequest.setEndDate(
				getDate(request.getParameter("enddate").trim()));
		wsRequest.setDataSource(request.getParameter("url").trim());
		
		try {
			wsResponse = importService.importMarketData(wsRequest);
		} catch (ImportDownloadFaultMsg e) {
			request.setAttribute("faultmsg", e.getLocalizedMessage());
			request.setAttribute("errcode", e.getFaultInfo().getFaultType());
			request.setAttribute("errmsg", e.getMessage());

			return "/error.jsp";
		}
		

		request.setAttribute("sec", wsRequest.getSec());
		request.setAttribute("startdate", wsRequest.getStartDate());
		request.setAttribute("enddate", wsRequest.getEndDate());
		request.setAttribute("url", wsRequest.getDataSource());
		request.setAttribute("eventsetid", wsResponse.getEventSetId());
		
		
		return "/import.jsp";
		

	}


	private String processDownloadRequest(HttpServletRequest request,
			HttpServletResponse response) throws MalformedURLException {
		URL wsdlLocation;
		wsdlLocation = 
				new URL("http://localhost:8080/ImportDownloadService/Service1?wsdl");
		
		Service1 downloadService = 
				new Service1ImplService(wsdlLocation).getService1ImplPort();
		
		DownloadFileRequest wsRequest = 
				factoryImportDownload.createDownloadFileRequest();
		DownloadFileResponse wsResponse =
				factoryImportDownload.createDownloadFileResponse();
		
		wsRequest.setEventSetId(request.getParameter("eventsetid").trim());
		wsRequest.setFileType(request.getParameter("filetype").trim());
		System.out.println("=================" + request.getParameter("eventsetid").trim() + "=================");
		System.out.println("=================" + request.getParameter("filetype").trim() + "=================");
		try {
			wsResponse = downloadService.downloadFile(wsRequest);
			
		} catch (ImportDownloadFaultMsg e) {
			request.setAttribute("faultmsg", e.getLocalizedMessage());
			request.setAttribute("errcode", e.getFaultInfo().getFaultType());
			request.setAttribute("errmsg", e.getMessage());

			return "/error.jsp";
		}
		
		request.setAttribute("eventsetid", wsRequest.getEventSetId());
		request.setAttribute("format", wsRequest.getFileType());
		request.setAttribute("url", wsResponse.getDataURL());
		
		return "/download.jsp";
		
		

	}

	private String processYahooRequest(HttpServletRequest request,
			HttpServletResponse response) throws MalformedURLException {
		URL wsdlLocation;
		wsdlLocation = 
				new URL("http://localhost:8080/SOAPCurrencyConvertService/SOAPCurrencyConvertService?wsdl");
		SOAPCurrencyConvertService yahooService = 
				new SOAPCurrencyConvertServiceImplService(wsdlLocation).getSOAPCurrencyConvertServiceImplPort();

		ExchangeRateRequest wsRequest = 
				factoryCurrencyConvert.createExchangeRateRequest();
		ExchangeRateResponse wsResponse = 
				factoryCurrencyConvert.createExchangeRateResponse();

		wsRequest.setBaseCurrencyCode(request.getParameter("base").trim());
		wsRequest.setTargetCurrencyCode(request.getParameter("target").trim());

		try {
			wsResponse = yahooService.yahooExchangeRate(wsRequest);
		} catch (ExchangeRateFaultMsg e) {
			request.setAttribute("faultmsg", e.getLocalizedMessage());
			request.setAttribute("errcode", e.getFaultInfo().getFaultType());
			request.setAttribute("errmsg", e.getMessage());

			return "/error.jsp";
		}

		request.setAttribute("base", wsRequest.getBaseCurrencyCode());
		request.setAttribute("target", wsRequest.getTargetCurrencyCode());
		request.setAttribute("rate", wsResponse.getRate());
		request.setAttribute("asat", wsResponse.getAsAt());

		return "/yahoo.jsp";

	}

	private String processConvertRequest(HttpServletRequest request,
			HttpServletResponse response) throws MalformedURLException {
		URL wsdlLocation;
		wsdlLocation = 
				new URL("http://localhost:8080/MarketDataUtilService/MarketDataUtilService?wsdl");
		MarketDataUtilService convertService =
				new MarketDataUtilServiceImplService(wsdlLocation).getMarketDataUtilServiceImplPort();
		ConvertMarketDataRequest wsRequest = 
				factoryMarketDataUtil.createConvertMarketDataRequest();
		ConvertMarketDataResponse wsResponse =
				factoryMarketDataUtil.createConvertMarketDataResponse();

		wsRequest.setEventSetId(request.getParameter("eventsetid").trim());
		wsRequest.setTargetCurrency(request.getParameter("target").trim());

		try {
			wsResponse = convertService.convertMarketData(wsRequest);

		} catch (ConvertMarketDataFaultMsg e) {
			request.setAttribute("faultmsg", e.getLocalizedMessage());
			request.setAttribute("errcode", e.getFaultInfo().getFaultType());
			request.setAttribute("errmsg", e.getMessage());

			return "/error.jsp";
		}

		request.setAttribute("eventsetid", wsResponse.getEventSetId());
		request.setAttribute("baseid", wsRequest.getEventSetId());
		request.setAttribute("target", wsRequest.getTargetCurrency());

		return "/convert.jsp";

	}

	private String processSummaryRequest(HttpServletRequest request,
			HttpServletResponse response) throws MalformedURLException {
		URL wsdlLocation;
		wsdlLocation = 
				new URL("http://localhost:8080/MarketDataUtilService/MarketDataUtilService?wsdl");
		MarketDataUtilService summaryService =
				new MarketDataUtilServiceImplService(wsdlLocation).getMarketDataUtilServiceImplPort();

		SummaryMarketDataRequest wsRequest = 
				factoryMarketDataUtil.createSummaryMarketDataRequest();
		SummaryMarketDataResponse wsResponse =
				factoryMarketDataUtil.createSummaryMarketDataResponse();

		wsRequest.setEventSetId(request.getParameter("eventsetid").trim());

		try {
			wsResponse = summaryService.summaryMarketData(wsRequest);
		} catch (SummaryMarketDataFaultMsg e) {
			request.setAttribute("faultmsg", e.getLocalizedMessage());
			request.setAttribute("errcode", e.getFaultInfo().getFaultType());
			request.setAttribute("errmsg", e.getMessage());

			return "/error.jsp";
		}

		request.setAttribute("eventsetid", wsResponse.getEventSetId());
		request.setAttribute("sec", wsResponse.getSec());
		request.setAttribute("startdate", wsResponse.getStartDate());
		request.setAttribute("enddate", wsResponse.getEndDate());
		request.setAttribute("curcode", wsResponse.getCurrencyCode());
		request.setAttribute("lines", wsResponse.getNumberOfLines());

		return "/summary.jsp";


	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	
	private String getDate(String date) {
		
		String[] datesplits = date.split("/");
		if (datesplits.length == 3) {
			return datesplits[1] + "-" + datesplits[0] + "-" + datesplits[2];
		} else {
			return null;
		}
		

	}

}
