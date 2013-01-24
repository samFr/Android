package com.ihm.resources;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

public class RenderLabyrith extends View {
	
	 public RenderLabyrith(Context context) {
         super(context);
     }

     // Dessinons sur la totalité de l'écran
     protected void onDraw(Canvas canvas) {
     	if(Ressources.startPlay)
     	{
     		canvas.drawRGB(200, 200, 220);
             Paint paint = new Paint();
             Paint paintParoit = new Paint();
             paint.setAntiAlias(true);
             paintParoit.setAntiAlias(true);

     		 canvas.drawBitmap(Ressources.bitmapMap, new Rect(0,0,480,800), new RectF(0,0,Ressources.widthScreen,Ressources.heigthScreen), paintParoit);
             
             // Affecter une couleur de manière aléatoire
             paint.setARGB(255, 0, 0, 50);
             paintParoit.setARGB(255,125,125,0);
             
             // Définir l'épaisseur du segment
             paint.setStrokeWidth (2);
             paintParoit.setStrokeWidth (2);
             
             //dessiner les mure
             /*
             for (RectF rect : Ressources.Walls) {
             	canvas.drawRect(rect, paintParoit);
 			 }
             
             for (RectF rect : Ressources.Holes) {
              	canvas.drawRect(rect, paintParoit);
  			 }
 			*/
             
             Ressources.HitboxPlayer = new RectF(Ressources.posXPlayer, Ressources.posYPlayer, Ressources.posXPlayer+Ressources.tailleBille, Ressources.posYPlayer+Ressources.tailleBille);
             //canvas.drawRect(Ressources.HitboxPlayer, paintParoit);

             canvas.drawBitmap(Ressources.bille, new Rect(0,0,128,128), Ressources.HitboxPlayer, paintParoit);
             
             /*
             canvas.drawRect(0,0,Ressources.widthScreen, Ressources.heigthScreen, paint);
             canvas.drawCircle(Ressources.widthScreen/2, Ressources.heigthScreen/2, 250, paintParoit); //cercle interieur
             canvas.drawCircle(Ressources.widthScreen/2, Ressources.heigthScreen/2, 100, paint); //cercle exterieur
             
             canvas.drawCircle(Ressources.posXPlayer, Ressources.posYPlayer, 30, paint); //bille
             */
     	}
         
     }

}
