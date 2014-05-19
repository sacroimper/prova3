package org.escoladeltreball.arcowabungaproject.ar;

//import com.badlogic.gdx.graphics.g3d.loaders.ogre.mesh.Mesh;

import geo.GeoObj;
//import gl.Color;
import gl.CustomGLSurfaceView;
import gl.GL1Renderer;
import gl.GLCamera;
import gl.GLFactory;
//import gl.animations.AnimationFaceToCamera;
//import gl.scenegraph.MeshComponent;
//import gl.scenegraph.Shape;
import gui.GuiSetup;

import java.util.ArrayList;

import markerDetection.DetectionThread;
import markerDetection.MarkerDetectionSetup;
import markerDetection.MarkerObjectMap;
import markerDetection.UnrecognizedMarkerListener;
import preview.Preview;
import system.EventManager;
//import util.IO;
//import util.Vec;
//import util.Wrapper;
//import worldData.MoveComp;
import worldData.Obj;
import worldData.SystemUpdater;
import actions.ActionBufferedCameraAR;
import android.app.Activity;
import de.rwth.GDXConnection;

public class OwnMarkerRenderSetup extends MarkerDetectionSetup {

    private DetectionThread myThread;
    private Preview cameraPreview;
    private GLCamera camera;
    public PizzaWorld world;
    public PizzaMesh meshComponent;
    private GL1Renderer renderer;

    // public Wrapper targetMoveWrapper = new Wrapper();

    @Override
    public UnrecognizedMarkerListener _a2_getUnrecognizedMarkerListener() {
	camera.setNewPosition(100, 100, 100);
	return null;
    }

    @Override
    public void _a3_registerMarkerObjects(MarkerObjectMap markerObjectMap) {
	camera.setNewPosition(0, 0, 0);
	markerObjectMap.put(new CameraMarker(0, camera));
    }

    @Override
    public void _a_initFieldsIfNecessary() {
	camera = new GLCamera();
	world = new PizzaWorld(camera);
    }

    @Override
    public void _b_addWorldsToRenderer(GL1Renderer renderer,
	    GLFactory objectFactory, GeoObj currentPosition) {
	this.renderer = renderer;
	GDXConnection.init(this.getActivity(), this.renderer);

	// Load a previous alpha texture of the ingredient model
	// Helps to show all correctly
	new OwnModelLoader(this.renderer, PizzaModelMapper.BASIC_PIZZA_MODEL,
		PizzaModelMapper.INGREDIENT_ALPHA_TEXTURE) {
	    @Override
	    public void modelLoaded(PizzaMesh pizzaMesh) {
		meshComponent = pizzaMesh;
		final Obj o = new Obj();
		o.setComp(pizzaMesh);
		world.add(o);
		world.remove(o);
	    }
	};

	// Pizza base model and texture loader
	new OwnModelLoader(this.renderer, PizzaModelMapper.BASIC_PIZZA_MODEL,
		PizzaModelMapper.BASIC_PIZZA_TEXTURE) {
	    @Override
	    public void modelLoaded(PizzaMesh pizzaMesh) {
		meshComponent = pizzaMesh;
		final Obj o = new Obj();
		o.setComp(pizzaMesh);
		world.add(o);
	    }
	};

	// Method to deploy the ingredients object and textures
	if (PizzaModelMapper.getIngredientsSize() > 0) {

	    ArrayList<String> ingredientTextures = (ArrayList<String>) PizzaModelMapper
		    .getModelIngredientTextures();

	    for (int i = 0; i < ingredientTextures.size(); i++) {
		// Load a previous alpha texture of the ingredient model
		// Helps to show all correctly
		new OwnModelLoader(this.renderer,
			PizzaModelMapper.BASIC_PIZZA_MODEL,
			PizzaModelMapper.INGREDIENT_ALPHA_TEXTURE) {
		    @Override
		    public void modelLoaded(PizzaMesh pizzaMesh) {
			meshComponent = pizzaMesh;
			final Obj o = new Obj();
			o.setComp(pizzaMesh);
			world.add(o);
			world.remove(o);
		    }
		};
		new OwnModelLoader(this.renderer,
			PizzaModelMapper.INGREDIENT_MODEL,
			ingredientTextures.get(i)) {
		    @Override
		    public void modelLoaded(PizzaMesh pizzaMesh) {
			meshComponent = pizzaMesh;
			final Obj o = new Obj();
			o.setComp(pizzaMesh);
			world.add(o);
		    }
		};
	    }
	}
	this.renderer.addRenderElement(world);
    }

    @Override
    public void _c_addActionsToEvents(EventManager eventManager,
	    CustomGLSurfaceView arView, SystemUpdater updater) {
	arView.addOnTouchMoveListener(new ActionBufferedCameraAR(camera));

    }

    @Override
    public void _d_addElementsToUpdateThread(SystemUpdater updater) {
	updater.addObjectToUpdateCycle(world);

    }

    @Override
    public void _e2_addElementsToGuiSetup(GuiSetup guiSetup, Activity activity) {
    }

    // This will send you back to the last activity
    @Override
    public void onDestroy(Activity a) {
	super.onDestroy(a);
	if (cameraPreview != null)
	    cameraPreview.releaseCamera();
	// Ensure app is gone after back button is pressed!
	if (myThread != null)
	    myThread.stopThread();
    }

}
