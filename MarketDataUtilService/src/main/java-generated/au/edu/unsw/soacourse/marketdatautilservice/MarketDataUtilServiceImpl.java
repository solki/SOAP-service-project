package au.edu.unsw.soacourse.marketdatautilservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.jws.WebService;

import au.edu.unsw.soacourse.soapcurrencyconvert.ExchangeRateFaultMsg;
import au.edu.unsw.soacourse.soapcurrencyconvert.ExchangeRateRequest;
import au.edu.unsw.soacourse.soapcurrencyconvert.ExchangeRateResponse;
import au.edu.unsw.soacourse.soapcurrencyconvert.SOAPCurrencyConvertService;
import au.edu.unsw.soacourse.soapcurrencyconvert.SOAPCurrencyConvertServiceImplService;


@WebService(endpointInterface = "au.edu.unsw.soacourse.marketdatautilservice.MarketDataUtilService")
public class MarketDataUtilServiceImpl implements MarketDataUtilService {

	private ObjectFactory localFactory;
	private au.edu.unsw.soacourse.soapcurrencyconvert.ObjectFactory remoteFactory;

	private final String FILE_TPYE = ".csv";

	public MarketDataUtilServiceImpl() throws ConvertMarketDataFaultMsg {
		super();

		localFactory = new ObjectFactory();
		remoteFactory = new au.edu.unsw.soacourse.soapcurrencyconvert.ObjectFactory();



	}

	@Override
	public ConvertMarketDataResponse convertMarketData(
			ConvertMarketDataRequest parameters)
					throws ConvertMarketDataFaultMsg {

		String id = parameters.getEventSetId();
		String cur = parameters.getTargetCurrency();

		// check input currency code
		if(!isCode(cur) || cur.equals("AUD")) {
			String errtxt = "Invalid Target Currency Code!";
			throwConvertError(errtxt, 
					MarketDataUtilServiceFaultType.INVALID_TARGET_CODE);
		}

		// prepare request and response for calling currency convert service
		ExchangeRateRequest serviceRequest = remoteFactory.createExchangeRateRequest();
		serviceRequest.setBaseCurrencyCode("AUD");
		serviceRequest.setTargetCurrencyCode(cur);

		ExchangeRateResponse currencyResponse = remoteFactory.createExchangeRateResponse();
		
		// call currency service
		try {			
			URL wsdlLocation;
			wsdlLocation = 
					new URL("http://localhost:8080/SOAPCurrencyConvertService/SOAPCurrencyConvertService?wsdl");
			SOAPCurrencyConvertService convertService = 
					new SOAPCurrencyConvertServiceImplService(wsdlLocation).getSOAPCurrencyConvertServiceImplPort();
			
			currencyResponse = convertService.yahooExchangeRate(serviceRequest);

		} catch (ExchangeRateFaultMsg e1) {
			String errtxt = "Currency information is not available.";
			throwConvertError(errtxt, 
					MarketDataUtilServiceFaultType.PROGRAM_ERROR);
		} catch (MalformedURLException e) {
			String errtxt = "Error when calling currency convert service.";
			throwConvertError(errtxt, 
					MarketDataUtilServiceFaultType.PROGRAM_ERROR);
		}

		// extract rate from response
		float rate = Float.valueOf(currencyResponse.getRate());
		
		// open source file prepare to convert
		File ff = new File(System.getProperty("catalina.home") + "/webapps/ROOT/" + id + FILE_TPYE);
		String newid = null;
		try {
			Scanner sc = new Scanner(ff);


			if (!sc.hasNextLine()) {
				// empty file
				String errtxt = "Unexpected empty file.";
				throwConvertError(errtxt, 
						MarketDataUtilServiceFaultType.PROGRAM_ERROR);
			}

			
			//generate new id
			newid = "ass1-" + UUID.randomUUID().toString().substring(1, 8);
			
			PrintWriter rr = null;
			sc.useDelimiter(Pattern.compile(",|(\\n)|(\\r)"));
			Boolean isFirstLine = true;
			// skip header and keep the headline
			String headline = sc.nextLine();
			while (sc.hasNext()) {
				String line = "";

				line = line + sc.next() + ",";	//#RIC,
				line = line + sc.next() + ",";	//Date[G],
				line = line + sc.next() + ",";	//Time[G],
				line = line + sc.next() + ",";	//GMT Offset,
				line = line + sc.next() + ",";	//Type,
				line = processNextFloat(line, sc.next(), cur, rate);	//Price,
				line = line + sc.next() + ",";	//Volume,
				line = processNextFloat(line, sc.next(), cur, rate);	//Bid Price,
				line = line + sc.next() + ",";	//Bid Size,
				line = processNextFloat(line, sc.next(), cur, rate);	//Ask Price,
				line = line + sc.next();	//Ask Size

				if (isFirstLine) {
					// create file			
					rr = new PrintWriter(
							System.getProperty("catalina.home") + "/webapps/ROOT/" + newid + FILE_TPYE, "UTF-8");

					rr.println(headline);
					isFirstLine = false;
				}
				
				rr.println(line);

			}
			rr.close();
			
			sc.close();

		} catch (FileNotFoundException e) {
			String errtxt = "Invalid EventSet ID.";
			throwConvertError(errtxt, 
					MarketDataUtilServiceFaultType.INVALID_EVENT_SET_ID);

		} catch (UnsupportedEncodingException e) {
			String errtxt = "Error when creating new converted file.";
			throwConvertError(errtxt, 
					MarketDataUtilServiceFaultType.PROGRAM_ERROR);

		} catch (Exception e) {
			// only go here when called processNextFloat
			String errtxt = "The target file has already been converted.";
			throwConvertError(errtxt, 
					MarketDataUtilServiceFaultType.ALREADY_BEING_CONVERED);
		}



		ConvertMarketDataResponse resultID = localFactory.createConvertMarketDataResponse();
		resultID.setEventSetId(newid);


		return resultID;

	}




	@Override
	public SummaryMarketDataResponse summaryMarketData(
			SummaryMarketDataRequest parameters)
					throws SummaryMarketDataFaultMsg {

		String id = parameters.getEventSetId();
		File ff = new File(System.getProperty("catalina.home") + "/webapps/ROOT/" + id + FILE_TPYE);

		SummaryMarketDataResponse resultSummary = localFactory.createSummaryMarketDataResponse();

		try {
			Scanner sc = new Scanner(ff);

			int numLines = 0;
			String firstLine = "";
			String lastLine = "";

			while(sc.hasNextLine()) {
				numLines++;
				if (numLines == 2) {
					firstLine = sc.nextLine();
				} else {
					lastLine = sc.nextLine();
				}

			}

			if (numLines <= 1) {
				resultSummary.setEventSetId(id);
				resultSummary.setSec("");
				resultSummary.setStartDate("");
				resultSummary.setEndDate("");
				resultSummary.setCurrencyCode("");
				resultSummary.setNumberOfLines("0");

				sc.close();
				return resultSummary;
			}

			String[] firstLineParts = firstLine.split(",");
			String[] lastLineParts = lastLine.split(",");


			resultSummary.setEventSetId(id);	// eventSetID
			resultSummary.setSec(firstLineParts[0]);	// Security code

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			Date date = sdf.parse(firstLineParts[1]);
			sdf = new SimpleDateFormat("dd-MM-yyyy");

			resultSummary.setStartDate(sdf.format(date));	// Start date

			sdf = new SimpleDateFormat("dd-MMM-yyyy");
			date = sdf.parse(lastLineParts[1]);
			sdf = new SimpleDateFormat("dd-MM-yyyy");

			resultSummary.setEndDate(sdf.format(date)); 	// End date

			resultSummary.setCurrencyCode("AUD");

			if (!isCurrencyAUD(firstLineParts[5])) {		// Currency code
				resultSummary.setCurrencyCode(extractCurrencyCode(firstLineParts[5]));
			} 

			if (firstLineParts.length >= 8 && !isCurrencyAUD(firstLineParts[7])) {		// Currency code
				resultSummary.setCurrencyCode(extractCurrencyCode(firstLineParts[7]));
			} 

			if (firstLineParts.length >= 10 && !isCurrencyAUD(firstLineParts[9])) {		// Currency code
				resultSummary.setCurrencyCode(extractCurrencyCode(firstLineParts[9]));
			} 

			resultSummary.setNumberOfLines(String.valueOf(numLines-1));	// Number of Lines


			sc.close();

		} catch (FileNotFoundException e) {
			String errtxt = "Invalid EventSet ID.";
			throwSummaryError(errtxt, 
					MarketDataUtilServiceFaultType.INVALID_EVENT_SET_ID);
		} catch (ParseException e) {
			String errtxt = "Error when converting date to required format.";
			throwSummaryError(errtxt, 
					MarketDataUtilServiceFaultType.PROGRAM_ERROR);;
		}



		return resultSummary;

	}

	public Boolean isCurrencyAUD(String str) {
		if (str.equals("") || str.equals(null))
			return true;

		if (!Character.isUpperCase(str.charAt(0)))
			return true;

		if (extractCurrencyCode(str).equals("AUD"))
			return true;

		return false;


	}

	public String extractCurrencyCode(String str) {
		return str.substring(0, 3);
	}


	public String processNextFloat(String line, String next, String currency, float rate) throws Exception {
		if (next.equals("") || next.equals(null)) {
			return line += ",";
		} else if (next.charAt(0) >= 'Z' && next.charAt(0) <= 'A') {
			// already converted
			throw new Exception();

		} else {
			String value = String.format("%.2f", Float.valueOf(next) * rate);
			return line = line + currency + value + ",";
		}

	}

	public boolean isCode(String code) {
		boolean codeValid = true;

		if (code.length() != 3) 
			codeValid = false;

		for (int i = 0; i < code.length(); i++) {
			if (code.charAt(i) > 'Z' || code.charAt(i) < 'A')
				codeValid = false;
		}

		return codeValid;

	}

	public void throwConvertError(String errtxt, MarketDataUtilServiceFaultType errcode) 
			throws ConvertMarketDataFaultMsg {
		ServiceFaultType sft = new ServiceFaultType();
		sft.setFaultType(errcode);
		sft.setFaultMessage(errtxt);
		throw new ConvertMarketDataFaultMsg(errtxt, sft);
	}

	public void throwSummaryError(String errtxt, MarketDataUtilServiceFaultType errcode) 
			throws SummaryMarketDataFaultMsg {
		ServiceFaultType sft = new ServiceFaultType();
		sft.setFaultType(errcode);
		sft.setFaultMessage(errtxt);
		throw new SummaryMarketDataFaultMsg(errtxt, sft);
	}

}
