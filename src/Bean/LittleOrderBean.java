package Bean;

public class LittleOrderBean implements IEntity{
	private int order_id;// ����id
	private String order_item;// ��Ʒ���ͣ���ƣ�
	private int order_type;//��������
	private float order_reward;// ���ͼ�ֵ������Ϊ���ֻ��Ǯ��
	private String order_address;// �ͻ���ַ
	private String order_shop;//ȡ����ַ
	private String start_time;// ����ʱ��
	private int state;//����״̬��0δ�ӵ���1�ѽӵ���2�������ͣ�3����ص㣬4������ɣ�5������ɡ�-1����ȡ����

	public void setOrderId(int orderId) {
		order_id = orderId;
	}

	public void setOrderAddress(String address) {
		order_address = address;
	}

	public void setOrderReward(float reward) {
		order_reward = reward;
	}

	public void setOrderItem(String item) {
		order_item = item;
	}
    public void setState(int state1){
        state = state1;
    }
    public void setStartTime(String startTime){
        start_time = startTime;
    }
    public void setType(int Type){
        order_type = Type;
    }
    public void setShop(String Shop){
        order_shop = Shop;
    }
    
	public int getOrderId() {
		return order_id;
	}

	public String getOrderAddress() {
		return order_address;
	}

	public float getOrderReward() {
		return order_reward;
	}

	public String getOrderItem() {
		return order_item;
	}
	public int getState( ){
        return state ;
    }
    public String getStartTime(){
        return start_time;
    }
    public String getShop( ){
        return order_shop ;
    }
    public int getType(){
        return order_type;
    }

}
