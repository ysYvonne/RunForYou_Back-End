package db;

import java.sql.*;
public class DBManager {
	private Connection conn;
    private String url = "jdbc:mysql://112.74.124.48:3306/RunForYou"; // 指定连接数据库的URL
    private String user = "user"; // 指定连接数据库的用户名
    private String password = "123456"; // 指定连接数据库的密码

    private Statement sta;
    private ResultSet rs;
    
    // 打开数据库连接
    public void openConnect() {
        try {
            // 加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            conn =DriverManager.getConnection(url, user, password);// 创建数据库连接
            if (conn != null) {
                System.out.println("数据库连接成功"); // 连接成功的提示信息
            }else
            	 System.out.println("数据库连接失败"); 
        } catch (Exception e) {
            //System.out.println("ERROR: " + e.getMessage());
        	e.printStackTrace();
        }
    }
    
    public void closeConnect() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (sta != null) {
                sta.close();
            }
            if (conn != null) {
                conn.close();
            }
            System.out.println("关闭数据库连接成功");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        try {
            rs = sta.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    // 增添/删除/修改
    public int executeUpdate(String sql) {
        int ret = 0;
        try {
            ret = sta.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
