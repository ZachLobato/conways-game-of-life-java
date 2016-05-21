package com.zachlobato.gameoflife;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.runner.RunWith;

@RunWith(MockitoJUnitRunner.class)
public class SimulatorSimulate {	

	@Test
	public void zeroGenerations(){
		// Given
		Simulator mockedSimulator = spy(Simulator.class);
		World seedWorld = new World();
		
		// When	
		when(mockedSimulator.generatesSeedWorld()).thenReturn(seedWorld);
		mockedSimulator.simulate(0);

		// Then
		verify(mockedSimulator).generatesSeedWorld();
		verify(mockedSimulator).outputWorld(seedWorld);
	}
	
	@Test
	public void oneGenerations(){
		// Given
		Simulator mockedSimulator = spy(Simulator.class);		
		World seedWorld = new World();
		World world2 = new World();

		// When
		when(mockedSimulator.generatesSeedWorld()).thenReturn(seedWorld);
		when(mockedSimulator.simulateGeneration(seedWorld)).thenReturn(world2);
		mockedSimulator.simulate(1);

		// Then
		verify(mockedSimulator).generatesSeedWorld();
		verify(mockedSimulator).outputWorld(seedWorld);
		verify(mockedSimulator).simulateGeneration(seedWorld);
		verify(mockedSimulator).outputWorld(world2);		
	}

}
