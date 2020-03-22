package com.chukun.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 池化的数据库连接
 */
public class PooledConnection {

    private Connection connection;
    private boolean isBusy = false;

    public PooledConnection(Connection connection,boolean isBusy){
        this.connection = connection;
        this.isBusy = isBusy;
    }
    public  void close(){
        this.isBusy = false;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public ResultSet query(String sql){
        PreparedStatement pstm;
        ResultSet resultSet = null;
        try{
            pstm = connection.prepareStatement(sql);
            resultSet = pstm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
