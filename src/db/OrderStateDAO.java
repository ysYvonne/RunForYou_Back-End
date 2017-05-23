package db;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import Bean.*;

public class OrderStateDAO extends DBManager implements IDAO{
	
	//private DBManager sql;
    private int recordNum=0;
    private int pageNum=0;
    
    public OrderStateDAO(){
    	super.openConnect();
    }

	public boolean AddEntity(IEntity entity) {
		// TODO Auto-generated method stub
        boolean succ=false;
        OrderStateBean orderState=(OrderStateBean)entity;
        
        try{
        	String sqlInsert = "insert into Order_State values(null,?,?,?,?,?,?,?,?);";
        	 preStmt=(PreparedStatement) conn.prepareStatement(sqlInsert);
        	 preStmt.setInt(1, orderState.getOrderId());
        	 preStmt.setInt(2,orderState.getClientId());
        	 preStmt.setInt(3,orderState.getDeliveryId());
        	 preStmt.setInt(4,orderState.getState());
        	 preStmt.setString(5,orderState.getOverTime());
        	 preStmt.setString(6,orderState.getStartTime());
        	 preStmt.setString(7,orderState.getArriveTime());
        	 preStmt.setString(8,orderState.getGetTime());
        	if(preStmt.executeUpdate()!=0){
        		succ = true;
        	}
        }catch(Exception e){
        	e.printStackTrace();
        }finally {
			super.closeConnect();
		}
        
		return succ;
	}

	public IEntity GetOneEntity(int orderId){
		OrderStateBean orderstate = null;
		
		if(orderId!=0){
			String sqlQuery = "select * from Order_State where order_id=?;";
			try{
				preStmt=(PreparedStatement) conn.prepareStatement(sqlQuery);
				preStmt.setInt(1, orderId);
				ResultSet res;
				res = preStmt.executeQuery(sqlQuery);
				
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
				
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				super.closeConnect();
			}			
		}

		return orderstate;
		
	}
	
	public ArrayList<Integer> GetOrderIdListClient(int num,int index, int clientId){
		
		ArrayList<Integer> orderIdList = null;
		//OrderStateBean orderState = null;
		int orderId = 0;
		
		if(clientId!=0){
			String sqlQuery = "Select * from Order_State where client_id=? and client_id<? desc limit ?;";
			//String sqlQuery = "Select * from Order_State where client_id="+clientId+";";
			try{
				preStmt=(PreparedStatement) conn.prepareStatement(sqlQuery);
				preStmt.setInt(1, clientId);
				preStmt.setInt(2, index);
				preStmt.setInt(3,num);
				ResultSet res;
				res = preStmt.executeQuery(sqlQuery);
				
				while(res.next()){
					orderId = res.getInt("order_id");
					orderIdList = new ArrayList<Integer>();
					orderIdList.add(orderId);			
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				super.closeConnect();
			}
		}
		
		return orderIdList;
	}

    public ArrayList<Integer> GetOrderIdListDelivery(int num,int index,int deliveryId){
		
    	ArrayList<Integer> orderIdList = null;
		//OrderStateBean orderState = null;
		int orderId = 0;
		
		if(deliveryId!=0){
			String sqlQuery = "Select * from Order_State where delivery_id=? and delivery_id<? desc limit ?;";
			//String sqlQuery = "Select * from Order_State where client_id="+clientId+";";
			try{
				preStmt=(PreparedStatement) conn.prepareStatement(sqlQuery);
				preStmt.setInt(1, deliveryId);
				preStmt.setInt(2, index);
				preStmt.setInt(3,num);
				ResultSet res;
				res = preStmt.executeQuery(sqlQuery);
				
				while(res.next()){
					orderId = res.getInt("order_id");
					orderIdList = new ArrayList<Integer>();
					orderIdList.add(orderId);	
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				super.closeConnect();
			}
		}

		return orderIdList;
	}

	public int getEntityNumber(){
		
		int total = 0;
		try{
			String sqlQuery = "Select count(*) from Order_State;";
			ResultSet res;
			res = sta.executeQuery(sqlQuery);
			if(res.next()){
				total = res.getInt(1);
			}
			
			return total;
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			super.closeConnect();
		}
		
		return total;
	}
	
	public boolean UpdateState(String orderId,int state,String time){
		boolean succ=false;
		 
		if(orderId!=null){
			String sqlUpdate = "update Order_State set state ="+state;
			try{
				if(state==-1){
					sqlUpdate += ",over_time="+time+" where order_id="+orderId+";";
				}else if(state==1){
					sqlUpdate += " where order_id="+orderId+";";
				}else if(state==2){
					sqlUpdate += ",get_time="+time+" where order_id="+orderId+";";
				}else if(state==3){
					sqlUpdate += ",arrive_time="+time+" where order_id="+orderId+";";
				}else if(state==4){
					sqlUpdate += ",over_time="+time+" where order_id="+orderId+";";
				}else if(state==5){
					sqlUpdate += " where order_id="+orderId+";";
				}
	
				if(sta.executeUpdate(sqlUpdate)!=0){
	        		succ = true;
	        	}

			 }catch(Exception e){
				 e.printStackTrace();
			 }finally{
				 super.closeConnect();
			 }
		 }
		
		return succ;
	}
	
    public ArrayList<Integer> GetOrders(int num,int index,int state){
    	ArrayList<Integer> orderIdList = null;
    	int orderId = 0;
    	
    	if((num>0)&&(index>0)){
    		String sqlQuery = "Select * from Order_State where state=? and order_id<? desc limit ?;";
    		try{
    			preStmt=(PreparedStatement) conn.prepareStatement(sqlQuery);
    			preStmt.setInt(1, state);
    			preStmt.setInt(2,index);
    			preStmt.setInt(3, num);
   			
				ResultSet res;
				res = preStmt.executeQuery(sqlQuery);
				
				while(res.next()){
					orderId = res.getInt("order_id");
					orderIdList = new ArrayList<Integer>();
					orderIdList.add(orderId);
				}
    			
    		}catch(Exception e){
    			e.printStackTrace();
    		}finally{
    			super.closeConnect();
    		}
    	}

    	return orderIdList;
    }

    public int getIdOrderNum(int id,String type){
		int total = 0;
		if(id>0){
			String sqlQuery = "Select count(*) from Order_State where "+type+"_id ="+id+";";
			try{				
				ResultSet res;
				res = sta.executeQuery(sqlQuery);
				if(res.next()){
					total = res.getInt(1);
				}	
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				super.closeConnect();
			}
		}
		return total;
    }
    
}
