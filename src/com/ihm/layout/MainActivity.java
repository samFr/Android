package com.ihm.layout;

import com.ihm.R;
import com.ihm.resources.BoiteDialogue;
import com.ihm.resources.Ressources;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	
	//les boutons
	Button Jouer = null;
	Button Option = null;
	Button Infos = null;
	Button Fermer = null;
	
	BoiteDialogue dialogue = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.LoadInfos();
		this.ActionElements();
		
		//initialiser les resources
		Ressources.Inisialized(this);
	}
	
	@Override
	public void onDestroy() {
        super.onDestroy();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
	
	//chargement des infos
	public void LoadInfos(){
		//chargement taille de l'ecrant
		Ressources.layoutScreen = (RelativeLayout)findViewById(R.id.LayoutMain);
		
		//chargement des boutons
		this.Jouer = (Button)findViewById(R.id.jouer);
		this.Option = (Button)findViewById(R.id.option);
		this.Infos = (Button)findViewById(R.id.infos);
		this.Fermer = (Button)findViewById(R.id.fermer);
		
		//chargemnet de l'option vibration
		Ressources.vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
	}
	
	//action des elements
	public void ActionElements(){
		this.Jouer.setOnClickListener(ButtonJouer);
		this.Option.setOnClickListener(ButtonOption);
		this.Infos.setOnClickListener(ButtonInfos);
		this.Fermer.setOnClickListener(ButtonFermer);
	}
	
	
	
	//methode des acction des element-------------------------------------------------------------
	//bouton jouer
	public OnClickListener ButtonJouer = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(MainActivity.this, DrawLabyrinthActivity.class);
			startActivity(intent);
			
			//singalisation de vibration
			Ressources.vibrator.vibrate(Ressources.longVibrator);
		}
	};
	
	//bouton option
	public OnClickListener ButtonOption = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(MainActivity.this, ParametreActivity.class);
			startActivity(intent);
			
			//singalisation de vibration
			Ressources.vibrator.vibrate(Ressources.longVibrator);
		}
	};
	
	//bouton infos
	public OnClickListener ButtonInfos = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(MainActivity.this, InfosActivity.class);
			startActivity(intent);
			
			//singalisation de vibration
			Ressources.vibrator.vibrate(Ressources.longVibrator);
		}
	};
	
	//bouton fermer applicatoin
	public OnClickListener ButtonFermer = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			//singalisation de vibration
			Ressources.vibrator.vibrate(Ressources.longVibrator);
			
			//afficher la btoite de dialogue
			showDialog(BoiteDialogue.ID_FERMER_DIALOG);
		}
	};
	//---------------------------------------------------------------------------------------------
	
	//bouton back
	@Override
	public void onBackPressed() {
		showDialog(BoiteDialogue.ID_FERMER_DIALOG);
	}

	
	@Override
	public Dialog onCreateDialog (int id){
		
		return BoiteDialogue.onCreateDialog(this, id);
	}
	
	@Override
	public void onPrepareDialog (int id, Dialog box) {
		
	}

}
