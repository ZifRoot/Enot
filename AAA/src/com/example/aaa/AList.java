package com.example.aaa;

import java.util.ArrayList;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.database.DataSetObserver;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class AList extends Activity {

	ListView Lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alist);
		Lv = (ListView) findViewById(R.id.listView1);
		ArrayList<Item> Ls = new ArrayList<Item>();
		Random r = new Random();
		Ls.add(new Item(r.nextInt()));
		Ls.add(new Item(r.nextInt()));
		Ls.add(new Item(r.nextInt()));
		Ls.add(new Item(r.nextInt()));
		Lv.setAdapter(new LA(Ls));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alist, menu);
		return true;
	}

}
