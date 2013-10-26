package com.example.aaa;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;


public class A1 extends Activity implements OnClickListener {

	List<View> Find(View _v)
	{
		ArrayList<View> vs = new ArrayList<View>();
		if(_v instanceof Button) vs.add(_v);
		ViewGroup v;
        if (_v instanceof ViewGroup) v = (ViewGroup ) _v;
        else return vs;
		
		int n = v.getChildCount();
		
		for(int i = 0; i < n; i++)
		{
			vs.addAll(Find(v.getChildAt(i)));
		}		
		return vs;		
	}
	
	TextView vL;
	View G; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_a1);
		vL = (TextView) findViewById(R.id.textViewtt);
		G = findViewById(R.id.L_A1);
		findViewById(R.id.buttonRed).setOnClickListener(this);
		findViewById(R.id.buttonGreen).setOnClickListener(this);
		findViewById(R.id.buttonBlue).setOnClickListener(this);
		findViewById(R.id.buttonUptext).setOnClickListener(this);
		findViewById(R.id.buttonDownText).setOnClickListener(this);
		
		//for (View v : Find(findViewById(R.layout.activity_a1))) { v.setOnClickListener(this); }		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.a1, menu);
		return true;
	}

	@Override
	public void onClick(View v) {		
		switch (v.getId()) {
		case R.id.buttonRed:
			vL.setTextColor(Color.RED);
			break;
		case R.id.buttonGreen:
			vL.setTextColor(Color.GREEN);
			break;
		case R.id.buttonBlue:
			vL.setTextColor(Color.BLUE);
			break;
		case R.id.buttonUptext: 
			vL.setTextSize(vL.getTextSize()*1.25f);
			break;
		case R.id.buttonDownText:
			vL.setTextSize(vL.getTextSize()*0.8f);
			break;

		default:
			break;
		}
		G.requestLayout();
		vL.requestLayout();
	}
}
