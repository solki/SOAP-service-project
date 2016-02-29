package au.edu.unsw.soacourse.soapcurrencyconvert;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "au.edu.unsw.soacourse.soapcurrencyconvert.SOAPCurrencyConvertService")
public class SOAPCurrencyConvertServiceImpl implements
SOAPCurrencyConvertService {

	private ObjectFactory of;
	
	@Override
	@WebMethod
	public ExchangeRateResponse yahooExchangeRate(ExchangeRateRequest parameters)
			throws ExchangeRateFaultMsg {
		of = new ObjectFactory();

		String qurl = "http://finance.yahoo.com/d/quotes.csv?e=.csv&f=sl1d1t1&s=";
		String baseCode = parameters.getBaseCurrencyCode();
		String targetCode = parameters.getTargetCurrencyCode();


		if (!isCode(baseCode)) {
			String errtxt = "Base code format is invalid.";
			throwError(errtxt, ExchangeRateFaultType.INVALID_BASE_CODE);
			
		}

		if (!isCode(targetCode)) {
			String errtxt = "Target code format is invalid.";
			throwError(errtxt, ExchangeRateFaultType.INVALID_TARGET_CODE);

		}

		qurl = qurl + baseCode + targetCode + "=X";

		try {
			URL url = new URL(qurl);
			Scanner sc = new Scanner(
					new InputStreamReader(new BufferedInputStream(url.openStream())));
			
			if (!sc.hasNextLine()) {
				String errtxt = "No result from yahoo service.";
				throwError(errtxt, ExchangeRateFaultType.NO_VALID_RESULT);
			} 
				
			sc.useDelimiter(Pattern.compile(",|(\\n)"));
			sc.next();
			ExchangeRateResponse err = of.createExchangeRateResponse();
			String rate = sc.next();
			String asat = sc.next();
			if (rate == null || rate.equals("N/A") || asat == null || asat.equals("N/A")) {
				String errtxt = "No result from yahoo service.";
				throwError(errtxt, ExchangeRateFaultType.NO_VALID_RESULT);
			}
		
			err.setRate(rate);
			

			SimpleDateFormat sdf = new SimpleDateFormat("\"MM/dd/yyyy\"");
			Date date = sdf.parse(asat);
			sdf = new SimpleDateFormat("dd-MM-yyyy");
			err.setAsAt(sdf.format(date));
	
			return err;
			
			

		} catch (IOException e) {
			String errtxt = "Connection error when connecting to URL: ";
			errtxt += qurl;
			throwError(errtxt, ExchangeRateFaultType.CONNECTION_IO_ERROR);
		} catch (ParseException e) {
			String errtxt = "Error when parsing date.";
			throwError(errtxt, ExchangeRateFaultType.PROGRAM_ERROR);
		}

		return null;
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
	
	public void throwError(String errtxt, ExchangeRateFaultType errcode) throws ExchangeRateFaultMsg {
		ServiceFaultType sft = new ServiceFaultType();
		sft.setFaultType(errcode);
		sft.setFaultMessage(errtxt);
		throw new ExchangeRateFaultMsg(errtxt, sft);
	}

	

}
