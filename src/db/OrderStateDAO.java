package db;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import Bean.*;

public class OrderStateDAO extends DBManager implements IDAO{
    
    public OrderStateDAO(){
    }

	public boolean AddEntity(IEntity entity) {
		 super.openConnect();
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
        	 System.out.println(preStmt.asSql());
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
		 super.openConnect();
		OrderStateBean orderstate = null;
		
		if(orderId!=0){
			String sqlQuery = "select * from Order_State where order_id=?;";
			try{
				preStmt=(PreparedStatement) conn.prepareStatement(sqlQuery);
				preStmt.setInt(1, orderId);
				ResultSet res;
				res = preStmt.executeQuery();
				
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
		 super.openConnect();
		ArrayList<Integer> orderIdList = new ArrayList<Integer>();
		//OrderStateBean orderState = null;
		int orderId = 0;
		
		if(clientId!=0){
			String sqlQuery = "Select * from (SELECT * from Order_State ORDER BY order_id DESC)table_a where client_id=? and order_id<? limit ?;";
			//String sqlQuery = "Select * from Order_State where client_id="+clientId+";";
			try{
				preStmt=(PreparedStatement) conn.prepareStatement(sqlQuery);
				preStmt.setInt(1, clientId);
				preStmt.setInt(2, index);
				preStmt.setInt(3,num);
				ResultSet res;
				System.out.println(preStmt.asSql());
				res = preStmt.executeQuery();
				
				while(res.next()){
					orderId = res.getInt("order_id");
					
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
    	 super.openConnect();
    	ArrayList<Integer> orderIdList = new ArrayList<Integer>(); 
		//OrderStateBean orderState = null;
		int orderId = 0;
		
		if(deliveryId!=0){
			String sqlQuery = "Select * from (SELECT * from Order_State ORDER BY order_id DESC)table_a where delivery_id=? and order_id<? limit ?;";
			//String sqlQuery = "Select * from Order_State where client_id="+clientId+";";
			try{
				preStmt=(PreparedStatement) conn.prepareStatement(sqlQuery);
				preStmt.setInt(1, deliveryId);
				preStmt.setInt(2, index);
				preStmt.setInt(3,num);
				ResultSet res;
				res = preStmt.executeQuery();
				
				while(res.next()){
					orderId = res.getInt("order_id");
					
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
		 super.openConnect();
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
	
	public boolean acceptOrder(int orderId,int userId){
		 super.openConnect();
		boolean succ=false;
		
		if(orderId>0){
			String sqlUpdate = "update Order_State set state =1";
			try{
				
					sqlUpdate += ",delivery_id="+userId+" where order_id="+orderId+";";
	
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
	
	public boolean UpdateState(int orderId,int state,String time){
		 super.openConnect();
		boolean succ=false;
		 
		if(orderId>0){
			String sqlUpdate = "update Order_State set state ="+state;
			try{
				if(state==-1){
					sqlUpdate += ",over_time='"+time+"' where order_id="+orderId+";";
				}else if(state==2){
					sqlUpdate += ",get_time='"+time+"' where order_id="+orderId+";";
				}else if(state==3){
					sqlUpdate += ",arrive_time='"+time+"' where order_id="+orderId+";";
				}else if(state==4||state==-1){
					
					sqlUpdate += ",over_time='"+time+"' where order_id="+orderId+";";
				}else if(state==5){
					sqlUpdate += " where order_id="+orderId+";";
				}
				System.out.println(sqlUpdate);
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
    	 super.openConnect();
    	ArrayList<Integer> orderIdList = new ArrayList<Integer>();
    	int orderId = 0;
    	
    	if((num>0)&&(index>0)){
    		String sqlQuery = "Select * from (SELECT * from Order_State ORDER BY order_id DESC)table_a where state=? and order_id<? limit ?;";
    		try{
    			preStmt=(PreparedStatement) conn.prepareStatement(sqlQuery);
    			preStmt.setInt(1, state);
    			preStmt.setInt(2,index);
    			preStmt.setInt(3, num);
   			
				ResultSet res;
				System.out.println(preStmt.asSql());
				res = preStmt.executeQuery();
				
				while(res.next()){
					orderId = res.getInt("order_id");
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
    
}
