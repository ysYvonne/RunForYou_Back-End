package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import Bean.UserBean;
import Service.LogService;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int SUCCESS = 1;//成功
	private static final int ERROR = 0;//密码错误
	private static final int CONFILICT = -1;//账号冲突或不存在
	
	private JSONObject jsonObject;
	private LogService logService;
	private UserBean userBean;
	private JSONObject jsonReply = new JSONObject();//封装服务器返回的JSON对象   
	
    public OrderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/json");
		StringBuffer sb = new StringBuffer("");
		String result = "";
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "utf-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			br.close();
			result = sb.toString();
			//打印android端上传的JSON数据
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonObject = JSONObject.fromObject(result);
		String type = jsonObject.getString("type");
		logService = new LogService(); //新建Service实例
		PrintWriter pw = response.getWriter();
		
		if(type.equals("emailLogin")){//邮件登录
			emailLogin();
		}else if(type.equals("phoneLogin")){
			phoneLogin();
		}else if(type.equals("register")){
			register();
		}
	
		System.out.println(jsonReply);
		pw.write(jsonReply.toString());
		pw.flush();
		pw.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
