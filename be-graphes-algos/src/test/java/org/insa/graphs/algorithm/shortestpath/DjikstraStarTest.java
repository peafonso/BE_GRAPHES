package org.insa.graphs.algorithm.shortestpath;

import static org.junit.Assert.*;

import org.junit.Test;

public class DjikstraStarTest {

	@Test
	//@Test
	public void testMode0() throws Exception {
		String carte = "C:/Users/clair/Desktop/Perrine/INSA/3A/SEM2/BE/Maps/haute-garonne.mapgr";
		
		ShortestPathTest test = new  ShortestPathTest ();	
		int orgn, dest;
		
		System.out.println("-----------------------------------------------");
		System.out.println("TEST DE VALIDITE AVEC ORACLE ");
		System.out.println("HAUTE GARONNE");
		System.out.println("MODE: 0. Shortest path, all roads allowed ");
		System.out.println();
		
		System.out.println("chemin nul.");	
		orgn=0;	dest=0;
		test.avecOracle(carte, 0, orgn, dest);  
			
		System.out.println("chemin simple.");
		orgn = 127949; dest = 41899;
		test.avecOracle(carte, 0, orgn, dest);    	
			
		System.out.println("sommets inexistants (origine n'existe pas).");
		orgn = -1;	dest = 41899;
		test.avecOracle(carte, 0, orgn, dest);    	


	}
	
	@Test
	//@Test
	public void testMode1() throws Exception {
		String carte = "C:/Users/clair/Desktop/Perrine/INSA/3A/SEM2/BE/Maps/haute-garonne.mapgr";
		
		ShortestPathTest test = new  ShortestPathTest ();	
		int orgn, dest;
		
		System.out.println("-----------------------------------------------");
		System.out.println("TEST DE VALIDITE AVEC ORACLE ");
		System.out.println("HAUTE GARONNE");
		System.out.println("MODE: 1. Shortest path, only roads open for cars ");
		System.out.println();
		
		System.out.println("chemin nul.");	
		orgn=0;	dest=0;
		test.avecOracle(carte, 1, orgn, dest);  
			
		System.out.println("chemin simple.");
		orgn = 127949; dest = 41899;
		test.avecOracle(carte, 1, orgn, dest);    	

		System.out.println("sommets inexistants (destination n'existe pas).");
		orgn = 127949; dest= 1000000;
		test.avecOracle(carte, 1, orgn, dest);   

  	
	}
	
	@Test
	//@Test
	public void testMode2() throws Exception {
		String carte = "C:/Users/clair/Desktop/Perrine/INSA/3A/SEM2/BE/Maps/toulouse.mapgr";
		
		ShortestPathTest test = new  ShortestPathTest ();	
		int orgn, dest;
			
		System.out.println("-----------------------------------------------");
		System.out.println("TEST DE VALIDITE AVEC ORACLE ");
		System.out.println("TOULOUSE");
		System.out.println("MODE: 2. Fastest path, all roads allowed  ");
		System.out.println();
		
		System.out.println("chemin nul.");	
		orgn=0;	dest=0;
		test.avecOracle(carte, 2, orgn, dest);  
			
		System.out.println("chemin simple.");
		orgn = 32947; dest = 4984;
		test.avecOracle(carte, 2, orgn, dest);    	

		System.out.println("sommets inexistants (origine et destination n'existent pas).");
		orgn = -1; dest= 50000;
		test.avecOracle(carte, 2, orgn, dest);    

  	
	}
	
	@Test
	//@Test
	public void testMode3() throws Exception {
		String carte = "C:/Users/clair/Desktop/Perrine/INSA/3A/SEM2/BE/Maps/toulouse.mapgr";
		
		ShortestPathTest test = new  ShortestPathTest ();	
		int orgn, dest;
			
		System.out.println("-----------------------------------------------");
		System.out.println("TEST DE VALIDITE AVEC ORACLE ");
		System.out.println("TOULOUSE");
		System.out.println("MODE: 3. Fastest path, only roads open for cars  ");
		System.out.println();
		
		System.out.println("chemin nul.");	
		orgn=0;	dest=0;
		test.avecOracle(carte, 3, orgn, dest);  
			
		System.out.println("chemin simple.");
		orgn = 4548; dest = 4984;
		test.avecOracle(carte, 3, orgn, dest);    	

		System.out.println("sommets inexistants (origine et destination n'existent pas).");
		orgn = -1; dest= 50000;
		test.avecOracle(carte, 3, orgn, dest);    
  	
	}
	
	@Test
	//@Test
	public void testMode4() throws Exception {
		String carte = "C:/Users/clair/Desktop/Perrine/INSA/3A/SEM2/BE/Maps/toulouse.mapgr";
		
		ShortestPathTest test = new  ShortestPathTest ();	
		int orgn, dest;
			
		System.out.println("-----------------------------------------------");
		System.out.println("TEST DE VALIDITE AVEC ORACLE ");
		System.out.println("TOULOUSE");
		System.out.println("MODE: 4. Fastest path for pedestrian ");
		System.out.println();
		
		System.out.println("chemin nul.");	
		orgn=0;	dest=0;
		test.avecOracle(carte, 4, orgn, dest);  
			
		System.out.println("chemin simple.");
		orgn = 4548; dest = 4984;
		test.avecOracle(carte, 4, orgn, dest);    	

		System.out.println("sommets inexistants (origine n'existe pas).");
		orgn = -1; dest= 9655;
		test.avecOracle(carte, 4, orgn, dest);    
  	
	}
	
	@Test
	//@Test
	public void testSansOracle() throws Exception {
		String carte = "C:/Users/clair/Desktop/Perrine/INSA/3A/SEM2/BE/Maps/toulouse.mapgr";
		
		ShortestPathTest test = new  ShortestPathTest ();	
		int orgn, dest;
			
		System.out.println("-----------------------------------------------");
		System.out.println("TEST DE VALIDITE SANS ORACLE ");
		System.out.println("TOULOUSE");
		System.out.println();
		
		System.out.println("chemin nul.");	
		orgn=0;	dest=0;
		test.sansOracle(carte, orgn, dest);  
			
		System.out.println("chemin simple.");
		orgn = 4548; dest = 4984;
		test.sansOracle(carte, orgn, dest);    	

		System.out.println("sommets inexistants (origine n'existe pas).");
		orgn = -1; dest= 9655;
		test.sansOracle(carte, orgn, dest);    
	}
	
}