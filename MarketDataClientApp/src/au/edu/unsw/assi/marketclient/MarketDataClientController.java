package au.edu.unsw.assi.marketclient;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/control")
public class MarketDataClientController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> commands;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MarketDataClientController() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {

		super.init(config);

		commands = new HashMap<String, Command>();
		commands.put("home", new HomeCommand());
		commands.put("composition1", new Composition1Command());// done
		commands.put("composition2",new Composition2Command());
		commands.put("composition3",new Composition3Command());
		commands.put("getresult1",new Result1Command());
		commands.put("getresult2",new Result2Command());
		commands.put("getresult3",new Result3Command());
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Command cmd = resolveCommand(request);
		String next;
		next = cmd.execute(request, response);

		if (next.indexOf('.') < 0) {
			cmd = (Command) commands.get(next);
			next = cmd.execute(request, response);
		}

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(next);
		dispatcher.forward(request, response);
	}

	/**
	 * @param request
	 * @return
	 */
	private Command resolveCommand(HttpServletRequest request) {
		Command cmd = commands.get(request.getParameter("action"));
		if (cmd == null) {
			cmd = commands.get("home");
		}
		return cmd;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}