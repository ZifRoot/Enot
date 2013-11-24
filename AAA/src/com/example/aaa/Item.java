package com.example.aaa;

import java.util.Random;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;


public class Item 
{
	public String text;
	public float size;
	public int color;
	View Vback;
	TextView Vtext;
	public Item(int f){
		Random r = new Random(f);
		size = 20;
		color =  Color.argb(255, r.nextInt(255), r.nextInt(255), r.nextInt(255));
		text = "";
		for (int i = 0; i < 10; i++) {
			text+= (char)(r.nextInt('z'-'a'-1)+'a');
		}
	}
}
