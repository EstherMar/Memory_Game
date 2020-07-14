package com.phoenix.memorygame;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.phoenix.memorygame.common.Shared;
import com.phoenix.memorygame.engine.Engine;
import com.phoenix.memorygame.engine.ScreenController;
import com.phoenix.memorygame.engine.ScreenController.Screen;
import com.phoenix.memorygame.events.EventBus;
import com.phoenix.memorygame.events.ui.BackGameEvent;
import com.phoenix.memorygame.ui.PopupManager;
import com.phoenix.memorygame.utils.Utils;

public class MainActivity extends FragmentActivity {

	private ImageView mBackgroundImage;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Shared.context = getApplicationContext();
		Shared.engine = Engine.getInstance();
		Shared.eventBus = EventBus.getInstance();
		setContentView(R.layout.activity_main);
		mBackgroundImage = (ImageView) findViewById(R.id.background_image);
		Shared.activity = this;
		Shared.engine.start();
		Shared.engine.setBackgroundImageView(mBackgroundImage);

		setBackgroundImage();

		ScreenController.getInstance().openScreen(Screen.MENU);
	}

	protected void onDestroy() {
		Shared.engine.stop();
		super.onDestroy();
	}

	public void onBackPressed() {
		if (PopupManager.isShown()) {
			PopupManager.closePopup();
			if (ScreenController.getLastScreen() == Screen.GAME) {
				Shared.eventBus.notify(new BackGameEvent());
			}
		} else if (ScreenController.getInstance().onBack()) {
			super.onBackPressed();
		}
	}

	private void setBackgroundImage() {
		Bitmap bitmap = Utils.scaleDown(R.drawable.background, Utils.screenWidth(), Utils.screenHeight());
		bitmap = Utils.crop(bitmap, Utils.screenHeight(), Utils.screenWidth());
		bitmap = Utils.downscaleBitmap(bitmap, 2);
		mBackgroundImage.setImageBitmap(bitmap);
	}

}
