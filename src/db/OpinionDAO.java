package db;

import Bean.*;

public class OpinionDAO implements IDAO {

	private DBManager sql;
    private int recordNum=0;
    private int pageNum=0;
    
	public OpinionDAO() {
		// TODO Auto-generated constructor stub
        sql = new DBManager();
        sql.openConnect();
	}

	public boolean AddEntity(IEntity entity) {
		// TODO Auto-generated method stub
        boolean succ=true;
        OpinionBean opinion = (OpinionBean)entity;
        
        try{
        	String sqlInsert = "insert into Opinion(user_id,opinion_time,content) values("
                                                            +opinion.getUserId()+","
        			                                        +opinion.getOpinionTime()+","
        			                                        +opinion.getContent()+")";
        	
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
