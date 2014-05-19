/*
 *  PizzaMesh.java
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

import gl.ObjectPicker;
import gl.Renderable;
import gl.scenegraph.MeshComponent;

import javax.microedition.khronos.opengles.GL10;

import util.Vec;
import worldData.Updateable;
import worldData.Visitor;
import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.model.Model;
import com.badlogic.gdx.graphics.g3d.model.keyframe.KeyframedAnimation;
import com.badlogic.gdx.graphics.g3d.model.keyframe.KeyframedModel;

/**
 * @author local
 * 
 */
public class PizzaMesh extends MeshComponent {

    private static final String LOGTAG = "PizzaMesh";
    private Model model;
    private Texture texture;
    private KeyframedAnimation anim;
    private float animTime;

    public PizzaMesh(Model model, Texture texture) {
	super(null);
	this.model = model;
	this.texture = texture;

	try {
	    anim = (KeyframedAnimation) ((KeyframedModel) model)
		    .getAnimations()[0];
	} catch (Exception e) {
	}
    }

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================

    private Vec myScale;

    // ====================
    // CONSTRUCTORS
    // ====================

    // ====================
    // PUBLIC METHODS
    // ====================

    /**
     * resize the Mesh equally in all 3 dimensions
     * 
     * @param scaleRate
     */
    public void scaleEqual(float scaleRate) {
	this.myScale = new Vec(scaleRate, scaleRate, scaleRate);
    }

    // ====================
    // PROTECTED METHODS
    // ====================

    // ====================
    // PRIVATE METHODS
    // ====================

    private void loadPosition(GL10 gl) {
	if (myPosition != null)
	    gl.glTranslatef(myPosition.x, myPosition.y, myPosition.z);
    }

    // ====================
    // OVERRIDE METHODS
    // ====================

    @Override
    public boolean accept(Visitor visitor) {
	return false;
    }

    @Override
    public void draw(GL10 gl, Renderable parent) {

	gl.glEnable(GL10.GL_CULL_FACE);

	if (model != null) {
	    if (!ObjectPicker.readyToDrawWithColor && texture != null) {
		gl.glEnable(GL10.GL_TEXTURE_2D);
		Gdx.gl.glEnable(GL10.GL_BLEND);
		Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA,
			GL10.GL_ONE_MINUS_SRC_ALPHA);
		texture.bind();
		model.render();
		gl.glDisable(GL10.GL_TEXTURE_2D);
	    } else {
		model.render();
	    }
	} else
	    Log.e(LOGTAG, "No model object existend");
    }

    @Override
    public synchronized boolean update(float timeDelta, Updateable parent) {
	super.update(timeDelta, parent);
	if (anim != null) {
	    animTime += timeDelta;
	    if (animTime > anim.totalDuration - anim.frameDuration) {
		animTime = 0;
	    }
	    try {
		((KeyframedModel) model)
			.setAnimation(anim.name, animTime, true);
	    } catch (Exception e) {
	    }

	}
	return true;
    }

    @Override
    public Vec getPosition() {
	if (myPosition == null)
	    myPosition = new Vec();
	return myPosition;
    }

    @Override
    public void setPosition(Vec position) {
	if (myPosition == null)
	    myPosition = position.copy();
	else
	    myPosition.setToVec(position);
    }

    // ====================
    // GETTERS & SETTERS
    // ====================
}
