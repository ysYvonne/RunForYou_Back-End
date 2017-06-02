package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import Bean.*;
import Service.OrderService;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final int SUCCESS = 1;// 成功
	private static final int ERROR = 0;// 密码错误
	private static final int CONFILICT = -1;// 账号冲突或不存在

	private JSONObject jsonObject;
	private OrderService orderService;
	private ArrayList<LittleOrderBean> orderList;
	private JSONObject jsonReply;// 封装服务器返回的JSON对象

	public OrderServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json");
		StringBuffer sb = new StringBuffer("");
		String result = "";

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			br.close();
			result = sb.toString();
			// 打印android端上传的JSON数据
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonReply = new JSONObject();
		orderService = new OrderService(); // 新建Service实例

		jsonObject = JSONObject.fromObject(result);
		String type = jsonObject.getString("type");
		PrintWriter pw = response.getWriter();

		if (type.equals("loadOrders")) {
			loadOrders();
		} else if (type.equals("loadMoreOrders")) {
			loadMoreOrders();
		} else if (type.equals("loadMyOrders")) {
			loadMyOrders();
		} else if (type.equals("loadMoreMyOrders")) {
			loadMoreMyOrders();
		} else if (type.equals("loadMyDeliveryOrders")) {
			loadMyDeliveryOrders();
		} else if (type.equals("loadMoreMyDeliveryOrders")) {
			loadMoreMyDeliveryOrders();
		} else if (type.equals("OrderPublish")) {// 邮件登录
			OrderPublish();
		} else if (type.equals("OrderInfo")) {
			OrderInfo();
		} else if (type.equals("OrderReceive")) {
			OrderReceive();
		} else if (type.equals("OrderUpdate")) {
			OrderUpdate();
		} else if (type.equals("OrderDrawback")) {
			OrderDrawback();
		} else if (type.equals("OrderFinish")) {
			OrderFinish();
		} else if (type.equals("OrderJudge")) {
			OrderJudge();
		}

		System.out.println(jsonReply);
		pw.write(jsonReply.toString());
		pw.flush();
		pw.close();
		jsonObject.clear();
		jsonReply.clear();
		orderList.clear();
	}

	private void loadOrders() {
		orderList = orderService.loadOrders();
		jsonReply.put("code", SUCCESS);
		jsonReply.put("orderList", orderList);
	}

	private void loadMoreOrders() {
		int index = Integer.parseInt(jsonObject.getString("order_id"));

		orderList = orderService.loadMoreOrders(index);
		jsonReply.put("code", SUCCESS);
		jsonReply.put("orderList", orderList);
	}

	private void loadMyOrders() {
		int user_id = Integer.parseInt(jsonObject.getString("user_id"));

		orderList = orderService.loadMyOrders(user_id);
		jsonReply.put("code", SUCCESS);
		jsonReply.put("orderList", orderList);
	}

	private void loadMoreMyOrders() {
		int user_id = Integer.parseInt(jsonObject.getString("user_id"));
		int index = Integer.parseInt(jsonObject.getString("order_id"));

		orderList = orderService.loadMoreMyOrders(index, user_id);
		jsonReply.put("code", SUCCESS);
		jsonReply.put("orderList", orderList);
	}

	private void loadMyDeliveryOrders() {
		int user_id = Integer.parseInt(jsonObject.getString("user_id"));

		orderList = orderService.loadMyDeliveryOrders(user_id);
		jsonReply.put("code", SUCCESS);
		jsonReply.put("orderList", orderList);
	}

	private void loadMoreMyDeliveryOrders() {
		int user_id = Integer.parseInt(jsonObject.getString("user_id"));
		int index = Integer.parseInt(jsonObject.getString("order_id"));

		orderList = orderService.loadMoreMyDeliveryOrders(index, user_id);
		jsonReply.put("code", SUCCESS);
		jsonReply.put("orderList", orderList);
	}

	public void OrderPublish() {
		OrdersBean order = new OrdersBean();

		order.setContactName(jsonObject.getString("name"));
		order.setContactPhone(jsonObject.getString("phone"));
		order.setOrderItem(jsonObject.getString("short"));
		order.setOrderDescribe(jsonObject.getString("long"));
		order.setOrderDestination(jsonObject.getString("shop"));
		order.setOrderAddress(jsonObject.getString("des"));
		order.setOrderTime(jsonObject.getString("time"));
		order.setOrderPredict((float) jsonObject.get("money"));
		order.setOrderReward((float) jsonObject.get("deliver"));
		order.setOrderType(jsonObject.getInt("deliver_method"));

		int userId = jsonObject.getInt("user_id");

		boolean succ = orderService.placeOrder(userId, order);

		if (succ)
			jsonReply.put("code", SUCCESS);
		else
			jsonReply.put("code", ERROR);

	}

	public void OrderInfo() {
		int orderId = jsonObject.getInt("order_id");

		OrdersBean order = orderService.getOrder(orderId);
		OrderStateBean orderState = orderService.getOrderState(orderId);
		if (orderState.getState() == 5) {
			int review = orderService.getReview(orderId);
			jsonReply.put("review", review);
		}
		jsonReply.put("order", order);
		jsonReply.put("orderState", orderState);
	}

	public void OrderReceive() {
		int orderId = jsonObject.getInt("order_id");
		int userId = jsonObject.getInt("user_id");
		boolean succ = orderService.acceptOrder(userId, orderId);

		if (succ)
			jsonReply.put("code", SUCCESS);
		else
			jsonReply.put("code", ERROR);
	}

	public void OrderUpdate() {
		int orderId = jsonObject.getInt("order_id");
		int state = jsonObject.getInt("state");

		boolean succ = orderService.updateState(orderId, state);

		if (succ)
			jsonReply.put("code", SUCCESS);
		else
			jsonReply.put("code", ERROR);
	}

	public void OrderDrawback() {
		int orderId = jsonObject.getInt("order_id");

		boolean succ = orderService.updateState(orderId, -1);

		if (succ)
			jsonReply.put("code", SUCCESS);
		else
			jsonReply.put("code", ERROR);
	}

	public void OrderFinish() {
		int orderId = jsonObject.getInt("order_id");

		boolean succ = orderService.updateState(orderId, 4);

		if (succ)
			jsonReply.put("code", SUCCESS);
		else
			jsonReply.put("code", ERROR);
	}

	public void OrderJudge() {
		int orderId = jsonObject.getInt("order_id");
		int review = jsonObject.getInt("review");

		boolean succ = orderService.placeReview(orderId, review);

		if (succ)
			jsonReply.put("code", SUCCESS);
		else
			jsonReply.put("code", ERROR);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
