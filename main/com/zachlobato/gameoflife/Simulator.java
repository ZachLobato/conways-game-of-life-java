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

	/**
	 * Generates a new empty replacement world of the correct (expanded if necessary)
	 * size based on the oldWorld. This will be used to populate the next generation
	 * of cells.
	 * 
	 * @param oldWorld
	 * @return
	 */
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

	/**
	 * Takes a world generated by createReplacementWorld and populates it
	 * with the oldWorld's nextGeneration of values.
	 * 
	 * @param oldWorld
	 * @param newWorld
	 */
	public void populateReplacementWorld(World oldWorld, World newWorld) {
		int xOffset = oldWorld.topLeft.x -  newWorld.topLeft.x;
		int yOffset = newWorld.topLeft.y - oldWorld.topLeft.y;
		
		int neighborCount = 0;
		for (int x = 0; x < oldWorld.width; x++){
			for (int y = 0; y < oldWorld.height; y++){
				newWorld.cells[x + xOffset][y + yOffset] = oldWorld.cells[x][y];
								
				neighborCount = oldWorld.getActiveNeighbors(x, y);
				
				switch (neighborCount) {
				// Cell Inactive
				case 0:
				case 1:
					newWorld.deactivateCell(x + xOffset, y + yOffset);
					break;
				// Cell does not change
				case 2:
					break;
				// Cell is Active					
				case 3:
					newWorld.activateCell(x + xOffset, y + yOffset);
					break;
				// Cell does not change
				default:
					break;
				}												
			}
		}
	}

	public void contractReplacementWorld(World newWorld) {
		
		boolean topContracts, bottomContracts, leftContracts, rightContracts;
		topContracts = bottomContracts = leftContracts = rightContracts = true;
		
		
		// Check top row
		// Check bottom row		
		for (int x = 0; x < newWorld.width; x++){
			if (newWorld.getActiveNeighbors(x, 0)>0){
				topContracts = false;
			}
			if (newWorld.getActiveNeighbors(x, newWorld.height-1)>0){
				bottomContracts = false;
			}
		}
		
		// Check left column		
		// Check right column
		for (int y = 0; y < newWorld.height; y++){
			if (newWorld.getActiveNeighbors(0, y)>0){
				leftContracts = false;
			}
			if (newWorld.getActiveNeighbors(newWorld.width-1, y)>0){
				rightContracts = false;
			}
		}
		
		if (topContracts){
			newWorld.topLeft.y -= 1;
			newWorld.height -= 1;
		}
		if (bottomContracts){
			newWorld.height -= 1;
		}
		if (leftContracts){
			newWorld.topLeft.x += 1;
			newWorld.width -= 1;
		}
		if (rightContracts){
			newWorld.width -= 1;
		}
	}		
	
}
