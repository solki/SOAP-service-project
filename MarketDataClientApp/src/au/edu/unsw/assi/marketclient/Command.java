package au.edu.unsw.assi.marketclient;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Generic interface for a command
 * 
 */
public interface Command {
	
	String execute(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException;
	
}