package db;

import java.sql.ResultSet;
import Bean.*;

public class CreditDAO implements IDAO {

	private DBManager sql;
    private int recordNum=0;
    private int pageNum=0;
    
	public CreditDAO() {
		// TODO Auto-generated constructor stub
        sql = new DBManager();
        sql.openConnect();
	}

	public boolean AddEntity(IEntity entity) {
		// TODO Auto-generated method stub
        boolean succ=true;
        CreditBean credit = (CreditBean)entity;
        
        try{
        	String sqlInsert = "insert into Credit(user_id,order_num,delivery_num,credit) values("+credit.getUserId()+","
        			                                       +credit.getOrderNum()+","
        			                                       +credit.getDeliveryNum()+","
        			                                       +credit.getCredit()+");";
        	
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
