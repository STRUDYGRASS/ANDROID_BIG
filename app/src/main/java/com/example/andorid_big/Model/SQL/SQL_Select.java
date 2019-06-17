package com.example.andorid_big.Model.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQL_Select {
    public static ResultSet Select() {
        Connection conn = SQL_Connection.Connection();
        try {
            String sql_select = "SELECT * FROM test2";
            PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql_select);
            ResultSet rs = stmt.executeQuery(sql_select);
            stmt.close();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static boolean Select(String key){
        Connection conn=SQL_Connection.Connection();
        try{
            String sql="SELECT * FROM test1 WHERE num = ?";
            PreparedStatement stmt=(PreparedStatement)conn.prepareStatement(sql);
            stmt.setString(1,key);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean Select_Sign(String key){
        Connection conn=SQL_Connection.Connection();
        try{
            String sql="SELECT * FROM test2 WHERE name = ?";
            PreparedStatement stmt=(PreparedStatement)conn.prepareStatement(sql);
            stmt.setString(1,key);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
