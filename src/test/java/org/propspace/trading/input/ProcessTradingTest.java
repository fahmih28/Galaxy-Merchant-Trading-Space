package org.propspace.trading.input;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.propspace.trading.service.ProcessTradingService;

public class ProcessTradingTest {
	private ProcessTradingService processTrading;

	@Before
	public void init() throws Exception {
		processTrading = new ProcessTradingService();
	}

	@Test
	public void proceedTheDataShouldOk() throws IOException {
		processTrading.proceedData(ProcessTradingTest.class.getResourceAsStream("/input.txt"), System.out);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testProceedDataIllegalArgumentInput() throws Exception {
		processTrading.proceedData(null, System.out);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testProceedDataIllegalArgumentOutput() throws Exception {
		processTrading.proceedData(System.in, null);
	}

	
	
	
}
