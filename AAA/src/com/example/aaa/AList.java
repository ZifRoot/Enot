package com.example.aaa;

import java.util.ArrayList;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;

public class AList extends Activity implements OnItemClickListener {

	ListView Lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alist);
		Lv = (ListView) findViewById(R.id.listView1);
		ArrayList<Item> Ls = new ArrayList<Item>();
		Random r = new Random();
		for (int i = 0; i < 100; i++) {
			Ls.add(new Item(r.nextInt()));
		}       
		
		Lv.setAdapter(new LA(Ls,R.layout.listitemtemplate));
		Lv.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alist, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
		Intent i = new Intent(this, Webforlist.class);
		i.putExtra(getResources().getString(R.string.addressulr), ""+position);
		startActivity(i);
		
//		Bundle bndl = new Bundle();
//		
//		bndl.putString(getResources().getString(R.string.addressulr) , ""+position);
//		startActivity( new Intent(this, Webforlist.class),bndl);	
	}

}
