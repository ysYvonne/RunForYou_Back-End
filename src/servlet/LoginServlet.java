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
	private static final int SUCCESS = 1;//�ɹ�
	private static final int ERROR = 0;//�������
	private static final int CONFILICT = -1;//�˺ų�ͻ�򲻴���
	
	private JSONObject jsonObject;
	private LogService logService;
	private UserBean userBean;
	private JSONObject jsonReply = new JSONObject();//��װ���������ص�JSON����

	
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
			//��ӡandroid���ϴ���JSON����
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonObject = JSONObject.fromObject(result);
		String type = jsonObject.getString("type");
		logService = new LogService(); //�½�Serviceʵ��
		PrintWriter pw = response.getWriter();
		
		if(type.equals("emailLogin")){//�ʼ���¼
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
			jsonReply.put("code", SUCCESS);//��½�ɹ�
			jsonReply.put("user", userBean);
		}else if(logService.EmailExist(email) == null){
			jsonReply.put("code",CONFILICT);//�ʼ���������
			//jsonReply.put("user", null);
		}else{
			jsonReply.put("code", ERROR);//�������
			//jsonReply.put("user", null);
		}
	}
		
	public void phoneLogin(){
		String phoneNum = jsonObject.getString("phoneNum");
		
		userBean=logService.PhoneExist(phoneNum);
		jsonReply.put("code", SUCCESS);//��½�ɹ�
		jsonReply.put("user", userBean);
	}
		
	public void phoneExist(){
		String phoneNum = jsonObject.getString("phoneNum");
		
		if(logService.PhoneExist(phoneNum) == null){
			jsonReply.put("code",CONFILICT);//�ֻ��Ų�����
			//jsonReply.put("user", null);
		}else{
			jsonReply.put("code", SUCCESS);//��֤�ɹ�
			//jsonReply.put("user", null);
		}
	}
	
	public void register(){
		String email = jsonObject.getString("email");
        int user_id = Integer.parseInt(jsonObject.getString("user_id"));//�û�id
        String name = jsonObject.getString("name");//��ʵ����
        int sex = Integer.parseInt(jsonObject.getString("sex"));//��������1Ϊ�У�2ΪŮ��0Ϊδ����
        int age = Integer.parseInt(jsonObject.getString("age"));//���䣬��-1Ϊδ����
        String nickname = jsonObject.getString("nickname");//�ǳ�
        String phoneNum = jsonObject.getString("phoneNum");//�绰
        String school = jsonObject.getString("school");//ѧУ
        String password = jsonObject.getString("password");//����
        
    	if ((userBean=logService.EmailExist(email)) != null) { // �˺Ŵ���  
    		jsonReply.put("code", CONFILICT);//�ʼ���ע��
            //jsonReply.put("user",userBean);
         }else { // ע��ɹ�
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
        		jsonReply.put("code",ERROR);//ע����̷�������
        	}
         } 
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
