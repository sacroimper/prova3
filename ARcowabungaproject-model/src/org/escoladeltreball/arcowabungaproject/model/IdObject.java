package org.escoladeltreball.arcowabungaproject.model;

public abstract class IdObject {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================

    private int id;

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

}
