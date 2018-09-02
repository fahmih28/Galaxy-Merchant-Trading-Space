package org.propspace.trading.input;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.propspace.trading.service.ProcessTradingService;

public class ProcessTradingTest {
	private ProcessTradingService processTrading;
	
	@Before
	public void init() throws Exception
	{
		FileInputStream in = new FileInputStream(new File("E:\\input.txt"));
		processTrading = new ProcessTradingService(in);
	}
	
	@Test
	public void proceedTheDataShouldOk() throws IOException
	{
		processTrading.proceedData(System.out);
	}
	
	
}
