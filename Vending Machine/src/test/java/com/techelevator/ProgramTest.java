package com.techelevator;

import com.techelevator.Program;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProgramTest {

	@Test
		public void Valid_Input_Returns_True_Test() {

		// Arrange

		String[] testArrayFirstThree = new String[]{"1", "2", "3"};

		// Act
		Boolean actual = Program.isValidInput(testArrayFirstThree,"1");
		Boolean expected = true;

		// Assert

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void Invalid_Input_Returns_False_Test() {

		// Arrange

		String[] testArrayFirstThree = new String[]{"1", "2", "3"};

		// Act
		Boolean actual = Program.isValidInput(testArrayFirstThree,"6");
		Boolean expected = false;

		// Assert

		Assert.assertEquals(expected, actual);
	}

	//TODO: empty string test
}
