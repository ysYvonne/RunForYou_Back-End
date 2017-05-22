package Service;


import java.util.*;
import Bean.*;
import db.*;

public class InformationService {
	private UserDAO userDao;
	public InformationService(){
		userDao = new UserDAO();
	}
	public boolean updateInformation(int userId,String column, String value){
		
		boolean succ = userDao.UpdateEntity(userId, column, value);
		
		return succ;
	}
	public UserBean getUser(int userId){
		return (UserBean)userDao.GetOneEntityId(userId);
	}
	public boolean placeReview(ReviewBean review){
		boolean succ = false;
		ReviewDAO reviewDao = new ReviewDAO();
		succ = reviewDao.AddEntity(review);
		return succ;
	}
}
