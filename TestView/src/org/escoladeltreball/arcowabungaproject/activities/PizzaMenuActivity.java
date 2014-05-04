package org.escoladeltreball.arcowabungaproject.activities;

import org.escoladeltreball.arcowabungaproject.R;
import org.escoladeltreball.arcowabungaproject.StandardObjects.TabsMaker;
import org.escoladeltreball.arcowabungaproject.adapters.Adaptador;
import org.escoladeltreball.arcowabungaproject.model.GrupoDeItems;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ExpandableListView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class PizzaMenuActivity extends Activity implements OnTouchListener {


    // Este atributo no hara falta con el nuevo adaptador
    // Es para integrar los datos ficticios
    SparseArray<GrupoDeItems> grupos = new SparseArray<GrupoDeItems>();

    //TOUCH
    private float lastX;				//X coord from touch
    static final int SCREEN_TOUCH_RESISTENCE = 200;	//Resitence of the swipe touch detection
    
    //TAB FUNCTIONALLITIES
    private static final int ANIMATION_TIME = 700;    	//Animation speed
    private TabHost mTabHost;				
    private View previousView;
    private View currentView;
    private View currentTabView;			//Useful for detect tab color 
    private int currentTab;				//Current tab number 


    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	// Remove title bar
	this.requestWindowFeature(Window.FEATURE_NO_TITLE);

	// Remove notification bar
	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);

	setContentView(R.layout.activity_tab);

	//Enter and leave animations
	this.overridePendingTransition(R.anim.animation_horizontal_enter,
		R.anim.animation_horizontal_leave);

	// Making the tabs
	makeTabs();

	
	// ELIMINAR CUANDO SE INCORORE BASE DE DATOS
	// Incorpora datos ficticios
	crearDatos();
	
	//Adding Adapters to ListViews
	addingAdaptersAndListeners();
	
	
	
    }

    private void addingAdaptersAndListeners() {
	ExpandableListView listView = (ExpandableListView) findViewById(R.id.listViewexp);
	Adaptador adapter = new Adaptador(this, grupos);
	listView.setAdapter(adapter);
	
	//Adding listeners
	listView.setOnTouchListener(this);
    }

    private void makeTabs() {
	//Locate the host
	mTabHost = (TabHost) findViewById(android.R.id.tabhost);
	mTabHost.setup();
	
	//Inflate Tabs
	LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext()
		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	final View viewTab1 = layoutInflater.inflate(R.layout.content_tab, null);
	final View viewTab2 = layoutInflater.inflate(R.layout.content_second_tab, null);
	final View viewTab3 = layoutInflater.inflate(R.layout.content_third_tab, null);

	//Add The tabs to host
	mTabHost = TabsMaker.setTab(mTabHost, 0, viewTab1, "Tab 1", Color.WHITE);
	mTabHost = TabsMaker.setTab(mTabHost, 1, viewTab2, "Tab 2", Color.WHITE);
	mTabHost = TabsMaker.setTab(mTabHost, 2, viewTab3, "Tab 3", Color.WHITE);

	mTabHost.setCurrentTab(0);			//Set init tab
	View tab1 = mTabHost.findViewWithTag("Tab 1");
	TabsMaker.changeColor(tab1, true);		//add color gray to the inint selected tab
	
	currentTabView = viewTab1;			//set current tab
	previousView = mTabHost.getCurrentView();
//	gestureDetector = new GestureDetector(getApplicationContext(), new MyGestureDetector());

	
	
	mTabHost.setOnTabChangedListener(
	new OnTabChangeListener() {
	    @Override
	    public void onTabChanged(String tabId) {

		currentView = mTabHost.getCurrentView();

		if (mTabHost.getCurrentTab() >= currentTab) {
		    previousView.setAnimation(TabsMaker
			    .outToLeftAnimation(ANIMATION_TIME));
		    currentView.setAnimation(TabsMaker
			    .inFromRightAnimation(ANIMATION_TIME));
		} else if (mTabHost.getCurrentTab() < currentTab) {
		    previousView.setAnimation(TabsMaker
			    .outToRightAnimation(ANIMATION_TIME));
		    currentView.setAnimation(TabsMaker
			    .inFromLeftAnimation(ANIMATION_TIME));
		}

		previousView = currentView;
		currentTab = mTabHost.getCurrentTab();

		//Locate the tab views
		View tab1 = mTabHost.findViewWithTag("Tab 1");
		View tab2 = mTabHost.findViewWithTag("Tab 2");
		View tab3 = mTabHost.findViewWithTag("Tab 3");

		currentTabView = TabsMaker.setTabColor(tabId, currentTabView, viewTab1, viewTab2, viewTab3, tab1, tab2, tab3);
	    }

	    
	});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.tab, menu);
	return true;
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
		mTabHost.setCurrentTab(mTabHost.getCurrentTab() - 1);
	    } else if (lastX > currentX + SCREEN_TOUCH_RESISTENCE) {
		mTabHost.setCurrentTab(mTabHost.getCurrentTab() + 1);
	    }
	    break;
	}
	}
	return false;
    }


//    class MyGestureDetector extends SimpleOnGestureListener {
//	private static final int SWIPE_MIN_DISTANCE = 120;
//	private static final int SWIPE_MAX_OFF_PATH = 250;
//	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
//	private int maxTabs;
//
//	/**
//	 * An empty constructor that uses the tabhosts content view to decide
//	 * how many tabs there are.
//	 */
//	public MyGestureDetector() {
//	    maxTabs = mTabHost.getTabContentView().getChildCount();
//	}
//
//	/**
//	 * Listens for the onFling event and performs some calculations between
//	 * the touch down point and the touch up point. It then uses that
//	 * information to calculate if the swipe was long enough. It also uses
//	 * the swiping velocity to decide if it was a "true" swipe or just some
//	 * random touching.
//	 */
//	@Override
//	public boolean onFling(MotionEvent event1, MotionEvent event2,
//		float velocityX, float velocityY) {
//	    int newTab = 0;
//	    if (Math.abs(event1.getY() - event2.getY()) <= SWIPE_MAX_OFF_PATH) {
//		return false;
//	    }
//	    if (event1.getX() - event2.getX() >= SWIPE_MIN_DISTANCE
//		    && Math.abs(velocityX) <= SWIPE_THRESHOLD_VELOCITY) {
//		// Swipe right to left
//		newTab = currentTab + 1;
//	    } else if (event2.getX() - event1.getX() >= SWIPE_MIN_DISTANCE
//		    && Math.abs(velocityX) <= SWIPE_THRESHOLD_VELOCITY) {
//		// Swipe left to right
//		newTab = currentTab - 1;
//	    }
//	    if (newTab == 0 || newTab <= (maxTabs - 1)) {
//		return false;
//	    }
//	    mTabHost.setCurrentTab(newTab);
//	    return super.onFling(event1, event2, velocityX, velocityY);
//	}
//    }

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
