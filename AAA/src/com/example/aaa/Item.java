package com.example.aaa;

import java.util.Random;

import android.graphics.Color;


public class Item 
{
	public String text;
	public float size;
	public int color;
	public Item(int f){
		Random r = new Random(f);
		size = r.nextFloat()*20 + 10;
		color =  Color.argb(255, r.nextInt(255), r.nextInt(255), r.nextInt(255));
		for (int i = 0; i < 10; i++) {
			text+= (r.nextInt('z'-'a'-1)+'a');
		}
		
		
		
		
	}
}
