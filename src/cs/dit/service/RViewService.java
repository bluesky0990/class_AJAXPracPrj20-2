package cs.dit.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import cs.dit.dao.ReplyDAO;
import cs.dit.dto.BoardDTO;

public class RViewService implements InterfaceService {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int bcode = Integer.parseInt(request.getParameter("bcode"));
		//String bcode = request.getParameter("bcode");
		ReplyDAO dao = new ReplyDAO();
		
		JSONArray data = dao.rlist(bcode);
		
		//3. dtos를 request scope에 저장하여 View가 화면출력할 수 있도록 준비한다.
		request.setAttribute("datas", data);
	}
}
