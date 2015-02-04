package com.example.galeriadomuseudomjoaovi;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Tour extends Activity {

	private WebView webview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tour);
		
		webview = (WebView) findViewById(R.id.webView1);
		webview.loadUrl("file:///android_asset/panorama.html");
		webview.setWebViewClient(new WebViewClient());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tour, menu);
		return true;
	}

}
