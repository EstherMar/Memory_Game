package com.phoenix.memorygame.model;

import java.util.Map;

import android.graphics.Bitmap;

import com.phoenix.memorygame.common.Shared;
import com.phoenix.memorygame.themes.Themes;
import com.phoenix.memorygame.utils.Utils;

public class BoardArrangment {

	public Map<Integer, Integer> pairs;

	public Map<Integer, String> tileUrls;

	public Bitmap getTileBitmap(int id, int size) {
		String string = tileUrls.get(id);
		if (string.contains(Themes.URI_DRAWABLE)) {
			String drawableResourceName = string.substring(Themes.URI_DRAWABLE.length());
			int drawableResourceId = Shared.context.getResources().getIdentifier(drawableResourceName, "drawable", Shared.context.getPackageName());
			Bitmap bitmap = Utils.scaleDown(drawableResourceId, size, size);
			return Utils.crop(bitmap, size, size);
		}
		return null;
	}

	public boolean isPair(int id1, int id2) {
		Integer integer = pairs.get(id1);
		if (integer == null) {
			// TODO Report this bug!!!
			return false;
		}
		return integer.equals(id2);
	}

}
