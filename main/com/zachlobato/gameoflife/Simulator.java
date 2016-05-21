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
		// createReplacementWorld
		World newWorld = createReplacementWorld(oldWorld);
		
		// populateReplacementWorldGeneration
		populateReplacementWorld(oldWorld, newWorld);
		
		// contractWorld		
		contractReplacementWorld(newWorld);
		
		return newWorld;
	}

	public World createReplacementWorld(World oldWorld) {
		boolean topExpands, bottomExpands, leftExpands, rightExpands;
		topExpands = bottomExpands = leftExpands = rightExpands = false;	
		
		// Check top row
		// Check bottom row		
		for (int x = 0; x < oldWorld.width; x++){
			if (oldWorld.getActiveNeighbors(x, 0)==3){
				topExpands = true;
			}
			if (oldWorld.getActiveNeighbors(x, oldWorld.height-1)==3){
				bottomExpands = true;
			}
		}
		
		// Check left column		
		// Check right column
		for (int y = 0; y < oldWorld.height; y++){
			if (oldWorld.getActiveNeighbors(0, y)==3){
				leftExpands = true;
			}
			if (oldWorld.getActiveNeighbors(oldWorld.width-1, y)==3){
				rightExpands = true;
			}
		}
		
		Coordinate newTopLeft = new Coordinate(oldWorld.topLeft.x, oldWorld.topLeft.y);
		int newWidth = oldWorld.width;
		int newHeight = oldWorld.height;
		
		if (topExpands){
			newTopLeft.y -= 1;
			newHeight += 1;
		}
		if (bottomExpands){
			newHeight += 1;
		}
		if (leftExpands){
			newTopLeft.x -= 1;
			newWidth += 1;
		}
		if (rightExpands){
			newWidth += 1;
		}
		
		return new World(newTopLeft, newWidth, newHeight);
	}

	public void populateReplacementWorld(World oldWorld, World newWorld) {
		// TODO Auto-generated method stub
		
	}

	public void contractReplacementWorld(World newWorld) {
		// TODO Auto-generated method stub
		
	}		
	
}
