package com.example.andorid_big.Model.SQL;

import com.example.andorid_big.Model.Sign_List;
import com.example.andorid_big.Model.login_model;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQL_Select {
    public static void Select_All() {
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
                login_model.namelist.add(sign_list);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void Select_Match(String key){
        Connection conn=SQL_Connection.Connection();
        try{
            String sql_select="SELECT * FROM test1 WHERE num = ?";
            PreparedStatement stmt=(PreparedStatement)conn.prepareStatement(sql_select);
            stmt.setString(1,key);
            ResultSet rs=stmt.executeQuery();
            if (rs.next()){
                Sign_List sign_list = new Sign_List(rs.getString(1),rs.getString(2));
                login_model.namelist.add(sign_list);
            }
            stmt.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void Select_Sign(String key){
        Connection conn=SQL_Connection.Connection();
        try{
            String sql_select="SELECT * FROM test2 WHERE name = ?";
            PreparedStatement stmt=(PreparedStatement)conn.prepareStatement(sql_select);
            stmt.setString(1,key);
            ResultSet rs=stmt.executeQuery();
            if (rs.next()){
                Sign_List sign_list = new Sign_List(rs.getString(1),rs.getString(2));
                login_model.namelist.add(sign_list);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
