package db;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import Bean.*;

public class UserDAO extends DBManager implements IDAO{
	
	private int recordNum=0;
    
    public UserDAO(){
        
    }
    
    public boolean AddEntity(IEntity entity) {
    	super.openConnect();
        boolean succ=false;
        UserBean user=(UserBean)entity;

        // »ñÈ¡Sql²éÑ¯Óï¾ä
        try{
            String sqlGetNum="select COUNT(*) from Users;";
            ResultSet res;
            res = sta.executeQuery(sqlGetNum);
            if(res.next()){
                this.recordNum=res.getInt(1)-1;
            }else
                this.recordNum = 0;
            String sqlInsert = "insert into Users values(?,?,?,?,?,?,?,?,?);";
            preStmt=(PreparedStatement) conn.prepareStatement(sqlInsert);
            preStmt.setInt(1, recordNum+1);
            preStmt.setString(2, user.getName());
            preStmt.setInt(3,user.getSex());
            preStmt.setInt(4,user.getAge());
            preStmt.setString(5,user.getNickname());
            preStmt.setString(6,user.getPhoneNum());
            preStmt.setString(7,user.getEmail());
            preStmt.setString(8,user.getSchool());
            preStmt.setString(9,user.getPassword());
            //String Sql = "insert into Credit values(1,2,3,4,5)";
            
            if (preStmt.executeUpdate()!=0) {
                succ = true;
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
			super.closeConnect();
		}
            
        return succ;        
    }

    public boolean UpdateEntity(int userId,String column,String value){
    	super.openConnect();
    	boolean succ = false;
    	
    	if(userId!=0&&column!=null&&value!=null){
    		int value1;
    		String sqlUpdate;
    		if(column.equals("sex")||column.equals("age")){
    			value1 = Integer.parseInt(value);
    			sqlUpdate = "update Users set "+column+"="+value1+" where user_id="+userId+";";
    		}
    		else
    		 sqlUpdate = "update Users set "+column+"='"+value+"' where user_id="+userId+";";
    		
    		try{		 		 			 
					
				if(sta.executeUpdate(sqlUpdate)!=0){
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
    
    public IEntity GetOneEntityPhone(String phone){
    	super.openConnect();
    	  	
    	UserBean user = null;
    	
    	if(phone!=null){
    		String sqlQuery ="select * from Users where phoneNum=?;";
    		try{
    			preStmt=(PreparedStatement) conn.prepareStatement(sqlQuery);
    			preStmt.setString(1, phone);
                ResultSet res;
                res = preStmt.executeQuery();
                
                if(res.next()){
                	user = new UserBean();
                	user.setUserId(res.getInt("user_id"));               	
                	user.setName(res.getString("name"));
                	user.setSex(res.getInt("sex"));
                	user.setAge(res.getInt("age"));
                	user.setNickname(res.getString("nickname"));
                	user.setPhoneNum(phone);		
                	user.setEmail(res.getString("email"));
                	user.setSchool(res.getString("school"));
                	user.setPassword(res.getString("password"));
                }
                
    	    }catch(Exception e){
    	    	e.printStackTrace();
    	    }finally {
    			super.closeConnect();
    		}
    	}
    		return user;
    	  		
		  	
    }
    
    public IEntity GetOneEntityEmail(String email){
    	super.openConnect();
	  	
    	UserBean user = null;
    	
    	if(email!=null){
    		String sqlQuery ="select * from Users where email = ?;";
    		try{
    			preStmt=(PreparedStatement) conn.prepareStatement(sqlQuery);
    			preStmt.setString(1, email);
                ResultSet res;
               
                res = preStmt.executeQuery();
                if(res.next()){
                	user = new UserBean();
                	user.setUserId(res.getInt("user_id"));               	
                	user.setName(res.getString("name"));
                	user.setSex(res.getInt("sex"));
                	user.setAge(res.getInt("age"));
                	user.setNickname(res.getString("nickname"));              	
                	user.setPhoneNum(res.getString("phoneNum"));		
                	user.setEmail(res.getString("email"));
                	user.setSchool(res.getString("school"));
                	user.setPassword(res.getString("password"));
                }
    	    }catch(Exception e){
    	    	e.printStackTrace();
    	    }finally {
    			super.closeConnect();
    		}
    	}
  	
    	return user;
	
    }

    public IEntity GetOneEntityId(int userId){
    	super.openConnect();
    	UserBean user = null;
    	
    	if(userId>0){
    		String sqlQuery ="select * from Users where user_id=?;";
    		try{
    			preStmt=(PreparedStatement) conn.prepareStatement(sqlQuery);
    			preStmt.setInt(1, userId);
                ResultSet res;
                res = preStmt.executeQuery();
                if(res.next()){
                	user = new UserBean();
                	user.setUserId(res.getInt("user_id"));               	
                	user.setName(res.getString("name"));
                	user.setSex(res.getInt("sex"));
                	user.setAge(res.getInt("age"));
                	user.setNickname(res.getString("nickname"));              	
                	user.setPhoneNum(res.getString("phoneNum"));		
                	user.setEmail(res.getString("email"));
                	user.setSchool(res.getString("school"));
                	user.setPassword(res.getString("password"));
                }
    	    }catch(Exception e){
    	    	e.printStackTrace();
    	    }finally {
    			super.closeConnect();
    		}
    	}

    	return user;	
    }
}
