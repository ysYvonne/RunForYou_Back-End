package Bean;


public class UserBean implements IEntity {
    private int user_id;//�û�id
    private String name;//��ʵ����
    private int sex;//��������1Ϊ�У�2ΪŮ��0Ϊδ����
    private int age;//���䣬��-1Ϊδ����
    private String nickname;//�ǳ�
    private String phoneNum;//�绰
    private String email;//����
    private String school;//ѧУ
    private String password;//����

    public UserBean(){
        sex = 0;
        age = -1;
        password = "δ����";
    }
    public void setUserId(int userId){
        user_id = userId;
    }
    public void setName(String name1){
        name = name1;
        nickname = name.substring(0,1) + "ͬѧ";
    }
    public void setSex(int sex1){
        sex = sex1;
    }
    public void setAge(int age1){
        age = age1;
    }
    public void setNickname(String nickname1){
        nickname = nickname1;
    }
    public void setPhoneNum(String phone){
        phoneNum = phone;
    }
    public void setEmail(String email1){
        email = email1;
    }
    public void setSchool(String school1){
        school = school1;
    }
    public void setPassword(String password1){
        password = password1;
    }

    public int getUserId(){
        return user_id;
    }
    public String getName(){
        return name;
    }
    public int getSex(){
        return sex ;
    }
    public int getAge( ){
        return age;
    }
    public String getNickname( ){
        return nickname ;
    }
    public String getPhoneNum(){
        return phoneNum ;
    }
    public String getEmail(){
        return email;
    }
    public String getSchool(){
        return school;
    }
    public String getPassword(){
        return password ;
    }


}
