package com.elitesoftwares.chatapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.elitesoftwares.chatapp.dto.UserDTO;
import com.elitesoftwares.chatapp.utils.Encryption;

//USER CRUD
public class UserDAO {
	//many arguments wrapped into class and sent
	public boolean isLogin(UserDTO userDTO) throws SQLException, ClassNotFoundException, Exception {
		Connection con=null;
		//Statement stmt=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		final String SQL="select userid from users where userid=? and password=?";
		try {
			con=CommonDAO.createConnection();
			pstmt=con.prepareStatement(SQL);
			pstmt.setString(1, userDTO.getUserid());
			String encryptedPwd=Encryption.passwordEncrypt(new String(userDTO.getPassword()));
			pstmt.setString(2,encryptedPwd);
			rs=pstmt.executeQuery();
			return rs.next();
//			if(rs.next()) {
//				return true;
//			}
//			else {
//				return false;
//			}
		}
		finally {
			if(rs!=null) {
				rs.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
			if(con!=null) {
				con.close();
			}
		}
	}
	public int add(UserDTO userDTO) throws ClassNotFoundException, SQLException,Exception {
		System.out.println("Rec "+userDTO.getUserid()+" "+userDTO.getPassword());
		Connection connection=null;
		Statement stmt=null;
		try {//guarded region where exception van occur.
		connection= CommonDAO.createConnection();
		//step1-create connection
		//step2-query
		stmt=connection.createStatement();
		int record=stmt.executeUpdate("insert into users (userid,password) values('"+userDTO.getUserid()+"','"+Encryption.passwordEncrypt(new String(userDTO.getPassword()))+"')");
		//Can perform insert,update,delete query through executeUpdate()
		return record;
		}
		finally {//will always execute
			if(stmt!=null) {
		stmt.close();
		}
			if(connection!=null) {
		connection.close();
		}
		}
		
	}
}
