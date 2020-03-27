package org.insa.graphs.algorithm.shortestpath;

import java.util.Arrays;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {
	protected Label label;
    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
  
        // Retrieve the graph.
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        Graph graph = data.getGraph();
 
        final int nbNodes = graph.size();
	        
	    // Initialize array of distances.
        double[] distances = new double[nbNodes];
        Arrays.fill(distances, Double.POSITIVE_INFINITY);
        distances[data.getOrigin().getId()] = 0;

        // Notify observers about the first event (origin processed).
        notifyOriginProcessed(data.getOrigin());

        // Initialize array of predecessors.
        Arc[] predecessorArcs = new Arc[nbNodes];

        //Initialisation
        label= new Label();
        
        //Iteration
        
        
        
        
        
        
        return solution;
    }

}
