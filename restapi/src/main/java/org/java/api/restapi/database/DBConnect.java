package org.java.api.restapi.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import org.java.api.restapi.model.AdminModel;
import org.java.api.restapi.model.CustomerModel;
import org.java.api.restapi.model.DailyFeedModel;
import org.java.api.restapi.model.FoodModel;
import org.java.api.restapi.model.LoginAdminModel;
import org.java.api.restapi.model.LoginModel;
import org.java.api.restapi.model.OrderModel;
import org.java.api.restapi.model.Temp;

import com.mysql.jdbc.PreparedStatement;
@XmlRootElement
public class DBConnect {
	private Connection con;
	private Statement stmnt;
	private ResultSet rs;
	private ArrayList<FoodModel> all_foods=new ArrayList<FoodModel>();
	private ArrayList<FoodModel> food=new ArrayList<FoodModel>();
	private ArrayList<CustomerModel> add_customer=new ArrayList<CustomerModel>();
	private ArrayList<CustomerModel> all_user_by_id=new ArrayList<CustomerModel>();
	private ArrayList<LoginModel> user_pass_status=new ArrayList<LoginModel>();
	private ArrayList<LoginAdminModel> admin_id=new ArrayList<LoginAdminModel>();
	private ArrayList<LoginAdminModel> admin_pass=new ArrayList<LoginAdminModel>();
	private ArrayList<AdminModel> admininfo=new ArrayList<AdminModel>();
	private ArrayList<FoodModel> foodModels=new ArrayList<FoodModel>();
	private ArrayList<DailyFeedModel> dailyFeedModels=new ArrayList<DailyFeedModel>();
	private ArrayList<OrderModel> orderModels=new ArrayList<OrderModel>();
	
	public static final String URL = "jdbc:mysql://localhost:3306/saroar";
    public static final String USER = "root";
    public static final String PASSWORD = "";
	
	public DBConnect() {
		// TODO Auto-generated constructor stub
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(URL,USER,PASSWORD);
			stmnt=con.createStatement();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Database connection error");
		}
	}
	public void close(){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<FoodModel> getAll() {
		try{
			String query="SELECT * FROM foods ORDER BY id DESC";
			
			rs=stmnt.executeQuery(query);
			while(rs.next()){
				FoodModel foodModel=new FoodModel();
				foodModel.setFoodID(rs.getInt("foodID"));
				foodModel.setTitle(rs.getString("title"));
				foodModel.setPrice(rs.getDouble("price"));
				foodModel.setQuantity(rs.getInt("quantity"));
				foodModel.setDescription(rs.getString("description"));
				foodModel.setCatagories(rs.getInt("catagories"));
				foodModel.setImage(rs.getBlob("img"));
				all_foods.add(foodModel);
			}
			close();
		}catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			System.out.println("null ex");
		}
		return all_foods;
	}
	public ArrayList<FoodModel> getFoods(String id){
		
		try {
			String query="SELECT * FROM foods WHERE "+id;
			rs=stmnt.executeQuery(query);
			while(rs.next()){
				FoodModel foodModel=new FoodModel();
				if(rs.getInt("foodId") == Integer.parseInt(id)){
					foodModel.setFoodID(rs.getInt("foodID"));
					foodModel.setTitle(rs.getString("title"));
					foodModel.setPrice(rs.getDouble("price"));
					foodModel.setQuantity(rs.getInt("quantity"));
					foodModel.setDescription(rs.getString("description"));
					foodModel.setCatagories(rs.getInt("catagories"));
					foodModel.setImage(rs.getBlob("img"));
					food.add(foodModel);
				}
				
				
			}
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return food;
	}
	
	public ArrayList<CustomerModel> getUser(String id){
		
		try{
			String query="SELECT * FROM customers WHERE "+id;
			rs=stmnt.executeQuery(query);
			while(rs.next()){
				CustomerModel customerModel=new CustomerModel();
				if(rs.getInt("id") == Integer.parseInt(id)){
					customerModel.setName(rs.getString("name"));
					customerModel.setAddress(rs.getString("address"));
					customerModel.setPhone(rs.getInt("phone"));
					customerModel.setId(rs.getInt("id"));
					all_user_by_id.add(customerModel);
				}
				
			}
			close();
		}catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			System.out.println("null ex");
		}
		return all_user_by_id;
		
	}
	public ArrayList<LoginModel> checkUnamePass(String id){
		
		try{
			String query="SELECT * FROM logincus WHERE "+id;
			rs=stmnt.executeQuery(query);
			while(rs.next()){
				LoginModel loginModel=new LoginModel();
				if(rs.getInt("id") == Integer.parseInt(id)){
					loginModel.setUname(rs.getString("uname"));
					loginModel.setPass(rs.getString("pass"));
					loginModel.setId(rs.getInt("id"));
					loginModel.setIdofcus(rs.getInt("idofcus"));
					user_pass_status.add(loginModel);
				}
				
			}
			close();
		}catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			System.out.println("null ex");
		}
		
		return user_pass_status;
	}
	public void addCustomer(CustomerModel customerModel){
		add_customer.add(customerModel);
		String name=add_customer.get(0).getName();
		String address=add_customer.get(0).getAddress();
		int phone=add_customer.get(0).getPhone();
		try{
			String query="INSERT INTO customers(name, address, phone)" + " VALUES (?, ?, ?)";
			java.sql.PreparedStatement preparedStatement=con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, address);
			preparedStatement.setInt(3, phone);
			preparedStatement.executeUpdate();
			
			close();
		}catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			System.out.println("null ex");
		}
	}
	
	public void addFoodInfo(FoodModel foodModel){
		foodModels.add(foodModel);
		int foodId=foodModels.get(0).getFoodID();
		String title=foodModels.get(0).getTitle();
		double price=foodModels.get(0).getPrice();
		int quantity=foodModels.get(0).getQuantity();
		String description=foodModels.get(0).getDescription();
		int catagories=foodModels.get(0).getCatagories();
		try{
			String query="INSERT INTO foods (foodId, title, price, quantity, description, catagories, img)" + " VALUES (?, ?, ?, ?, ?, ?, ?)";
			java.sql.PreparedStatement preparedStatement=con.prepareStatement(query);
			preparedStatement.setInt(1, foodId);
			preparedStatement.setString(2, title);
			preparedStatement.setDouble(3, price);
			preparedStatement.setInt(4, quantity);
			preparedStatement.setString(5, description);
			preparedStatement.setInt(6, catagories);
			preparedStatement.setInt(7, 1);
			
			preparedStatement.executeUpdate();
			
			close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("null ex");
		}
	}
	public void delete_foods(String id){
		String query="DELETE FROM foods WHERE foods.foodId ="+id;
		try{
			java.sql.PreparedStatement preparedStatement=con.prepareStatement(query);
			preparedStatement.execute();
			close();
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("null ex");
		}
	}
	public void update_foods(String id, FoodModel foodModel){
		foodModels.add(foodModel);
		int foodId=foodModels.get(0).getFoodID();
		String title=foodModels.get(0).getTitle();
		double price=foodModels.get(0).getPrice();
		int quantity=foodModels.get(0).getQuantity();
		String description=foodModels.get(0).getDescription();
		int catagories=foodModels.get(0).getCatagories();
		
		String query="UPDATE foods SET  title= ?, price= ?, quantity= ?, description= ?, catagories= ?, img= ? WHERE foodId="+id;
		try{
			java.sql.PreparedStatement preparedStatement=con.prepareStatement(query);
			preparedStatement.setString(1, title);
			preparedStatement.setDouble(2, price);
			preparedStatement.setInt(3, quantity);
			preparedStatement.setString(4, description);
			preparedStatement.setInt(5, catagories);
			preparedStatement.setInt(6, 1);
			preparedStatement.execute();
			close();
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("null ex");
		}
	}
	
	public void update_order(String id, OrderModel orderModel){
		orderModels.add(orderModel);
		String progress=orderModels.get(0).getProgress();
		String query="UPDATE orderlist SET Progress= ? WHERE id="+id;
		try{
			java.sql.PreparedStatement preparedStatement=con.prepareStatement(query);
			preparedStatement.setString(1, progress);
			preparedStatement.execute();
			close();
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("null ex");
		}
	}
	
	public void addOrder(OrderModel model){
		orderModels.add(model);
		int table_no=orderModels.get(0).getTable_no();
		String foods=orderModels.get(0).getFoods();
		double price=orderModels.get(0).getPrice();
		String payments=orderModels.get(0).getPayments();
		String phone=orderModels.get(0).getPhone();
		long millis=System.currentTimeMillis();  
		Date date=new Date(millis);
		Time time=new Time(millis);
		
		
		
		try {
			String query="INSERT INTO orderlist (tableNo, foods, price, payments, Date, Time, Progress, phone)" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			java.sql.PreparedStatement preparedStatement=con.prepareStatement(query);
			preparedStatement.setInt(1, table_no);
			preparedStatement.setString(2, foods);
			preparedStatement.setDouble(3, price);
			preparedStatement.setString(4, payments);
			preparedStatement.setDate(5, date);
			preparedStatement.setTime(6, time);
			preparedStatement.setString(7, "Pending");
			preparedStatement.setString(8, phone);
			
			
			
			preparedStatement.executeUpdate();
			close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("null ex");
		}
		
		
	}
	
	public ArrayList<OrderModel> getOrder(){
		try{
			String query="SELECT * FROM orderlist";
			
			rs=stmnt.executeQuery(query);
			while(rs.next()){
				OrderModel orderModel=new OrderModel();
				orderModel.setId(rs.getInt("id"));
				orderModel.setTable_no(rs.getInt("tableNo"));
				orderModel.setFoods(rs.getString("foods"));
				orderModel.setPrice(rs.getDouble("price"));
				orderModel.setPayments(rs.getString("payments"));
				orderModel.setDate(rs.getDate("Date"));
				orderModel.setProgress(rs.getString("Progress"));
				orderModel.setPhone(rs.getString("phone"));
				orderModels.add(orderModel);
				
			}
			close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("null ex");
		}
		return orderModels;
		
	}
	
	public ArrayList<OrderModel> getCurrentTOrder(){
		long millis=System.currentTimeMillis();  
		Date date=new Date(millis);
		//System.out.println(date.toString().trim().replace("-", ""));
		try{
			String query="SELECT * FROM orderlist WHERE Date="+ date.toString().trim().replace("-", "");
			
			rs=stmnt.executeQuery(query);
			while(rs.next()){
				OrderModel orderModel=new OrderModel();
				orderModel.setId(rs.getInt("id"));
				orderModel.setTable_no(rs.getInt("tableNo"));
				orderModel.setFoods(rs.getString("foods"));
				orderModel.setPrice(rs.getDouble("price"));
				orderModel.setPayments(rs.getString("payments"));
				orderModel.setDate(rs.getDate("Date"));
				orderModel.setProgress(rs.getString("Progress"));
				orderModel.setPhone(rs.getString("phone"));
				orderModels.add(orderModel);
				
			}
			close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("null ex");
		}
		return orderModels;
		
	}
	
	public ArrayList<OrderModel> getCurrentDOrder(){
		try{
			
			String query="SELECT * FROM orderlist ORDER BY Date DESC";
			
			rs=stmnt.executeQuery(query);
			while(rs.next()){
					OrderModel orderModel=new OrderModel();
					orderModel.setId(rs.getInt("id"));
					orderModel.setTable_no(rs.getInt("tableNo"));
					orderModel.setFoods(rs.getString("foods"));
					orderModel.setPrice(rs.getDouble("price"));
					orderModel.setPayments(rs.getString("payments"));
					orderModel.setDate(rs.getDate("Date"));
					orderModel.setProgress(rs.getString("Progress"));
					orderModel.setPhone(rs.getString("phone"));
					orderModels.add(orderModel);
				
				
			}
			close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("null ex");
		}
		return orderModels;
		
	}
	
	public ArrayList<DailyFeedModel> dailyFeed(){
		long millis=System.currentTimeMillis();  
		Date date=new Date(millis);
		try{
			String query="SELECT ( SELECT SUM(price) FROM orderlist WHERE Progress='Delivered' AND Date="+ date.toString().trim().replace("-", "")
					+") AS sumprice,(SELECT COUNT(Progress) FROM orderlist WHERE Progress='Delivered' AND Date="+date.toString().trim().replace("-", "")
					+") AS countdeli,(SELECT COUNT(id) FROM orderlist WHERE Date="+date.toString().trim().replace("-", "")
					+") AS countrec";
			
			rs=stmnt.executeQuery(query);
			while(rs.next()){
				DailyFeedModel dailyFeedModel=new DailyFeedModel();
				dailyFeedModel.setSales(Double.parseDouble(rs.getString("sumprice")));
				dailyFeedModel.setOrderDelivered(Integer.parseInt(rs.getString("countdeli")));
				dailyFeedModel.setOrderReceived(Integer.parseInt(rs.getString("countrec")));
				dailyFeedModels.add(dailyFeedModel);
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Null");
		}
		return dailyFeedModels;
	}
	
	
	
	
	public ArrayList<LoginAdminModel> getAdminId(String id){
		try{
			String query="SELECT * FROM adminp WHERE admin = '" + Integer.parseInt(id) + "'";
			rs=stmnt.executeQuery(query);
			while(rs.next()){
				LoginAdminModel loginModel=new LoginAdminModel();
				loginModel.setAck(rs.getBoolean("ack"));
					admin_id.add(loginModel);
			}
			close();
		}catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			System.out.println("null ex");
		}
		
		return admin_id;
	}
	
	public ArrayList<LoginAdminModel> getAdminPass(String pass){
		try{
			String query="SELECT * FROM adminp WHERE pass = '" + pass + "'";
			rs=stmnt.executeQuery(query);
			while(rs.next()){
				LoginAdminModel loginModel=new LoginAdminModel();
					loginModel.setAck(rs.getBoolean("ack"));
					admin_pass.add(loginModel);
			}
			close();
		}catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			System.out.println("null ex");
		}
		
		return admin_pass;
	}
	public ArrayList<AdminModel> getAdmininfo(String id){
		try{
			String query="SELECT * FROM adminp WHERE "+id;
			rs=stmnt.executeQuery(query);
			while(rs.next()){
				AdminModel adminModel=new AdminModel();
				if(rs.getInt("admin") == Integer.parseInt(id)){
					adminModel.setName(rs.getString("name"));
					adminModel.setAddress(rs.getString("address"));
					adminModel.setPhone(rs.getInt("phone"));
					adminModel.setEmail(rs.getString("email"));
					adminModel.setUid(rs.getInt("admin"));
					admininfo.add(adminModel);
				}
				
			}
			close();
		}catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			System.out.println("null ex");
		}
		
		return admininfo;
	}
	
	
}
