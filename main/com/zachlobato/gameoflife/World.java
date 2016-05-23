package com.zachlobato.gameoflife;

public class World {

	Coordinate topLeft;
	int width;
	int height;
	private boolean[][] cells;
	private int yOffset;
	private int xOffset;

	public World(Coordinate topLeftCoordinate, int width, int height){
		this.topLeft = new Coordinate(topLeftCoordinate.x, topLeftCoordinate.y);
		this.width = width;
		this.height = height;
		cells = new boolean[width][height];
	}

	public int getActiveNeighbors(int x, int y) {		
		int activeCount = 0;

		for (int i = -1; i < 2; i++){
			for (int j = -1; j < 2; j++){
				// Don't count own cell
				if (!(i == 0 && j == 0)){
					if (cellIsActive(x + i + xOffset, y + j + yOffset)){
						activeCount++;
					}						
				}				
			}
		}

		return activeCount;
	}
	
	public boolean getCellValue(int x, int y){
		return cells[x + xOffset][y + yOffset];
	}
	
	public void setCellValue(int x, int y, boolean newValue){
		cells[x + xOffset][y + yOffset] = newValue;
	}

	private boolean cellIsActive(int x, int y) {
		try{
			if (cells[x][y]){
				return true;
			}

			return false;
		} catch (ArrayIndexOutOfBoundsException e){
			return false;
		}
	}

	public String toString(){
		StringBuilder output = new StringBuilder();

		for (int y = 0; y < this.height; y++){		
			for(int x = 0; x < this.width; x++){
				{
					if (cells[x + xOffset][y + yOffset])
						output.append("X");
					else
						output.append("O");
				}
			}
			output.append("\n");
		}
		return output.toString();
	}

	public void contractWorld() {

		boolean topContracts, bottomContracts, leftContracts, rightContracts;
		topContracts = bottomContracts = leftContracts = rightContracts = true;


		// Check top row
		// Check bottom row		
		for (int x = 0; x < this.width; x++){
			if (this.getActiveNeighbors(x, 0)>0){
				topContracts = false;
			}
			if (this.getActiveNeighbors(x, this.height-1)>0){
				bottomContracts = false;
			}
		}

		// Check left column		
		// Check right column
		for (int y = 0; y < this.height; y++){
			if (this.getActiveNeighbors(0, y)>0){
				leftContracts = false;
			}
			if (this.getActiveNeighbors(this.width-1, y)>0){
				rightContracts = false;
			}
		}

		if (topContracts){
			this.topLeft.y -= 1;
			this.yOffset += 1;
			this.height -= 1;
		}
		if (bottomContracts){
			this.height -= 1;
		}
		if (leftContracts){
			this.topLeft.x += 1;
			this.xOffset += 1;
			this.width -= 1;
		}
		if (rightContracts){
			this.width -= 1;
		}
	}


	public void activateCell(int x, int y) {
		cells[x][y] = true;
	}

	public void deactivateCell(int x, int y){
		cells[x][y] = false;
	}

}
