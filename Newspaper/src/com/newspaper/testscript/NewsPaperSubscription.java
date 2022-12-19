package com.newspaper.testscript;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class NewsPaperSubscription {

	static List<NewsPaper> lstNewsPaper = new ArrayList<NewsPaper>();
	static String possibleCombination = "";
	@Test
	public static void test() {
		NewsPaper toi = new NewsPaper ("TOI", 3.0, 3.0, 3.0, 3.0, 3.0, 5.0, 6.0);//26
		NewsPaper hindu = new NewsPaper ("Hindu", 2.5, 2.5, 2.5, 2.5, 2.5, 4.0, 4.0);//20.5
		NewsPaper et = new NewsPaper ("ET", 4.0, 4.0, 4.0, 4.0, 4.0, 4.0, 10.0);//34
		NewsPaper bm = new NewsPaper ("BM", 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5);//10.5
		NewsPaper ht = new NewsPaper ("HT", 2.0, 2.0, 2.0, 2.0, 2.0, 4.0, 4.0);//18
		
		lstNewsPaper.add(toi);
		lstNewsPaper.add(hindu);
		lstNewsPaper.add(et);
		lstNewsPaper.add(bm);
		lstNewsPaper.add(ht);

		Reporter.log("Please Enter the Budget amount for the week: ",true);
		Scanner sc = new Scanner(System.in);
		Double weeklyBudget = sc.nextDouble();
		sc.close();
		
		
				
		for(NewsPaper paper : lstNewsPaper) {
					
			String currentCombination = findCombination(paper, weeklyBudget);
			if (currentCombination != "") {				
				if(!possibleCombination.contains(currentCombination)) {
					if(possibleCombination != "") {
						possibleCombination = possibleCombination + ", ";
					}
					possibleCombination = possibleCombination + currentCombination;
				}
			}
		}		

		if(possibleCombination == "") {
			possibleCombination = "{}";
		}
		
		if (possibleCombination == "{}") {
			Reporter.log("There are no possible Subscriptions available for the given input!",true);
		}
		else {
			Reporter.log("The possible Subscriptions are: "+ possibleCombination,true);
					}
		

	}
	
	public static String findCombination(NewsPaper currentPaper, Double weeklyBudget){
		String currentPaperName = currentPaper.Name;
		Double currentPaperAmount = currentPaper.Total;
		String result = "";
		
		for(NewsPaper paper : lstNewsPaper) {
			if(paper.Name != currentPaperName) {
				if(currentPaperAmount + paper.Total <= weeklyBudget) {
					List<String> lstStr = new ArrayList<String>();
					lstStr.add(currentPaperName);
					lstStr.add(paper.Name);
					Collections.sort(lstStr);
					if(result != "") {
						result = result + ", ";
					}
					
					String currentIterationResult = "{" + lstStr.get(0) + "," + lstStr.get(1) + "}";
					if(!possibleCombination.contains(currentIterationResult)) {
						result = result + currentIterationResult;
					}
					
				}
			}
		}
		return result;
	}
	

}

class NewsPaper {
	String Name;
	Double Mon, Tue, Wed, Thu, Fri, Sat, Sun, Total;
	
	NewsPaper(String name, Double mon, Double tue, Double wed, Double thu, Double fri, Double sat, Double sun)
	{
		this.Name = name;
		this.Mon = mon;
		this.Tue = tue;
		this.Wed = wed;
		this.Thu = thu;
		this.Fri = fri;
		this.Sat = sat;
		this.Sun = sun;
		this.Total = mon + tue + wed + thu + fri + sat + sun;
	}
}
