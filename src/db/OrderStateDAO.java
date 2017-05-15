package db;

import Bean.IEntity;
import Bean.OrderStateBean;

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

}
