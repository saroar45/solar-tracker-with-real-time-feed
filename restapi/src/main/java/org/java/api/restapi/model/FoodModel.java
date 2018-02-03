package org.java.api.restapi.model;
import java.sql.Blob;

public class FoodModel {
	private int foodID;
	private String title;
	private double price;
	private int quantity;
	private String description;
	private int catagories;
	private Blob image;
	public void setImage(Blob blob) {
		this.image = blob;
		
	}
	public Blob getImage() {
		return image;
	}
	public void setFoodID(int foodID) {
		this.foodID = foodID;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCatagories(int catagories) {
		this.catagories = catagories;
	}
	public int getFoodID() {
		return foodID;
	}
	public String getTitle() {
		return title;
	}
	public double getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}
	public String getDescription() {
		return description;
	}
	public int getCatagories() {
		return catagories;
	}
	

}
