package com.reactnativenavigation.parse;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.reactnativenavigation.utils.TypefaceLoader;

import org.json.JSONObject;

public class Options implements DEFAULT_VALUES {

    @NonNull
    public static Options parse(TypefaceLoader typefaceManager, JSONObject json) {
        return parse(typefaceManager, json, new Options());
    }

    @NonNull
    public static Options parse(TypefaceLoader typefaceManager, JSONObject json, @NonNull Options defaultOptions) {
        Options result = new Options();
        if (json == null) return result;

        result.topBarOptions = TopBarOptions.parse(typefaceManager, json.optJSONObject("topBar"));
        result.topTabsOptions = TopTabsOptions.parse(json.optJSONObject("topTabs"));
        result.topTabOptions = TopTabOptions.parse(typefaceManager, json.optJSONObject("topTab"));
        result.bottomTabOptions = BottomTabOptions.parse(json.optJSONObject("bottomTab"));
        result.bottomTabsOptions = BottomTabsOptions.parse(json.optJSONObject("bottomTabs"));
        result.overlayOptions = OverlayOptions.parse(json.optJSONObject("overlay"));
        result.fabOptions = FabOptions.parse(json.optJSONObject("fab"));
        result.fabMenuOptions = FabOptions.parse(json.optJSONObject("fabMenu"));

        return result.withDefaultOptions(defaultOptions);
    }

    @NonNull public TopBarOptions topBarOptions = new TopBarOptions();
    @NonNull public TopTabsOptions topTabsOptions = new TopTabsOptions();
    @NonNull public TopTabOptions topTabOptions = new TopTabOptions();
    @NonNull public BottomTabOptions bottomTabOptions = new BottomTabOptions();
    @NonNull public BottomTabsOptions bottomTabsOptions = new BottomTabsOptions();
    @NonNull public OverlayOptions overlayOptions = new OverlayOptions();
    @NonNull public FabOptions fabOptions = new FabOptions();
    @NonNull public FabOptions fabMenuOptions = new FabOptions();

    void setTopTabIndex(int i) {
        topTabOptions.tabIndex = i;
    }

    @CheckResult
    public Options copy() {
        Options result = new Options();
        result.topBarOptions.mergeWith(topBarOptions);
        result.topTabsOptions.mergeWith(topTabsOptions);
        result.topTabOptions.mergeWith(topTabOptions);
        result.bottomTabOptions.mergeWith(bottomTabOptions);
        result.bottomTabsOptions.mergeWith(bottomTabsOptions);
        result.fabOptions.mergeWith(fabOptions);
        result.fabMenuOptions.mergeWith(fabMenuOptions);
        return result;
    }

    @CheckResult
	public Options mergeWith(final Options other) {
        Options result = copy();
        result.topBarOptions.mergeWith(other.topBarOptions);
        result.topTabsOptions.mergeWith(other.topTabsOptions);
        result.topTabOptions.mergeWith(other.topTabOptions);
        result.bottomTabOptions.mergeWith(other.bottomTabOptions);
        result.bottomTabsOptions.mergeWith(other.bottomTabsOptions);
        result.fabOptions.mergeWith(other.fabOptions);
        result.fabMenuOptions.mergeWith(other.fabMenuOptions);
        return result;
    }

    Options withDefaultOptions(final Options other) {
        topBarOptions.mergeWithDefault(other.topBarOptions);
        topTabOptions.mergeWithDefault(other.topTabOptions);
        topTabsOptions.mergeWithDefault(other.topTabsOptions);
        bottomTabOptions.mergeWithDefault(other.bottomTabOptions);
        bottomTabsOptions.mergeWithDefault(other.bottomTabsOptions);
        fabOptions.mergeWithDefault(other.fabOptions);
        fabMenuOptions.mergeWithDefault(other.fabMenuOptions);
        return this;
    }

    public Options clearTopBarOptions() {
        topBarOptions = new TopBarOptions();
        return this;
    }

    public Options clearBottomTabsOptions() {
        bottomTabsOptions = new BottomTabsOptions();
        return this;
    }
}
