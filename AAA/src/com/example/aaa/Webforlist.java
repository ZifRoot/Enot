package com.example.aaa;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Webforlist extends Activity {

	String str;
	private WebView ww;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		str = savedInstanceState.getString( getResources().getString(R.string.addressulr) , "");
		setContentView(R.layout.activity_webforlist);
		
		ww = (WebView) findViewById(R.id.webView1);		
		ww.setWebViewClient(new WebViewClient());
		ww.loadUrl("http://ru.wikipedia.org/"+str);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.webforlist, menu);
		return true;
	}

}
