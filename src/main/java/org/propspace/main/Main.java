package org.propspace.main;

import org.propspace.trading.service.ProcessTradingService;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args ) throws Exception
    {
    	ProcessTradingService pts = new ProcessTradingService(Main.class.getResourceAsStream("/input.txt"));
    	pts.proceedData(System.out);
    }
}
