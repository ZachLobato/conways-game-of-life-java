package com.zachlobato.gameoflife;

public class Simulator {

	public void simulate(int numberOfGenerations) {
		// generateSeedWorld
		World seedWorld = generatesSeedWorld();
		
		// outputWorld to Screen
		outputWorld(seedWorld);
								
		// outputWorld numberOfGenerations times
		World world = seedWorld;
		for (int generations = numberOfGenerations; generations > 0; generations--){
			world = simulateGeneration(world);
			outputWorld(world);
		}		
	}

	protected World generatesSeedWorld() {
		// createRandomSizedWorld
		
		// populateWorldWithCells
				
		return null;
	}

	protected void outputWorld(World world) {		
		// mapWorldToOutput
	}

	protected World simulateGeneration(World oldWorld) {
		// determineExpansion
		
		// createReplacementWorld
		
		// simulateGeneration
		
		// contractWorld		
		
		return null;
	}		
	
}
