package com.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import java.io.InputStream;
import java.sql.Connection;

import java.sql.SQLException;
import java.util.Properties;


public class DruidUtils {
    private static final Properties PROPERTIES = new Properties();
    private static final ThreadLocal<Connection> THREAD_LOCAL = new ThreadLocal<>();
    private static DruidDataSource druidDataSource;
    static {
        InputStream is = DruidUtils.class.getResourceAsStream("/db.properties");
        try {
            PROPERTIES.load(is);
            druidDataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(PROPERTIES);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void begin() {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            close(connection);
            e.printStackTrace();
        }
    }
    public static void rollback() {
        Connection connection = getConnection();
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection);
        }
    }
    public static void commit() {
        Connection connection = getConnection();
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection);
        }
    }
    public static Connection getConnection(){
        Connection connection = THREAD_LOCAL.get();
        if (connection == null) {
            try {
                connection = druidDataSource.getConnection();
                THREAD_LOCAL.set(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    public static void close(Connection connection){
        try {
            connection.close();
            THREAD_LOCAL.remove();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
