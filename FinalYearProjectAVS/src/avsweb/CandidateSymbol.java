package avsweb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CandidateSymbol")
public class CandidateSymbol extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream sos=null;
		String id=request.getParameter("cid");
		ImageDAO imageDAO=new ImageDAO();
		byte[] sym = imageDAO.getImage(id);
		sos=response.getOutputStream();
		sos.write(sym);
		sos.close();
	}

}
