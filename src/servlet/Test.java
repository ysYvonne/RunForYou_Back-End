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
	// 先封装一个 JSON 对象
	// 绑定到请求 Entry
	        	StringEntity se = null;
	        	try {
	            se = new StringEntity(parameter.toString());
	            request.setEntity(se);
	// 发送请求
	            HttpResponse httpResponse = new DefaultHttpClient().execute(request);
	// 得到应答的字符串，这也是一个 JSON 格式保存的数据
	            String retSrc = EntityUtils.toString(httpResponse.getEntity());
	// 生成 JSON 对象
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
