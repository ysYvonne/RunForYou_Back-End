package db;

import java.sql.*;
import com.mysql.jdbc.Driver;

public class DBUtils {
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
            System.out.println("conn="+conn); 
        } catch (Exception e) {
            //System.out.println("ERROR: " + e.getMessage());
        	e.printStackTrace();
        }
    }
    //��ò�ѯuser�������ݼ�
    public ResultSet getUser() {
        // ���� statement����
        try {
            sta = conn.createStatement();
            // ִ��SQL��ѯ���
            rs = sta.executeQuery("select * from user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    // �ж����ݿ����Ƿ����ĳ���û�����������,ע��͵�¼��ʱ���ж�
    public boolean isExistInDB(String username, String password) {
        boolean isFlag = false;
        // ���� statement����
        try {
            sta =  conn.createStatement();
            // ִ��SQL��ѯ���
            rs =  sta.executeQuery("select * from user");//��ý����
            if (rs != null) {
                while (rs.next()) {  //���������
                    if (rs.getString("username").equals(username)) {
                        if (rs.getString("password").equals(password)){
                            isFlag = true;
                            break;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            isFlag = false;
        }
        return isFlag;
    }

    //ע��  ���û�����������뵽���ݿ�(id���õ����������ģ���˲���Ҫ����)
    public boolean insertDataToDB(String username, String password){
        String sql = " insert into user ( username , password ) values ( " + "'" + username
                + "', " + "'" + password + "' )";
        try {
            sta =  conn.createStatement();
            // ִ��SQL��ѯ���
            return sta.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // �ر����ݿ�����
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
}