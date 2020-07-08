package avsweb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/CandidateInfo")
public class CandidateInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String const1=(String)session.getAttribute("CONSTITUENCY");
		String constituency=AES256.encrypt_it(const1);
		String sql="select * from candidates where c_constituency=?";
		try {
			Connection con=DBConnector.getConnection();
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, constituency); 
			ResultSet rs123=pst.executeQuery();
			int i=1;
			while(rs123.next()) {
				//session.setAttribute("canId", rs123.getString("c_id"));
					String id=AES256.decrypt_it(rs123.getString("c_id"));
					String name=AES256.decrypt_it(rs123.getString("c_name"));
					String party=AES256.decrypt_it(rs123.getString("c_party"));
					request.setAttribute("cid", id);
					request.setAttribute("cname", name);
					request.setAttribute("cparty", party);
					i++;
					RequestDispatcher rd=request.getRequestDispatcher("./candidateInfo.jsp");
					rd.include(request, response);
			}
			int count=i-1;
			request.setAttribute("numberOfCandidates", count);
//			RequestDispatcher rd1=request.getRequestDispatcher("./candidateInfo.jsp");
//			rd1.forward(request,response);
			

		}catch(Exception e){
			Logger.getLogger(CandidateInfo.class.getName()).log(Level.SEVERE, null, e);
		}
	}	
}

/*
 	int j=1;
	while(j<=request.getAttribute("numberOfCandidates")){
		out.println("<tr>");
		out.println("<td >"+request.getAttribute("cid"+j)+"</td>");
		out.println("<td >"+request.getAttribute("cname"+j)+"</td>");
		out.println("<td >"+request.getAttribute("cparty"+j)+"</td>");
		out.println("<td ><img src=\"./Symbol\" width='100px' height='100px'/></td>");
		out.println("<td ><img src=\"./CandidateQR\"/></td>");
		out.println("</tr>");
	} */
