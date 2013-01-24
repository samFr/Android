package com.ihm.resources;

import java.text.MessageFormat;

import com.ihm.layout.DrawLabyrinthActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

public final class BoiteDialogue {
	//les types de boites de dialogue a afficher
	public static final int ID_FERMER_DIALOG = 0;
	public static final int ID_QUITTER_DIALOG = 1;
	public static final int ID_ANNULER_MODIFICATION_PARAMETRES_DIALOG = 2;
	public static final int ID_REJOUER_DIALOG = 3;
	public static final int ID_LEVEL_DIALOG = 4;
	public static final int ID_FIN_DIALOG = 5;

	//les message a afficher
	public static String messageFermer = "Êtes-vous sûr de vouloir quitter la partie ..?";
	public static String messageQuitterPartie = "Êtes-vous sûr de vouloir quitter la partie ..?";
	public static String messageAnnulerParametre = "Êtes-vous sur de vouloir annuler les modifications des paramètres ..?";
	public static String messageRjouer = "Souhaitez-vous réessayer le niveau..?";
	public static String messageLevel = "Félicitations, vous les vous continuez ou rejouez le niveau ..?";
	public static String messageFinJeux = "Félicitations, vous avez terminé le jeu ..^^!!";
	
	
	//creation d'une boite de dialogue
	public static Dialog onCreateDialog (final Activity context, int id){
		AlertDialog.Builder dialogue = new AlertDialog.Builder(context);
		
		switch (id) {
		
		//fermer l'application-------------------------------------------------------------
		case ID_FERMER_DIALOG:
		      dialogue.setTitle("Attention");
		      dialogue.setMessage(messageFermer);
		      
		      //bouton ok
              dialogue.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int id) {
                      context.finish();
                  }
              });
              
              //bouton annuler
              dialogue.setPositiveButton("Annuler", new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int id) {
                	  dialog.cancel();
                  }
              });
		      break;
		//----------------------------------------------------------------------------------     
		
		//quitter la partie-----------------------------------------------------------------
		case ID_QUITTER_DIALOG:
			dialogue.setTitle("Attention");
		    dialogue.setMessage(messageQuitterPartie);
		      
		      //bouton ok
            dialogue.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                	context.finish();
                }
            });
            
            //bouton annuler
            dialogue.setPositiveButton("Annuler", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
              	  dialog.cancel();
                }
            });
			break;
		//----------------------------------------------------------------------------------
		
		//message qui s'affiche l'orsque le joueur annul la modification des parametre------
		case ID_ANNULER_MODIFICATION_PARAMETRES_DIALOG:
			dialogue.setTitle("Attention");
			dialogue.setMessage(messageAnnulerParametre);

			//bouton ok
			dialogue.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					context.finish();
				}
			});

			//bouton annuler
			dialogue.setPositiveButton("Annuler", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});
			break;
		//----------------------------------------------------------------------------------

		//message qui s'affiche l'orsque le joueur a perdu une partie-----------------------
		case ID_REJOUER_DIALOG:
			dialogue.setTitle("Information");
		    dialogue.setMessage(messageRjouer);
		      
		      //bouton ok
            dialogue.setNegativeButton("Réessayer", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                	Ressources.ReJouer();
                	dialog.cancel();
                }
            });
            //-------------------------------------------------------------------------------
            //bouton quitter
            dialogue.setPositiveButton("Quitter", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                	context.finish();
                }
            });
		      break;
		//-----------------------------------------------------------------------------------     
		
		//message qui s'affiche quand le jour a ganger une partie----------------------------
		case ID_LEVEL_DIALOG:
			dialogue.setTitle("Information");
		    dialogue.setMessage(messageLevel);
		      
		      //bouton ok
            dialogue.setNegativeButton("Oui", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    context.finish();
                }
            });
            
            //bouton réessayer
            dialogue.setPositiveButton("Réessayer", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
              	  dialog.cancel();
                }
            });
		      break;
		      //----------------------------------------------------------------------------------

		//message qui s'affiche quand le joueur a fini le jeu-------------------------------------
		case ID_FIN_DIALOG:
			dialogue.setTitle("Information");
			dialogue.setMessage(messageFinJeux);

			//bouton ok
			dialogue.setNegativeButton("Quitter", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					context.finish();
				}
			});
			break;
			//----------------------------------------------------------------------------------
		}
		return dialogue.create();
	}
	
	//modification de contenu d'une boite de dialogue
	public static void onPrepareDialog (int id, Dialog box) {
		
	}
}
