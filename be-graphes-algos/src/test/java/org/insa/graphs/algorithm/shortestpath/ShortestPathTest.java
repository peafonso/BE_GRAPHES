package org.insa.graphs.algorithm.shortestpath;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.GraphReader;
import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.model.Graph;



public class ShortestPathTest {
	// Mode DISTANCE
	// 0=Shortest path, all roads allowed         1=Shortest path, only roads open for cars 
	// Mode TEMPS
	// 2=Fastest path, all roads allowed          3=Fastest path, only roads open for cars
	// 4=Fastest path for pedestrian
	public void avecOracle(String carte, int mode, int origine, int destination) throws Exception{
		 
			//Creation graphreader
			GraphReader reader = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(carte))));
			Graph graph= reader.read();
			
			if (mode<0 && mode>4) {
				System.out.println("Argument du mode invalide!");
			}
			else if (origine<0 || destination<0 || origine>=(graph.size()-1) || destination>=(graph.size()-1)) {
				System.out.println("Arguments origine et destination invalides!");
			}
			else {
				ArcInspector arcinsp;
				//On affecte le bon mode
				arcinsp=  ArcInspectorFactory.getAllFilters().get(mode);
				
				System.out.println("ORIGINE. " + origine);
				System.out.println("DESTINATION. " + destination);
				
				if (origine==destination) {
					System.out.println("Cout: 0");
				}
				else {
					ShortestPathData data = new ShortestPathData(graph, graph.get(origine), graph.get(destination), arcinsp);
					
					//Test avec les 3 algorithmes, avec bellman comme réference
					AStarAlgorithm AStar= new AStarAlgorithm(data);
					DijkstraAlgorithm Dijkstra= new DijkstraAlgorithm(data);
					BellmanFordAlgorithm Bellman= new BellmanFordAlgorithm(data);
					
					ShortestPathSolution astarsol= AStar.run();
					ShortestPathSolution dijsol= Dijkstra.run();
					ShortestPathSolution belsol= Bellman.run();
					
					//Cas où solution de réference null
					if (belsol.getPath() == null) {
						assertEquals(astarsol.getPath(), belsol.getPath());
						assertEquals(dijsol.getPath(), belsol.getPath());
						System.out.println("PAS DE SOLUTION");
					}
					else {
						double costbel,costdij,coststar ;
						if (mode==0 || mode==1) {
							//Mode distance
							costbel= belsol.getPath().getLength();
							costdij= dijsol.getPath().getLength();
							coststar= astarsol.getPath().getLength();
						}
						else {
							//Mode temps
							costbel= belsol.getPath().getMinimumTravelTime();
							costdij= dijsol.getPath().getMinimumTravelTime();
							coststar= astarsol.getPath().getMinimumTravelTime();
						}
						System.out.println("Bellman "+costbel);
						System.out.println(belsol.toString());
						System.out.println("Dijkstra "+costdij);
						System.out.println(dijsol.toString());
						System.out.println("AStar "+coststar);
						System.out.println(astarsol.toString());
						assertEquals(costdij, costbel, 0.001);
						assertEquals(coststar, costbel, 0.001);
					}
				}
			}
			System.out.println();
			System.out.println();
	}

	
	// On n'utilise pas d'argument mode, car sans oracle nous effectuons ces tests
	// en comparant les temps et distances entre les modes 0 et 2 (distance  
	// et temps avec toutes les routes autorisés )
	public void sansOracle(String carte, int origine, int destination) throws Exception {
		//Creation graphreader
		GraphReader reader = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(carte))));
		Graph graph= reader.read();
		
		if (origine<0 || destination<0 || origine>=(graph.size()-1) || destination>=(graph.size()-1)) {
			System.out.println("Arguments origine et destination invalides!");
		}
		else {
			ArcInspector arcinsp;
			
			System.out.println("ORIGINE. " + origine);
			System.out.println("DESTINATION. " + destination);
			
			if (origine==destination) {
				System.out.println("Cout: 0");
			}
			else {
				ShortestPathData data ;
				double costShortDijTime=Double.POSITIVE_INFINITY, costShortDijLength=Double.POSITIVE_INFINITY;
				double costShortStarTime=Double.POSITIVE_INFINITY, costShortStarLength=Double.POSITIVE_INFINITY;
				double costFastDijTime=Double.POSITIVE_INFINITY, costFastDijLength=Double.POSITIVE_INFINITY;
				double costFastStarTime=Double.POSITIVE_INFINITY, costFastStarLength=Double.POSITIVE_INFINITY;
				
				//Mode 0 : Chemin le plus court
				arcinsp= ArcInspectorFactory.getAllFilters().get(0);
				data= new ShortestPathData(graph, graph.get(origine),graph.get(destination), arcinsp);
		
				DijkstraAlgorithm Dijkstra = new DijkstraAlgorithm(data);
				AStarAlgorithm AStar= new AStarAlgorithm(data);
				
				ShortestPathSolution dijsol = Dijkstra.run();
				ShortestPathSolution starsol = AStar.run();
	
				//Pas de chemin pour ce mode
				if (dijsol.getPath() == null) {
					System.out.println("PAS DE SOLUTION MODE DISTANCE");
				}
				//Chemin trouvé!
				else {				
					costShortDijTime= dijsol.getPath().getMinimumTravelTime();
					costShortDijLength= dijsol.getPath().getLength();
					costShortStarTime= starsol.getPath().getMinimumTravelTime();
					costShortStarLength= starsol.getPath().getLength();
					
				}
			
				
				//Mode 2: Chemin le plus rapide
				arcinsp= ArcInspectorFactory.getAllFilters().get(2);
				data= new ShortestPathData(graph, graph.get(origine),graph.get(destination), arcinsp);
		
				Dijkstra = new DijkstraAlgorithm(data);
				AStar= new AStarAlgorithm(data);
				
				dijsol = Dijkstra.run();
				starsol = AStar.run();
	
				//Pas de chemin pour ce mode
				if (dijsol.getPath() == null) {
					System.out.println("PAS DE SOLUTION MODE TEMPS");
				}
				//Chemin trouvé!
				else {				
					costFastDijTime= dijsol.getPath().getMinimumTravelTime();
					costFastDijLength= dijsol.getPath().getLength();
					costFastStarTime= starsol.getPath().getMinimumTravelTime();
					costFastStarLength= starsol.getPath().getLength();
					
				}
				
				assertTrue(costFastDijTime <= costShortDijTime);
				assertTrue(costFastStarTime <= costShortStarTime);
				System.out.println("Comparaison des couts en temps :");
				System.out.println("Dijkstra.");
				System.out.println("+rapide. " + costFastDijTime+ " <= +court. "+costShortDijTime);
				System.out.println("AStar.");
				System.out.println("+rapide. " + costFastStarTime+ " <= +court. "+costShortStarTime);
		
				assertTrue(costFastDijLength >= costShortDijTime);
				assertTrue(costFastStarLength >= costShortStarTime);
				System.out.println("Comparaison des couts en distance :");
				System.out.println("Dijkstra.");
				System.out.println("+rapide. " + costFastDijLength+ " >= +court. "+costShortStarLength);
				System.out.println("AStar.");
				System.out.println("+rapide. " + costFastStarLength+ " >= +court. "+costShortStarLength);
			}
		}
		System.out.println();
		System.out.println();

	}
}
