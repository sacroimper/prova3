package org.escoladeltreball.arcowabungaproject.model;

public class Address extends IdObject {

    // ====================
    // ATTRIBUTES
    // ====================

    private String street;
    private String number;
    private String postCode;
    private String floor;
    private String stair;
    private String door;

    // ====================
    // CONSTRUCTORS
    // ====================

    public Address(int id) {
	super(id);
    }

    public Address(int id, String street, String number, String postCode,
	    String floor, String stair, String door) {
	super(id);
	this.street = street;
	this.number = number;
	this.postCode = postCode;
	this.floor = floor;
	this.stair = stair;
	this.door = door;
    }

    // ====================
    // PUBLIC METHODS
    // ====================

    public void print() {
	System.out.println(toString());
    }

    // ====================
    // OVERRIDE METHODS
    // ====================

    @Override
    public String toString() {
	return "Address [street=" + street + ", number=" + number
		+ ", postCode=" + postCode + ", floor=" + floor + ", stair="
		+ stair + ", door=" + door + "]";
    }

    // ====================
    // GETTERS & SETTERS
    // ====================

    public String getStreet() {
	return street;
    }

    public void setStreet(String street) {
	this.street = street;
    }

    public String getNumber() {
	return number;
    }

    public void setNumber(String number) {
	this.number = number;
    }

    public String getPostCode() {
	return postCode;
    }

    public void setPostCode(String postCode) {
	this.postCode = postCode;
    }

    public String getFloor() {
	return floor;
    }

    public void setFloor(String floor) {
	this.floor = floor;
    }

    public String getStair() {
	return stair;
    }

    public void setStair(String stair) {
	this.stair = stair;
    }

    public String getDoor() {
	return door;
    }

    public void setDoor(String door) {
	this.door = door;
    }
}
