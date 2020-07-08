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

@WebServlet("/ConfirmVote")
public class ConfirmVote extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private int vc=0;
	int c=0;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String STMT="select vote_count from votes where c_id=?";
		String INCREASE="update votes set vote_count=? where c_id=?";
		String idDec=request.getParameter("cid3");
		String id=AES256.encrypt_it(idDec);
		try {
			Connection con=DBConnector.getConnection();
			PreparedStatement pst=con.prepareStatement(STMT);
			PreparedStatement pst1=con.prepareStatement(INCREASE);
			pst.setString(1, id);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				vc=rs.getInt("vote_count");
				vc+=1;
				pst1.setInt(1, vc);
				pst1.setString(2, id);
				c=pst1.executeUpdate();
			}
			con.close();
			rs.close();
			RequestDispatcher rd=request.getRequestDispatcher("./thankyou.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			Logger.getLogger(ConfirmVote.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
