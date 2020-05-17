package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {
    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }
    
    //Création d'une méthode d'instanciation de label pour faciliter le passage 
    // label à labestar en A* (2ème argument nécessaire au labelstar)
    protected Label newlab (Node node, ShortestPathData data) {
    	return new Label(node);
    }

    @Override
    protected ShortestPathSolution doRun() {
  
        // Retrieve the graph.
        final ShortestPathData data = getInputData();
        Graph graph = data.getGraph();
 
        final int nbNodes = graph.size();
	        
	    // Initialisation tableau de labels
        Label[] tablelabels = new Label[nbNodes];
        
        // Initialisation tas de labels
		BinaryHeap<Label> tas = new BinaryHeap<Label>();

        // Initialize array of predecessors
        Arc[] predecessorArcs = new Arc[nbNodes];

        //Initialisation
       Label first= newlab(data.getOrigin(), data);
       tablelabels[first.getCourant().getId()] = first;
       tas.insert(first);
       first.setDansletas(true);
       first.setCost(0);
       boolean fin=false;
        
       // Notify observers about the first event (origin processed).
       notifyOriginProcessed(data.getOrigin());
       
       //Iteration
       //Tant qu'il existe des sommets non marqués
       while (!(tas.isEmpty())&& !fin) {
    	   //Extractmin(Tas)
    	   Label x=tas.deleteMin();
    	   x.setMarque(true);
    	   notifyNodeMarked(x.getCourant());
    	   //Vérification couts des labels marqués croissants
    	   //System.out.println("Node "+x.getCourant().getId()+" COUT: "+x.getCost());
    	   
    	   if (x.getCourant() == data.getDestination()) {
    		   fin = true;
    	   }
    	   
    	   int succ=0;
           for (Arc arc: x.getCourant().getSuccessors()) {
        	   // Small test to check allowed roads...
               if (!data.isAllowed(arc)) {
                   continue;
               }
               succ++;
               Node nody = arc.getDestination();

				Label y = tablelabels[nody.getId()];
				
				//On atteint un nouveau noeud qu'on place dans le tableau des labels
				if (y == null) {
					notifyNodeReached(arc.getDestination());
					y= newlab(nody, data);
					tablelabels[nody.getId()] = y;
				}

        	   //Si le noeud n'est pas marqué
        	   if (!(y.isMarque())) {
        		   if((y.getCost()>(x.getCost()+data.getCost(arc)))	|| (y.getCost()==Float.POSITIVE_INFINITY)){
						y.setCost(x.getCost()+(float)data.getCost(arc));
						y.setPere(x.getCourant());
						
						if(y.isDansletas()) {
							tas.remove(y);
						}
						/* Sinon on l'ajoute dans le tas */
						else {
							y.setDansletas(true);

						}
						tas.insert(y);
						predecessorArcs[arc.getDestination().getId()] = arc;
						
					
        		   }
        	   }
        	   
           }
           //Vérification taille du tas
           //System.out.println("taille tas: "+tas.size());
           //System.out.println("tasvalide? "+tas.isValid());
           //Vérification nombre de successeurs explorés à chaque itération = nb de successeurs d'un node
           // System.out.println("expl: "+succ+" tr:"+x.getCourant().getNumberOfSuccessors());
       }
       
       ShortestPathSolution solution = null;

       
       // Destination has no predecessor, the solution is infeasible...
       if (predecessorArcs[data.getDestination().getId()] == null) {
           solution = new ShortestPathSolution(data, Status.INFEASIBLE);
       }
       else {

           // The destination has been found, notify the observers.
           notifyDestinationReached(data.getDestination());

           // Create the path from the array of predecessors...
           ArrayList<Arc> arcs = new ArrayList<>();
           Arc arc = predecessorArcs[data.getDestination().getId()];
           while (arc != null) {
               arcs.add(arc);
               arc = predecessorArcs[arc.getOrigin().getId()];
           }

           // Reverse the path...
           Collections.reverse(arcs);

           // Create the final solution.
           solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));
           
           //Vérification avec méthode classe path
           if (solution.getPath().isValid()) {
        	   System.out.println("Path validé");
           }

           if (Math.round(tablelabels[data.getDestination().getId()].getCost()) == Math.round(solution.getPath().getLength())) {
        	   System.out.println("longueur du chemin validé");
        	}
       }
       
     
       return solution;

    }
}


