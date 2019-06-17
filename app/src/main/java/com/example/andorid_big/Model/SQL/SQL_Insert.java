package com.example.andorid_big.Model.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public  class SQL_Insert {
    public static int InsertSql1(String name, String account) {
        Connection conn = SQL_Connection.Connection();
        try {
            String sql = "INSERT INTO test1 (name,num) Values(?,?)";
            PreparedStatement st = (PreparedStatement) conn.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, account);
            st.executeUpdate();
            st.close();
            return 200;
        } catch (SQLException e) {
            return 201;
        }
    }

    public static int InsertSql2(String name) {
        Connection conn = SQL_Connection.Connection();
        try {
            String sql = "INSERT INTO test2 (name,time) Values(?,NOW())";
            PreparedStatement st = (PreparedStatement) conn.prepareStatement(sql);
            st.setString(1, name);
            st.executeUpdate();
            st.close();
            return 202;
        } catch (SQLException e) {
            e.printStackTrace();
            return 203;
        }
    }
}
