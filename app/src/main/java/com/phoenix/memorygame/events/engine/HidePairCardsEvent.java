package com.phoenix.memorygame.events.engine;

import com.phoenix.memorygame.events.AbstractEvent;
import com.phoenix.memorygame.events.EventObserver;

public class HidePairCardsEvent extends AbstractEvent {

	public static final String TYPE = HidePairCardsEvent.class.getName();
	public int id1;
	public int id2;

	public HidePairCardsEvent(int id1, int id2) {
		this.id1 = id1;
		this.id2 = id2;
	}

	protected void fire(EventObserver eventObserver) {
		eventObserver.onEvent(this);
	}

	public String getType() {
		return TYPE;
	}

}
