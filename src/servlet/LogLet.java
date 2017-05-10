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
        String username = request.getParameter("username"); // 获取客户端传过来的参数
        String password = request.getParameter("password");

        if (username == null || username.equals("") || password == null || password.equals("")) {
            System.out.println("用户名或密码为空");
            return;
        }

        // 请求数据库
        DBUtils dbUtils = new DBUtils();
        System.out.println(username+password); // 连接成功的提示信息
        dbUtils.openConnect(); // 打开数据库连接
        System.out.println("数据库连接啦"); // 连接成功的提示信息
        BaseBean data = new BaseBean(); // 基类对象，回传给客户端的json对象
        UserBean userBean = new UserBean();   //user的对象
        if (dbUtils.isExistInDB(username, password)) { // 判断账号是否存在
            data.setCode(-1);
            data.setData(userBean);
            data.setMsg("该账号已存在");
        } else if (!dbUtils.insertDataToDB(username, password)) { // 注册成功
            data.setCode(0);
            data.setMsg("注册成功！！");
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
            
        } else { // 注册不成功，这里错误没有细分，都归为数据库错误
            data.setCode(500);
            data.setData(userBean);
            data.setMsg("数据库错误");
        }
        Gson gson = new Gson();
        String json = gson.toJson(data);  //将对象转化成json字符串
        try {
            response.getWriter().println(json); // 将json数据传给客户端
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.getWriter().close(); // 关闭这个流，不然会发生错误的
        }
        dbUtils.closeConnect(); // 关闭数据库连接
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	doPost(request,response);

    }

}