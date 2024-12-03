package use_case.main;

import org.junit.*;

import app.Main;

public class MainTest {

	@Test
	// Tests the main file functionality
	public void MainTest() {
		Main.main(new String[] {});
	}

	@Test
	// Covers the main file creation.
	public void DummyMainTest() {
		Main main = new Main();
	}
}
