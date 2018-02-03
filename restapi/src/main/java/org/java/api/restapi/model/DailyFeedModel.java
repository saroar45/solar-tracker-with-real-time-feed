package org.java.api.restapi.model;

public class DailyFeedModel {
	private double sales;
	private int orderReceived;
	private int orderDelivered;
	public double getSales() {
		return sales;
	}
	public int getOrderReceived() {
		return orderReceived;
	}
	public int getOrderDelivered() {
		return orderDelivered;
	}
	public void setSales(double sales) {
		this.sales = sales;
	}
	public void setOrderReceived(int orderReceived) {
		this.orderReceived = orderReceived;
	}
	public void setOrderDelivered(int orderDelivered) {
		this.orderDelivered = orderDelivered;
	}
	

}
