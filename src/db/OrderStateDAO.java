package db;

import java.sql.ResultSet;
import java.util.ArrayList;

import Bean.*;

public class OrderStateDAO implements IDAO{
	
	private DBManager sql;
    private int recordNum=0;
    private int pageNum=0;
    
    public OrderStateDAO(){
    	sql = new DBManager();
    	sql.openConnect();
    }

	public boolean AddEntity(IEntity entity) {
		// TODO Auto-generated method stub
        boolean succ=true;
        OrderStateBean orderState=(OrderStateBean)entity;
        
        try{
        	String sqlInsert = "insert into Order_State(order_id,client_id,delivery_id,state,over_time,start_time,arrive_time,get_time) values("
                                                                +orderState.getOrderId()+","
        			                                            +orderState.getClientId()+","
        			                                            +orderState.getDeliveryId()+","
        			                                            +orderState.getState()+","
        			                                            +orderState.getOverTime()+","
        			                                            +orderState.getStartTime()+","
        			                                            +orderState.getArriveTime()+","
        			                                            +orderState.getGetTime()+");";
        	
        	int rs = sql.executeUpdate(sqlInsert);
        	if(rs!=0){
        		sql.closeConnect();
        		succ = true;
        	}
        	sql.closeConnect();
        	succ = false;
        }catch(Exception e){
        	succ = false;
        }
		return succ;
	}

	public IEntity GetOneEntity(int orderId){
		OrderStateBean orderstate = null;
		
		if(orderId!=0){
			String sqlQuery = "select * from Order_State where order_id="+orderId+";";
			try{
				ResultSet res;
				res = sql.executeQuery(sqlQuery);
				
				if(res.next()){
					orderstate = new OrderStateBean();
					orderstate.setOrderStateId(res.getInt("order_state_id"));
					orderstate.setOrderId(res.getInt("order_id"));
					orderstate.setClientId(res.getInt("client_id"));
					orderstate.setDeliveryId(res.getInt("delivery_id"));
					orderstate.setGetTime(res.getString("get_time"));
					orderstate.setArriveTime(res.getString("arrive_time"));
					orderstate.setStartTime(res.getString("start_time"));
					orderstate.setOverTime(res.getString("over_time"));
					orderstate.setState(res.getInt("state"));
				}
				
				sql.closeConnect();
				return orderstate;
			}catch(Exception e){
				e.printStackTrace();
			}			
		}
		sql.closeConnect();
		return null;
		
	}
	
	public ArrayList<Integer> GetOrderIdListClient(int clientId){
		
		ArrayList<Integer> orderIdList = null;
		//OrderStateBean orderState = null;
		int orderId = 0;
		
		if(clientId!=0){
			String sqlQuery = "Select * from Order_State where client_id="+clientId+";";
			try{
				ResultSet res;
				res = sql.executeQuery(sqlQuery);
				
				while(res.next()){
					orderId = res.getInt("order_id");
					orderIdList = new ArrayList<Integer>();
					orderIdList.add(orderId);			
				}
				
				sql.closeConnect();
				return orderIdList;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		sql.closeConnect();
		return orderIdList;
	}

    public ArrayList<Integer> GetOrderIdListDelivery(int deliveryId){
		
    	ArrayList<Integer> orderIdList = null;
		//OrderStateBean orderState = null;
		int orderId = 0;
		
		if(deliveryId!=0){
			String sqlQuery = "Select * from Order_State where delivery_id="+deliveryId+";";
			try{
				ResultSet res;
				res = sql.executeQuery(sqlQuery);
				
				while(res.next()){
					orderId = res.getInt("order_id");
					orderIdList = new ArrayList<Integer>();
					orderIdList.add(orderId);			
				}
				
				sql.closeConnect();
				return orderIdList;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		sql.closeConnect();
		return orderIdList;
	}

	public int getEntityNumber(){
		
		int total = 0;
		try{
			String sqlQuery = "Select count(*) from Order_State;";
			ResultSet res;
			res = sql.executeQuery(sqlQuery);
			if(res.next()){
				total = res.getInt(1);
			}
			
			return total;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return total;
	}
	
	public boolean UpdateState(String orderId,int state){
		boolean succ=true;
		 
		if(orderId!=null){			 
			String sqlUpdate = "update Order_State set state ="+state+" where order_id="+orderId+";";
			try{		 		 			 
				int rs = sql.executeUpdate(sqlUpdate);
					
				if(rs!=0){
	        		sql.closeConnect();
	        		succ = true;
	        	}
	        	sql.closeConnect();
	        	succ = false;
			 }catch(Exception e){
				 succ = false;
			 }
		 }
		
		return succ;
	}
	
    public ArrayList<Integer> GetOrders(int num,int index,int state){
    	ArrayList<Integer> orderIdList = null;
    	int orderId = 0;
    	
    	if((num>0)&&(index>0)){
    		String sqlQuery = "Select * from Order_State where state="+state+ " and order_id<"+ index +" desc limit "+num+";";
    		try{
				ResultSet res;
				res = sql.executeQuery(sqlQuery);
				
				while(res.next()){
					orderId = res.getInt("order_id");
					orderIdList = new ArrayList<Integer>();
					orderIdList.add(orderId);
				}
				
				sql.closeConnect();
				return orderIdList;
    			
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    	}
    	
    	sql.closeConnect();
    	return orderIdList;
    }
}
