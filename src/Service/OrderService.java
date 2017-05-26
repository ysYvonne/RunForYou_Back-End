package Service;

import java.util.*;
import Bean.*;
import db.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class OrderService {
	
	private OrdersDAO ordersDao;
	private OrderStateDAO orderStateDao;
	

	public ArrayList<LittleOrderBean> loadOrders(){
		orderStateDao = new OrderStateDAO();
		int totalnum = orderStateDao.getEntityNumber();
		
		orderStateDao = new OrderStateDAO();
		ArrayList<Integer> orderIdList;
		orderIdList = orderStateDao.GetOrders(15, totalnum+1, 0);
		ArrayList<LittleOrderBean> orderList = new ArrayList<LittleOrderBean>();
		for(int i = 0;i<orderIdList.size();i++)
		{
			System.out.println(orderIdList.size()+"   "+i);
			ordersDao = new OrdersDAO();
			OrdersBean order = (OrdersBean)ordersDao.GetOneEntity(orderIdList.get(i));
			orderStateDao = new OrderStateDAO();
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
		orderStateDao = new OrderStateDAO();
		ArrayList<Integer> orderIdList = orderStateDao.GetOrders(15, index, 0);
		ArrayList<LittleOrderBean> orderList = new ArrayList<LittleOrderBean>();
		for(int i = 0;i<orderIdList.size();i++)
		{
			ordersDao = new OrdersDAO();
			OrdersBean order = (OrdersBean)ordersDao.GetOneEntity(orderIdList.get(i));
			orderStateDao = new OrderStateDAO();
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
		
		ordersDao = new OrdersDAO();
		int order_id = ordersDao.AddEntity(order);
		
		if(order_id > 0)
			succ1 = true;
		
		OrderStateBean orderState = new OrderStateBean();
		orderState.setOrderId(order_id);
		orderState.setClientId(userId);
		//orderState.setDeliveryId();
		orderState.setState(0);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		orderState.setStartTime(df.format(new Date()));
		orderStateDao = new OrderStateDAO();
		succ2 = orderStateDao.AddEntity(orderState);
		
		boolean succ3 = false;
		CreditDAO creditDao = new CreditDAO();
		if(order.getOrderType() == 1&&((CreditBean)creditDao.GetOneEntityId(userId)).getCredit()-order.getOrderReward()>=0){
			creditDao = new CreditDAO();
			succ3 = creditDao.UpdateEntityCredit(userId, order.getOrderReward()*-1);
		}
		if(succ1&&succ2)
			return true;
		
		return false;
	}
	public OrdersBean getOrder(int orderId){
		ordersDao = new OrdersDAO();
		return (OrdersBean)ordersDao.GetOneEntity(orderId);
	}
	
	public OrderStateBean getOrderState(int orderId){
		orderStateDao = new OrderStateDAO();
		return (OrderStateBean)orderStateDao.GetOneEntity(orderId);
	}
	
	public int getReview(int orderId){
		ReviewDAO reviewDao= new ReviewDAO();
		return ((ReviewBean)reviewDao.GetOneEntity(orderId)).getReviewType();
	}
	public boolean acceptOrder(int userId,int orderId){
		boolean succ = false;
		orderStateDao = new OrderStateDAO();
		succ = orderStateDao.acceptOrder(orderId, userId);
		return succ;
	}
	public ArrayList<LittleOrderBean> loadMyOrders(int userId){
		orderStateDao = new OrderStateDAO();
		int totalnum = orderStateDao.getEntityNumber();
		System.out.println(totalnum);
		orderStateDao = new OrderStateDAO();
		ArrayList<Integer> orderIdList = orderStateDao.GetOrderIdListClient(15, totalnum+1, userId);
		ArrayList<LittleOrderBean> orderList = new ArrayList<LittleOrderBean>();
		for(int i = 0;i<orderIdList.size();i++)
		{
			ordersDao = new OrdersDAO();
			OrdersBean order = (OrdersBean)ordersDao.GetOneEntity(orderIdList.get(i));
			orderStateDao = new OrderStateDAO();
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

		orderStateDao = new OrderStateDAO();
		ArrayList<Integer> orderIdList = orderStateDao.GetOrderIdListClient(15, index, userId);
		ArrayList<LittleOrderBean> orderList = new ArrayList<LittleOrderBean>();
		for(int i = 0;i<orderIdList.size();i++)
		{
			ordersDao = new OrdersDAO();
			OrdersBean order = (OrdersBean)ordersDao.GetOneEntity(orderIdList.get(i));
			orderStateDao = new OrderStateDAO();
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
		orderStateDao = new OrderStateDAO();
		int totalnum = orderStateDao.getEntityNumber();
		orderStateDao = new OrderStateDAO();
		ArrayList<Integer> orderIdList = orderStateDao.GetOrderIdListDelivery(15, totalnum+1, userId);
		ArrayList<LittleOrderBean> orderList = new ArrayList<LittleOrderBean>();
		for(int i = 0;i<orderIdList.size();i++)
		{
			ordersDao = new OrdersDAO();
			OrdersBean order = (OrdersBean)ordersDao.GetOneEntity(orderIdList.get(i));
			orderStateDao = new OrderStateDAO();
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

		orderStateDao = new OrderStateDAO();
		ArrayList<Integer> orderIdList = orderStateDao.GetOrderIdListDelivery(15, index, userId);
		ArrayList<LittleOrderBean> orderList = new ArrayList<LittleOrderBean>();
		for(int i = 0;i<orderIdList.size();i++)
		{
			ordersDao = new OrdersDAO();
			OrdersBean order = (OrdersBean)ordersDao.GetOneEntity(orderIdList.get(i));
			orderStateDao = new OrderStateDAO();
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
		orderStateDao = new OrderStateDAO();
		succ = orderStateDao.UpdateState(orderId, state, df.format(new Date()));
		
		if(state == 4){//当订单完成时 根据积分订单加分
			ordersDao = new OrdersDAO();
			OrdersBean order = (OrdersBean)ordersDao.GetOneEntity(orderId);
			orderStateDao = new OrderStateDAO();
			OrderStateBean orderState = (OrderStateBean)orderStateDao.GetOneEntity(orderId);
			CreditDAO creditDao = new CreditDAO();
			if(order.getOrderType() == 1)
				succ = creditDao.UpdateEntityCredit(orderState.getDeliveryId(), order.getOrderReward());
			creditDao = new CreditDAO();
			succ = creditDao.UpdateEntityDelivery(orderState.getDeliveryId(), 1);
			creditDao = new CreditDAO();
			succ = creditDao.UpdateEntityOrder(orderState.getClientId(), 1);
		}
		if(state == -1){//当订单取消时 根据积分订单返回积分
			
			ordersDao = new OrdersDAO();
			OrdersBean order = (OrdersBean)ordersDao.GetOneEntity(orderId);
			orderStateDao = new OrderStateDAO();
			OrderStateBean orderState = (OrderStateBean)orderStateDao.GetOneEntity(orderId);
			CreditDAO creditDao = new CreditDAO();
			if(order.getOrderType() == 1){
				succ = creditDao.UpdateEntityCredit(orderState.getClientId(), order.getOrderReward());
			}
				
		}
		return succ;
	}
	public boolean placeReview(int orderId,int reviewType){
		boolean succ = false;
		orderStateDao = new OrderStateDAO();
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
		orderStateDao = new OrderStateDAO();
		OrderStateBean orderstate = (OrderStateBean)orderStateDao.GetOneEntity(orderId);
		if(reviewType<=2)
			succ = creditDao.UpdateEntityCredit(orderstate.getDeliveryId(), 1);
		else if(reviewType>2&&reviewType<=4)
			succ = creditDao.UpdateEntityCredit(orderstate.getDeliveryId(), 2);
		else
			succ = creditDao.UpdateEntityCredit(orderstate.getDeliveryId(), 3);
		
		return succ;
	}
}
