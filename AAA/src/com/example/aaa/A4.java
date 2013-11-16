package com.example.aaa;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class A4 extends Activity implements OnCheckedChangeListener {

	private ImageView En;
	private RadioGroup RG;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_a4);
		En = (ImageView) findViewById(R.id.imageEn1);
		RG = (RadioGroup) findViewById(R.id.radioGroup1);
		RG.setOnCheckedChangeListener(this);
		for (ScaleType sc : ImageView.ScaleType.values())
		{
			RadioButton rb = new RadioButton(this);
			rb.setText(sc.toString());
			RG.addView(rb);		
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.a4, menu);
		return true;
	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		En.setScaleType( ImageView.ScaleType.valueOf(
				(String) ((RadioButton)arg0.findViewById(arg1)).getText()));
	}

}
