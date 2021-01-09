package com.blank.util;

import java.sql.*;

/**
 * JDBC工具类，简化JDBC编程。
 */
public class DBUtil {
    /**
     * 工具类中的构造对象都是私有的。
     * 因为工具类中的方法都是静态的,不需要new对象，直接采用类名调用。
     */
    public DBUtil(){
    }
    //静态代码块在类加载的时候执行，并且只执行一次
    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象
     * @return 连接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineshop?useUnicode=true&characterEncoding=utf-8",
                "root","root");
    }

    /**
     * 关闭资源
     * @param conn 连接对象
     * @param pstmt 数据库操作对象
     * @param rs 结果集
     */
    public static void close(Connection conn, Statement pstmt, ResultSet rs){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null){
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
