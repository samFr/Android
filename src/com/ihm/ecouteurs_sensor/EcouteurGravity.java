package com.ihm.ecouteurs_sensor;

/*avec simulateur
import org.openintents.sensorsimulator.hardware.Sensor;
import org.openintents.sensorsimulator.hardware.SensorEvent;
import org.openintents.sensorsimulator.hardware.SensorEventListener;
*/

//*sans simulateur
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.util.Log;
//*/

import android.app.Activity;
import android.app.Dialog;
import android.graphics.RectF;

import com.ihm.layout.DrawLabyrinthActivity;
import com.ihm.resources.BoiteDialogue;
import com.ihm.resources.Ressources;

public class EcouteurGravity implements SensorEventListener {

	Activity activity;
	boolean mouveOk = true;
	
	public EcouteurGravity(Activity ma)
	{
		this.activity = ma;
	}

	@Override
	public void onSensorChanged(SensorEvent arg0) {
		
		//Log.d("Sam1", "je suis dans ecouteur gravity");
		
		//this.MouvementBill(arg0.values[0], arg0.values[1]);
		
		//detection des collision sur  les mure
		this.CollisionMure(arg0.values[0], arg0.values[1]);
		
		//detection des collision sur les trous
		this.CollisionTrous(arg0.values[0], arg0.values[1]);
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	
	//detction collision sur les cercle
	public void MouvementBill(float x, float y){
		
		float bx = Ressources.posXPlayer-x;
		float by = Ressources.posYPlayer+y;
		
		float cx = Ressources.widthScreen/2;
		float cy = Ressources.heigthScreen/2;
		
		float dx = (bx - cx);
		float dy = (by - cy);
		
		
		
		double distance = Math.hypot(dx, dy);
		
		if( distance < (double) (250 - 30) && distance > (double)(100+30)){
		
		Ressources.posXPlayer -= x;
		Ressources.posYPlayer += y;
		}
		
		if( distance < (double) (250 - 30) && distance > (double)(100+30)){
			
			Ressources.posXPlayer -= x;
			Ressources.posYPlayer += y;
		}
		//rafrechire
		Ressources.renderLabyrith.postInvalidate();
	}
	
	//collision sur les murs---------------------------------------------------------
	public void CollisionMure(float x0, float y0){
		float x, y;
		RectF rectX, rectY;
		
		//collision sur les mure
		//collision sur l'axe x-------------------------------------------------------
		x = Ressources.posXPlayer - x0;
		y = Ressources.posYPlayer;
		rectX = new RectF(x, y, x+Ressources.tailleBille, y+Ressources.tailleBille);
		
		if(Ressources.CollisionX(rectX))
		{
			Ressources.posXPlayer = x;
		}
		//----------------------------------------------------------------------------
		//rafrechire
		Ressources.renderLabyrith.postInvalidate();
		//collision sur l'axe y--------------------------------------------------------
		x = Ressources.posXPlayer;// - arg0.values[0];
		y = Ressources.posYPlayer + y0;
		rectY = new RectF(x, y, x+Ressources.tailleBille, y+Ressources.tailleBille);
		
		if(Ressources.CollisionY(rectY))
		{
			Ressources.posYPlayer = y;
		}
		//----------------------------------------------------------------------------
		
		//rafrechire
		Ressources.renderLabyrith.postInvalidate();
	}
	//-------------------------------------------------------------------------------
	
	//collision sur les trous---------------------------------------------------------
		public void CollisionTrous(float x0, float y0){
			float x, y;
			RectF rect;
			
			//collision sur les mure
			//collision sur l'axe x-------------------------------------------------------
			x = Ressources.posXPlayer - x0;
			y = Ressources.posYPlayer + y0;
			rect = new RectF(x, y, x+Ressources.tailleBille, y+Ressources.tailleBille);
			
			if(Ressources.CollisionHoles(rect))
			{
				Ressources.posXPlayer = x;
			}else{
				Ressources.ReJouer();
				Ressources.vibrator.vibrate(Ressources.lognVibrator);
			}
			//----------------------------------------------------------------------------
			
			//rafrechire
			Ressources.renderLabyrith.postInvalidate();
		}
		//-------------------------------------------------------------------------------

}
