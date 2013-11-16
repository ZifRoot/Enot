package com.example.aaa;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB) public class A3 extends Activity implements TextWatcher {

	TextView tt;
	EditText et;
	TextView LIP;
	View WL;
	Button GoB;
	ProgressBar progressbar;
	WebView ww;
	Map<CharSequence, Integer> Ms;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_a3);
		
		tt = (TextView) findViewById(R.id.LisenTTT);
		this.et = (EditText) findViewById(R.id.TTT);
		GoB = (Button) findViewById(R.id.GoButton);
		progressbar = (ProgressBar) findViewById(R.id.progressBar2);
		ww = (WebView) findViewById(R.id.webView1);
		LIP = (TextView) findViewById(R.id.LInputtype);
		WL= findViewById(R.id.WL);
		
		et.addTextChangedListener(this);
		
		tt.setText("Input type: "+et.getInputType());
		
		ww.setWebViewClient(new WebViewClient());
		
		GoB.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String url = et.getText().toString();				
				if(TextUtils.isEmpty(url)) Toast.makeText(A3.this, "123", Toast.LENGTH_LONG).show();
				else{
					
				ww.loadUrl(url);
				
				}
			}
		});
		
		for (Field iterable_element : InputType.class.getFields()) {
		LIP.setText(LIP.getText()+"\n"+iterable_element.getName());	
		}
		
		Ms = new HashMap<CharSequence, Integer>();
		Ms.put("@", InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
		Ms.put("h", InputType.TYPE_TEXT_VARIATION_URI);
		
		
		//et.setVisibility()
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.a3, menu);
		return true;
	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
	//	try {
			if(count==0){this.et.setInputType(InputType.TYPE_TEXT_VARIATION_URI);return ;}
			CharSequence ss = ""+s.charAt(0);
			tt.setText("Input type: "+et.getInputType() + " ;s:" + s +" ; start: "+ start + " ;before: " + before +" ;count: "+ count +
					" ;getSelectionStart(): " + et.getSelectionStart() + 
					" ;getSelectionEnd(): " + et.getSelectionEnd() + " ;ss: " + ss);
			
			
			
			Integer i = Ms.get(ss);
			if(i==null)i=0;
			if(Character.isDigit(ss.charAt(0))){i=InputType.TYPE_CLASS_NUMBER;}
			if(ss.charAt(0)=='+'){i=InputType.TYPE_CLASS_PHONE;}
			if(ss.charAt(0)=='#'){i=InputType.TYPE_NUMBER_VARIATION_PASSWORD;}
			if(i != 0)this.et.setInputType((int)i);
			if(i == InputType.TYPE_TEXT_VARIATION_URI){
				GoB.setVisibility(View.VISIBLE);
				LIP.setVisibility(View.GONE);
				WL.setVisibility(View.VISIBLE);
			}
			else{
				GoB.setVisibility(View.GONE);
				LIP.setVisibility(View.VISIBLE);
				WL.setVisibility(View.GONE);
			}
			
//		} catch (Exception e) {			tt.setText("E: " + e.toString());		}
		
	}
	

}
