package db;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import Bean.*;

public class OrdersDAO extends DBManager implements IDAO{

    private int recordNum=0;
    
    public OrdersDAO(){
    }

	public int AddEntity(IEntity entity) {
		 super.openConnect();
		// TODO Auto-generated method stub
		boolean succ=false;
		OrdersBean order=(OrdersBean)entity;
		
		try{
			String sqlGetNum="select count(*) from Orders;";
			ResultSet res;
			res = sta.executeQuery(sqlGetNum);
			if(res.next()){
				this.recordNum=res.getInt(1);
			}else
				this.recordNum = 0;
			
			String sqlInsert = "insert into Orders values(?,?,?,?,?,?,?,?,?,?,?);";
            preStmt=(PreparedStatement) conn.prepareStatement(sqlInsert);
            preStmt.setInt(1, recordNum+1);
            preStmt.setInt(2, order.getOrderType());
            preStmt.setString(3, order.getOrderAddress());
            preStmt.setString(4,order.getOrderDestination());
            preStmt.setString(5, order.getOrderTime());
            preStmt.setString(6, order.getOrderItem());
            preStmt.setString(7, order.getOrderDescribe());
            preStmt.setFloat(8, order.getOrderReward());
            preStmt.setFloat(9, order.getOrderPredict());
            preStmt.setString(10, order.getContactName());
            preStmt.setString(11, order.getContactPhone());

			if(preStmt.executeUpdate()!=0){
				succ = true;
			}	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			super.closeConnect();
		}
		
		if(succ)
			return recordNum+1;
		
		return -1;
	} 

	public IEntity GetOneEntity(int orderId){
		 super.openConnect();
		OrdersBean order = null;
		
		if(orderId!=0){
			String sqlQuery = "select * from Orders where order_id=?;";
			try{
				preStmt=(PreparedStatement) conn.prepareStatement(sqlQuery);
				preStmt.setInt(1, orderId);
				ResultSet res;
				res = preStmt.executeQuery();
				
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
					order.setContactName(res.getString("contactName"));
					order.setContactPhone(res.getString("contactPhone"));
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				super.closeConnect();
			}
		}
		return order;
		
	}		
}
