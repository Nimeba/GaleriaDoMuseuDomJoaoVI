package util;

import android.content.Context;

public class App {
	private static Context context;

	public static Context getContext() {
		return context;
	}

	public static void setContext(Context context) {
		App.context = context;
	}

}
