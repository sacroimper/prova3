package org.escoladeltreball.arcowabungaproject.activities;

import org.escoladeltreball.arcowabungaproject.R;
import org.escoladeltreball.arcowabungaproject.adapters.Adaptador;
import org.escoladeltreball.arcowabungaproject.model.GrupoDeItems;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TabHost;

public class DishesMenuActivity extends Activity implements OnTouchListener,
	OnClickListener {

    // Esta variable guarda el valor de resistencia al touch swipe
    // Cuando esta a 0 con mover un milimetro el dedo salta de tab
    static final int SCREEN_TOUCH_RESISTENCE = 200;

    SparseArray<GrupoDeItems> grupos = new SparseArray<GrupoDeItems>();
    TabHost tabs;
    float lastX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	// Remove title bar
	this.requestWindowFeature(Window.FEATURE_NO_TITLE);

	// Remove notification bar
	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);

	setContentView(R.layout.activity_tabs);

	// ELIMINAR CUANDO SE INCORORE BASE DE DATOS
	// Incorpora datos ficticios
	crearDatos();

	// Vista expandible
	ExpandableListView listView = (ExpandableListView) findViewById(R.id.listViewexp);
	Adaptador adapter = new Adaptador(this, grupos);
	listView.setAdapter(adapter);

	// Pestañas
	makeTabs();

	
	
	// Listeners de tab para animación. (necesita extender de TabActivity)
	
	// getTabHost().setOnTabChangedListener(new OnTabChangeListener() {
	// public void onTabChanged(String tabId)
	// {
	// View currentView = getTabHost().getCurrentView();
	// if (getTabHost().getCurrentTab() > tabs.getCurrentTab())
	// {
	// currentView.setAnimation( inFromRightAnimation() );
	// }
	// else
	// {
	// currentView.setAnimation( outToRightAnimation() );
	// }
	// currentTab = getTabHost().getCurrentTab();
	// }
	// });

	// añadiendo la posibilidad de arrastrar a la derecha tambien en los
	// botones
	// De momento solo hay una listview en la tab1 (listViewexp)
	// ExpandableListView elv = (ExpandableListView)
	// findViewById(R.id.listViewexp);
	listView.setOnTouchListener(this);

	// Button b = (Button) findViewById(R.id.pizzaButtonInSubItem);
	// b.setOnClickListener(this);

    }

    private void makeTabs() {

	tabs = (TabHost) findViewById(android.R.id.tabhost);
	tabs.setup();

	TabHost.TabSpec spec = tabs.newTabSpec("mitab1");
	spec.setContent(R.id.tab1);
	spec.setIndicator("TAB1");
	tabs.addTab(spec);

	spec = tabs.newTabSpec("mitab2");
	spec.setContent(R.id.tab2);
	spec.setIndicator("TAB2");
	tabs.addTab(spec);

	spec = tabs.newTabSpec("mitab3");
	spec.setContent(R.id.tab3);
	spec.setIndicator("TAB3");
	tabs.addTab(spec);

	tabs.setCurrentTab(0);
    }

    /**
     * ESTE CREAR DATOS DEBE SER SUBSTITUIDO POR LA CARGA DE DATOS DE BASE DE
     * DATOS
     */
    public void crearDatos() {

	GrupoDeItems grupo0 = new GrupoDeItems("Pizza Margarita");
	grupo0.children
		.add("Tomate, mozzarella, albahaca fresca, sal y aceite");
	// grupo0.children.add("A la parrilla");
	grupos.append(0, grupo0);

	GrupoDeItems grupo1 = new GrupoDeItems("Pizza Caprichosa");
	grupo1.children
		.add("Tomate, mozzarella,alcachofas,champiñones, anchoas.");
	// grupo1.children.add("A la parrilla");
	// grupo1.children.add("Frito");
	grupos.append(1, grupo1);

	GrupoDeItems grupo2 = new GrupoDeItems("Pizza Cuatro Estaciones");
	grupo2.children.add("Champiñones, alcachofa, jamon de york, aceitunas");
	// grupo2.children.add("Pollo, morrones y aceitunas");
	// grupo2.children.add("Carlitos");
	grupos.append(2, grupo2);

	GrupoDeItems grupo3 = new GrupoDeItems("Pizza Cuatro Quesos");
	grupo3.children.add("Mozarrella, Gouda, Roquefort y Emental");
	grupos.append(3, grupo3);

	GrupoDeItems grupo4 = new GrupoDeItems("Pizza Calzone");
	grupo4.children.add("Jamón, champiñones y huevo");
	grupos.append(4, grupo4);

	GrupoDeItems grupo5 = new GrupoDeItems("Pizza Frutti di Mare");
	grupo5.children.add("Mejillones, gambas y albaca fresca");
	grupos.append(5, grupo5);

	GrupoDeItems grupo6 = new GrupoDeItems("Pizza Proschiutto e funghi");
	grupo6.children.add("Jamón y Champiñones");
	grupos.append(6, grupo6);
    }

    public void switchTabs(boolean direction) {

	if (direction) // true = move left
	{
	    if (tabs.getCurrentTab() == 0)
		tabs.setCurrentTab(tabs.getTabWidget().getTabCount() - 1);
	    else
		tabs.setCurrentTab(tabs.getCurrentTab() - 1);
	} else
	// move right
	{
	    if (tabs.getCurrentTab() != (tabs.getTabWidget().getTabCount() - 1))
		tabs.setCurrentTab(tabs.getCurrentTab() + 1);
	    else
		tabs.setCurrentTab(0);
	}
    }

    // El onTouchEvent viene de serie con la Activity
    @Override
    public boolean onTouchEvent(MotionEvent touchevent) {
	return touchController(touchevent);
    }

    // He añadido un onTouch para cuando se toque la lista expandible
    // (cualquiera de sus elementos)
    @Override
    public boolean onTouch(View v, MotionEvent event) {
	return touchController(event);
    }

    private boolean touchController(MotionEvent event) {
	switch (event.getAction()) {
	// when user first touches the screen to swap
	case MotionEvent.ACTION_DOWN: {
	    lastX = event.getRawX();
	    break;
	}
	case MotionEvent.ACTION_UP: {
	    float currentX = event.getRawX();
	    // if left to right swipe on screen
	    if (lastX < currentX + SCREEN_TOUCH_RESISTENCE) {

		switchTabs(true);
	    }
	    // if right to left swipe on screen
	    if (lastX > currentX - SCREEN_TOUCH_RESISTENCE) {
		switchTabs(false);
	    }

	    break;
	}
	}
	return false;
    }

    // Intent para que empieze la realidad aumentada
    // Habrá que identificar el id del boton para saber que pizza lanzar
    @Override
    public void onClick(View v) {
	// Intent arPizza = new Intent(this, ARViewActivity.class);
	// startActivity(arPizza);
    }

    // Para hacer animaciones de swipe.
    
    // public Animation inFromRightAnimation() {
    // Animation inFromRight = new TranslateAnimation(
    // Animation.RELATIVE_TO_PARENT, +1.0f,
    // Animation.RELATIVE_TO_PARENT, 0.0f,
    // Animation.RELATIVE_TO_PARENT, 0.0f,
    // Animation.RELATIVE_TO_PARENT, 0.0f);
    // inFromRight.setDuration(240);
    // inFromRight.setInterpolator(new AccelerateInterpolator());
    // return inFromRight;
    // }
    //
    // public Animation outToRightAnimation() {
    // Animation outtoLeft = new TranslateAnimation(
    // Animation.RELATIVE_TO_PARENT, -1.0f,
    // Animation.RELATIVE_TO_PARENT, 0.0f,
    // Animation.RELATIVE_TO_PARENT, 0.0f,
    // Animation.RELATIVE_TO_PARENT, 0.0f);
    // outtoLeft.setDuration(240);
    // outtoLeft.setInterpolator(new AccelerateInterpolator());
    // return outtoLeft;
    // }

}
