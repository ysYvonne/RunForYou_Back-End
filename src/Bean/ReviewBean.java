package Bean;

public class ReviewBean implements IEntity{
	    private int review_id;//����id
	    private int order_id;//����id
	    private int review_type;//�������ͣ�1Ϊ������2Ϊ������3Ϊ����
	    private String review_time;//����ʱ��

	    public void setReviewId(int Rid){
	        review_id = Rid;
	    }
	    public void setOrderId(int Oid){
	        order_id = Oid;
	    }
	    public void setReviewType(int Rtype){
	        review_type = Rtype;
	    }
	    public void setReviewTime(String Rtime){
	        review_time = Rtime;
	    }

	    public int getReviewId(){
	        return review_id;
	    }
	    public int getOrderId(){
	        return order_id;
	    }
	    public int getReviewType(){
	        return review_type;
	    }
	    public String getReviewTime(){
	        return review_time;
	    }
}
