package Service;

import java.util.*;
import Bean.*;
import db.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class OrderService {
	
	private OrdersDAO ordersDao;
	private OrderStateDAO orderStateDao;
	
	public OrderService(){
		ordersDao = new OrdersDAO();
		orderStateDao = new OrderStateDAO();
	}
	public ArrayList<LittleOrderBean> loadOrders(){
		int totalnum = orderStateDao.getEntityNumber();
		ArrayList<Integer> orderIdList = orderStateDao.GetOrders(15, totalnum, 0);
		ArrayList<LittleOrderBean> orderList = new ArrayList<LittleOrderBean>();
		for(int i = 0;i<15;i++)
		{
			OrdersBean order = (OrdersBean)ordersDao.GetOneEntity(orderIdList.get(i));
			OrderStateBean orderState = (OrderStateBean)orderStateDao.GetOneEntity(orderIdList.get(i));
			LittleOrderBean littleOrder = new LittleOrderBean();
			littleOrder.setOrderId(order.getOrderId());
			littleOrder.setOrderItem(order.getOrderItem());
			littleOrder.setOrderReward(order.getOrderReward());
			littleOrder.setOrderAddress(order.getOrderAddress());
			littleOrder.setStartTime(orderState.getStartTime());
			littleOrder.setState(orderState.getState());
			orderList.add(littleOrder);
		}
		return orderList;
	}
	public  ArrayList<LittleOrderBean> loadMoreOrders(int index){

		ArrayList<Integer> orderIdList = orderStateDao.GetOrders(15, index, 0);
		ArrayList<LittleOrderBean> orderList = new ArrayList<LittleOrderBean>();
		for(int i = 0;i<15;i++)
		{
			OrdersBean order = (OrdersBean)ordersDao.GetOneEntity(orderIdList.get(i));
			OrderStateBean orderState = (OrderStateBean)orderStateDao.GetOneEntity(orderIdList.get(i));
			LittleOrderBean littleOrder = new LittleOrderBean();
			littleOrder.setOrderId(order.getOrderId());
			littleOrder.setOrderItem(order.getOrderItem());
			littleOrder.setOrderReward(order.getOrderReward());
			littleOrder.setOrderAddress(order.getOrderAddress());
			littleOrder.setStartTime(orderState.getStartTime());
			littleOrder.setState(orderState.getState());
			orderList.add(littleOrder);
		}
		return orderList;
	}
	
	public boolean placeOrder(int userId,OrdersBean order){
		boolean succ1 = false;
		boolean succ2 = false;
		
		int order_id = ordersDao.AddEntity(order);
		
		if(order_id > 0)
			succ1 = true;
		
		OrderStateBean orderState = new OrderStateBean();
		orderState.setOrderId(order_id);
		orderState.setClientId(userId);
		orderState.setState(0);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		orderState.setStartTime(df.format(new Date()));
		
		succ2 = orderStateDao.AddEntity(orderState);
		
		if(succ1&&succ2)
			return true;
		
		return false;
	}
	public OrdersBean getOrder(int orderId){
		return (OrdersBean)ordersDao.GetOneEntity(orderId);
	}
	
	public OrderStateBean getOrderState(int orderId){
		return (OrderStateBean)orderStateDao.GetOneEntity(orderId);
	}
	
}
