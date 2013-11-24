package com.example.aaa;
import java.util.ArrayList;


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


public class LA extends BaseAdapter {

	ArrayList<Item> Ls;
	int v;
	public LA(ArrayList<Item> Ls, int listitemtemplate){
		this.Ls = Ls;
		this.v = listitemtemplate;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Ls.size();
	}
	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return Ls.get(arg0);
	}
	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		
		Item it = Ls.get(arg0);
		
		if(arg1 == null) {
			 arg1 = View.inflate(arg2.getContext(), v, null );//new Button(arg2.getContext());
					 
		}
		 it.Vback = it.Vback==null?it.Vback: arg1.findViewById(R.id.litback);
		 it.Vtext = it.Vtext==null?it.Vtext: (TextView) arg1.findViewById(R.id.littext);	
		 
		it.Vtext.setText(""+arg0 + " | " + it.text);
		it.Vtext.setTextSize(it.size);
		it.Vback.setBackgroundColor(it.color);		
		return arg1;
	}


}
