package com.example.aaa;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends Activity {
	
	List<Button> Bs = new ArrayList<Button>();
	
/*	List<View> Find(View _v)
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
	}*/
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button)findViewById(R.id.button_colorx);
        StateListDrawable b1 = (StateListDrawable) b.getBackground();
        //b1.
        
        b.setText( b1.getClass().getName());
        
        Bs.add((Button)findViewById(R.id.buttonA1));
        Bs.add((Button)findViewById(R.id.buttonA2));
        Bs.add((Button)findViewById(R.id.buttonA3));
        
        Bs.get(0).setOnClickListener(new BL(A1.class));
        Bs.get(1).setOnClickListener(new BL(A2.class));
        Bs.get(2).setOnClickListener(new BL(A3.class));
        
        		
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public class BL implements OnClickListener{

    	public BL(Class<?> ca){
    		Ca = ca;
    		}
    	Class<?> Ca;
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startActivity( new Intent(MainActivity.this,Ca));
		}
	}
}


