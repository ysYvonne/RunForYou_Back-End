package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;


public class Test {

	public static void main(String[] args) {
		
		 String myUrl="http://localhost:8080/RunForYou/LoginServlet";
	        HttpPost request = new HttpPost(myUrl);
	        JSONObject parameter=new JSONObject();
	        try {
//	            parameter.put("username", username.getText());
//	            parameter.put("password", password.getText());
	            parameter.put("type", "emailLogin");
	            parameter.put("email", "1_i3456@bjtu.edu.cn");
	            parameter.put("password", "123456");
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	// �ȷ�װһ�� JSON ����
	// �󶨵����� Entry
	        	StringEntity se = null;
	        	try {
	            se = new StringEntity(parameter.toString());
	            request.setEntity(se);
	// ��������
	            HttpResponse httpResponse = new DefaultHttpClient().execute(request);
	// �õ�Ӧ����ַ�������Ҳ��һ�� JSON ��ʽ���������
	            String retSrc = EntityUtils.toString(httpResponse.getEntity());
	// ���� JSON ����
	            System.out.println(retSrc);
//	            JSONObject result = new JSONObject(retSrc);
//	            String user = (String) result.get("user");
//	           System.out.println(user);
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
//	        	catch (JSONException e) {
//	            e.printStackTrace();
//	        }

	        
	}

}
