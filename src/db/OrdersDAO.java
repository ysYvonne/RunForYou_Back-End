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

	public boolean AddEntity(IEntity entity) {
		// TODO Auto-generated method stub
		boolean succ=true;
		OrdersBean order=(OrdersBean)entity;
		
		try{
			String sqlGetNum="select count(*) from Orders";
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
			                                               +order.getOrderPredict()+")";
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
		
		return succ;
	} 
}
