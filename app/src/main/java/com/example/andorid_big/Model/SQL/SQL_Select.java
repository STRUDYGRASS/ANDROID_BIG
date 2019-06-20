package com.example.andorid_big.Model.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQL_Select {
    public static ResultSet Select_All(ArrayList namelist,String[] nameArray) {
        Connection conn = SQL_Connection.Connection();
        try {
            String sql_select = "SELECT * FROM test2";
            Statement stmt = conn.createStatement();
            conn.setAutoCommit(false);
            String sql_show = "SELECT * FROM test2 ";
            ResultSet rs = stmt.executeQuery(sql_show);
            conn.commit();
            conn.setAutoCommit(true);
//            List<String> namelist=new ArrayList<String>();
            while (rs.next()){
                namelist.add(rs.getString(1)+" ------------ "+rs.getString(2));
//                dateList.add(rs.getString(2));
            }
            if(namelist!=null&&namelist.size()>0){
                nameArray=new String[namelist.size()];
                for(int i=0;i<namelist.size();i++){
                    nameArray[i]= (String) namelist.get(i);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static ResultSet Select_Match(String key){
        Connection conn=SQL_Connection.Connection();
        boolean Mark = false;
        try{
            String sql_select="SELECT * FROM test1 WHERE num = ?";
            PreparedStatement stmt=(PreparedStatement)conn.prepareStatement(sql_select);
            stmt.setString(1,key);
            ResultSet rs=stmt.executeQuery();
            stmt.close();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
