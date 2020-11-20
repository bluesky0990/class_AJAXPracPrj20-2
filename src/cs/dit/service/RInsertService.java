package cs.dit.service;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs.dit.dao.ReplyDAO;

public class RInsertService implements InterfaceService {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		int bcode = Integer.parseInt(request.getParameter("bcode"));
		String reply = URLDecoder.decode(request.getParameter("reply"), "utf-8");
		//String reply = request.getParameter("reply");
		System.out.println("insert service" + bcode + reply);
		
		ReplyDAO dao = new ReplyDAO();
		
		dao.register(bcode, reply);
	}
}
