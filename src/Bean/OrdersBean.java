package Bean;

public class OrdersBean implements IEntity{
	    private int order_id;//����id
	    private int order_type;//�������ͣ���1Ϊ���֣�2Ϊ��Ǯ
	    private String order_address;//�ͻ���ַ
	    private String order_destination;//ȡ����ַ
	    private float order_reward;//���ͼ�ֵ������Ϊ���ֻ��Ǯ��
	    private float order_predict;//Ԥ����Ʒ���
	    private String order_item;//��Ʒ���ͣ���ƣ�
	    private String order_time;//��������ʱ��
	    private String order_describe;//��������

	    public void setOrderId(int orderId){
	        order_id = orderId;
	    }
	    public void setOrderType(int type){
	        order_type = type;
	    }
	    public void setOrderAddress(String address){
	        order_address = address;
	    }
	    public void setOrderDestination(String destination){
	        order_destination = destination;
	    }
	    public void setOrderReward(float reward){
	        order_reward = reward;
	    }
	    public void setOrderPredict(float predict){
	        order_predict = predict;
	    }
	    public void setOrderItem(String item){
	        order_item = item;
	    }
	    public void setOrderTime(String time){
	        order_time = time;
	    }
	    public void setOrderDescribe(String describe){
	        order_describe = describe;
	    }

	    public int getOrderId(){
	       return order_id;
	    }
	    public int getOrderType(){
	        return order_type;
	    }
	    public String getOrderAddress( ){
	        return order_address;
	    }
	    public String getOrderDestination(){
	        return order_destination;
	    }
	    public float getOrderReward(){
	        return order_reward;
	    }
	    public float getOrderPredict(){
	        return order_predict;
	    }
	    public String getOrderItem(){
	        return order_item;
	    }
	    public String getOrderTime() {
	        return order_time;
	    }
	    public String getOrderDescribe() {
	        return order_describe;
	    }
}
