package au.edu.unsw.assi.marketclient;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Composition3Command implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tmpdir = System.getProperty("catalina.home");
		request.setAttribute("empty", "0");
		ArrayList<String> setIdList = new ArrayList<String>();
		String path = tmpdir + "/webapps/ROOT";
		File f = new File(path);
		if (!f.exists()) {
			// System.out.println(path + " not exists");
			request.setAttribute("empty", "1");
			return "/Comp3.jsp";
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
			return "/Comp3.jsp";
		}
		request.setAttribute("setList", setIdList);
		
		return "/Comp3.jsp";
	}

}
