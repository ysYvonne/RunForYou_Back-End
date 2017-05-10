package db;
import java.sql.ResultSet;

import Bean.*;

public class UserDAO {
	
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
            String Sql1 = "insert into User values("+(recordNum+1)+
                                                       ",'"+ user.getName()+
                                                       "',"+user.getSex()+
                                                       ","+user.getAge()+
                                                       ",'"+user.getNickname()+
                                                       "','"+user.getPhoneNum()+
                                                       "','"+user.getEmail()+
                                                       "','"+user.getSchool()+
                                                       "','"+user.getPassword()+"') ";
            String Sql = "insert into Credit values(1,2,3,4,5)";
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
}
