package com.example.aaa;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class AWeb extends Activity implements OnClickListener {

	WebView ww;
	Button b1;
	private Button b2;
	TextView ttt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aweb);
		ttt =  (TextView) findViewById(R.id.textView1);
		ww = (WebView) findViewById(R.id.webView1);		
		ww.setWebViewClient(new WebViewClient()
		{			
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				super.onPageStarted(view, url, favicon);
			}
			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				// TODO Auto-generated method stub
				super.onReceivedError(view, errorCode, description, failingUrl);
			}
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
			}
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				boolean f = false;//super.shouldOverrideUrlLoading(view, url);
				ttt.setText(ttt.getText()+"U "+url+"; R "+f);
				//view.loadUrl("http://google.ru/#q=0");
				return f;				
			}			
		});
		ww.loadUrl("http://ru.wikipedia.org/");
		(b1 = (Button) findViewById(R.id.buttonList)).setOnClickListener(this);		
		(b2 = (Button) findViewById(R.id.button2)).setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.aweb, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    // Check if the key event was the Back button and if there's history
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && ww.canGoBack()) {
	    	ww.goBack();
	        return true;
	    }
	    // If it wasn't the Back key or there's no web page history, bubble up to the default
	    // system behavior (probably exit the activity)
	    return super.onKeyDown(keyCode, event);
	}
}
