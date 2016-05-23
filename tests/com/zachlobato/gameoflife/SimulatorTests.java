package com.zachlobato.gameoflife;

import org.junit.*;
import static org.mockito.Mockito.*;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.runner.RunWith;

@RunWith(MockitoJUnitRunner.class)
public class SimulatorTests {	

	@Spy
	Simulator mockedSimulator;
	
	@Test
	public void simulateGenerationTest(){
		// Given
		World oldWorld = new World(new Coordinate(1234,1234), 3, 3);
		World newWorld = new World(new Coordinate(1234,1234), 3, 3);
				
		// When	
		when(mockedSimulator.createReplacementWorld(oldWorld)).thenReturn(newWorld);			
		mockedSimulator.simulateGeneration(oldWorld);

		// Then
		verify(mockedSimulator).createReplacementWorld(oldWorld);
		verify(mockedSimulator).populateReplacementWorld(oldWorld, newWorld);
		
	}
	
	@Test
	public void createReplacementWorldTestNoChanges(){
		// Given
		World oldWorld = new World(new Coordinate(1234,1234), 3, 3);
		World spyWorld = spy(oldWorld);
		
		// When
		//when(spy.getActiveNeighbors(0, 0)).thenReturn(0);
		mockedSimulator.createReplacementWorld(spyWorld);

		// Then
		
		// Corners
		verify(spyWorld, times(2)).getActiveNeighbors(0,0);
		verify(spyWorld, times(2)).getActiveNeighbors(2,0);
		verify(spyWorld, times(2)).getActiveNeighbors(0,2);
		verify(spyWorld, times(2)).getActiveNeighbors(2,2);
		
		// Top		
		verify(spyWorld).getActiveNeighbors(1,0);
			
		// Bottom		
		verify(spyWorld).getActiveNeighbors(1,2);
		
		// Left
		verify(spyWorld).getActiveNeighbors(1,0);
		
		// Right
		verify(spyWorld).getActiveNeighbors(1,2);
		
	}

	public void populateReplacementWorldTest(){
		// Given
		World oldWorld = new World(new Coordinate(123, 456), 3, 3);
		World newWorld = new World(new Coordinate(123, 456), 3, 3);
		World spyNewWorld = spy(newWorld);
		
		// When
		mockedSimulator.populateReplacementWorld(oldWorld, spyNewWorld);
		
		// Then
		verify(spyNewWorld).getActiveNeighbors(0, 0);
	}
	
	/* UNIT TESTING */
	
	public void createReplacementWorldTest1(){
		Simulator sim = new Simulator();
		
		World oldWorld = new World(new Coordinate(0, 0), 3, 5);
		
		oldWorld.activateCell(1, 1);
		oldWorld.activateCell(1, 2);
		oldWorld.activateCell(1, 3);
		
		World newWorld = sim.createReplacementWorld(oldWorld);
		Assert.assertEquals(-1, newWorld.topLeft.x);
		Assert.assertEquals(0, newWorld.topLeft.y);		
		
		String output = newWorld.toString();
		
		Assert.assertEquals("OOOOO\n"
						  + "OOOOO\n"
						  + "OOOOO\n"
						  + "OOOOO\n"
						  + "OOOOO\n", output);				
	}
	
	public void simulateTest1(){
		Simulator simulator =  new Simulator();
		
		World world = new World(new Coordinate(0,0), 5, 3);
		world.activateCell(1,1);
		world.activateCell(2,1);
		world.activateCell(3,1);
		String output = world.toString();		
		Assert.assertEquals("OOOOO\n"
						  + "OXXXO\n"
						  + "OOOOO\n", output);
		
		World nextGenWorld = simulator.simulateGeneration(world);
				
		output = nextGenWorld.toString();		
		Assert.assertEquals("OOO\n"
						  + "OXO\n"
						  + "OXO\n"
						  + "OXO\n"
						  + "OOO\n", output);
		Assert.assertEquals(1, nextGenWorld.topLeft.x);
		Assert.assertEquals(-1, nextGenWorld.topLeft.y);
		
		
//		World nextNextGenWorld = simulator.simulateGeneration(nextGenWorld);
//		output = nextNextGenWorld.toString();
//		
//		Assert.assertEquals("OOOOO\n"
//						  + "OXXXO\n"
//						  + "OOOOO\n", output);
//		
//		nextGenWorld = simulator.simulateGeneration(nextNextGenWorld);
//		output = nextGenWorld.toString();
//		
//		Assert.assertEquals("OOO\n"
//						  + "OXO\n"
//						  + "OXO\n"
//						  + "OXO\n"
//						  + "OOO\n", output);
//		
//		nextNextGenWorld = simulator.simulateGeneration(nextGenWorld);
//		output = nextNextGenWorld.toString();
//		
//		Assert.assertEquals("OOOOO\n"
//				  + "OXXXO\n"
//				  + "OOOOO\n", output);
		
	}
	
}
