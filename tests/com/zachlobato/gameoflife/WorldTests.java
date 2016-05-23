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
	
	@Test
	public void toStringTest(){
		World world = new World(new Coordinate(1234,1234), 3, 3);
		world.activateCell(1,1);		
		String output = world.toString();		
		Assert.assertEquals("OOO\nOXO\nOOO\n", output);
	}
	
	public void contractWorldTest1(){
		World world = new World(new Coordinate(1234,1234), 5, 5);
		world.activateCell(1,1);
		world.activateCell(2,1);
		world.activateCell(3,1);
		String output = world.toString();		
		Assert.assertEquals("OOOOO\n"
						  + "OOOOO\n"
						  + "OXXXO\n"
						  + "OOOOO\n"
						  + "OOOOO\n", output);
		world.contractWorld();
		Assert.assertEquals("OOOOO\n"
						  + "OXXXO\n"
						  + "OOOOO\n", output);
	}
	
	public void contractWorldTest2(){
		World world = new World(new Coordinate(1234,1234), 5, 5);
		world.activateCell(1,1);
		world.activateCell(2,1);
		world.activateCell(3,1);
		String output = world.toString();		
		Assert.assertEquals("OOXOO\n"
						  + "OOXOO\n"
						  + "OOXOO\n"
						  + "OOXOO\n"
						  + "OOXOO\n", output);
		world.contractWorld();
		Assert.assertEquals("OXO\n"
						  + "OXO\n"
						  + "OXO\n"
						  + "OXO\n"
						  + "OXO\n", output);
	}
	
	
	

}
