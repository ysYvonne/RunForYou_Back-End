package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Bean.*;
import db.DBUtils;

public class LogLet extends HttpServlet {

    private static final long serialVersionUID = 369840050351775312L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username"); // ��ȡ�ͻ��˴������Ĳ���
        String password = request.getParameter("password");

        if (username == null || username.equals("") || password == null || password.equals("")) {
            System.out.println("�û���������Ϊ��");
            return;
        }

        // �������ݿ�
        DBUtils dbUtils = new DBUtils();
        System.out.println(username+password); // ���ӳɹ�����ʾ��Ϣ
        dbUtils.openConnect(); // �����ݿ�����
        System.out.println("���ݿ�������"); // ���ӳɹ�����ʾ��Ϣ
        BaseBean data = new BaseBean(); // ������󣬻ش����ͻ��˵�json����
        UserBean userBean = new UserBean();   //user�Ķ���
        if (dbUtils.isExistInDB(username, password)) { // �ж��˺��Ƿ����
            data.setCode(-1);
            data.setData(userBean);
            data.setMsg("���˺��Ѵ���");
        } else if (!dbUtils.insertDataToDB(username, password)) { // ע��ɹ�
            data.setCode(0);
            data.setMsg("ע��ɹ�����");
            ResultSet rs = dbUtils.getUser();
            int id = -1;
            if (rs != null) {
                try {
                    while (rs.next()) {
                        if (rs.getString("username").equals(username) && rs.getString("password").equals(password)) {
                           // id = rs.getInt("user_id");
                        	id = 1;
                        }
                    }
                    userBean.setId(id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            userBean.setUsername(username);
            userBean.setPassword(password);
            data.setData(userBean);
            
        } else { // ע�᲻�ɹ����������û��ϸ�֣�����Ϊ���ݿ����
            data.setCode(500);
            data.setData(userBean);
            data.setMsg("���ݿ����");
        }
        Gson gson = new Gson();
        String json = gson.toJson(data);  //������ת����json�ַ���
        try {
            response.getWriter().println(json); // ��json���ݴ����ͻ���
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.getWriter().close(); // �ر����������Ȼ�ᷢ�������
        }
        dbUtils.closeConnect(); // �ر����ݿ�����
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	doPost(request,response);

    }

}