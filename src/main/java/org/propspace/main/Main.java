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
    	ProcessTradingService pts = new ProcessTradingService();
    	pts.proceedData(Main.class.getResourceAsStream("/input.txt"),System.out);
    }
}
