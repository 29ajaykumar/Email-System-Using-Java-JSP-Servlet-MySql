/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaapp.emailcastingproject;

import com.DB.GetCon;
import static com.javaapp.emailcastingproject.RegisterUser.status;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ajay
 */
public class alreadyRegUser {

    public static boolean alreadyRegister(String uname, String email) {
        //public static int register(String email,String password,String gender,String country,String name){
        boolean status_alreadyRegUser = true;
        Connection con = GetCon.getCon();
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("Select * from MAILCASTINGUSER where uname = ? and email =?");
            ps.setString(1, uname);
            ps.setString(2, email);
            ResultSet rs = ps.executeQuery();

            status_alreadyRegUser = rs.next();

        } catch (SQLException e) {
            System.out.println("com.javaapp.emailcastingproject.alreadyRegUser.alreadyRegister()");
        }
        return status_alreadyRegUser;

    }
}
