package avsweb;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Candidates")
public class Candidates extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String const1=(String)session.getAttribute("CONSTITUENCY");
		String constituency=AES256.encrypt_it(const1);
		ImageDAO imageDAO=null;
		imageDAO=new ImageDAO();
		List<ImageDTO> list = imageDAO.getData(constituency);
		request.setAttribute("list", list );
		RequestDispatcher dis= request.getRequestDispatcher("./candidatelist.jsp");
		dis.forward(request, response);
	}
}
