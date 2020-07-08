package avsweb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/YouVotedTo")
public class YouVotedTo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id=request.getParameter("cid0");

		String SQL="select * from candidates where c_id=?";
		try {
			String id1;
			String name1;
			String party1;
	
			Connection con=DBConnector.getConnection();
			PreparedStatement pst=con.prepareStatement(SQL);
			pst.setString(1, id);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				id1=AES256.decrypt_it(rs.getString("c_id"));
				name1=AES256.decrypt_it(rs.getString("c_name"));
				party1=AES256.decrypt_it(rs.getString("c_party"));
				request.setAttribute("cid", id1);
				request.setAttribute("cname", name1);
				request.setAttribute("cparty", party1);
			}
			con.close();
			rs.close();
			RequestDispatcher rd=request.getRequestDispatcher("./youvoted.jsp");
			rd.forward(request, response);
			
		} catch (SQLException e) {
			Logger.getLogger(YouVotedTo.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
