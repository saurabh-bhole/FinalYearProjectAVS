package avsweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String sql="select b_password from booth_login where b_username=?";
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		String i=request.getParameter("b_id");
		String j=request.getParameter("b_pass");
		try {
			Connection con=DBConnector.getConnection();
			PreparedStatement pst=con.prepareStatement(sql);
			String u=AES256.encrypt_it(i);
			pst.setString(1,u);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				//String user=AES256.decrypt_it(rs.getString("b_username"));
				String pass=AES256.decrypt_it(rs.getString("b_password"));
				boolean t=BCryptHash.passwordHash(j, pass);
				if(t) {
					response.sendRedirect("./votervalidation.jsp");
				}
				else {
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Wrong Password!');");
					out.println("location='./index.jsp'");
					out.println("</script>");
				}
			}
			else {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('This User Does Not Exists!');");
				out.println("location='./index.jsp'");
				out.println("</script>");
			}
		} catch (SQLException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

}
