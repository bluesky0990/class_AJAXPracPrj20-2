package cs.dit.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs.dit.dao.BoardDAO;
import cs.dit.dto.BoardDTO;


public class BViewService implements InterfaceService{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int bcode = Integer.parseInt(request.getParameter("bcode"));
		
		BoardDAO dao = new BoardDAO();
		
		BoardDTO dto = dao.view(bcode);
		
		request.setAttribute("dto", dto);
	}
}
