package com.example.andorid_big.Model.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public  class SQL_Insert {
    public static int InsertSql(String name, String account){
        Connection conn=SQL_Connection.Connection();
        try{
            String sql="INSERT INTO test1 (name,num) Values(?,?)";
            PreparedStatement st=(PreparedStatement)conn.prepareStatement(sql);
            st.setString(1,name);
            st.setString(2,account);
            st.executeUpdate();
            st.close();
            return 200;
        } catch (SQLException e) {
            return 201;
        }
    }
}
