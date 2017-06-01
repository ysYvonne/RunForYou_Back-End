package Service;
import db.*;
import Bean.*;


public class LogService {
	
	private UserDAO userdao;
	public  LogService(){
		userdao = new UserDAO();
	}
	public UserBean AccountLogin(String email,String password){
		UserBean user;
		user = (UserBean)userdao.GetOneEntityEmail(email);//取出user类
			
		if(user == null){
			return null;
		}
		else{
			if(user.getPassword().equals(password))
				return user;
		}
		return null;
	}
	
	public UserBean PhoneExist(String phoneNum){

		UserBean user = (UserBean)userdao.GetOneEntityPhone(phoneNum);
		
		if(user == null){
			return null;
		}else{
			return user;
		}
	}
	
	public UserBean EmailExist(String email){

		UserBean user = (UserBean)userdao.GetOneEntityEmail(email);
		
		if(user == null){
			return null;
		}else{
			return user;
		}
	}	
	
	public UserBean Register(UserBean user){
		
		boolean succ1 = false;
		boolean succ2 = false;
		

		succ1 = userdao.AddEntity(user);
		System.out.println("插入"+succ1);
		CreditBean credit = new CreditBean();
		credit.setCredit(25);
		credit.setDeliveryNum(0);
		credit.setOrderNum(0);

		credit.setUserId(((UserBean)userdao.GetOneEntityEmail(user.getEmail())).getUserId());
		
		CreditDAO creditDao = new CreditDAO();
		succ2 = creditDao.AddEntity(credit);
		
		if(succ1&&succ2){

			return (UserBean)userdao.GetOneEntityEmail(user.getEmail());
		}else
			return null;
		
	}
}
