package avsweb;

import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.spec.SecretKeySpec;

public class ImageDAO {
	Connection con=null;
	PreparedStatement ps= null;
	ResultSet rs=null;

	private final String SQL="select c_id,c_name,c_party from candidates where c_constituency=?"; 
	private final String SQL_IMAGE="select c_symbol from candidates where c_id=?";
	private final String KEYS="select * from keys_db where c_id=?";
	public List<ImageDTO> getData(String s){
		ImageDTO imageDTO=null;
		List<ImageDTO> imageList=new ArrayList<ImageDTO>();
		try {
			con=DBConnector.getConnection();
			ps=con.prepareStatement(SQL);
			ps.setString(1, s);
			rs=ps.executeQuery();
			while(rs.next()) {
				imageDTO = new ImageDTO();
				String idEnc=rs.getString("c_id");
				String id1=AES256.decrypt_it(rs.getString("c_id"));
				String name1=AES256.decrypt_it(rs.getString("c_name"));
				String party1=AES256.decrypt_it(rs.getString("c_party"));
				imageDTO.setIdEnc(idEnc);
				imageDTO.setId(id1);
				imageDTO.setName(name1);
				imageDTO.setParty(party1);
				imageList.add(imageDTO);
			}
		}catch(SQLException e) {
			Logger.getLogger(ImageDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return imageList;
	}
	
	
	
	
	public byte[] getImage(String id) {
		//System.out.println(id);
		byte[] symbol=null;
		Key key=null;
		try {
			con=DBConnector.getConnection();
			PreparedStatement ps2=con.prepareStatement(SQL_IMAGE);
			ps2.setString(1, id);
			ResultSet rs2=ps2.executeQuery();
			if(rs2.next()) {
				//key=this.getKey(id);
				con=DBConnector.getConnection();
				PreparedStatement ps3=con.prepareStatement(KEYS);
				ps3.setString(1, id);
				ResultSet rs3=ps3.executeQuery();
				if(rs3.next()) {
					String key1=AES256.decrypt_it(rs3.getString("c_key"));
					//System.out.println(key1);
					byte[] keyString=Base64.getDecoder().decode(key1);
					key=new SecretKeySpec(keyString,0,keyString.length,"AES");
				}
				symbol=ImageDec.decryptPdfFile(key, rs2.getBytes("c_symbol"));
			}
			
		} catch (SQLException e) {
			Logger.getLogger(ImageDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		
		
		return symbol;
	}

}
