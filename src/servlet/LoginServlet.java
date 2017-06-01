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

import Bean.UserBean;
import Service.*;
import net.sf.json.JSONObject;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = -2491674161266859446L;
	private static final int SUCCESS = 1;//成功
	private static final int ERROR = 0;//密码错误
	private static final int CONFILICT = -1;//账号冲突或不存在
	
	private JSONObject jsonObject;
	private LogService logService;
	private UserBean userBean;
	private JSONObject jsonReply = new JSONObject();//封装服务器返回的JSON对象

	
    public LoginServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
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
		}else if(type.equals("phoneExist")){
			phoneExist();
		}else if(type.equals("register")){
			register();
		}
	
		System.out.println(jsonReply);
		pw.write(jsonReply.toString());
		pw.flush();
		pw.close();
	}	
	
	public void emailLogin(){
		String email = jsonObject.getString("email");
		String password = jsonObject.getString("password");
		
		if((userBean=logService.AccountLogin(email, password)) != null){
			jsonReply.put("code", SUCCESS);//登陆成功
			jsonReply.put("user", userBean);
		}else if(logService.EmailExist(email) == null){
			jsonReply.put("code",CONFILICT);//邮件名不存在
			//jsonReply.put("user", null);
		}else{
			jsonReply.put("code", ERROR);//密码错误
			//jsonReply.put("user", null);
		}
	}
		
	public void phoneLogin(){
		String phoneNum = jsonObject.getString("phoneNum");
		
		userBean=logService.PhoneExist(phoneNum);
		jsonReply.put("code", SUCCESS);//登陆成功
		jsonReply.put("user", userBean);
	}
		
	public void phoneExist(){
		String phoneNum = jsonObject.getString("phoneNum");
		
		if(logService.PhoneExist(phoneNum) == null){
			jsonReply.put("code",CONFILICT);//手机号不存在
			//jsonReply.put("user", null);
		}else{
			jsonReply.put("code", SUCCESS);//验证成功
			//jsonReply.put("user", null);
		}
	}
	
	public void register(){
		String email = jsonObject.getString("email");
        int user_id = Integer.parseInt(jsonObject.getString("user_id"));//用户id
        String name = jsonObject.getString("name");//真实姓名
        int sex = Integer.parseInt(jsonObject.getString("sex"));//姓名，记1为男，2为女，0为未设置
        int age = Integer.parseInt(jsonObject.getString("age"));//年龄，记-1为未设置
        String nickname = jsonObject.getString("nickname");//昵称
        String phoneNum = jsonObject.getString("phoneNum");//电话
        String school = jsonObject.getString("school");//学校
        String password = jsonObject.getString("password");//密码
        
    	if ((userBean=logService.EmailExist(email)) != null) { // 账号存在  
    		jsonReply.put("code", CONFILICT);//邮件已注册
            //jsonReply.put("user",userBean);
         }else { // 注册成功
        	userBean = new UserBean();
        	userBean.setEmail(email);
        	userBean.setUserId(user_id);
        	userBean.setName(name);
        	userBean.setNickname(nickname);
        	userBean.setPhoneNum(phoneNum);
        	userBean.setSchool(school);
        	userBean.setPassword(password);
        	
        	if(logService.Register(userBean) != null){
        		jsonReply.put("code", SUCCESS);
        		jsonReply.put("user",userBean);
        	}else{
        		jsonReply.put("code",ERROR);//注册过程发生错误
        	}
         } 
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
