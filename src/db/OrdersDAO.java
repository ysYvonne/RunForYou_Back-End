package db;
import java.sql.ResultSet;

import Bean.*;

public class OrdersDAO implements IDAO{
	private DBManager sql;
    private int recordNum=0;
    private int pageNum=0;
    
    public OrdersDAO(){
        sql = new DBManager();
        sql.openConnect();
    }

	public int AddEntity(IEntity entity) {
		// TODO Auto-generated method stub
		boolean succ=true;
		OrdersBean order=(OrdersBean)entity;
		
		try{
			String sqlGetNum="select count(*) from Orders;";
			ResultSet res;
			res = sql.executeQuery(sqlGetNum);
			if(res.next()){
				this.recordNum=res.getInt(1);
			}else
				this.recordNum = 0;
			
			String sqlInsert = "insert into Orders values("+(recordNum+1)+","
			                                               +order.getOrderType()+","
			                                               +order.getOrderAddress()+","
			                                               +order.getOrderDestination()+","
			                                               +order.getOrderTime()+","
			                                               +order.getOrderItem()+","
			                                               +order.getOrderDescribe()+","
			                                               +order.getOrderReward()+","
			                                               +order.getOrderPredict()+");";
			//²Ù×÷DB¶ÔÏó
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
		if(succ)
			return recordNum+1;
		return -1;
	} 

	public IEntity GetOneEntity(int orderId){
		OrdersBean order = null;
		
		if(orderId!=0){
			String sqlQuery = "select * from Orders where order_id="+orderId+";";
			try{
				ResultSet res;
				res = sql.executeQuery(sqlQuery);
				
				if(res.next()){
					order = new OrdersBean();
					order.setOrderId(res.getInt("order_id"));
					order.setOrderType(res.getInt("order_type"));
					order.setOrderAddress(res.getString("order_address"));
					order.setOrderDestination(res.getString("order_destination"));
					order.setOrderTime(res.getString("order_time"));
					order.setOrderItem(res.getString("order_item"));
					order.setOrderDescribe(res.getString("order_describe"));
					order.setOrderReward(res.getFloat("order_reward"));
					order.setOrderPredict(res.getFloat("order_predict"));
				}
				
				sql.closeConnect();
				return order;
			}catch(Exception e){
				e.printStackTrace();
			}			
		}
		sql.closeConnect();
		return null;
		
	}
		
}
