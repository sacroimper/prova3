package org.escoladeltreball.arcowabungaproject.ar;

import geo.GeoObj;
import gl.CustomGLSurfaceView;
import gl.GL1Renderer;
import gl.GLCamera;
import gl.GLFactory;
import gui.GuiSetup;

import java.util.ArrayList;

import markerDetection.DetectionThread;
import markerDetection.MarkerDetectionSetup;
import markerDetection.MarkerObjectMap;
import markerDetection.UnrecognizedMarkerListener;
import preview.Preview;
import system.EventManager;
import util.Vec;
import worldData.Obj;
import worldData.SystemUpdater;
import actions.ActionBufferedCameraAR;
import android.app.Activity;
import de.rwth.GDXConnection;

public class OwnMarkerRenderSetup extends MarkerDetectionSetup {
    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================

    private DetectionThread myThread;
    private Preview cameraPreview;
    private GLCamera camera;
    private GL1Renderer renderer;
    private Vec pizzaSizeAndMeshVector;

    public PizzaWorld world;
    public PizzaMesh meshComponent;

    // ====================
    // CONSTRUCTORS
    // ====================

    // ====================
    // PUBLIC METHODS
    // ====================

    /**
     * @return
     */
    private Vec pizzaVectorCalculator() {
	float pizzaMesh = PizzaModelMapper.getPizzaMassType();
	float pizzaScale = (float) PizzaModelMapper.getPizzaScale();
	Vec resultVector = new Vec(pizzaScale, pizzaScale, pizzaScale
		* pizzaMesh);
	return resultVector;
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
	pizzaSizeAndMeshVector = pizzaVectorCalculator();
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
		pizzaMesh.setScale(pizzaSizeAndMeshVector);
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
		pizzaMesh.setScale(pizzaSizeAndMeshVector);
		final Obj o = new Obj();
		o.setComp(pizzaMesh);
		world.add(o);
	    }
	};

	ArrayList<String> ingredientTextures = (ArrayList<String>) PizzaModelMapper
		.getModelIngredientTextures();

	// Method to deploy the ingredients object and textures
	if (ingredientTextures.size() > 0) {
	    for (int i = 0; i < ingredientTextures.size(); i++) {
		// Load a previous alpha texture of the ingredient model
		// Helps to show all correctly
		new OwnModelLoader(this.renderer,
			PizzaModelMapper.BASIC_PIZZA_MODEL,
			PizzaModelMapper.INGREDIENT_ALPHA_TEXTURE) {
		    @Override
		    public void modelLoaded(PizzaMesh pizzaMesh) {
			pizzaMesh.setScale(pizzaSizeAndMeshVector);
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
			// pizzaMesh.setRotation(new Vec(0f, 0f, (float) (Math
			// .random() * 10)));
			pizzaMesh.setScale(pizzaSizeAndMeshVector);
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

    // ====================
    // GETTERS & SETTERS
    // ====================

}
