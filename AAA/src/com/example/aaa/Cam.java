package com.example.aaa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Cam extends Activity implements OnClickListener, PictureCallback {
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private Uri fileUri ;
	TextView w;
	ArrayList<Integer> idbs = new ArrayList<Integer>();
	private int ncam;
	//private PictureCallback pictureListener;
	Camera camera = null;
	private CameraPreview mPreview;
	private TextView tCamL;
	private Button buttonRelease;
	private ViewGroup LCams;
	private FrameLayout preview;
	private Button buttonDefault;
	private Button buttonTake;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cam);
		findViewById(R.id.buttonCamX).setOnClickListener(this);
		(buttonDefault = (Button)findViewById(R.id.buttonCamDefault))
			.setOnClickListener(this);
		(buttonTake = (Button)findViewById(R.id.buttonTakeCam))
			.setOnClickListener(this);
		(buttonRelease = (Button)findViewById(R.id.buttonRelease))
			.setOnClickListener(this);
		w = (TextView) findViewById(R.id.TTT1);
		tCamL = (TextView)findViewById(R.id.tCamL);
		LCams = (ViewGroup)findViewById(R.id.LayoutCams);
        preview = (FrameLayout) findViewById(R.id.camera_preview);
		Log("CheckCameraHardware = " + checkCameraHardware(this) + "\n"+
		"NCam = "+(ncam=Camera.getNumberOfCameras())+"\n");
		LCams.removeAllViews();
		for(int idx = 0; idx < ncam ; idx++)
		{
			Button b = new Button(LCams.getContext());
			LCams.addView(b);
			b.setId(idx);
			b.setText("" + idx);			
			b.setOnClickListener(this);						
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cam, menu);
		return true;
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		LogClear();
		if(camera != null) buttonRelease.performClick();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
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
			} // User cancelled the image capture
			else if ( resultCode == RESULT_CANCELED ) {}	 
		}	 
	}
	public void Log(String a) {
		tCamL.setText(tCamL.getText()+a);
	}
	public void LogClear() {
		tCamL.setText("");
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int ID = v.getId(); 
		switch (ID) {
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
		case R.id.buttonTakeCam:
			if(camera == null)break;
			 camera.takePicture (
						null , // for image capture moment
						 null , // for raw ( uncompressed ) image data
						this );
			break;
		case R.id.buttonCamDefault:
			LogClear();
			if(camera != null)
			{
				 buttonRelease.performClick();				 				
			}			
			 
			if(camera != null) Log("!!!!!!!!!!!!!!");
			//Camera is not available ( in use or does not exist )
			try { camera = Camera.open ();
			} catch ( Exception e ){Log("Problem open\n"); camera=null; return;}
			
			Log("Camera opened\n");
			
			for(String effect : camera.getParameters().getSupportedColorEffects())
				Log("Effect " + effect+"\t|\t");
			
			mPreview = new CameraPreview(this, camera);
	        preview.addView(mPreview);
			break;
		case R.id.buttonRelease:
			if(camera == null) break;
			preview.removeView(mPreview);
			mPreview.release();			
			camera.release();
			Log("Camera released\n");
			camera = null;
			break;
		default:
			if(ID >= ncam) break;
			LogClear();
			if(camera != null) buttonRelease.performClick(); 
			//Camera is not available ( in use or does not exist )
			try { camera = Camera.open (ID);
			} catch ( Exception e ){Log("Problem open\n"); return;}
			
			Log("Camera opened\n");
			
			for(String effect : camera.getParameters().getSupportedColorEffects())
				Log("Effect " + effect+"\t|\t");
			
			Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
			Camera.getCameraInfo(ID, cameraInfo);//???
			 
			Log("Facing = " + cameraInfo.facing + "\n"+
				"Orientation = " + cameraInfo.orientation+"\n");
			mPreview = new CameraPreview(Cam.this, camera);
	        preview.addView(mPreview);
			break;
		}
			
	}


	private boolean checkCameraHardware( Context context )
	{
		return context.getPackageManager().hasSystemFeature ( PackageManager.FEATURE_CAMERA );
	}
	
	public static final int MEDIA_TYPE_IMAGE = 1;
	public static final int MEDIA_TYPE_VIDEO = 2;

	/** Create a file Uri for saving an image or video */
	private static Uri getOutputMediaFileUri(int type){
	      return Uri.fromFile(getOutputMediaFile(type));
	}

	/** Create a File for saving an image or video */
	private static File getOutputMediaFile(int type){
	    // To be safe, you should check that the SDCard is mounted
	    // using Environment.getExternalStorageState() before doing this.

	    File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	              Environment.DIRECTORY_PICTURES), "MyCameraApp");
	    // This location works best if you want the created images to be shared
	    // between applications and persist after your app has been uninstalled.

	    // Create the storage directory if it does not exist
	    if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	            android.util.Log.d("MyCameraApp", "failed to create directory");
	            return null;
	        }
	    }

	    // Create a media file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    File mediaFile;
	    if (type == MEDIA_TYPE_IMAGE){
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "IMG_"+ timeStamp + ".jpg");
	    } else if(type == MEDIA_TYPE_VIDEO) {
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "VID_"+ timeStamp + ".mp4");
	    } else {
	        return null;
	    }

	    return mediaFile;
	}


	@Override
	public void onPictureTaken(byte[] data, Camera camera) {
		// TODO Auto-generated method stub
		File pictureFile = getOutputMediaFile ( MEDIA_TYPE_IMAGE );
		
		if ( pictureFile == null ){
		Log ( " Error creating media file , check permissions : "/* +
		e. getMessage ()*/);
		 return ;
		}

		try {
		FileOutputStream fos = new FileOutputStream ( pictureFile );
		fos . write ( data );
		fos . close ();
		} catch ( FileNotFoundException e) {
		Log ( " File not found : " + e. getMessage ());
		} catch ( IOException e) {
		Log ( " Error accessing file : " + e . getMessage ());
		}
	}
}