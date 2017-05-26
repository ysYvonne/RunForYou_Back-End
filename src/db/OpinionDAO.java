package db;

import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import Bean.*;

public class OpinionDAO extends DBManager implements IDAO {

    
	public OpinionDAO() {
		// TODO Auto-generated constructor stub
        super.openConnect();
	}

	public boolean AddEntity(IEntity entity) {
		// TODO Auto-generated method stub
        boolean succ=false;
        OpinionBean opinion = (OpinionBean)entity;
        
        try{
        	String sqlInsert = "insert into Opinion values(null,?,?,?);";
        	preStmt = (PreparedStatement) conn.prepareStatement(sqlInsert);
        	preStmt.setInt(1, opinion.getUserId());
        	preStmt.setString(2, opinion.getOpinionTime());
        	preStmt.setString(3, opinion.getContent());

        	if(preStmt.executeUpdate()!=0){
        		succ = true;
        	}        	
        }catch(Exception e){
        	succ = false;
        }finally{
        	super.closeConnect();
        }
		return succ;
	}

	public IEntity GetOneEntity(int userId){
		OpinionBean opinion = null;
		
		if(userId>0){
			String sqlQuery = "Select * form Opinion where user_id = ?";
			try{
				preStmt=(PreparedStatement) conn.prepareStatement(sqlQuery);
    			preStmt.setInt(1, userId);
                ResultSet res;;
                res = preStmt.executeQuery();
                if(res.next()){
                	opinion = new OpinionBean();
                	opinion.setUserId(res.getInt("user_id"));
                	opinion.setOpinionTime(res.getString("opinion_time"));
                	opinion.setContent(res.getString("content"));
                }
			}catch(Exception e){
				e.printStackTrace();
			}finally {
    			super.closeConnect();
    		}
		}
		return opinion;
		
	}
}
