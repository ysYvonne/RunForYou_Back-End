package db;

import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import Bean.*;

public class CreditDAO extends DBManager implements IDAO {

    private int recordNum=0;
    private int pageNum=0;
    
	public CreditDAO() {
		// TODO Auto-generated constructor stub
		super.openConnect();
	}

	public boolean AddEntity(IEntity entity) {
		// TODO Auto-generated method stub
        boolean succ=false;
        CreditBean credit = (CreditBean)entity;      
        
        try{
        	String sqlInsert = "insert into Credit values(null,?,?,?,?);";
        	preStmt = (PreparedStatement) conn.prepareStatement(sqlInsert);
        	preStmt.setInt(1, credit.getUserId());
        	preStmt.setInt(2, credit.getOrderNum());
        	preStmt.setInt(3, credit.getDeliveryNum());
        	preStmt.setInt(4, credit.getCredit());
        	
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

	public boolean UpdateEntityOrder(int userId,int value){
		boolean succ = false;
		
		if(userId>0){
			String sqlUpdate = "update Credit set order_num = (order_num + ?) where user_id =?;";
			try{
				preStmt = (PreparedStatement) conn.prepareStatement(sqlUpdate);		
				preStmt.setInt(1, value);
				preStmt.setInt(2, userId);
				if(preStmt.executeUpdate(sqlUpdate)!=0){
	        		succ = true;
	        	}
	        	
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				super.closeConnect();
			}
		}
		return succ;
	}
	
	public boolean UpdateEntityDelivery(int userId,int value){
		
		boolean succ = false;
		
		if(userId>0){
			String sqlUpdate = "update Credit set delivery_num = (delivery_num + ?) where user_id =?;";
			try{			
				preStmt = (PreparedStatement) conn.prepareStatement(sqlUpdate);		
				preStmt.setInt(1, value);
				preStmt.setInt(2, userId);
				if(preStmt.executeUpdate(sqlUpdate)!=0){
	        		succ = true;
	        	}
	        	
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				super.closeConnect();
			}
		}
		return succ;
	}
	
	public boolean UpdateEntityCredit(int userId,int value){
		boolean succ = false;
		
		if(userId>0){
			String sqlUpdate = "update Credit set credit = (credit + ?) where user_id =?;";
			try{
				preStmt = (PreparedStatement) conn.prepareStatement(sqlUpdate);		
				preStmt.setInt(1, value);
				preStmt.setInt(2, userId);
				if(preStmt.executeUpdate(sqlUpdate)!=0){
	        		succ = true;
	        	}
	        	
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				super.closeConnect();
			}
		}
		return succ;
	}
}
