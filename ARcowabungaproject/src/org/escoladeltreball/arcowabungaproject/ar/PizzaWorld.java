/*
 *  PizzaWorld.java
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
package org.escoladeltreball.arcowabungaproject.ar;

import gl.GLCamera;
import gl.Renderable;

import javax.microedition.khronos.opengles.GL10;

import util.Vec;
import worldData.World;

/**
 * @author local
 * 
 */
public class PizzaWorld extends World {

    /**
     * think of this as the position on the screen
     */
    private Vec myScreenPosition;

    /**
     * the camera which is responsible to display the world correctly
     */
    private GLCamera myCamera;

    /**
     * think of this as the scale of the whole world on the screen
     */
    private Vec myScale;

    /**
     * @param glCamera
     */
    public PizzaWorld(GLCamera glCamera) {
	super(glCamera);
	myCamera = glCamera;
    }

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================

    // ====================
    // CONSTRUCTORS
    // ====================

    // ====================
    // PUBLIC METHODS
    // ====================

    // ====================
    // PROTECTED METHODS
    // ====================

    // ====================
    // PRIVATE METHODS
    // ====================

    private void glLoadScreenPosition(GL10 gl) {
	if (myScreenPosition != null)
	    gl.glTranslatef(myScreenPosition.x, myScreenPosition.y,
		    myScreenPosition.z);
    }

    private void glLoadScale(GL10 gl) {
	if (myScale != null)
	    gl.glScalef(myScale.x, myScale.y, myScale.z);
    }

    // ====================
    // OVERRIDE METHODS
    // ====================

    @Override
    public void render(GL10 gl, Renderable parent) {
	glLoadScreenPosition(gl);
	myCamera.render(gl, this);
	glLoadScale(gl);
	// This shows the coordinate axis in world
	// CordinateAxis.draw(gl);
	drawElements(myCamera, gl);

    }

    // ====================
    // GETTERS & SETTERS
    // ====================
}
