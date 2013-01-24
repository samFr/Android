package com.ihm.resources;

import java.util.ArrayList;

import com.ihm.R;
import com.ihm.layout.MainActivity;
import com.ihm.level.Level1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Vibrator;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public final class Ressources {
	//infos taille ecrant de travail
	public static RelativeLayout layoutScreen = null;
	public static float widthScreen = 0;
	public static float heigthScreen = 0;
	
	//les tableau de la map
	public static RectF[][] Map;
	public static int[][] numCaseMap;
	public static ArrayList<RectF> Walls;
	public static ArrayList<RectF> Holes;
	public static RectF distination;
	public static float posinitX,  posinitY;
	
	//information de player
	public static RectF HitboxPlayer;
	
	public static float posXPlayer, posYPlayer;
	
	public static Bitmap bille;
	public static Bitmap bitmapMap;
	
	public static float tailleBille;
	
	//controleur de jeux
	public static boolean startPlay;
	
	//element de l'activity drawLabyrith
	public static RenderLabyrith renderLabyrith = null;
	
	//vibration
	public static Vibrator vibrator = null;
	public static int lognVibrator = 35;
	
	//parametrage de l'application--------------------------------------
	public static boolean VibrationOk = false;
	public static int levelSelected = 1;
	public static int niveauDifficulte = 1;
	//-------------------------------------------------------------------
	
	//le level
	public static Level1 level1 = null;
	
	//initialisation des element
	public static void Inisialized(MainActivity context){
		Map = new RectF[100][60];;
		Walls = new ArrayList<RectF>();
		Holes = new ArrayList<RectF>();
		numCaseMap = new int[100][60];
		tailleBille = 30.0f;
		
		HitboxPlayer = new RectF(posXPlayer, posYPlayer, posXPlayer+tailleBille, posYPlayer+tailleBille);
		
		startPlay = false;
		bille = BitmapFactory.decodeResource(context.getResources(),R.drawable.bille_noire, new BitmapFactory.Options());
	}
	

	public static void ReJouer() {
		Ressources.posXPlayer = Ressources.posinitX;
		Ressources.posYPlayer = Ressources.posinitY;
	}
	
	//detection de la collision entre la bille et les trous----------------------------------------------------
		public static boolean CollisionHoles(RectF bille){
			for (RectF rect : Holes) {
				if(RectF.intersects(bille,rect))
				{
					return false;
				}
			}
			return true;
		}
		//---------------------------------------------------------------------------------------------------------
		
	//detection de la collison entre la bille et les murs------------------------------------------------------
	
	public static boolean CollisionX(RectF bille){
		for (RectF rect : Walls) {
			if(RectF.intersects(bille,rect))
			{
				if(bille.centerX() < rect.centerX()){
					if( bille.right >= rect.left )
				    {
						Ressources.posXPlayer = rect.left-Ressources.tailleBille;
				        return false;
				    }
				}
				
				if(bille.centerX() > rect.centerX()){
					if( bille.left <= rect.right )
				    {
				    	Ressources.posXPlayer = rect.right;
				        return false;
				    }
				}
			    
			}
		}
		return true;
	}
	
	public static boolean CollisionY(RectF bille){
		for (RectF rect : Walls) {
			if(RectF.intersects(bille,rect))
			{
				CollisionIntern(rect, bille);
				if(bille.centerY() < rect.centerY()){
					if( bille.bottom >= rect.top )
				    {
						Ressources.posYPlayer = rect.top-Ressources.tailleBille;
				        return false;
				    }
				}
				

				if(bille.centerY() > rect.centerY()){
				    if( bille.top <= rect.bottom )
				    {
				    	Ressources.posYPlayer = rect.bottom;
				        return false;
				    }
				}
			}
		}
		return true;
	}
	//--------------------------------------------------------------------------------------------------------------
	
	//dessiner la map
	public static void DrawMap(Canvas canvas, Paint paint){
		int sourceX = 0;
		int sourceY = 0;

		for(int i =0; i < 100; i++){
			for(int j = 0; j < 60; j++){
				sourceX = ((Ressources.numCaseMap[i][j] % 28)) * 8;
				sourceY = ((Ressources.numCaseMap[i][j]  / 28)) * 8;
				canvas.drawBitmap(Ressources.bitmapMap, new Rect(sourceX,sourceY,sourceX+8,sourceY+8), Ressources.Map[i][j] , paint);
			}
		}

	}
	
	//gestion des collision
    public static boolean Collision1(RectF bille){
        for (RectF rect : Walls) {
            if(RectF.intersects(bille,rect))
            {
                return CollisionIntern(rect, bille);
            }
        }
        //Log.d("Chris", "true ");
        return true;
    }
   
    private static boolean CollisionIntern(RectF rectMur, RectF bille){
        RectF intersect = new RectF(rectMur);
       
        if(intersect.intersect(bille)){
           
            int[] pixels = new int[(int)(intersect.width()*intersect.height())];
           
            Log.d("D", "" + (int)intersect.left + " - "  + (int)intersect.top + " - "  + ((intersect.width())) + " - "  + ((intersect.height())));
            
            /*
            Ressources.bitmapMap.getPixels(pixels, 0, 1, (int)intersect.left, (int)intersect.top, ((int)intersect.width())-1, ((int)intersect.height())-1);

            for (int couleur : pixels) {
                if(couleur != Color.rgb(243, 187, 90))
                    return false;
            }
            */

           
            for(int x = (int)intersect.left; x < intersect.right; x++){
                for(int y = (int)intersect.top; y < (int)intersect.bottom; y++){
                    if(Ressources.bitmapMap.getPixel(x,y) == Color.rgb(243, 187, 90))
                        return false;
                }
            }
            
        }
       
        return true;
    }
}
