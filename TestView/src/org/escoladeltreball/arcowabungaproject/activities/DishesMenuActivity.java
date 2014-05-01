package org.escoladeltreball.arcowabungaproject.activities;

import org.escoladeltreball.arcowabungaproject.R;
import org.escoladeltreball.arcowabungaproject.StandardObjects.TabsMaker;
import org.escoladeltreball.arcowabungaproject.adapters.Adaptador;
import org.escoladeltreball.arcowabungaproject.model.GrupoDeItems;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

public class DishesMenuActivity extends Activity implements OnTouchListener {

    // Esta variable guarda el valor de resistencia al touch swipe
    // Cuando esta a 0 con mover un milimetro el dedo salta de tab
    static final int SCREEN_TOUCH_RESISTENCE = 200;

    SparseArray<GrupoDeItems> grupos = new SparseArray<GrupoDeItems>();
    TabHost tabs;
    float lastX;
    LinearLayout actualTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	// Remove title bar
	this.requestWindowFeature(Window.FEATURE_NO_TITLE);

	// Remove notification bar
	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);

	setContentView(R.layout.activity_tabs);

	this.overridePendingTransition(R.anim.animation_enter,
		R.anim.animation_leave);

	// ELIMINAR CUANDO SE INCORORE BASE DE DATOS
	// Incorpora datos ficticios
	crearDatos();

	// Vista expandible
	ExpandableListView listView = (ExpandableListView) findViewById(R.id.listViewexp);
	Adaptador adapter = new Adaptador(this, grupos);
	listView.setAdapter(adapter);

	// Pestañas
	makeTabs();

	// Para añadir swipe action al la Expandable list
	listView.setOnTouchListener(this);

    }

    private void makeTabs() {

	tabs = (TabHost) findViewById(android.R.id.tabhost);
	tabs.setup();

	// Make the neccesary tabs
	TabSpec tab1 = TabsMaker.makeTab(tabs, "mytab1", R.id.tab1, "TAB1");
	TabSpec tab2 = TabsMaker.makeTab(tabs, "mytab2", R.id.tab2, "TAB2");
	TabSpec tab3 = TabsMaker.makeTab(tabs, "mytab3", R.id.tab3, "TAB3");

	// Add tabs to tabHost
	tabs.addTab(tab1);
	tabs.addTab(tab2);
	tabs.addTab(tab3);

	// STYLE
	// TabsColor
	tabs = TabsMaker.tabsColor(tabs, "#3be0d0");
	// Aspect Ratio
	tabs = TabsMaker.tabsAspectRatio(tabs, 0.66);

	// BEHAVIOUR
	// Default selected
	tabs.setCurrentTab(0);
	actualTab = (LinearLayout) findViewById(R.id.tab1);

	// ANIMATION
	// On change animation
	tabs.setOnTabChangedListener(new OnTabChangeListener() {

	    // Tabs definidas en el layout
	    LinearLayout t1l = (LinearLayout) findViewById(R.id.tab1);
	    LinearLayout t2l = (LinearLayout) findViewById(R.id.tab2);
	    LinearLayout t3l = (LinearLayout) findViewById(R.id.tab3);

	    public void onTabChanged(String tabId) {
		actualTab = TabsMaker.OnChangeTabAnimation(tabId, tabs, actualTab, t1l, t2l, t3l);
	    }

	});
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

    // Es necesario el Touch controller para que el evento de cambio
    // Venga tambien desde el ListExpandable y su contenido
    private boolean touchController(MotionEvent event) {
	switch (event.getAction()) {
	// when user first touches the screen to swap
	case MotionEvent.ACTION_DOWN: {
	    lastX = event.getX();
	    break;
	}
	case MotionEvent.ACTION_UP: {
	    float currentX = event.getX();
	    // if left to right swipe on screen
	    if (lastX < currentX - SCREEN_TOUCH_RESISTENCE) {
		tabs.setCurrentTab(tabs.getCurrentTab() - 1);
	    } else if (lastX > currentX + SCREEN_TOUCH_RESISTENCE) {
		tabs.setCurrentTab(tabs.getCurrentTab() + 1);
	    }
	    break;
	}
	}
	return false;
    }

    //BERNA ESTOS METODOS NO SE PARA QUE EXISTEN

    // private void setupTab(final View view, final String tag, int id) {
    // View tabview = createTabView(tabs.getContext(), tag);
    // TabSpec setContent = tabs.newTabSpec(tag).setIndicator(tabview)
    // .setContent(new TabContentFactory() {
    // public View createTabContent(String tag) {
    // return view;
    // }
    // });
    // tabs.addTab(setContent);
    // }
    //
    // private static View createTabView(final Context context, final String
    // text) {
    // View view = LayoutInflater.from(context)
    // .inflate(R.layout.tabs_bg, null);
    // TextView tv = (TextView) view.findViewById(R.id.tabsText);
    // tv.setText(text);
    // return view;
    // }
    
   
    /**
     * ESTE CREAR DATOS DEBE SER SUBSTITUIDO POR LA CARGA DE DATOS DE BASE DE
     * DATOS
     */
    public void crearDatos() {

	GrupoDeItems grupo0 = new GrupoDeItems("Pizza Margarita");
	grupo0.children
		.add("Tomate, mozzarella, albahaca fresca, sal y aceite");
	grupos.append(0, grupo0);

	GrupoDeItems grupo1 = new GrupoDeItems("Pizza Caprichosa");
	grupo1.children
		.add("Tomate, mozzarella,alcachofas,champiñones, anchoas.");
	grupos.append(1, grupo1);

	GrupoDeItems grupo2 = new GrupoDeItems("Pizza Cuatro Estaciones");
	grupo2.children.add("Champiñones, alcachofa, jamon de york, aceitunas");
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

}
