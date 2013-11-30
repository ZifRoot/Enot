package com.example.aaa;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class Webforlist extends Activity {

	String str;
	private WebView ww;
	private TextView text1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		str =savedInstanceState==null?
				null:
					savedInstanceState.getString( getResources().getString(R.string.addressulr) , null);
		
		setContentView(R.layout.activity_webforlist);
		if(TextUtils.isEmpty(str))
			str = this.getIntent().getExtras().getString(getResources().getString(R.string.addressulr), "");
		ww = (WebView) findViewById(R.id.webView1);	
		text1 = (TextView)findViewById(R.id.textView1);
		ww.setWebViewClient(new WebViewClient());
		str = "http://ru.wikipedia.org/"+str;
		text1.setText(str);
		ww.loadUrl(str);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.webforlist, menu);
		return true;
	}

}
