package com.ihm.level;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.util.Log;

import com.ihm.R;
import com.ihm.resources.Ressources;

public class Level1 {
	
	private Activity context;
	
	public Level1(Activity contextIn){
		this.context = contextIn;
		
		//charger des donnes
		this.ChargementWalls();
		
		//chargement de l'image de map
		Ressources.bitmapMap = BitmapFactory.decodeResource(context.getResources(),R.drawable.map_level1, new BitmapFactory.Options());
		
	}
	
	
	//chargement walls
	public void ChargementWalls()
    {
        float pasX = Ressources.widthScreen/30f; 
        float pasY = Ressources.heigthScreen/50f;
        float posX = 0;
        float posY = 0;
        float x,y;
        try
        {
      	  	BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open("level/level1/collision.txt")));	
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] split = line.split(",");
                for (String s : split)
                {
                	int c = Integer.valueOf(s);
                	switch (c) {
					case 1://mur
						Ressources.Walls.add(new RectF(posX, posY, posX+pasX, posY+pasY));
						break;
					case 2://trou
						x = (posX+pasX/2)-Ressources.tailleBille/2;
                    	y = (posY+pasX/2)-Ressources.tailleBille/2;
                    	Ressources.Holes.add(new RectF(x, y, x+Ressources.tailleBille, y+Ressources.tailleBille));
						break;
					case 3://distination
						x = (posX+pasX/2)-Ressources.tailleBille/2;
                    	y = (posY+pasX/2)-Ressources.tailleBille/2;
                    	Ressources.distination = new RectF(x, y, x+Ressources.tailleBille, y+Ressources.tailleBille);
						break;
					case 4://localisation de la bille
						Ressources.posXPlayer = posX;
						Ressources.posYPlayer = posY;
						
						Ressources.posinitX = posX;
						Ressources.posinitY = posY;
						break;
					default:
						break;
					}
                    
                    posX += pasX;
                }
                //initialiser les position
                posX = 0;
                posY += pasY;
            }
  			br.close(); 
        }
        catch (Exception e)
        {
            // Let the user know what went wrong.
        }
    }
	
}
