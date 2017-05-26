package db;

import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import Bean.*;

public class CreditDAO extends DBManager implements IDAO {

    
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

	public boolean UpdateEntityOrder(int userId,int value){
		boolean succ = false;
		
		if(userId>0){
			String sqlUpdate = "update Credit set order_num = (order_num + ?) where user_id =?;";
			try{
				preStmt = (PreparedStatement) conn.prepareStatement(sqlUpdate);		
				preStmt.setInt(1, value);
				preStmt.setInt(2, userId);
				if(preStmt.executeUpdate()!=0){
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
				if(preStmt.executeUpdate()!=0){
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
	
	public boolean UpdateEntityCredit(int userId,float value){
		boolean succ = false;
		
		if(userId>0){
			String sqlUpdate = "update Credit set credit = (credit + ?) where user_id =?;";
			try{
				preStmt = (PreparedStatement) conn.prepareStatement(sqlUpdate);		
				preStmt.setFloat(1, value);
				preStmt.setInt(2, userId);
				if(preStmt.executeUpdate()!=0){
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

	public IEntity GetOneEntityId(int userId){
		  CreditBean credit = null;
		  if(userId>0){
			  String sqlQuery ="select * from Credit where user_id=?;";
		   		try{
	    			preStmt=(PreparedStatement) conn.prepareStatement(sqlQuery);
	    			preStmt.setInt(1, userId);
	                ResultSet res;
	                res = preStmt.executeQuery();
	                if(res.next()){
	                	credit = new CreditBean();
	                	credit.setUserId(res.getInt("user_id"));
	                	credit.setOrderNum(res.getInt("order_num"));
	                	credit.setDeliveryNum(res.getInt("delivery_num"));
	                	credit.setCredit(res.getInt("credit")); 	                	
	                }
	    	    }catch(Exception e){
	    	    	e.printStackTrace();
	    	    }finally {
	    			super.closeConnect();
	    		}
		  }
		  
		  return credit;
	}
}
