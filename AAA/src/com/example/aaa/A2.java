package com.example.aaa;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Spinner;
import android.widget.TextView;

public class A2 extends Activity implements OnItemSelectedListener  {
	Spinner sp;
	TextView tt;
	ImageView En;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_a2);
		sp = (Spinner) findViewById(R.id.spinner1);
		tt = (TextView) findViewById(R.id.textViewtt);
		En = (ImageView) findViewById(R.id.imageEn);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		
		for (ScaleType sc : ImageView.ScaleType.values()) {
			adapter.add(sc.toString());
		}
		
		sp.setAdapter(adapter);
		
		sp.setOnItemSelectedListener(this);	
	
		//sp.sets
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.a2, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		CharSequence str = ((TextView) arg1).getText();
		tt.setText(str);
		En.setScaleType( ImageView.ScaleType.valueOf((String) str));
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
