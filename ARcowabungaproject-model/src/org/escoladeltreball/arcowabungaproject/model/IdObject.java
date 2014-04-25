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
