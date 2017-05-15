package db;
import java.sql.ResultSet;

import Bean.*;

public class UserDAO implements IDAO{
	
	private DBManager sql;
    private int recordNum=0;
    private int pageNum=0;
    

    public UserDAO(){
        sql = new DBManager();
        sql.openConnect();
    }
    
    public boolean AddEntity(IEntity entity) {

        boolean succ=true;
        UserBean user=(UserBean)entity;

        // 获取Sql查询语句
        try{
            String sqlGetNum="select COUNT(user_id) from User";
            ResultSet res;
            res = sql.executeQuery(sqlGetNum);
            if(res.next()){
                this.recordNum=res.getInt(1);
            }else
                this.recordNum = 0;
            String Sql = "insert into User values("+(recordNum+1)+
                                                       ",'"+ user.getName()+
                                                       "',"+user.getSex()+
                                                       ","+user.getAge()+
                                                       ",'"+user.getNickname()+
                                                       "','"+user.getPhoneNum()+
                                                       "','"+user.getEmail()+
                                                       "','"+user.getSchool()+
                                                       "','"+user.getPassword()+"') ";
            //String Sql = "insert into Credit values(1,2,3,4,5)";
            // 操作DB对象
            int rs = sql.executeUpdate(Sql);
            if (rs != 0) {
                sql.closeConnect();
                succ = true;
                //return true;
            }
            sql.closeConnect();
            succ = false;
            //return false;

        }catch (Exception e) {
            succ=false;
            //e.printStackTrace();
            //return false;
        }
       // return false;
        return succ;
    }

    public IEntity GetOneEntityPhone(String phone){
    	  	
    	UserBean user = null;
    	
    	if(phone!=null){
    		String sqlQuery ="select * from User where phoneNum="+phone;
    		try{
                ResultSet res;
                res = sql.executeQuery(sqlQuery);
                if(res.next()){
                	user = new UserBean();
                	int userId = Integer.parseInt(res.getString("user_Id"));
                	user.setUserId(userId);               	
                	user.setName(res.getString("name"));
                	int sex =  Integer.parseInt(res.getString("sex"));
                	user.setSex(sex);
                	int age = Integer.parseInt(res.getString("age"));
                	user.setAge(age);
                	user.setNickname(res.getString("nickname"));
                	user.setPhoneNum(phone);		
                	user.setEmail(res.getString("email"));
                	user.setSchool(res.getString("school"));
                	user.setPassword(res.getString("password"));
                }
                sql.closeConnect();
                return user;
    	    }catch(Exception e){
    	    	return null;
    	    }
    	}else{
    		sql.closeConnect();
    		return null;
    	}   		
		  	
    }
    
    public IEntity GetOneEntityEmail(String email){
	  	
    	UserBean user = null;
    	
    	if(email!=null){
    		String sqlQuery ="select * from User where email="+email;
    		try{
                ResultSet res;
                res = sql.executeQuery(sqlQuery);
                if(res.next()){
                	user = new UserBean();
                	int userId = Integer.parseInt(res.getString("user_Id"));
                	user.setUserId(userId);               	
                	user.setName(res.getString("name"));
                	int sex =  Integer.parseInt(res.getString("sex"));
                	user.setSex(sex);
                	int age = Integer.parseInt(res.getString("age"));
                	user.setAge(age);
                	user.setNickname(res.getString("nickname"));              	
                	user.setPhoneNum(res.getString("phoneNum"));		
                	user.setEmail(res.getString("email"));
                	user.setSchool(res.getString("school"));
                	user.setPassword(res.getString("password"));
                }
                sql.closeConnect();
    	    }catch(Exception e){
    	    }
    	}
    	sql.closeConnect();
    	
    	return user;
	
    }

}
