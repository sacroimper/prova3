package org.escoladeltreball.arcowabungaproject.ar;

import gl.GL1Renderer;
import gl.Renderable;
import gl.scenegraph.MeshComponent;
import system.Setup;
import worldData.World;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;

import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g3d.loaders.collada.ColladaLoader;
//import com.badlogic.gdx.graphics.g3d.loaders.g3d.G3dLoader;
//import com.badlogic.gdx.graphics.g3d.loaders.g3d.G3dtLoader;
//import com.badlogic.gdx.graphics.g3d.loaders.md2.MD2Loader;
//import com.badlogic.gdx.graphics.g3d.loaders.ogre.OgreXmlLoader;
import com.badlogic.gdx.graphics.g3d.loaders.wavefront.ObjLoader;
//import com.badlogic.gdx.graphics.g3d.materials.Material;
//import com.badlogic.gdx.graphics.g3d.materials.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.model.keyframe.KeyframedModel;
import com.badlogic.gdx.graphics.g3d.model.skeleton.SkeletonModel;
import com.badlogic.gdx.graphics.g3d.model.still.StillModel;

import de.rwth.GDXMesh;

/**
 * The ModelCreator has to load the model from the rendering thread. Therefor it
 * implements {@link Renderable} and has to be passed to the {@link Renderer} in
 * the
 * {@link Setup#_b_addWorldsToRenderer(gl.GLRenderer, gl.GLFactory, geo.GeoObj)}
 * methods. The ModelCreator will call the
 * {@link OwnModelLoader#modelLoaded(StillModel, Texture)} method when the model
 * was loaded. Then you can create a {@link GDXMesh} with the returned data and
 * add it to the {@link World}
 * 
 * @author Spobo
 * 
 */
public abstract class OwnModelLoader implements gl.Renderable {

    private static final String LOGTAG = "ModelCreator";

    private String fileName;
    private String textureFileName;

    private Texture texture;

    private StillModel model;
    private KeyframedModel keyFramedModel;
    private SkeletonModel skeletonModel;

    private GL1Renderer renderer;

    public OwnModelLoader(GL1Renderer renderer, String fileName,
	    String textureFileName) {
	this.renderer = renderer;
	this.fileName = fileName;
	this.textureFileName = textureFileName;
	renderer.addRenderElement(this);
    }

    private void loadModelFromFile() {

	try {
	    if (textureFileName != null)
		texture = new Texture(Gdx.files.internal(textureFileName), true);
	} catch (Exception e) {
	    Log.e(LOGTAG, "Could not load the specified texture: "
		    + textureFileName);
	    e.printStackTrace();
	}

	if (fileName.endsWith(".obj"))
	    model = new ObjLoader().loadObj(Gdx.files.internal(fileName), true);

    }

    public MeshComponent getGDXShape() {
	GDXMesh x = new GDXMesh(model, texture);
	return x;
    }

    @Override
    public void render(javax.microedition.khronos.opengles.GL10 gl,
	    gl.Renderable parent) {

	Log.d(LOGTAG, "Trying to load " + fileName);

	try {
	    loadModelFromFile();
	} catch (Exception e) {
	    Log.e(LOGTAG, "Could not load model");
	    e.printStackTrace();
	}

	if (model != null)
	    modelLoaded(new GDXMesh(model, texture));
	else if (keyFramedModel != null)
	    modelLoaded(new GDXMesh(keyFramedModel, texture));
	else if (skeletonModel != null)
	    modelLoaded(new GDXMesh(skeletonModel, texture));

	Log.d(LOGTAG, "Result of trying is:");
	Log.d(LOGTAG, "fileName=" + fileName);
	Log.d(LOGTAG, "textureFileName=" + textureFileName);
	Log.d(LOGTAG, "model=" + model);
	Log.d(LOGTAG, "keyFramedModel=" + keyFramedModel);
	Log.d(LOGTAG, "skeletonModel=" + skeletonModel);
	Log.d(LOGTAG, "texture=" + texture);

	renderer.removeRenderElement(this);

    }

    public abstract void modelLoaded(MeshComponent gdxMesh);

}
