package Service;


import java.text.SimpleDateFormat;
import java.util.*;
import Bean.*;
import db.*;

public class InformationService {
	private UserDAO userDao;
	public InformationService(){
		
	}
	public boolean updateInformation(int userId,String column, String value){
		userDao = new UserDAO();
		boolean succ = userDao.UpdateEntity(userId, column, value);
		
		return succ;
	}
	public UserBean getUser(int userId){
		userDao = new UserDAO();
		return (UserBean)userDao.GetOneEntityId(userId);
	}
	public boolean placeOpinion(int userId, String content){
		boolean succ = false;
		OpinionBean opinion  = new OpinionBean();
		opinion.setUserId(userId);
		opinion.setContent(content);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		opinion.setOpinionTime(df.format(new Date()));
		OpinionDAO opinionDao = new OpinionDAO();
		succ = opinionDao.AddEntity(opinion);
		return succ;
	}
}
