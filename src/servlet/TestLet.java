package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Bean.*;
import db.*;
import Service.*;

public class TestLet extends HttpServlet {

    private static final long serialVersionUID = 369840050351775312L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/json");
    	
    	System.out.println("helloworld!");
    	LogService logService = new LogService();
        //OrderService orderService = new OrderService();
    	//InformationService informationService = new InformationService();
    	/*
    	OrdersBean order = new OrdersBean();
    	order.setOrderType(1);
    	order.setOrderAddress("16��¥");
    	order.setOrderDestination("�³��㳡");
    	order.setOrderDescribe("��Ҫ���");
    	order.setOrderItem("����跹444");
    	order.setOrderPredict(13);
    	order.setOrderReward(5);
    	order.setOrderTime("30����");
    	*/
    	UserBean user = new UserBean();
    	user.setAge(18);
    	user.setEmail("14301063@bjtu.edu.cn");
    	user.setName("�α�ΰ");
    	user.setNickname("С��̬");
    	user.setPassword("123456");
    	user.setPhoneNum("13121956192");
    	user.setSchool("������ͨ��ѧ");
    	user.setSex(2);
    	
    	
    	//UserBean user1 = informationService.getUser(2);
    	
    	//OrdersBean order1 = orderService.getOrder(1);
    	//OrderStateBean orderState = orderService.getOrderState(1);
    	UserBean succ = logService.Register(user);
    	//ArrayList<LittleOrderBean> succ = orderService.updateState(1, 2);
    	
        try {
        	if(succ!=null){
        		//for(int i=0;i<succ.size();i++)
        			response.getWriter().println(succ.getName());
        			
        	}
        		//response.getWriter().println(succ);
        	else
        		response.getWriter().println("gnmd"); 	
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.getWriter().close(); // �ر����������Ȼ�ᷢ�������
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	doPost(request,response);

    }

}