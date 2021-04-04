package com.swmansion.rnscreens;

import android.widget.SearchView;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = ScreenStackViewManager.REACT_CLASS)
public class RNSSearchBarManager extends SimpleViewManager<SearchView> {

  public static final String REACT_CLASS = "RNSSearchBar";
  ReactApplicationContext mCallerContext;

  public RNSSearchBarManager(ReactApplicationContext reactContext) {
    mCallerContext = reactContext;
  }

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  @Override
  public SearchView createViewInstance(ThemedReactContext context) {
    final SearchView searchView = new SearchView(context);
    searchView.setIconifiedByDefault(false);

    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        WritableMap event = Arguments.createMap();
        event.putString("searchText", query);
        context.getJSModule(RCTEventEmitter.class).receiveEvent(searchView.getId(), "topSearchButtonPress", event);
        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        WritableMap event = Arguments.createMap();
        event.putString("text", newText);
        context.getJSModule(RCTEventEmitter.class).receiveEvent(searchView.getId(), "topChange", event);
        return false;
      }
    });

    return searchView;
  }
}