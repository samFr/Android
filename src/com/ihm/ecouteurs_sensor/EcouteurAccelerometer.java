package com.ihm.ecouteurs_sensor;


/*avec simulateur
import org.openintents.sensorsimulator.hardware.Sensor;
import org.openintents.sensorsimulator.hardware.SensorEvent;
import org.openintents.sensorsimulator.hardware.SensorEventListener;
*/

//*sans simulateur
import android.app.Activity;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import com.ihm.resources.Ressources;
//*/

public class EcouteurAccelerometer implements SensorEventListener {

	Activity activity;
	
	public EcouteurAccelerometer(Activity ma)
	{
		this.activity = ma;
	}
	
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent arg0) {
		
		float x = Ressources.posXPlayer - arg0.values[0];
		float y = Ressources.posYPlayer;
		
		RectF rect = new RectF(x, y, x+Ressources.tailleBille, y+Ressources.tailleBille);
		
		
		if(Ressources.CollisionX(rect))
		{
			Ressources.posXPlayer = x;
			//Ressources.HitboxPlayer = new RectF(x, y, x+40, y+40);
			//activity.accelerometer.setText("No Collision x");
		}else
		{
			//activity.accelerometer.setText("Collision x");
			//activity.v.vibrate(100);
		}
		
		Ressources.renderLabyrith.postInvalidate();
	}

}
