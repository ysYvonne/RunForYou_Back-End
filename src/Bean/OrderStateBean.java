package Bean;

public class OrderStateBean implements IEntity{
	  private int order_state_id;//����״̬id
	    private int order_id;//�������
	    private int client_id;//�µ���id
	    private int delivery_id;//������id
	    private int state;//����״̬��0δ�ӵ���1�ѽӵ���2�������ͣ�3����ص㣬4������ɣ�5������ɡ�-1����ȡ����
	    private String start_time;//����ʱ��
	    private String get_time;//�õ���Ʒ����������ʱ��
	    private String arrive_time;//����ʱ��
	    private String over_time;//��������ʱ��

	    public void setOrderStateId(int orderStateId){
	        order_state_id = orderStateId;
	    }
	    public void setOrderId(int orderId){
	        order_id = orderId;
	    }
	    public void setClientId(int clientId){
	        client_id = clientId;
	    }
	    public void setDeliveryId(int deliveryId){
	        delivery_id = deliveryId;
	    }
	    public void setState(int state1){
	        state = state1;
	    }
	    public void setOverTime(String overTime){
	        over_time = overTime;
	    }
	    public void setStartTime(String startTime){
	        start_time = startTime;
	    }
	    public void setArriveTime(String arriveTime){
	        arrive_time = arriveTime;
	    }
	    public void setGetTime(String getTime){
	        get_time = getTime;
	    }

	    public int getOrderStateId(){
	        return order_state_id;
	    }
	    public int getOrderId( ){
	        return order_id  ;
	    }
	    public int getClientId( ){
	        return client_id  ;
	    }
	    public int getDeliveryId( ){
	        return delivery_id  ;
	    }
	    public int getState( ){
	        return state ;
	    }
	    public String getOverTime(){
	        return over_time;
	    }
	    public String getStartTime(){
	        return start_time;
	    }
	    public String getArriveTime(){
	        return arrive_time ;
	    }
	    public String getGetTime(){
	        return get_time ;
	    }
}
