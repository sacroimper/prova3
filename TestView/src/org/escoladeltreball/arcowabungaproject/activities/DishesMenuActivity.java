package org.escoladeltreball.arcowabungaproject.activities;

import org.escoladeltreball.arcowabungaproject.R;
import org.escoladeltreball.arcowabungaproject.adapters.Adaptador;
import org.escoladeltreball.arcowabungaproject.model.GrupoDeItems;
import org.escoladeltreball.arcowabungaproject.visualeffects.TabsSwipeAnimation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
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
	// Para añadir swipe action al la Expandable list
	listView.setOnTouchListener(this);

	// Pestañas
	makeTabs();

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
	
	//TabsColor
	tabs.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#3be0d0"));
	tabs.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#3be0d0"));
	tabs.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#3be0d0"));

	int tabCount = tabs.getTabWidget().getTabCount();
	for (int i = 0; i < tabCount; i++) {
	    final View view = tabs.getTabWidget().getChildTabViewAt(i);
	    if (view != null) {
		// reduce height of the tab
		view.getLayoutParams().height *= 0.66;

		// get title text view
		final View textView = view.findViewById(android.R.id.title);
		if (textView instanceof TextView) {
		    // just in case check the type

		    // center text
		    ((TextView) textView).setGravity(Gravity.CENTER);
		    // wrap text
		    ((TextView) textView).setSingleLine(false);

		    // explicitly set layout parameters
		    textView.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
		    textView.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;

		}
	    }
	}
	tabs.setCurrentTab(0);

	actualTab = (LinearLayout) findViewById(R.id.tab1);
	tabs.setOnTabChangedListener(new OnTabChangeListener() {

	    LinearLayout t1l = (LinearLayout) findViewById(R.id.tab1);
	    LinearLayout t2l = (LinearLayout) findViewById(R.id.tab2);
	    LinearLayout t3l = (LinearLayout) findViewById(R.id.tab3);

	    public void onTabChanged(String tabId) {
		if (actualTab.equals(t1l) && tabId.equals("mitab2")) {
		    t1l.setAnimation(TabsSwipeAnimation.outToLeftAnimation());
		    t2l.setAnimation(TabsSwipeAnimation.inFromRightAnimation());
		    actualTab = t2l;
		} else if (actualTab.equals(t2l) && tabId.equals("mitab1")) {
		    t2l.setAnimation(TabsSwipeAnimation.leftToLeftAnimation());
		    t1l.setAnimation(TabsSwipeAnimation
			    .leftFromRightAnimation());
		    actualTab = t1l;
		} else if (actualTab.equals(t2l) && tabId.equals("mitab3")) {
		    t2l.setAnimation(TabsSwipeAnimation.outToLeftAnimation());
		    t3l.setAnimation(TabsSwipeAnimation.inFromRightAnimation());
		    actualTab = t3l;
		} else if (actualTab.equals(t3l) && tabId.equals("mitab2")) {
		    t3l.setAnimation(TabsSwipeAnimation.leftToLeftAnimation());
		    t2l.setAnimation(TabsSwipeAnimation
			    .leftFromRightAnimation());
		    actualTab = t2l;
		} else if (actualTab.equals(t1l) && tabId.equals("mitab3")) {
		    t1l.setAnimation(TabsSwipeAnimation.outToLeftAnimation());
		    t3l.setAnimation(TabsSwipeAnimation.inFromRightAnimation());
		    actualTab = t3l;
		} else if (actualTab.equals(t3l) && tabId.equals("mitab1")) {
		    t3l.setAnimation(TabsSwipeAnimation.leftToLeftAnimation());
		    t1l.setAnimation(TabsSwipeAnimation
			    .leftFromRightAnimation());
		    actualTab = t1l;
		}
	    }
	});
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

    /**
     * Move swipe touch acction
     * 
     * @param direction
     */
    public void switchTabs(boolean direction) {

	if (direction) {
	    if (tabs.getCurrentTab() != 0) {
		tabs.setCurrentTab(tabs.getCurrentTab() - 1);
	    }
	} else {
	    if (tabs.getCurrentTab() != (tabs.getTabWidget().getTabCount() - 1)) {
		tabs.setCurrentTab(tabs.getCurrentTab() + 1);
	    } 
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

    private void setupTab(final View view, final String tag, int id) {
	View tabview = createTabView(tabs.getContext(), tag);
	TabSpec setContent = tabs.newTabSpec(tag).setIndicator(tabview)
		.setContent(new TabContentFactory() {
		    public View createTabContent(String tag) {
			return view;
		    }
		});
	tabs.addTab(setContent);
    }

    private static View createTabView(final Context context, final String text) {
	View view = LayoutInflater.from(context)
		.inflate(R.layout.tabs_bg, null);
	TextView tv = (TextView) view.findViewById(R.id.tabsText);
	tv.setText(text);
	return view;
    }

}
