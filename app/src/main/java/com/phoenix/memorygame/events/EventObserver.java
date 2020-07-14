package com.phoenix.memorygame.events;

import com.phoenix.memorygame.events.engine.FlipDownCardsEvent;
import com.phoenix.memorygame.events.engine.GameWonEvent;
import com.phoenix.memorygame.events.engine.HidePairCardsEvent;
import com.phoenix.memorygame.events.ui.BackGameEvent;
import com.phoenix.memorygame.events.ui.DifficultySelectedEvent;
import com.phoenix.memorygame.events.ui.FlipCardEvent;
import com.phoenix.memorygame.events.ui.NextGameEvent;
import com.phoenix.memorygame.events.ui.ResetBackgroundEvent;
import com.phoenix.memorygame.events.ui.StartEvent;
import com.phoenix.memorygame.events.ui.ThemeSelectedEvent;


public interface EventObserver {

	void onEvent(FlipCardEvent event);

	void onEvent(DifficultySelectedEvent event);

	void onEvent(HidePairCardsEvent event);

	void onEvent(FlipDownCardsEvent event);

	void onEvent(StartEvent event);

	void onEvent(ThemeSelectedEvent event);

	void onEvent(GameWonEvent event);

	void onEvent(BackGameEvent event);

	void onEvent(NextGameEvent event);

	void onEvent(ResetBackgroundEvent event);

}
