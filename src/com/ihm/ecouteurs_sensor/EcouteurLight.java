package com.ihm.ecouteurs_sensor;


//*avec simulateur
import org.openintents.sensorsimulator.hardware.Sensor;
import org.openintents.sensorsimulator.hardware.SensorEvent;
import org.openintents.sensorsimulator.hardware.SensorEventListener;

import android.app.Activity;
//*/
/*sans simulateur
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
*/

public class EcouteurLight implements SensorEventListener {

	Activity activity;
	
	public EcouteurLight(Activity ma)
	{
		this.activity = ma;
	}
	
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent arg0) {
	}

}
