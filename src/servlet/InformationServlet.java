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

import Bean.CreditBean;
import Bean.UserBean;
import Service.InformationService;
import Service.LogService;
import net.sf.json.JSONObject;

@WebServlet("/InformationServlet")
public class InformationServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4780417720324893743L;
	private static final int SUCCESS = 1;//�ɹ�
	private static final int ERROR = 0;//�������
	private static final int CONFILICT = -1;//�˺ų�ͻ�򲻴���
	
	private JSONObject jsonObject;
	private InformationService informationService;
	private UserBean userBean;
	private JSONObject jsonReply = new JSONObject();//��װ���������ص�JSON����   
	
	public InformationServlet() {
		// TODO Auto-generated constructor stub
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
			//��ӡandroid���ϴ���JSON����
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonObject = JSONObject.fromObject(result);
		String type = jsonObject.getString("type");
		informationService = new InformationService(); //�½�Serviceʵ��
		PrintWriter pw = response.getWriter();
		
		if(type.equals("placeOpinion")){
			placeOpinion();
		}else if(type.equals("getUser")){
			getUser();
		}else if(type.equals("updateInfomation")){
			updateInfomation();
		}else if(type.equals("getCredit")){
			getCredit();
		}
		
		System.out.println(jsonReply);
		pw.write(jsonReply.toString());
		pw.flush();
		pw.close();
	}
	
	public void placeOpinion(){
		int user_id = Integer.parseInt(jsonObject.getString("userId"));//�û�id
		String content = jsonObject.getString("content");
		
		boolean succ = informationService.placeOpinion(user_id, content);
		
		if(succ){
			jsonReply.put("code", SUCCESS);
		}else{
			jsonReply.put("code", ERROR);//�޸Ĳ��ɹ�
		}
		
	}
	public void getCredit(){
		
		int user_id = Integer.parseInt(jsonObject.getString("userId"));//�û�id
		
		CreditBean credit = informationService.getCredit(user_id);
		if(credit != null){
			jsonReply.put("code", SUCCESS);
			jsonReply.put("credit", credit);
		}else{
			jsonReply.put("code", ERROR);
			jsonReply.put("credit", null);
		}
		
		
	}
	public void getUser(){
		int user_id = Integer.parseInt(jsonObject.getString("userId"));//�û�id
		
		userBean = informationService.getUser(user_id);
		
		
		
		if(userBean != null){
			jsonReply.put("code", SUCCESS);
			jsonReply.put("user", userBean);
		}else{
			jsonReply.put("code", ERROR);
			jsonReply.put("user", null);
		}
		
	}
	
	public void updateInfomation(){
		int user_id = Integer.parseInt(jsonObject.getString("userId"));//�û�id
		String column = jsonObject.getString("column");
		String value = jsonObject.getString("value");
		
		if(informationService.updateInformation(user_id, column, value)){
			jsonReply.put("code", SUCCESS);//�޸ĳɹ�
		}else{
			jsonReply.put("code", ERROR);//�޸Ĳ��ɹ�
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}


}
