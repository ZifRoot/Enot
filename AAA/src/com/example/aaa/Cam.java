package com.example.aaa;

import java.io.File;
import java.util.ArrayList;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;


public class Cam extends Activity implements OnClickListener {
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private Uri fileUri ;
	TextView w;
	ArrayList<Integer> idbs = new ArrayList<Integer>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cam);
		findViewById(R.id.buttonCamX).setOnClickListener(this);
		findViewById(R.id.buttonCamSelf).setOnClickListener(this);
		w = (TextView) findViewById(R.id.TTT1);
		int ncam = 0;		
		Log("CheckCameraHardware = " + checkCameraHardware(this) + "\n"+
		"NCam = "+(ncam=Camera.getNumberOfCameras())+"\n");
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cam, menu);
		return true;
	}
	
	@Override
	protected void onActivityResult ( int requestCode ,	int resultCode , Intent data ) 
	{		
		if ( requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE ) 
		{
			if ( resultCode == RESULT_OK ) 
			{
				try{
				Toast.makeText ( this , "Image saved to :\n" + data.getData () , Toast.LENGTH_LONG ).show ();
				}catch(Exception e)
				{
					Log(e.toString() + "  "+ data);					
				}
			}
			else
				if ( resultCode == RESULT_CANCELED ) {
	// User cancelled the image capture
	 }
	}
	 
	}
	public void Log(String a) {
		w.setText(w.getText()+a);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonCamX:
			Intent intent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE );
			File imagesFolder =
			new File ( Environment . getExternalStorageDirectory () , "images" );
			imagesFolder.mkdirs();
			File image = new File ( imagesFolder , "photo.jpg" );
			fileUri = Uri.fromFile ( image );
			intent.putExtra ( MediaStore.EXTRA_OUTPUT , fileUri );
		
			startActivityForResult ( intent , CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE );
	
			break;
		case R.id.buttonCamSelf:
			
			Camera camera = null ;
			//Camera is not available ( in use or does not exist )
			try { camera = Camera.open ();
			} catch ( Exception e ){Log("Problem open\n"); break;}
			
			Log("Camera opened\n");
			
			for(String effect : camera.getParameters().getSupportedColorEffects())
				Log("Effect " + effect+"\n");
			
			//camera.getParameters().setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
			camera.release();
			Log("Camera released\n");
			
			//if(camera == null) break;
			
			Camera.CameraInfo cameraInfo;
			for(int idx = 0; idx < Camera.getNumberOfCameras(); idx++)
			{
				try { camera = Camera.open ( idx );
				} catch ( Exception e ){Log("Problem open " + idx + "\n"); break;}				
				Log("Camera opened " + idx + "\n");
				cameraInfo = new Camera.CameraInfo();
				Camera.getCameraInfo(idx, cameraInfo);//???
				 
				Log("Facing = " + cameraInfo.facing + "\n"+
					"Orientation" + cameraInfo.orientation+"\n");
				for(String effect : camera.getParameters().getSupportedColorEffects())
					Log("Effect " + effect+"\n");
				camera.release();
				Log("Camera released\n"); 			
			}
			break;

		default:
			break;
		}
			
	}


	private boolean checkCameraHardware( Context context )
	{
		return context.getPackageManager().hasSystemFeature ( PackageManager.FEATURE_CAMERA );
	}
}