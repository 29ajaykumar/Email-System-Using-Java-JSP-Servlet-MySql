/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaapp.emailcastingproject;

import com.DB.GetCon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class RegisterUser {
static int status=0;
public static int register(String uname,String email,String pass){
	//public static int register(String email,String password,String gender,String country,String name){

	Connection con=GetCon.getCon();
	PreparedStatement ps;
        String Query_String = "INSERT INTO MAILCASTINGUSER(uname,email,pass) VALUES ('"+uname+"','"+email+"','"+pass+"')";

	try {
		ps = con.prepareStatement("Insert into MAILCASTINGUSER values(?,?,?)");
               //ps = con.prepareStatement(Query_String);
		ps.setString(1,uname);
		ps.setString(2,email);
		ps.setString(3,pass);
		status=ps.executeUpdate(Query_String);
		
	} catch (SQLException e) {
            //System.out.println("com.javaapp.emailcastingproject.RegisterUser.register()\n User Already Registered");
            System.out.println(e);
        }
	return status;
	
}
}

