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
    	order.setOrderAddress("16号楼");
    	order.setOrderDestination("下沉广场");
    	order.setOrderDescribe("不要香菜");
    	order.setOrderItem("烤肉拌饭444");
    	order.setOrderPredict(13);
    	order.setOrderReward(5);
    	order.setOrderTime("30分钟");
    	*/
    	UserBean user = new UserBean();
    	user.setAge(18);
    	user.setEmail("14301063@bjtu.edu.cn");
    	user.setName("何本伟");
    	user.setNickname("小变态");
    	user.setPassword("123456");
    	user.setPhoneNum("13121956192");
    	user.setSchool("北京交通大学");
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
            response.getWriter().close(); // 关闭这个流，不然会发生错误的
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	doPost(request,response);

    }

}