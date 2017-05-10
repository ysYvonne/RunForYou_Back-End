package db;

import java.sql.*;
public class DBManager {
	private Connection conn;
    private String url = "jdbc:mysql://112.74.124.48:3306/RunForYou"; // ָ���������ݿ��URL
    private String user = "user"; // ָ���������ݿ���û���
    private String password = "123456"; // ָ���������ݿ������

    private Statement sta;
    private ResultSet rs;
    
    // �����ݿ�����
    public void openConnect() {
        try {
            // �������ݿ�����
            Class.forName("com.mysql.jdbc.Driver");
            conn =DriverManager.getConnection(url, user, password);// �������ݿ�����
            if (conn != null) {
                System.out.println("���ݿ����ӳɹ�"); // ���ӳɹ�����ʾ��Ϣ
            }else
            	 System.out.println("���ݿ�����ʧ��"); 
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
            System.out.println("�ر����ݿ����ӳɹ�");
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

    // ����/ɾ��/�޸�
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
