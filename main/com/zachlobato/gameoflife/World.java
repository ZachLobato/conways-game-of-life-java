package com.zachlobato.gameoflife;

public class World {

	Coordinate topLeft;
	int width;
	int height;
	public boolean[][] cells;
	public int yOffset;
	public int xOffset;

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
					if (cellIsActive(x + i, y + j)){
						activeCount++;
					}						
				}				
			}
		}

		return activeCount;
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

		for (int y = 0; y < this.height; y++)		
			for(int x = 0; x < this.width; x++){
				{
					if (cells[x + xOffset][y + yOffset])
						output.append("X");
					else
						output.append("O");
				}
				output.append("\n");
			}

		return output.toString();
	}

	public void activateCell(int x, int y) {
		cells[x][y] = true;
	}

	public void deactivateCell(int x, int y){
		cells[x][y] = false;
	}

}
