package com.phoenix.memorygame.fragments;


import androidx.fragment.app.Fragment;

import com.phoenix.memorygame.events.EventObserver;
import com.phoenix.memorygame.events.engine.FlipDownCardsEvent;
import com.phoenix.memorygame.events.engine.GameWonEvent;
import com.phoenix.memorygame.events.engine.HidePairCardsEvent;
import com.phoenix.memorygame.events.ui.BackGameEvent;
import com.phoenix.memorygame.events.ui.FlipCardEvent;
import com.phoenix.memorygame.events.ui.NextGameEvent;
import com.phoenix.memorygame.events.ui.ResetBackgroundEvent;
import com.phoenix.memorygame.events.ui.ThemeSelectedEvent;
import com.phoenix.memorygame.events.ui.DifficultySelectedEvent;
import com.phoenix.memorygame.events.ui.StartEvent;

public class BaseFragment extends Fragment implements EventObserver {

	public void onEvent(FlipCardEvent event) {
		throw new UnsupportedOperationException();
	}

	public void onEvent(DifficultySelectedEvent event) {
		throw new UnsupportedOperationException();
	}

	public void onEvent(HidePairCardsEvent event) {
		throw new UnsupportedOperationException();
	}

	public void onEvent(FlipDownCardsEvent event) {
		throw new UnsupportedOperationException();
	}

	public void onEvent(StartEvent event) {
		throw new UnsupportedOperationException();
	}

	public void onEvent(ThemeSelectedEvent event) {
		throw new UnsupportedOperationException();
	}

	public void onEvent(GameWonEvent event) {
		throw new UnsupportedOperationException();
	}

	public void onEvent(BackGameEvent event) {
		throw new UnsupportedOperationException();
	}

	public void onEvent(NextGameEvent event) {
		throw new UnsupportedOperationException();
	}

	public void onEvent(ResetBackgroundEvent event) {
		throw new UnsupportedOperationException();
	}

}
