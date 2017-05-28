package db;

import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import Bean.*;

public class ReviewDAO extends DBManager implements IDAO {

    
	public ReviewDAO() {
		// TODO Auto-generated constructor stub
       
	}

	public boolean AddEntity(IEntity entity) {
		 super.openConnect();
		// TODO Auto-generated method stub
        boolean succ=false;
        ReviewBean review = (ReviewBean)entity;    
        String sqlInsert = "insert into Order_Review values(null,?,?,?)";
        try{
        	preStmt=(PreparedStatement) conn.prepareStatement(sqlInsert);
        	preStmt.setInt(1, review.getOrderId());
        	preStmt.setInt(2, review.getReviewType());
        	preStmt.setString(3, review.getReviewTime());                                       

        	if(preStmt.executeUpdate()!=0){
        		succ = true;
        	}

        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	super.closeConnect();
        }
		return succ;
	}

	public IEntity GetOneEntity(int orderId){
		 super.openConnect();
		ReviewBean review = null;
		
		if(orderId>0){
			String sqlQuery = "Select * form Order_Review where order_id = ?";
			try{
				preStmt=(PreparedStatement) conn.prepareStatement(sqlQuery);
    			preStmt.setInt(1, orderId);
                ResultSet res;;
                res = preStmt.executeQuery();
                if(res.next()){
                	review = new ReviewBean();
                	review.setOrderId(res.getInt("order_id"));
                	review.setReviewTime(res.getString("review_time"));
                	review.setReviewType(res.getInt("review_type"));
                }
			}catch(Exception e){
				e.printStackTrace();
			}finally {
    			super.closeConnect();
    		}
		}
		return review;
		
	}
}
