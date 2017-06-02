package Service;


import java.text.SimpleDateFormat;
import java.util.*;
import Bean.*;
import db.*;

public class InformationService {
	private UserDAO userDao;
	private OpinionDAO opinionDao ;
	public InformationService(){
		opinionDao = new OpinionDAO();
		userDao = new UserDAO();
	}
	public boolean updateInformation(int userId,String column, String value){
		
		boolean succ = userDao.UpdateEntity(userId, column, value);
		
		return succ;
	}
	public UserBean getUser(int userId){

		return (UserBean)userDao.GetOneEntityId(userId);
	}
	public boolean placeOpinion(int userId, String content){
		boolean succ = false;
		
		OpinionBean opinion  = new OpinionBean();
		opinion.setUserId(userId);
		opinion.setContent(content);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		opinion.setOpinionTime(df.format(new Date()));
		
		succ = opinionDao.AddEntity(opinion);
		return succ;
	}
	
	public CreditBean getCredit(int userId){
		CreditDAO creditDao = new CreditDAO();
		CreditBean credit = (CreditBean) creditDao.GetOneEntityId(userId);
		
		return credit;
	}
}
