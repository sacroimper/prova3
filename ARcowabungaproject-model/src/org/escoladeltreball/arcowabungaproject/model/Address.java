/*
 *  Address.java
 *  
 *  This file is part of ARcowabungaproject.
 *  
 *  Copyright 2014 	Bernabe Gonzalez Garcia <bernagonzga@gmail.com>
 *  			Marc Sabate Piñol <masapim@hotmail.com>
 *  			Victor Purcallas Marchesi <vpurcallas@gmail.com>
 *  			Joaquim Dalmau Torva <jdalmaut@gmail.com>
 *
 *   ARcowabungaproject is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   ARcowabungaproject is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with ARcowabungaproject.  If not, see <http://www.gnu.org/licenses/>. 
 */

package org.escoladeltreball.arcowabungaproject.model;

public class Address extends IdObject {

    // ====================
    // CONSTANTS
    // ====================

    private static final long serialVersionUID = 2162643300824182716L;

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
