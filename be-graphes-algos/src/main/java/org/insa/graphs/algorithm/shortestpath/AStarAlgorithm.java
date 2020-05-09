package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Node;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }
    
    //Redéfinition de newlab dans djikstra pour utiliser désormais des labelstar
    @Override
    protected Label newlab (Node node, ShortestPathData data) {
    	return new LabelStar(node, data);
    }

}
