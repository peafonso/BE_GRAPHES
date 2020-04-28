package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.algorithm.AbstractInputData;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Point;

public class LabelStar extends Label {
	
	protected double costest;
	
	public LabelStar(Node sommet, ShortestPathData data) {
		super(sommet);
		this.costest=calculcostest(sommet,data);
	}
	
	protected double calculcostest (Node sommet, ShortestPathData data) {
		double result;
		//MODE TIME
		if (data.getMode()==AbstractInputData.Mode.TIME) {
			/*The maximum speed associated with input data is different from the maximum speed associated with graph (accessible via Graph.getGraphInformation())*/
			int speed= Math.max(data.getMaximumSpeed(), data.getGraph().getGraphInformation().getMaximumSpeed());
			result=(Point.distance(sommet.getPoint(), data.getDestination().getPoint()))/(speed/3.6);
			
		}
		//MODE LENGTH
		else {
			result=Point.distance(sommet.getPoint(), data.getDestination().getPoint());
		}
		return result;
	}
	

	public double getTotalCost () {
		return this.costest+this.getCost();
	}

	
	public int compareTo (LabelStar autre) {
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

}
