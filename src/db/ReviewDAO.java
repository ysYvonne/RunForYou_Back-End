package db;

import Bean.*;

public class ReviewDAO implements IDAO {

	private DBManager sql;
    private int recordNum=0;
    private int pageNum=0;
    
	public ReviewDAO() {
		// TODO Auto-generated constructor stub
        sql = new DBManager();
        sql.openConnect();
	}

	public boolean AddEntity(IEntity entity) {
		// TODO Auto-generated method stub
        boolean succ=true;
        ReviewBean review = (ReviewBean)entity;    
        
        try{
        	String sqlInsert = "insert into Order_Review(order_id,review_type,review_time) values("
                                                           + review.getOrderId()+","
        			                                       + review.getReviewType()+","
        			                                       + review.getReviewTime()+");";
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
