package com.phoenix.memorygame.events.engine;

import com.phoenix.memorygame.events.AbstractEvent;
import com.phoenix.memorygame.events.EventObserver;

public class FlipDownCardsEvent extends AbstractEvent {

	public static final String TYPE = FlipDownCardsEvent.class.getName();

	public FlipDownCardsEvent() {
	}

	protected void fire(EventObserver eventObserver) {
		eventObserver.onEvent(this);
	}

	public String getType() {
		return TYPE;
	}

}
