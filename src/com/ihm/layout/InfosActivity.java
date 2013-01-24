package com.ihm.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.ihm.R;
import com.ihm.resources.Ressources;

public class InfosActivity extends Activity {

	private Button fermer = null;
	private TextView infos = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_infos);
		
		this.fermer = (Button)findViewById(R.id.fermerInfos);
		this.infos = (TextView)findViewById(R.id.textInfos);
		
		this.AfficheInfos();
		
		this.fermer.setOnClickListener(OnFermerInfos);
		
	}
	
	//affichage infos
	public void AfficheInfos(){
		String infos = 	"Projet Android IHM-2013.\n" +
						"\n"+
						"Developpement d'un jeux Labyrinth.\n" +
						"\n" +
						"Développeur : \n" +
						"\t - Christohpe NICK\n" +
						"\t - Samir GUENDOUL\n" +
						"\n"+
						"Université de Laurrine, Janvier 2013\n"+
						"\n"+
						"copyright tous droit reservé."+ 
						"\n"+
						"\n";
		this.infos.setText(infos);
	}
	
	public OnClickListener OnFermerInfos = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			Ressources.vibrator.vibrate(Ressources.longVibrator);
			InfosActivity.this.finish();
		}
	};
}
