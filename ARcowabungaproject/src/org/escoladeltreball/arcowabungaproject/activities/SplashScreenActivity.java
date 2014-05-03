package org.escoladeltreball.arcowabungaproject.activities;

import java.util.Timer;
import java.util.TimerTask;

import org.escoladeltreball.arcowabungaproject.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreenActivity extends Activity {

    // ***ESTA CONSTANTE DESAPARECERA
    // ***La duracion se ha de corresponder con el tiempo necesario de carga
    // ***(Hasta que no acabe de cargar no comnzara la Main Activity)
    // Set the duration of the splash screen
    private static final long SPLASH_SCREEN_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	// Hide the tilte and remove the notification bar
	setTheWindow();

	super.onCreate(savedInstanceState);
	setContentView(R.layout.splash_screen);

	// Check the database
	checkDataBase();

    }

    private void setTheWindow() {
	// Hide title bar
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	// Remove notification bar
	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void checkDataBase() {

	// ***ESTE METODO CAMBIARA
	// ***HA DE COMPROBAR LA ACTUALIZACION DE LA BASE DE DATOS
	TimerTask task = new TimerTask() {

	    @Override
	    public void run() {

		// Start the next activity
		Intent mainIntent = new Intent().setClass(
			SplashScreenActivity.this, MainMenuActivity.class);
		startActivity(mainIntent);

		// Close the activity so the user won't able to go back this
		// activity pressing Back button
		finish();
	    }
	};

	// Simulate a long loading process on application startup.
	Timer timer = new Timer();
	timer.schedule(task, SPLASH_SCREEN_DELAY);
    }
}
