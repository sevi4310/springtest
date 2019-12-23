package com.sevi.sptingtest;

public class Product implements Cloneable{
	private String Id;
	private String name;
	private String description;
	private double price;	
	//TODO not implemented
//	private int quantity;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
