package com.example.aaa;
import java.util.ArrayList;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;



public class LA extends BaseAdapter {

	ArrayList<Item> Ls;
	public LA(ArrayList<Item> Ls){
		this.Ls = Ls;
		
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
		return 0;
	}
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		return null;
	}


}
