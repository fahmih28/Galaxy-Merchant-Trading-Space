package org.propspace.trading.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import org.propspace.roman.RomanEnum;
import org.propspace.roman.RomanSymbolException;
import org.propspace.roman.RomanTranslator;
import org.propspace.trading.vo.GalaxyTrading;
import org.propspace.trading.vo.Unit;
import org.propspace.util.StringUtils;

public class ProcessTradingService {
	private BufferedReader reader;
	private GalaxyTrading galaxyTrading;
	private PrintStream output;
	public ProcessTradingService(InputStream inputStream)
	{
		reader = new BufferedReader(new InputStreamReader(inputStream));
		galaxyTrading = new GalaxyTrading();
	}
	
	public void proceedData(OutputStream out) throws IOException{
		String line = null;
		this.output = new PrintStream(out);
		while((line = reader.readLine()) != null)
		{
			String[] splits = line.split("\\s+");
			try {
				if(splits[splits.length-1].equals("?"))
				{
					askQuestion(splits);
				}
				else {
					setData(splits);
				}
			}
			catch(IllegalArgumentException ex) {
				output.println(ex.getMessage());
			}
		}
	}
	
	private void askQuestion(String[] question)
	{
		int i = 0;
		if(!"how".equalsIgnoreCase(question[i++]))
		{
			throw new IllegalArgumentException("I don't know what you are talking about");
		}
		
		if(!StringUtils.isInIgnoreCase(question[i++],"much","many"))
		{
			throw new IllegalArgumentException("I don't know what you are talking about");
		}
		
		
		if("is".equalsIgnoreCase(question[i]))
		{
			i++;
			StringBuilder romanCompounder = new StringBuilder();
			StringBuilder questionCompounder = new StringBuilder();
			for(;i < question.length-1;i++)
			{
				String roman = galaxyTrading.getRomanCode(question[i]);
				if(roman == null)
				{
					throw new IllegalArgumentException("I cannot find alias '"+question[i]+"'");
				}
				romanCompounder.append(roman);
				if(questionCompounder.length() != 0)
				{
					questionCompounder.append(" ");
				}
				questionCompounder.append(question[i]);
			}
			
			
			try {
				BigDecimal value = RomanTranslator.translate(romanCompounder.toString());
				output.printf("%s is %d%n",questionCompounder,value.longValue());
			}
			catch(RomanSymbolException ex) {
				throw new IllegalArgumentException("Invalid roman number '"+romanCompounder.toString()+"', Cause : "+ex.getMessage());
			}
		}
		else {
			String unitType = question[i++];
			if(!"is".equalsIgnoreCase(question[i++])) {
				throw new IllegalArgumentException("I have no iden what you are talking about ");
			}
			
			StringBuilder romanCompounder = new StringBuilder();
			StringBuilder codeCompounder = new StringBuilder();
			String itemName = null;
			for(;i < question.length-1;i++)
			{
				if(i < question.length-2) {
					String roman = galaxyTrading.getRomanCode(question[i]);
					if(roman == null)
					{
						throw new IllegalArgumentException("I don't know alias '"+question[i]+"' for any roman character");
					}
					
					romanCompounder.append(roman);
					if(codeCompounder.length() != 0)
					{
						codeCompounder.append(" ");
					}
					codeCompounder.append(question[i]);
				}
				else{
					itemName = question[i];
				}
			}
			
			Unit itemUnit = null;
			if(itemName == null || (itemUnit =  galaxyTrading.getItem(itemName)) == null)
			{
				throw new IllegalArgumentException("I have no idea what item you are talking about");
			}

			if(!itemUnit.getName().equalsIgnoreCase(unitType)) {
				throw new IllegalArgumentException("I have no idea to convert unit of item '"+itemName+"' from '"+itemUnit.getName()+"' To '"+unitType+"'");
			}
			
			try {
				if(romanCompounder.length() == 0)
				{
					throw new IllegalArgumentException("I have no idea what you are talking about");
				}
				
				BigDecimal value = itemUnit.getValue().multiply(RomanTranslator.translate(romanCompounder.toString()));
				output.println(codeCompounder.toString()+" "+itemName+" is "+value.longValue()+" "+unitType);
			}
			catch(RomanSymbolException ex)
			{
				throw new IllegalArgumentException("There is something wrong with the roman '"+romanCompounder.toString()+"'");
			}
		}
	}
	
	private void setData(String[] data) throws IOException
	{
		if(data.length == 3)
		{
			if(data[1].equalsIgnoreCase("is"))
			{
				try {
					if(RomanEnum.valueOf(data[2]) != null)
					{
						galaxyTrading.setRomanCode(data[0], data[2]);
					}
				}
				catch(IllegalArgumentException ex)
				{
					throw new IllegalArgumentException("Invalid Roman Character '"+data[2]+"'");
				}
			}
			else {
				throw new IllegalArgumentException("I have no idea what you are talking about");
			}
		}
		else {
			StringBuilder numbers = new StringBuilder();
			String itemName = null;
			int i = 0;
			for(;i < data.length;i++)
			{
				if(itemName == null) {
					String romanCode = galaxyTrading.getRomanCode(data[i]);
					if(romanCode != null)
					{
						numbers.append(romanCode);
					}
					else {
						itemName = data[i];
						break;
					}
				}
			}
			
			int left = data.length-(++i);
			if(left != 3)
			{
				throw new IllegalArgumentException("I Have No Idea What Are You Talking About");
			}
			else {
				if(!"is".equalsIgnoreCase(data[i]))
				{
					throw new IllegalArgumentException("I Have No Idea What Are You Talking About");
				}
				
				if(!data[++i].matches("\\d+"))
				{
					throw new IllegalArgumentException("I Have No Idea What Are You Talking About");
				}
				
				
				try {
					BigDecimal value = new BigDecimal(data[i]).divide(RomanTranslator.translate(numbers.toString()));
					galaxyTrading.setItem(itemName, value, data[++i]);
				}
				catch(RomanSymbolException ex)
				{
					throw new IllegalArgumentException("There is an error : "+ex.getMessage());
				}
				
			}
		}
	}
}
