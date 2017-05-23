package db;

import com.mysql.jdbc.PreparedStatement;

import Bean.*;

public class OpinionDAO extends DBManager implements IDAO {

	//private DBManager sql;
    private int recordNum=0;
    private int pageNum=0;
    
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

        	if(preStmt.executeUpdate(sqlInsert)!=0){
        		succ = true;
        	}
        	succ = false;
        }catch(Exception e){
        	succ = false;
        }finally{
        	super.closeConnect();
        }
		return succ;
	}

}
