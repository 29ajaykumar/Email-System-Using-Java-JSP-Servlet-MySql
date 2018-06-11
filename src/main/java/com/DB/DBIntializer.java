package com.DB;


public interface DBIntializer {
//String DRIVER="oracle.jdbc.driver.OracleDriver";
//String CON_STRING="jdbc:oracle:thin:@localhost:1521:xe";
//String USERNAME="system";
//String PASSWORD="root";

    String DRIVER = "com.mysql.jdbc.Driver";
    String CON_STRING = "jdbc:mysql://localhost:3306/emailcasting";
    String USERNAME = "root";
    String PASSWORD = "root";
}
