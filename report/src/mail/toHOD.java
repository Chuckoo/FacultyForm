package mail;

import com.sql.jsp.User;

public class toHOD {

	//describe what is to be sent to HOD in mail
	//different mails maybe be specified for different situations
	
	public static void submission(String ID) {
		String to = User.getHOD(ID);
		String sub ="notification";
		String msg ="the user "+ID+" submitted the from \n http://localhost:8080/report/login.jsp";
		mailConfig.sendMail(to,sub,msg);
	}
}
