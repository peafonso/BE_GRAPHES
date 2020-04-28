package org.insa.graphs.algorithm.shortestpath;
import org.insa.graphs.model.Node;

public class Label implements Comparable<Label>{	
	/*  sommet courant : sommet associé à ce label (sommet ou numéro de sommet).
    marque : booléen, vrai lorsque le coût min de ce sommet est définitivement connu par l'algorithme.
    coût : valeur courante du plus court chemin depuis l'origine vers le sommet.
    père : correspond au sommet précédent sur le chemin correspondant au plus court chemin courant. Afin de reconstruire le chemin à la fin de l'algorithme, mieux vaut stocker l'arc plutôt que seulement le père.
    Important pour la suite : une méthode getCost() qui renvoie le coût de ce label.*/
	
	
	private Node courant;
	private boolean marque;
	private double cost;
	private Node pere;
	private boolean dansletas;
	
	public Label (Node sommet) {
		this.setCourant(sommet);
		this.setMarque(false);
		this.cost=Double.POSITIVE_INFINITY;
		this.setPere(null);
		this.setDansletas(false);
	}
	
	public double getCost() {
		return cost;
	}
	
	public void setCost(double cost) {
		this.cost=cost;
	}

	public Node getCourant() {
		return courant;
	}

	public void setCourant(Node courant) {
		this.courant = courant;
	}

	public boolean isMarque() {
		return marque;
	}

	public void setMarque(boolean marque) {
		this.marque = marque;
	}

	public Node getPere() {
		return pere;
	}

	public void setPere(Node pere) {
		this.pere = pere;
	}
	
	public double getTotalCost () {
		return this.cost;
	}
	 
	public int compareTo (Label autre) {
		int fin;
		if (this.getTotalCost()>autre.getTotalCost()) {
			fin=1;
		}
		else if (this.getTotalCost()<autre.getTotalCost()) {
			fin=-1;
		}
		else {
			fin=0;
		}
		return fin;
	}

	public boolean isDansletas() {
		return dansletas;
	}

	public void setDansletas(boolean dansletas) {
		this.dansletas = dansletas;
	}
	
	
}
