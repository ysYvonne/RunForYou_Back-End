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
		for(int i = 0;i<orderIdList.size();i++)
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
		for(int i = 0;i<orderIdList.size();i++)
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
	public boolean acceptOrder(int userId,int orderId){
		boolean succ = false;
		succ = orderStateDao.acceptOrder(orderId, userId);
		return succ;
	}
	public ArrayList<LittleOrderBean> loadMyOrders(int userId){
		int totalnum = orderStateDao.getIdOrderNum(userId, "client");
		ArrayList<Integer> orderIdList = orderStateDao.GetOrderIdListClient(15, totalnum, userId);
		ArrayList<LittleOrderBean> orderList = new ArrayList<LittleOrderBean>();
		for(int i = 0;i<orderIdList.size();i++)
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
	public  ArrayList<LittleOrderBean> loadMoreMyOrders(int index,int userId){

		ArrayList<Integer> orderIdList = orderStateDao.GetOrderIdListClient(15, index, userId);
		ArrayList<LittleOrderBean> orderList = new ArrayList<LittleOrderBean>();
		for(int i = 0;i<orderIdList.size();i++)
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
	public ArrayList<LittleOrderBean> loadMyDeliveryOrders(int userId){
		int totalnum = orderStateDao.getIdOrderNum(userId, "Delivery");
		ArrayList<Integer> orderIdList = orderStateDao.GetOrderIdListDelivery(15, totalnum, userId);
		ArrayList<LittleOrderBean> orderList = new ArrayList<LittleOrderBean>();
		for(int i = 0;i<orderIdList.size();i++)
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
	public  ArrayList<LittleOrderBean> loadMoreMyDeliveryOrders(int index,int userId){

		ArrayList<Integer> orderIdList = orderStateDao.GetOrderIdListDelivery(15, index, userId);
		ArrayList<LittleOrderBean> orderList = new ArrayList<LittleOrderBean>();
		for(int i = 0;i<orderIdList.size();i++)
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
	
	public boolean updateState(int orderId,int state){
		
		boolean succ = false;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		succ = orderStateDao.UpdateState(orderId, state, df.format(new Date()));
		if(state == 4){//当订单完成时 根据积分订单加分
			CreditDAO creditDao = new CreditDAO();
			OrdersBean order = (OrdersBean)ordersDao.GetOneEntity(orderId);
			OrderStateBean orderState = (OrderStateBean)orderStateDao.GetOneEntity(orderId);
			if(order.getOrderType() == 1)
				succ = creditDao.UpdateEntityCredit(orderState.getDeliveryId(), order.getOrderReward());
			succ = creditDao.UpdateEntityDelivery(orderState.getDeliveryId(), 1);
			succ = creditDao.UpdateEntityOrder(orderState.getClientId(), 1);
		}
		return succ;
	}
	public boolean placeReview(int orderId,int reviewType){
		boolean succ = false;
		succ = orderStateDao.UpdateState(orderId, 5, null);//设置状态
		//插入review
		ReviewBean review = new ReviewBean();
		review.setOrderId(orderId);
		review.setReviewType(reviewType);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		review.setReviewTime(df.format(new Date()));
		ReviewDAO reviewDao = new ReviewDAO();
		reviewDao.AddEntity(review);
		//根据评价等级加分
		CreditDAO creditDao = new CreditDAO();
		if(reviewType<=2)
			succ = creditDao.UpdateEntityCredit(orderId, 1);
		else if(reviewType>2&&reviewType<=4)
			succ = creditDao.UpdateEntityCredit(orderId, 2);
		else
			succ = creditDao.UpdateEntityCredit(orderId, 3);
		
		return succ;
	}
}
