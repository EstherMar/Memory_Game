package com.phoenix.memorygame.events.ui;

import com.phoenix.memorygame.events.AbstractEvent;
import com.phoenix.memorygame.events.EventObserver;

/**
 * When the 'back to menu' was pressed.
 */
public class DifficultySelectedEvent extends AbstractEvent {

	public static final String TYPE = DifficultySelectedEvent.class.getName();

	public final int difficulty;
	
	public DifficultySelectedEvent(int difficulty) {
		this.difficulty = difficulty;
	}
	
	@Override
	protected void fire(EventObserver eventObserver) {
		eventObserver.onEvent(this);
	}

	@Override
	public String getType() {
		return TYPE;
	}

}
