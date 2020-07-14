package com.phoenix.memorygame.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.phoenix.memorygame.R;
import com.phoenix.memorygame.common.Music;
import com.phoenix.memorygame.common.Shared;
import com.phoenix.memorygame.events.ui.StartEvent;
import com.phoenix.memorygame.ui.PopupManager;
import com.phoenix.memorygame.utils.Utils;

public class MenuFragment extends Fragment {

	private ImageView mTitle;
	private ImageView mStartGameButton;
	private ImageView mStartButtonLights;
	private ImageView mTooltip;
	private ImageView mSettingsGameButton;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.menu_fragment, container, false);
		mTitle = (ImageView) view.findViewById(R.id.title);
		mStartGameButton = (ImageView) view.findViewById(R.id.start_game_button);
		mSettingsGameButton = (ImageView) view.findViewById(R.id.settings_game_button);
		mSettingsGameButton.setSoundEffectsEnabled(false);
		mSettingsGameButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				PopupManager.showPopupSettings();
			}
		});
		mStartButtonLights = (ImageView) view.findViewById(R.id.start_game_button_lights);
		mTooltip = (ImageView) view.findViewById(R.id.tooltip);
		mStartGameButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				animateAllAssetsOff(new AnimatorListenerAdapter() {
					public void onAnimationEnd(Animator animation) {
						Shared.eventBus.notify(new StartEvent());
					}
				});
			}
		});
		AdView mAdView = (AdView) view.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);

		startLightsAnimation();

		startTootipAnimation();

		Music.playBackgroundMusic();
		return view;
	}

	protected void animateAllAssetsOff(AnimatorListenerAdapter adapter) {

		ObjectAnimator titleAnimator = ObjectAnimator.ofFloat(mTitle, "translationY", Utils.px(-200));
		titleAnimator.setInterpolator(new AccelerateInterpolator(2));
		titleAnimator.setDuration(300);

		ObjectAnimator lightsAnimatorX = ObjectAnimator.ofFloat(mStartButtonLights, "scaleX", 0f);
		ObjectAnimator lightsAnimatorY = ObjectAnimator.ofFloat(mStartButtonLights, "scaleY", 0f);

		ObjectAnimator tooltipAnimator = ObjectAnimator.ofFloat(mTooltip, "alpha", 0f);
		tooltipAnimator.setDuration(100);

		ObjectAnimator settingsAnimator = ObjectAnimator.ofFloat(mSettingsGameButton, "translationY", Utils.px(120));
		settingsAnimator.setInterpolator(new AccelerateInterpolator(2));
		settingsAnimator.setDuration(300);

		ObjectAnimator startButtonAnimator = ObjectAnimator.ofFloat(mStartGameButton, "translationY", Utils.px(130));
		startButtonAnimator.setInterpolator(new AccelerateInterpolator(2));
		startButtonAnimator.setDuration(300);

		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.playTogether(titleAnimator, lightsAnimatorX, lightsAnimatorY, tooltipAnimator, settingsAnimator, startButtonAnimator);
		animatorSet.addListener(adapter);
		animatorSet.start();
	}

	private void startTootipAnimation() {
		ObjectAnimator scaleY = ObjectAnimator.ofFloat(mTooltip, "scaleY", 0.8f);
		scaleY.setDuration(200);
		ObjectAnimator scaleYBack = ObjectAnimator.ofFloat(mTooltip, "scaleY", 1f);
		scaleYBack.setDuration(500);
		scaleYBack.setInterpolator(new BounceInterpolator());
		final AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.setStartDelay(1000);
		animatorSet.playSequentially(scaleY, scaleYBack);
		animatorSet.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				animatorSet.setStartDelay(2000);
				animatorSet.start();
			}
		});
		mTooltip.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		animatorSet.start();
	}

	private void startLightsAnimation() {
		ObjectAnimator animator = ObjectAnimator.ofFloat(mStartButtonLights, "rotation", 0f, 360f);
		animator.setInterpolator(new AccelerateDecelerateInterpolator());
		animator.setDuration(6000);
		animator.setRepeatCount(ValueAnimator.INFINITE);
		mStartButtonLights.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		animator.start();
	}
}
