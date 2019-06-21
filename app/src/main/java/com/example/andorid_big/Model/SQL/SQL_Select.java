package com.example.andorid_big.Model.SQL;

import com.example.andorid_big.Model.Sign_List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQL_Select {
    public static List<Sign_List> Select_All(List<Sign_List> namelist) {
        Connection conn = SQL_Connection.Connection();
        try {
            Statement stmt = conn.createStatement();
            conn.setAutoCommit(false);
            String sql_show = "SELECT * FROM test2 ";
            ResultSet rs = stmt.executeQuery(sql_show);
            conn.commit();
            conn.setAutoCommit(true);
//            List<String> namelist=new ArrayList<String>();
            while (rs.next()){
                Sign_List sign_list = new Sign_List(rs.getString(1),rs.getString(2));
                namelist.add(sign_list);
//                dateList.add(rs.getString(2));
            }
            return namelist;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return namelist;
    }
    public static Boolean Select_Match(String key){
        Connection conn=SQL_Connection.Connection();
        boolean Mark = false;
        try{
            String sql_select="SELECT * FROM test1 WHERE num = ?";
            PreparedStatement stmt=(PreparedStatement)conn.prepareStatement(sql_select);
//            stmt.setString(1,key);
//            ResultSet rs=stmt.executeQuery();
//            stmt.close();
//            if (rs == null || !rs.next()) {
//                Mark =  false;
//            }
//            else {
//                Mark = true;
//            }
//            if (rs == null){
//                System.err.println("11111111111111111111111111111111111");
//            }
//            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Mark;
    }
    public static ResultSet Select_Sign(String key){
        Connection conn=SQL_Connection.Connection();
        try{
            String sql_select="SELECT * FROM test2 WHERE name = ?";
            PreparedStatement stmt=(PreparedStatement)conn.prepareStatement(sql_select);
            stmt.setString(1,key);
            ResultSet rs=stmt.executeQuery();
            return  rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
