//package org.escoladeltreball.arcowabungaproject.activities;
//
//import org.escoladeltreball.arcowabungaproject.StandardObjects.TabsMaker2;
//import org.escoladeltreball.layouttest.TabsMaker;
//
//import android.view.View;
//import android.widget.TabHost;
//import android.widget.TabHost.OnTabChangeListener;
//
//public class OwnOnTabChangeListener implements OnTabChangeListener {
//
//
//    @Override
//    public void onTabChanged(String tabId) {
//		currentView = mTabHost.getCurrentView();
//		
//		if (mTabHost.getCurrentTab() >= currentTab) {
//			previousView.setAnimation(TabsMaker2outToLeftAnimation());
//			currentView.setAnimation(inFromRightAnimation());
//		} else if (mTabHost.getCurrentTab() < currentTab) {
//			previousView.setAnimation(outToRightAnimation());
//			currentView.setAnimation(inFromLeftAnimation());
//		}
//		
//		previousView = currentView;
//		currentTab = mTabHost.getCurrentTab();
//
//		View tab1 = mTabHost.findViewWithTag("Tab 1");
//		View tab2 = mTabHost.findViewWithTag("Tab 2");
//		View tab3 = mTabHost.findViewWithTag("Tab 3");
//
//		if (actualTab.equals(viewTab1) && tabId.equals("Tab 2")) {
//			TabsMaker.changeColor(tab1, false);
//			TabsMaker.changeColor(tab2, true);
//			actualTab = viewTab2;
//		} else if (actualTab.equals(viewTab2) && tabId.equals("Tab 1")) {
//			TabsMaker.changeColor(tab2, false);
//			TabsMaker.changeColor(tab1, true);
//			actualTab = viewTab1;
//		} else if (actualTab.equals(viewTab2) && tabId.equals("Tab 3")) {
//			TabsMaker.changeColor(tab2, false);
//			TabsMaker.changeColor(tab3, true);
//			actualTab = viewTab3;
//		} else if (actualTab.equals(viewTab3) && tabId.equals("Tab 2")) {
//			TabsMaker.changeColor(tab3, false);
//			TabsMaker.changeColor(tab2, true);
//			actualTab = viewTab2;
//		} else if (actualTab.equals(viewTab1) && tabId.equals("Tab 3")) {
//			TabsMaker.changeColor(tab1, false);
//			TabsMaker.changeColor(tab3, true);
//			actualTab = viewTab3;
//		} else if (actualTab.equals(viewTab3) && tabId.equals("Tab 1")) {
//			TabsMaker.changeColor(tab3, false);
//			TabsMaker.changeColor(tab1, true);
//			actualTab = viewTab1;
//		}
//	
//    }
//
//    @Override
//    public void onTabChanged(String arg0) {
//	// TODO Auto-generated method stub
//	
//    }
//
//    //====================
//    // CONSTANTS
//    //====================
//
//    //====================
//    // ATTRIBUTES
//    //====================
//
//    //====================
//    // CONSTRUCTORS
//    //====================
//
//    //====================
//    // PUBLIC METHODS
//    //====================
//
//    //====================
//    // PROTECTED METHODS
//    //====================
//
//    //====================
//    // PRIVATE METHODS
//    //====================
//
//    //====================
//    // OVERRIDE METHODS
//    //====================
//
//    //====================
//    // GETTERS & SETTERS
//    //====================
//}
