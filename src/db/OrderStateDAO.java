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
        			                                            +orderState.getGetTime()+")";
        	
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

	public ArrayList<String> getOrderIdListState(int state){
		ArrayList<String> orderIdList = null;
		//OrderStateBean orderState = null;
		String orderId = null;
		if(state==0){
			String sqlQuery = "select * from Order_State where state=0";
			try{
				ResultSet res;
				res = sql.executeQuery(sqlQuery);
				
				while(res.next()){
					orderId = res.getString("order_id");
					orderIdList = new ArrayList<String>();
					orderIdList.add(orderId);
				}	
				
				sql.closeConnect();
				return orderIdList;
				
			}catch(Exception e){
				return null;
			}
		}
		
		sql.closeConnect();
		return null;
		
	}

	public ArrayList<String> GetOrderIdListClient(int clientId){
		
		ArrayList<String> orderIdList = null;
		//OrderStateBean orderState = null;
		String orderId = null;
		
		if(clientId!=0){
			String sqlQuery = "Select * from Order_State where client_id="+clientId;
			try{
				ResultSet res;
				res = sql.executeQuery(sqlQuery);
				
				while(res.next()){
					orderId = res.getString("order_id");
					orderIdList = new ArrayList<String>();
					orderIdList.add(orderId);			
				}
				
				sql.closeConnect();
				return orderIdList;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		sql.closeConnect();
		return null;
	}

    public ArrayList<String> GetOrderIdListDelivery(int deliveryId){
		
    	ArrayList<String> orderIdList = null;
		//OrderStateBean orderState = null;
		String orderId = null;
		
		if(deliveryId!=0){
			String sqlQuery = "Select * from Order_State where delivery_id="+deliveryId;
			try{
				ResultSet res;
				res = sql.executeQuery(sqlQuery);
				
				while(res.next()){
					orderId = res.getString("order_id");
					orderIdList = new ArrayList<String>();
					orderIdList.add(orderId);			
				}
				
				sql.closeConnect();
				return orderIdList;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		sql.closeConnect();
		return null;
	}

}
