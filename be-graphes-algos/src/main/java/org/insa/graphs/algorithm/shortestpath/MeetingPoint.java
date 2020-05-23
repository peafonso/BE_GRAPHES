package org.insa.graphs.algorithm.shortestpath;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.GraphReader;

public class MeetingPoint  {

	public static void main (String args[]) throws IOException{
		String carte= "C:/Users/clair/Desktop/Perrine/INSA/3A/SEM2/BE/Maps/toulouse.mapgr";
		int V1=1;
		int V2=24;
		System.out.println("V1: "+V1+" V2: "+V2);
		
		//Creation graphreader
		GraphReader reader = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(carte))));
		Graph graph= reader.read();
		
        final int nbNodes = graph.size();
        System.out.println("nb noeuds :"+nbNodes);
        double [] costV1= new double[nbNodes];
        double [] costV2= new double[nbNodes];
        ArcInspector arcinsp = ArcInspectorFactory.getAllFilters().get(0);
        ShortestPathData data = new ShortestPathData(graph, graph.get(V1), graph.get(V2), arcinsp);
        //Chemin 0102
        DijkstraAlgorithm Dijkstra= new DijkstraAlgorithm(data);
		ShortestPathSolution dijsol= Dijkstra.run();
		double costO1O2;
		
		
		costO1O2=dijsol.getPath().getLength();
		System.out.println("coutO1O2: "+costO1O2);
		
		
        
		//Dijkstra
        for (int i=1;i<nbNodes;i++) {
        	
        	if (V1!=i) {
        		ShortestPathData data1 = new ShortestPathData(graph, graph.get(V1), graph.get(i), arcinsp);
        		AStarAlgorithm Dijkstra1= new AStarAlgorithm(data1);
        		ShortestPathSolution dijsol1= Dijkstra1.run();
        		//System.out.println("dij entre "+V1 +" "+i);
        		
        		if (dijsol1.getPath()!=null){
    				costV1[i]= dijsol1.getPath().getLength();
    				//System.out.println(costV1[i]);
    			}
        	}
        	if (V2!=i) {
        		ShortestPathData data2 = new ShortestPathData(graph, graph.get(i), graph.get(V2), arcinsp);
        		AStarAlgorithm Dijkstra2= new AStarAlgorithm(data2);
        		ShortestPathSolution dijsol2= Dijkstra2.run();
        		//System.out.println("dij entre "+i +" "+V2);
        		
        		if (dijsol2.getPath()!=null){
    				costV2[i]=dijsol2.getPath().getLength();
    				//System.out.println(costV2[i]);
    			}
        	}
			
			
			
        }
        int nd=0;
        costO1O2=costO1O2/2;
        System.out.println("costO1O2/2 "+costO1O2);
        double incertitude = (0.3)*costO1O2;
        double incert1= costO1O2-incertitude;
        double incert2= costO1O2+incertitude;
        System.out.println();
        System.out.println();
        for (int i=1;i<nbNodes;i++) {
        	if (incert1<=costV1[i] && costV1[i]<=incert2) {
        		if (incert1<=costV2[i] && costV2[i]<=incert2) {
        			incertitude = (0.15)*costV2[i];
        	        incert1= costV2[i]-incertitude;
        	        incert2= costV2[i]+incertitude;
        			if (incert1<=costV1[i] && costV1[i]<=incert2) {
       					System.out.println("Noeud validé à "+ i+ "!");
       					nd++;
       				}
       			}
       		}      	
        }
        System.out.println("Nbrs noeuds valides: "+nd);
      
	}
	
	
	
}
