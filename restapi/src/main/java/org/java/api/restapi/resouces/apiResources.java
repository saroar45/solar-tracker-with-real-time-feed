package org.java.api.restapi.resouces;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.java.api.restapi.database.DBConnect;
import org.java.api.restapi.model.AdminModel;
import org.java.api.restapi.model.CustomerModel;
import org.java.api.restapi.model.DailyFeedModel;
import org.java.api.restapi.model.FoodModel;
import org.java.api.restapi.model.LoginAdminModel;
import org.java.api.restapi.model.LoginModel;
import org.java.api.restapi.model.OrderModel;
import org.java.api.restapi.model.Temp;
@Path("/api")
public class apiResources {
	DBConnect dbcon=new DBConnect();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<FoodModel> getFoods(){
		return dbcon.getAll();
	}
	@GET
	@Path("/foods/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<FoodModel> DelFood(@PathParam("id") String id){
		return dbcon.getFoods(id);
	}
	@DELETE
	@Path("/foods/del/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFoodById(@PathParam("id") String id){
		dbcon.delete_foods(id);
		return Response.ok().build();
	}
	
	@GET
	@Path("/user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<CustomerModel> getUser(@PathParam("id") String id){
		return dbcon.getUser(id);
	}
	
	@GET
	@Path("/user/name/{getUserName}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserName(@PathParam("getUserName") String name){
		return name+" OK";
	}
	
	@GET
	@Path("/user/pass/{getUserPass}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<LoginModel> getUserPass(@PathParam("getUserPass") String pass){
		
		return dbcon.checkUnamePass(pass);
	}
	@POST
	@Path("/food/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addFood(FoodModel foodModel){
		dbcon.addFoodInfo(foodModel);
		return Response.ok().build();
	}
	
	@PUT
	@Path("/food/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateFood(@PathParam("id") String id,FoodModel foodModel){
		dbcon.update_foods(id, foodModel);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/food/del/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteFood(@PathParam("id") String id){
		dbcon.delete_foods(id);
		return Response.ok().build();
	}
	
	
	@POST
	@Path("/order/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addOrderInfo(OrderModel orderModel){
		dbcon.addOrder(orderModel);
		System.out.println(orderModel.getPrice());
		return Response.ok().build();
	}
	
	@GET
	@Path("/order")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<OrderModel> getOrder(){
		return dbcon.getOrder();
	}
	
	@GET
	@Path("/order/curt")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<OrderModel> getCrtOrder(){
		return dbcon.getCurrentTOrder();
	}
	
	@GET
	@Path("/order/curd")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<OrderModel> getCrtDOrder(){
		return dbcon.getCurrentDOrder();
	}
	@GET
	@Path("/order/feed")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<DailyFeedModel> getFeed(){
		return dbcon.dailyFeed();
	}
	@PUT
	@Path("/order/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateOrder(@PathParam("id") String id,OrderModel orderModel){
		dbcon.update_order(id, orderModel);
		return Response.ok().build();
	}
	
	
	@GET
	@Path("/admin/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<LoginAdminModel> getAdminId(@PathParam("id") String id){
		return dbcon.getAdminId(id);
	}
	
	@GET
	@Path("/admin/pass/{pass}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<LoginAdminModel> getAdminPass(@PathParam("pass") String pass){
		return dbcon.getAdminPass(pass);
	}
	
	@GET
	@Path("/admin/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<AdminModel> getAdmininfo(@PathParam("id") String id){
		return dbcon.getAdmininfo(id);
	}
	
	
	
	
}	
