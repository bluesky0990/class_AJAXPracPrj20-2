package cs.dit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import cs.dit.dao.ReplyDAO;
import cs.dit.service.InterfaceService;
import cs.dit.service.RInsertService;
import cs.dit.service.RViewService;


@WebServlet("*.rp")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String viewPage = null;
		InterfaceService command = null;
		
		String uri = request.getRequestURI();
		String com = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf(".rp"));
		
		int bcode = Integer.parseInt(request.getParameter("bcode"));
		String reply = request.getParameter("reply");
		
		if(com !=null && com.trim().equals("replyLoad")) {
			command = new RViewService();
			command.execute(request, response);
			viewPage = "output.jsp";
		}
		
		if(com !=null && com.trim().equals("replyInsert")) {
			command = new RInsertService();
			command.execute(request, response);
			viewPage = "output.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

}
