package Bean;

public class CreditBean implements IEntity{
	private int credit_id;//����id
    private int user_id;//�û�id
    private int order_num;//������������
    private int delivery_num;//���ܶ�������
    private int credit;//�û�������

    public void setCreditId(int Cid){
        credit_id = Cid;
    }
    public void setUserId(int userId){
        user_id = userId;
    }
    public void setOrderNum(int Onum){
        order_num = Onum;
    }
    public void setDeliveryNum(int deliveryNum){
        delivery_num = deliveryNum;
    }
    public void setCredit(int c){
        credit = c;
    }

    public int getCreditId(){
        return credit_id;
    }
    public int getUserId(){
        return  user_id;
    }
    public int getOrderNum(){
        return order_num;
    }
    public int getDeliveryNum(){
        return delivery_num;
    }
    public int getCredit(){
        return credit;
    }
}
