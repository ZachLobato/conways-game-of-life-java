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
	public void simulateTestZeroGenerations(){
		// Given
		World seedWorld = new World(new Coordinate(1234,1234), 3, 3);
		
		// When	
		when(mockedSimulator.generatesSeedWorld()).thenReturn(seedWorld);
		mockedSimulator.simulate(0);

		// Then
		verify(mockedSimulator).generatesSeedWorld();
		verify(mockedSimulator).outputWorld(seedWorld);
	}
	
	@Test
	public void simulateTestOneGenerations(){
		// Given		
		//Simulator mockedSimulator = spy(Simulator.class);
		World seedWorld = new World(new Coordinate(1234,1234), 3, 3);
		World world2 = new World(new Coordinate(1234,1234), 3, 3);

		// When
		when(mockedSimulator.generatesSeedWorld()).thenReturn(seedWorld);
		when(mockedSimulator.simulateGeneration(seedWorld)).thenReturn(world2);
		
		mockedSimulator.simulate(1);

		// Then
		verify(mockedSimulator).generatesSeedWorld();
		verify(mockedSimulator).outputWorld(seedWorld);
		verify(mockedSimulator).outputWorld(world2);		
	}
	
	@Test
	public void simulateTestOneGenerations2(){
		// Given		
		//Simulator mockedSimulator = spy(Simulator.class);
		World seedWorld = new World(new Coordinate(1234,1234), 3, 3);
		World world2 = new World(new Coordinate(1234,1234), 3, 3);

		// When
		when(mockedSimulator.generatesSeedWorld()).thenReturn(seedWorld);
		
		mockedSimulator.simulate(1);

		// Then
		verify(mockedSimulator).generatesSeedWorld();
		verify(mockedSimulator).outputWorld(seedWorld);
		verify(mockedSimulator).simulateGeneration(seedWorld);
	}
	
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
		verify(mockedSimulator).contractReplacementWorld(newWorld);
		
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

}
