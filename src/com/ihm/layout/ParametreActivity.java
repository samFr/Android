package com.ihm.layout;
import com.ihm.R;
import com.ihm.resources.BoiteDialogue;
import com.ihm.resources.Ressources;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class ParametreActivity extends Activity {

	//les boutons valider et annuler
	private Button 	valider = null;

	//le check box de bouton vibration
	private CheckBox vibration = null;

	// les bouton radio de difficulté
	private RadioButton difficulte1 = null,
						difficulte2  = null,
						difficulte3  = null;

	// les bouton radio de difficulté
	private RadioButton level1 = null,
						level2  = null,
						level3  = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parametre);	
		
		//appel des methodes
		this.LoadElement();
		this.ActionElement();
		this.Initialisation();
	}
	
	//chargement des element
	public void LoadElement(){
		this.valider = (Button)findViewById(R.id.valider);
		
		this.vibration = (CheckBox)findViewById(R.id.vibration);
		
		this.difficulte1 = (RadioButton)findViewById(R.id.difficulte1);
		this.difficulte2 = (RadioButton)findViewById(R.id.difficulte2);
		this.difficulte3 = (RadioButton)findViewById(R.id.difficulte3);
		
		this.level1 = (RadioButton)findViewById(R.id.niveau1);
		this.level2 = (RadioButton)findViewById(R.id.niveau2);
		this.level3 = (RadioButton)findViewById(R.id.niveau3);
	}
	
	//action sur les elements
	public void ActionElement(){
		this.valider.setOnClickListener(OnValider);
		this.vibration.setOnClickListener(OnVibration);
	}
	
	//initialisation de la fenetre
	public void Initialisation(){
	
		//verifier si la vibration a été coché ou pas
		this.vibration.setChecked(Ressources.VibrationOk);
		
		//verifier le level selectionner
		switch (Ressources.levelSelected) {
		case 1: level1.setChecked(true);
			break;
		case 2: level2.setChecked(true);
			break;
		case 3: level3.setChecked(true);
			break;
		default:
			break;
		}
		
		//verifier la difficulter selectionner
		switch (Ressources.niveauDifficulte) {
		case 1: difficulte1.setChecked(true);
			break;
		case 2: difficulte2.setChecked(true);
			break;
		case 3: difficulte3.setChecked(true);
			break;
		default:
			break;
		}
		
	}
	
	//methode des acction des element-------------------------------------------------------------
	//bouton valider
	public OnClickListener OnValider = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			//voir la vibration
			if(vibration.isChecked()){
				Ressources.VibrationOk = true;
			}
			
			//selection de niveau
			if(level1.isChecked()){
				Ressources.levelSelected = 1;
			}else if(level2.isChecked()){
				Ressources.levelSelected = 2;
			}else if(level3.isChecked()){
				Ressources.levelSelected = 3;
			}
			
			//selection de difficulter
			if(difficulte1.isChecked()){
				Ressources.niveauDifficulte = 1;
			}else if(difficulte2.isChecked()){
				Ressources.niveauDifficulte = 2;
			}else if(difficulte3.isChecked()){
				Ressources.niveauDifficulte = 3;
			}
			
			//singalisation de vibration
			Ressources.vibrator.vibrate(Ressources.longVibrator);
			
			//fermer la fenetre
			ParametreActivity.this.finish();
		}
	};
	//----------------------------------------------------------------------------------------------
	
	//bouton valider
	public OnClickListener OnVibration= new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			if(vibration.isChecked())
				//singalisation de vibration
				Ressources.vibrator.vibrate(Ressources.longVibrator);
		}
	};
	
	//bouton back
	@Override
	public void onBackPressed() {
		showDialog(BoiteDialogue.ID_ANNULER_MODIFICATION_PARAMETRES_DIALOG);
	}


	//gestiond des boite de dialogues
	@Override
	public Dialog onCreateDialog (int id){

		return BoiteDialogue.onCreateDialog(this, id);
	}
}
