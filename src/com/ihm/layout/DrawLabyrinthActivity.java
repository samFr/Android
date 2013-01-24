package com.ihm.layout;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.LinearLayout;

import com.ihm.R;
import com.ihm.ecouteurs_sensor.EcouteurAccelerometer;
import com.ihm.ecouteurs_sensor.EcouteurGravity;
import com.ihm.level.Level1;
import com.ihm.resources.BoiteDialogue;
import com.ihm.resources.RenderLabyrith;
import com.ihm.resources.Ressources;

public class DrawLabyrinthActivity extends Activity {

	private LinearLayout layoutDessin = null;
	private SensorManager sensorManager = null;
	private Sensor sensor = null;
	
	EcouteurAccelerometer ecouteurAccelerometer = null;
	EcouteurGravity ecouteurGravity = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_draw_labyrinth);
		
		//chargement de layout dessin
		layoutDessin = (LinearLayout)findViewById(R.id.dessin);

		//initialisation de renderlabyrith pour afficher le labyrinth
		Ressources.renderLabyrith = new RenderLabyrith(this);
		
		//affihcer le dessin
		layoutDessin.addView(Ressources.renderLabyrith);
		


		//chargement de la taille de lecrant
		if(Ressources.widthScreen == 0 && Ressources.heigthScreen == 0){
			Ressources.widthScreen = Ressources.layoutScreen.getWidth();
			Ressources.heigthScreen = Ressources.layoutScreen.getHeight();
		}
		//chargement de level
        Ressources.level1 = new Level1(this);
				
		//initiation des ecouteur sensor
		this.InitSensor();
	}
	
	//initalisation des ecouteur sensor
	public void InitSensor(){

		//initialisation de sensor
		sensorManager = (SensorManager)this.getSystemService(Context.SENSOR_SERVICE);

		//les écouteurs
		this.ecouteurAccelerometer = new EcouteurAccelerometer(this);
		this.ecouteurGravity = new EcouteurGravity(this);
		
		//initialisation de thread
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); 
        StrictMode.setThreadPolicy(policy); 
        
        //lancer les ecouteur
		//sensorManager.registerListener(ecouteurAccelerometer, sensorManager.getDefaultSensor(sensor.TYPE_ACCELEROMETER), sensorManager.SENSOR_DELAY_GAME);
		sensorManager.registerListener(ecouteurGravity, sensorManager.getDefaultSensor(sensor.TYPE_GRAVITY), sensorManager.SENSOR_DELAY_GAME);
		
		//rafrichir le rendrelabyrinth
		Ressources.mouveBille = true;
		Ressources.startPlay = true;
		Ressources.renderLabyrith.postInvalidate();
		
	}
	
	
	//bouton back
	@Override
	public void onBackPressed() {
		showDialog(BoiteDialogue.ID_QUITTER_DIALOG);
	}
	
	public void PerduRessayer(){
		showDialog(BoiteDialogue.ID_REJOUER_DIALOG);
	}
	
	
	//gestiond des boite de dialogues
	@Override
	public Dialog onCreateDialog (int id){
		
		return BoiteDialogue.onCreateDialog(this, id);
	}

}
