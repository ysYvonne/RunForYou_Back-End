package Bean;

public class OpinionBean implements IEntity{
	private int opinion_id;//���id
    private int user_id;//�û�id
    private String content;//�������
    private String opinion_time;//����ʱ��

    public void setOpinionId(int opinionId){
        opinion_id = opinionId;
    }
    public void setUserId(int userId) {
        user_id = userId;
    }
    public void setContent(String con) {
        content = con;
    }
    public void setOpinionTime(String time) {
        opinion_time = time;
    }

    public int getOpinionId() {
        return opinion_id;
    }
    public int getUserId() {
        return user_id;
    }
    public String getContent() {
        return content;
    }
    public String getOpinionTime() {
        return opinion_time;
    }
}
