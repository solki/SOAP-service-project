/**
 * 
 */
package au.edu.unsw.assi.soapservice;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

/**
 * @author Solki
 *
 */
public class Service1Impl implements Service1 {

	private static ObjectFactory obj;
	private static ImportDownloadFault fault;
	private static int sysFaultInd;

	public Service1Impl() {
		obj = new ObjectFactory();
		fault = obj.createImportDownloadFault();
		sysFaultInd = 0;
	}

	/*
	 * (non-Javadoc) The importMarketData() operation reads a Market Data file
	 * that exists outside of the system, filters the content according to the
	 * given parameters, and produces another Market Data file to store it
	 * inside the system. The filter conditions are specified by the input
	 * parameters.
	 */
	@Override
	public ImportMarketDataResponse importMarketData(ImportMarketDataRequest parameters) throws ImportDownloadFaultMsg {
		String sec = parameters.getSec();
		String startDate = parameters.getStartDate();
		String endDate = parameters.getEndDate();
		String dataSource = parameters.getDataSource();
		URL url;
		if (!sec.matches("[A-Z]{3,4}") || sec == null) {
			fault.setFaultMessage("sec444");
			fault.setFaultType(ImportDownloadFaultType.INVALID_SEC_CODE);
			throw new ImportDownloadFaultMsg("secCode syntax error, 3 or 4 letters in UPPER CASE are required", fault);
		}
		try {
			url = new URL(dataSource);
		} catch (MalformedURLException e) {
			fault.setFaultMessage("url111");
			fault.setFaultType(ImportDownloadFaultType.INVALID_URL);
			e.printStackTrace();
			throw new ImportDownloadFaultMsg("wrong url", fault);
		}
		// now url is correct
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MMM-yyyy");
		sdf.setLenient(false);
		sdf2.setLenient(false);
		Date stDate;
		Date edDate;
		try {
			stDate = sdf.parse(startDate);
			edDate = sdf.parse(endDate);
			if (stDate.after(edDate)) {
				fault.setFaultMessage("date222");
				fault.setFaultType(ImportDownloadFaultType.INVALID_DATES);
				throw new ImportDownloadFaultMsg("The end date must be before the start date", fault);
			}
		} catch (ParseException e1) {
			fault.setFaultMessage("other333");
			fault.setFaultType(ImportDownloadFaultType.PROGRAM_ERROR);
			e1.printStackTrace();
			throw new ImportDownloadFaultMsg("wrong date format", fault);
		}
		// now dates are correct.

		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(new BufferedInputStream(url.openStream())));
			String line;
			ArrayList<String> tmpFile = new ArrayList<>();
			int num_of_correct_rec = 0;
			int count_line = 0;
			String title = null;
			sysFaultInd++;
			System.out.println(sysFaultInd);
			while ((line = reader.readLine()) != null) {
				// jump over the title line
				// System.out.println(line);
				count_line++;
				if (count_line == 1) {
					title = line;
					if (!title.equals("#RIC,Date[G],Time[G],GMT Offset,Type,Price,Volume,Bid Price,Bid Size,Ask Price,Ask Size")) {
						fault.setFaultMessage("url111");
						fault.setFaultType(ImportDownloadFaultType.INVALID_URL);
						throw new ImportDownloadFaultMsg("wrong url", fault);
					}
					continue;
				}
				String[] splited_line = line.split(",");
				if (splited_line[0].equals(sec)) {
					num_of_correct_rec++;
				} else {
					continue;
				}
				Date tuple_date;
				try {
					tuple_date = sdf2.parse(splited_line[1]);
					if (tuple_date.before(stDate) || tuple_date.after(edDate)) {
						continue;
					}
				} catch (ParseException e) {
					fault.setFaultMessage("other333");
					fault.setFaultType(ImportDownloadFaultType.PROGRAM_ERROR);
					e.printStackTrace();
					throw new ImportDownloadFaultMsg("the date of marketdata is missing or not valid", fault);
				}
				tmpFile.add(line);
			}
			if (num_of_correct_rec == 0) {
				fault.setFaultMessage("sec444");
				fault.setFaultType(ImportDownloadFaultType.INVALID_SEC_CODE);
				throw new ImportDownloadFaultMsg("secCode not found", fault);
			}
			// the content of generated eventID corresponding file is empty.
			if (tmpFile.isEmpty()) {
				fault.setFaultMessage("other333");
				fault.setFaultType(ImportDownloadFaultType.PROGRAM_ERROR);
				throw new ImportDownloadFaultMsg("no match data", fault);
			}
			// System.out.println(tmpFile.size());
			String tmpdir = System.getProperty("catalina.home");
			if (tmpdir == null) {
				fault.setFaultMessage("other333");
				fault.setFaultType(ImportDownloadFaultType.PROGRAM_ERROR);
				throw new ImportDownloadFaultMsg("Cannot get catalina.home, tomcat server may not work well", fault);
			}
			String uuid = UUID.randomUUID().toString();
			System.out.println(uuid);
			File outFile1 = new File(tmpdir + "/webapps/ROOT/ass1-" + uuid.substring(1, 8) + ".csv");
			BufferedWriter writer1 = new BufferedWriter(new FileWriter(outFile1));
			Iterator<String> it = tmpFile.iterator();
			writer1.write(title);
			writer1.newLine();
			// System.out.println(title);
			while (it.hasNext()) {
				String next = it.next();
				// System.out.println(next);
				writer1.write(next);
				writer1.newLine();
			}
			sysFaultInd++;
			System.out.println(sysFaultInd);
			reader.close();
			writer1.close();

			ImportMarketDataResponse res = new ImportMarketDataResponse();
			res.setEventSetId("ass1-" + uuid.substring(1, 8));
			sysFaultInd = 0;
			return res;
		} catch (IOException e) {
			if (sysFaultInd == 0) {
				fault.setFaultMessage("file not found");
				fault.setFaultType(ImportDownloadFaultType.INVALID_URL);
				e.printStackTrace();
				sysFaultInd = 0;
				throw new ImportDownloadFaultMsg("the url corresponding file is not found", fault);
			} else if (sysFaultInd == 1) {
				fault.setFaultMessage("other333");
				fault.setFaultType(ImportDownloadFaultType.PROGRAM_ERROR);
				e.printStackTrace();
				sysFaultInd = 0;
				throw new ImportDownloadFaultMsg("cannot write", fault);
			} else {
				fault.setFaultMessage("other333");
				fault.setFaultType(ImportDownloadFaultType.PROGRAM_ERROR);
				e.printStackTrace();
				sysFaultInd = 0;
				throw new ImportDownloadFaultMsg("program error", fault);
			}
		}
	}

	/*
	 * (non-Javadoc) This operation has a single output parameter
	 */
	@Override
	public DownloadFileResponse downloadFile(DownloadFileRequest parameters) throws ImportDownloadFaultMsg {
		String eventSetId = parameters.getEventSetId();
		DocType fileType = parameters.getFileType();
		System.out.println(fileType);
		if (fileType == null) {
			fault.setFaultMessage("type555");
			fault.setFaultType(ImportDownloadFaultType.INVALID_FILE_TYPE);
			throw new ImportDownloadFaultMsg("invalid file type", fault);
		} else {
			String tmpdir = System.getProperty("catalina.home");
			File file = new File(tmpdir + "/webapps/ROOT/" + eventSetId + ".csv");
			if (file.exists()) {
				DownloadFileResponse res = new DownloadFileResponse();
				if (fileType.equals(DocType.CSV)) {
					res.setDataURL("http://localhost:8080/" + eventSetId + "." + fileType.toString().toLowerCase());
				} else if (fileType.equals(DocType.XML)) {
					String outPath = tmpdir + "/webapps/ROOT/" + eventSetId + ".xml";
					try {
						File outFile = new File(outPath);
						BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
						BufferedWriter writer2 = new BufferedWriter(new FileWriter(outFile));
						writer2.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
						writer2.newLine();
						writer2.write("<MarketDatas>");
						writer2.newLine();
						reader.readLine();
						String line = "";
						while ((line = reader.readLine()) != null) {
							writer2.write("  <MarketData>");
							writer2.newLine();
							String new_line = "    <data>" + line + "</data>";
							writer2.write(new_line);
							writer2.newLine();
							writer2.write("  </MarketData>");
							writer2.newLine();
						}
						writer2.write("</MarketDatas>");
						reader.close();
						writer2.close();
						res.setDataURL(outPath);
					} catch (Exception e1) {
						fault.setFaultMessage("other333");
						fault.setFaultType(ImportDownloadFaultType.PROGRAM_ERROR);
						e1.printStackTrace();
						throw new ImportDownloadFaultMsg("program error", fault);
					}

				} else {
					String outPath = tmpdir + "/webapps/ROOT/" + eventSetId + ".html";
					try {
						File outFile = new File(outPath);
						BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
						BufferedWriter writer2 = new BufferedWriter(new FileWriter(outFile));
						writer2.write(
								"<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
						writer2.newLine();
						writer2.write("<html>");
						writer2.newLine();
						writer2.write("<head>");
						writer2.newLine();
						writer2.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
						writer2.newLine();
						writer2.write("<title>Market Data</title>");
						writer2.newLine();
						writer2.write("</head>");
						writer2.newLine();
						writer2.write("<body>");
						writer2.newLine();
						//reader.readLine();
						String line = "";
						while ((line = reader.readLine()) != null) {
							writer2.write("    <br>");
							String new_line = line;
							writer2.write(new_line);
							writer2.newLine();
						}
						writer2.write("</body>");
						writer2.newLine();
						writer2.write("</html>");
						reader.close();
						writer2.close();
						res.setDataURL(outPath);
					} catch (Exception e1) {
						fault.setFaultMessage("other333");
						fault.setFaultType(ImportDownloadFaultType.PROGRAM_ERROR);
						e1.printStackTrace();
						throw new ImportDownloadFaultMsg("program error", fault);
					}
				}
				return res;
			} else {
				fault.setFaultMessage("setID666");
				fault.setFaultType(ImportDownloadFaultType.INVALID_EVENT_SET_ID);
				throw new ImportDownloadFaultMsg("invalid enent set ID", fault);
			}
		}
	}
}
