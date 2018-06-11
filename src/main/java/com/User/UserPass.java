/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.User;

import com.DB.GetCon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ajay
 */
public class UserPass {

    String userpass = null;

    public String Userpass(String email) {
        
        Connection con = GetCon.getCon();
        String sql = "select * from mailcastinguser where email = '" + email + "'";
	try {
		//PreparedStatement ps=con.prepareStatement("Select * from MAILCASTINGUSER where EMAILADD = ? and PASSWORD =?");
		PreparedStatement ps=con.prepareStatement("Select * from MAILCASTINGUSER where email = ?");
		ps.setString(1,email);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
                    //userpass = rs.getString("pass");
                    userpass = rs.getString(3);
                     //System.out.println(rs.getString(3));
                }		
	} catch (SQLException e) {
	}
        return userpass;
    }
}
