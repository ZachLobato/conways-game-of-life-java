package com.zachlobato.gameoflife;

import org.junit.*;
import static org.mockito.Mockito.*;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.runner.RunWith;

@RunWith(MockitoJUnitRunner.class)
public class WorldTests {	

	@Spy
	Simulator mockedSimulator;
	
	@Test
	public void getActiveNeighborsTestZeroNeighbors(){
		World world = new World(new Coordinate(1234,1234), 3, 3);
		Assert.assertEquals(0, world.getActiveNeighbors(0, 0));
	}		
	
	@Test
	public void getActiveNeighborsTestOneNeighbor(){
		World world = new World(new Coordinate(1234,1234), 3, 3);
		world.activateCell(0,0);		
		Assert.assertEquals(1, world.getActiveNeighbors(1, 1));
	}
	
	@Test
	public void getActiveNeighborsTestFourNeighbor(){
		World world = new World(new Coordinate(1234,1234), 3, 3);
		world.activateCell(0,0);
		world.activateCell(2,0);
		world.activateCell(0,2);
		world.activateCell(2,2);
		Assert.assertEquals(4, world.getActiveNeighbors(1, 1));
	}

}
