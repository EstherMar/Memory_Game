package com.phoenix.memorygame.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.phoenix.memorygame.R;
import com.phoenix.memorygame.common.Shared;
import com.phoenix.memorygame.events.engine.FlipDownCardsEvent;
import com.phoenix.memorygame.events.engine.GameWonEvent;
import com.phoenix.memorygame.events.engine.HidePairCardsEvent;
import com.phoenix.memorygame.model.Game;
import com.phoenix.memorygame.ui.BoardView;
import com.phoenix.memorygame.ui.PopupManager;
import com.phoenix.memorygame.utils.Clock;
import com.phoenix.memorygame.utils.Clock.OnTimerCount;
import com.phoenix.memorygame.utils.FontLoader;
import com.phoenix.memorygame.utils.FontLoader.Font;

public class GameFragment extends BaseFragment {

	private BoardView mBoardView;
	private TextView mTime;
	private ImageView mTimeImage;
	private LinearLayout ads;
	private AdView mAdView;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup view = (ViewGroup) inflater.inflate(R.layout.game_fragment, container, false);
		view.setClipChildren(false);
		((ViewGroup)view.findViewById(R.id.game_board)).setClipChildren(false);
		mTime = (TextView) view.findViewById(R.id.time_bar_text);
		mTimeImage = (ImageView) view.findViewById(R.id.time_bar_image);
		FontLoader.setTypeface(Shared.context, new TextView[] {mTime}, Font.GROBOLD);
		mBoardView = BoardView.fromXml(getActivity().getApplicationContext(), view);
		FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.game_container);
		frameLayout.addView(mBoardView);
		frameLayout.setClipChildren(false);
		AdView mAdView = (AdView) view.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);
		buildBoard();
		Shared.eventBus.listen(FlipDownCardsEvent.TYPE, this);
		Shared.eventBus.listen(HidePairCardsEvent.TYPE, this);
		Shared.eventBus.listen(GameWonEvent.TYPE, this);
		
		return view;
	}

	public void onDestroy() {
		Shared.eventBus.unlisten(FlipDownCardsEvent.TYPE, this);
		Shared.eventBus.unlisten(HidePairCardsEvent.TYPE, this);
		Shared.eventBus.unlisten(GameWonEvent.TYPE, this);
		super.onDestroy();
	}

	private void buildBoard() {
		Game game = Shared.engine.getActiveGame();
		int time = game.boardConfiguration.time;
		setTime(time);
		mBoardView.setBoard(game);
		
		startClock(time);
	}
	
	private void setTime(int time) {
		int min = time / 60;
		int sec = time - min*60;
		mTime.setText(" " + String.format("%02d", min) + ":" + String.format("%02d", sec));
	}

	private void startClock(int sec) {
		Clock clock = Clock.getInstance();
		clock.startTimer(sec*1000, 1000, new OnTimerCount() {
			
			@Override
			public void onTick(long millisUntilFinished) {
				setTime((int) (millisUntilFinished/1000));
			}
			
			@Override
			public void onFinish() {
				setTime(0);
			}
		});
	}

	@Override
	public void onEvent(GameWonEvent event) {
		mTime.setVisibility(View.GONE);
		mTimeImage.setVisibility(View.GONE);
		PopupManager.showPopupWon(event.gameState);
	}

	@Override
	public void onEvent(FlipDownCardsEvent event) {
		mBoardView.flipDownAll();
	}

	@Override
	public void onEvent(HidePairCardsEvent event) {
		mBoardView.hideCards(event.id1, event.id2);
	}

}
