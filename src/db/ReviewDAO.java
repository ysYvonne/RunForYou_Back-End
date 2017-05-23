package db;

import com.mysql.jdbc.PreparedStatement;

import Bean.*;

public class ReviewDAO extends DBManager implements IDAO {

	//private DBManager sql;
    private int recordNum=0;
    private int pageNum=0;
    
	public ReviewDAO() {
		// TODO Auto-generated constructor stub
        super.openConnect();
	}

	public boolean AddEntity(IEntity entity) {
		// TODO Auto-generated method stub
        boolean succ=false;
        ReviewBean review = (ReviewBean)entity;    
        String sqlInsert = "insert into Order_Review values(null,?,?,?)";
        try{
        	preStmt=(PreparedStatement) conn.prepareStatement(sqlInsert);
        	preStmt.setInt(1, review.getOrderId());
        	preStmt.setInt(2, review.getReviewType());
        	preStmt.setString(3, review.getReviewTime());                                       

        	if(preStmt.executeUpdate(sqlInsert)!=0){
        		succ = true;
        	}

        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	super.closeConnect();
        }
		return succ;
	}

}
