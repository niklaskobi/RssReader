package main;

import java.util.Scanner;

import newsSource.NewsSource;
import newsSource.XmlHandler;



public class Menu
{

	public Menu()
	{
		Scanner input = new Scanner(System.in);
		String in = "";

		while (!in.equals("c"))
		{
			presentMenu();
			in = input.nextLine();
			switch (in)
			{
				case "l":
					listSources();
					break;
				case "e":
					editSource();
					break;
				case "a":
					addSource();
					break;
				case "r":
					removeSource();
					break;
				case "s":
					saveXML();
					break;
				case "o":
					openXML();
					break;
				case "c":
					break;
			}
		}
	}
	
	
	private static void openXML()
	{
		XmlHandler.readXML("test.xml");
	}

	private static void saveXML()
	{
		XmlHandler.writeXML(Main.sourceController.getNewsSourceList(), "test.xml");
	}


	private static void editSource()
	{
		System.out.print("Enter source name you would like to edit: ");
		Scanner input = new Scanner(System.in);
		String in = input.nextLine();
		//int sourceIndex = Integer.parseInt(in);
		if (Main.sourceController.editSource(in) == 0) System.out.println("element modified");
		else System.out.println("element not found");
		//input.close();
	}
	
		
	private static void removeSource()
	{
		System.out.print("Enter source name you would like to remove: ");
		Scanner input = new Scanner(System.in);
		String in = input.nextLine();
		if (Main.sourceController.removeSource(in) == 0) System.out.println("element removed");
		else System.out.println("element not found");
		//input.close();
	}


	private static void addSource()
	{
		NewsSource newSource = NewsSource.createWithUserInput(null);
		Main.sourceController.addSource(newSource);
	}


	private static void listSources()
	{
		int cnt = 0;
		for (NewsSource source : Main.sourceController.getNewsSourceList())
		{
			System.out.println("----------------------");
			System.out.println("("+cnt+")");
			source.print();
			cnt++;		
		}
	}


	public static void presentMenu()
	{
		System.out.println("============================");
		System.out.println("l: List sources");
		System.out.println("e: Edit source");
		System.out.println("a: Add source");
		System.out.println("r: Remove source");
		System.out.println("s: Save XML");
		System.out.println("o: Open XML");
		System.out.println("c: Close");
		System.out.println("============================");
	}
}
