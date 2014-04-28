package org.escoladeltreball.arcowabungaproject.model;

import java.io.Serializable;

public abstract class IdObject implements Serializable {

    // ====================
    // CONSTANTS
    // ====================

    private static final long serialVersionUID = -7056112589728294900L;
    public static final int MIN_CUSTOM_ID = 10000;

    // ====================
    // ATTRIBUTES
    // ====================

    private static int nextId = 1;
    private static int nextCustomId = MIN_CUSTOM_ID + 1;

    protected int id;

    // ====================
    // CONSTRUCTORS
    // ====================

    public IdObject(int id) {
	super();
	this.id = id;
    }

    // ====================
    // PUBLIC METHODS
    // ====================

    public static int nextId() {
	return nextId++;
    }

    public static int nextCustomId() {
	return nextCustomId++;
    }

    // ====================
    // PROTECTED METHODS
    // ====================

    // ====================
    // PRIVATE METHODS
    // ====================

    // ====================
    // OVERRIDE METHODS
    // ====================

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	IdObject other = (IdObject) obj;
	if (id != other.id)
	    return false;
	return true;
    }

    // ====================
    // GETTERS & SETTERS
    // ====================

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public static int getNextId() {
	return nextId;
    }

    public static void setNextId(int nextId) {
	IdObject.nextId = nextId;
    }

    public static int getNextCustomId() {
	return nextCustomId;
    }

    public static void setNextCostumId(int nextCustomId) {
	IdObject.nextCustomId = nextCustomId;
    }

}
