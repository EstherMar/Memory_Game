package com.phoenix.memorygame;

import android.app.Application;

import com.phoenix.memorygame.utils.FontLoader;

public class GameApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		FontLoader.loadFonts(this);

	}
}
