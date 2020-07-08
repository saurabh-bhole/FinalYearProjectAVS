package avsweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/VoterValidation")
public class VoterValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String sql1="select * from voter_voted where voter_id=?";
	String sql2="select * from voter_address where voter_id=? and v_mobile_no=?";
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		String i=request.getParameter("voter_id");
		String j=request.getParameter("mobile_no");
		try {
			Connection con=DBConnector.getConnection();
			PreparedStatement pst=con.prepareStatement(sql1);
			String p=AES256.encrypt_it(i);
			String q=AES256.encrypt_it(j);
			pst.setString(1, p);
			pst.executeQuery();
			ResultSet rs1=pst.executeQuery();
			if(rs1.next()) {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Voter already Voted!!!');");
				out.println("location='votervalidation.jsp';");
				out.println("</script>");
			}
			else {
				PreparedStatement pst1=con.prepareStatement(sql2);
				pst1.setString(1, p);
				pst1.setString(2, q);
				pst1.executeQuery();
				ResultSet rs2=pst1.executeQuery();
				if(rs2.next()) {
					String id=rs2.getString("voter_id") ;
					String name=rs2.getString("v_name");
					String cons=rs2.getString("v_constituency");
					String ward=rs2.getString("v_ward") ;
					String village=rs2.getString("v_village");
					String city=rs2.getString("v_city");
					String taluka=rs2.getString("v_taluka");
					String district=rs2.getString("v_district");
					String state=rs2.getString("v_state");
					String pin=rs2.getString("v_pincode");
					String mobile=rs2.getString("v_mobile_no");
					String aadhar=rs2.getString("v_aadhar");
					
					String i1=AES256.decrypt_it(id);
					String nm=AES256.decrypt_it(name);
					String co=AES256.decrypt_it(cons);
					String w=AES256.decrypt_it(ward);
					String v=AES256.decrypt_it(village);
					String ci=AES256.decrypt_it(city);
					String ta=AES256.decrypt_it(taluka);
					String dis=AES256.decrypt_it(district);
					String stt=AES256.decrypt_it(state);
					String pn=AES256.decrypt_it(pin);
					String mo=AES256.decrypt_it(mobile);
					String aa=AES256.decrypt_it(aadhar);
					
						HttpSession session=request.getSession();
						session.setAttribute("ID",i1 );
						session.setAttribute("NAME", nm );
						session.setAttribute("CONSTITUENCY", co );
						session.setAttribute("WARD", w);
						session.setAttribute("VILLAGE", v );
						session.setAttribute("CITY", ci );
						session.setAttribute("TALUKA",  ta);
						session.setAttribute("DISTRICT", dis  );
						session.setAttribute("STATE", stt );
						session.setAttribute("PINCODE", pn );
						session.setAttribute("MOBILE", mo );
						session.setAttribute("AADHAR", aa );
						response.sendRedirect("voterInfo.jsp");
					}
					else {
						out.println("<script type=\"text/javascript\">");
						out.println("alert('This Mobile Number is NOT Linked with This Voter ID!!!');");
						out.println("location='votervalidation.jsp';");
						out.println("</script>");
					}
				}
			//}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	


}
