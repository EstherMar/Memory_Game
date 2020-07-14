package com.phoenix.memorygame.events.engine;

import com.phoenix.memorygame.events.AbstractEvent;
import com.phoenix.memorygame.events.EventObserver;
import com.phoenix.memorygame.model.GameState;

public class GameWonEvent extends AbstractEvent {

	public static final String TYPE = GameWonEvent.class.getName();

	public GameState gameState;

	
	public GameWonEvent(GameState gameState) {
		this.gameState = gameState;
	}

	protected void fire(EventObserver eventObserver) {
		eventObserver.onEvent(this);
	}

	public String getType() {
		return TYPE;
	}

}
