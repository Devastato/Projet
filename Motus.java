package com.projet.algodev.l3;

import com.projet.algodev.l3.inter.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Motus extends Dico implements jeuMot{
	
	protected int nbCpRestant=8;
	protected String motATrouver_ = this.motAleatoireHuitLettres();
	protected List<String> motATrouver = this.motDansTableau(motATrouver_);
	protected List<Boolean> trouvee= new ArrayList<Boolean>();
	protected List<String> affichage= new ArrayList<String>();
	Boolean trouve = false;

	
	
	public boolean motTrouve() {
		int i;
		boolean test=true;
		for(i=0;i<this.motATrouver.size();i++) {
			if(this.trouvee.get(i)==false) {
				test=false;
			}
		}
		return test;
	}
	
	public void jouerCoup (String coup){
		int i,j;
		try {
			if(this.motDansDico(coup)==true) {
				if(coup.length()==8) {
					List <String> mot_=this.motDansTableau(coup);
					for(i=0;i<mot_.size();i++) {
						Boolean res = false;
						for(j=0;j<mot_.size();j++) {
							if(this.motATrouver.get(i).equals(mot_.get(i))) {
								this.trouvee.set(i, true);
								this.affichage.set(i, mot_.get(i));
					
							}
							else {
								if(this.motATrouver.get(j).equals(mot_.get(i))) {
									res = true;
								}
				
							}
						}
						if(res==true) {
							int pos = i+1;
							System.out.println("La lettre : "+mot_.get(i)+" en position "+pos+" n'est pas a la bonne place !");
						}
			
				
			}
			}
				else {
					System.out.println("ATTENTION, LE MOT "+coup+" NE FAIT PAS 8 LETTRES");
					this.nbCpRestant--;
					System.out.println("NOMBRE DE COUPS RESTANTS: "+this.nbCpRestant);
				}
			}
			else {
				System.out.println("ATTENTION, LE MOT "+coup+" N'EXISTE PAS !");
				this.nbCpRestant--;
				System.out.println("NOMBRE DE COUPS RESTANTS: "+this.nbCpRestant);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	
	public void afficherListe(List<String> l) {
		int x=l.size();
		int i;
		for(i=0;i<x;i++) {
			System.out.print(l.get(i));
			
		}
		System.out.println();
	}
	
	public void initialisation() {
		int nbL=motATrouver.size();
		int i;
		for(i=0;i<nbL;i++) {
			this.trouvee.add(false);
			this.affichage.add("_ ");
			this.nbCpRestant=8;
		}
		System.out.println("JOUONS AU MOTUS ! ");
	}
	
	public void jouer() {
		this.initialisation();
		System.out.println("(mot a trouver) : "+motATrouver);
		this.afficherListe(affichage);
		Scanner sc = new Scanner(System.in);
		while( !trouve /*trouve == false*/ && nbCpRestant>=0){
			
			System.out.println("Veuillez saisir un mot :");
			String motRentre_ = sc.nextLine();
			String motRentre = motRentre_.toUpperCase();
			this.jouerCoup(motRentre);
			trouve = this.motTrouve();
			this.afficherListe(affichage);
			this.nbCpRestant--;
			System.out.println("NOMBRE DE COUPS RESTANTS : "+this.nbCpRestant);
		}
		if(this.motTrouve()==true) {
			System.out.println("GAGNE");
		}
		else {
			System.out.println("PERDU, PLUS DE COUPS POSSIBLES");
		}
		sc.close();
	}
	
}
