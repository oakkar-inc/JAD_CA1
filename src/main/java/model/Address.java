package model;

public class Address {
	private int id;
	private String postal;
	private int floor;
	private int unit;
	
	public Address(int id, String postal, int floor, int unit) {
		this.id = id;
		this.postal = postal;
		this.floor = floor;
		this.unit = unit;
	}
	
	public int getId() {
		return id;
	}
	public String getPostal() {
		return postal;
	}
	public int getFloor() {
		return floor;
	}
	public int getUnit() {
		return unit;
	}
	public String toString() {
		String floorStr = floor < 10 ? "0" + floor : "" + floor;
		String unitStr = unit < 10 ? "0" + unit : "" + unit;
		return postal + " " + floorStr + "#" + unitStr;
	}
	
}
