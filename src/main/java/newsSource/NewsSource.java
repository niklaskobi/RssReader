package newsSource;

import java.util.Scanner;

/**
 * This is the class for a news source information
 * it contains all relevant information about the source
 * @author kola
 *
 */
public class NewsSource
{

	private String	sourceName;
	private String	description;
	private String	uri;
	private String	xmlField_summary;
	private String	xmlField_text;


	/**
	 * default constructor
	 */
	public NewsSource()
	{
		new NewsSource(null, null, null, null, null);
	}
	
	
	/**
	 * standard constructor with given parameters
	 * @param sN
	 * @param descr
	 * @param uri
	 * @param xmlSum
	 * @param xmlText
	 */
	public NewsSource(String sN, String descr, String uri, String xmlSum, String xmlText)
	{
		this.setSourceName(sN);
		this.setDescription(descr);
		this.setUri(uri);
		this.setXmlField_summary(xmlSum);
		this.setXmlField_text(xmlText);
	}
	
	
	public void print()
	{
		System.out.printf("%-20s %s %n", "name", this.getSourceName());
		System.out.printf("%-20s %s %n","descr",this.getDescription());
		System.out.printf("%-20s %s %n","uri",this.getUri());
		System.out.printf("%-20s %s %n","xml_summary",this.getXmlField_summary());
		System.out.printf("%-20s %s %n","xml_text",this.getXmlField_text());
	}
	
	
	/**
	 * constructor with user input, can be used for modifying an existing newsSOurce object
	 * @param oldSource if != null, modify, else create completely new one
	 * @return new object
	 */
	public static NewsSource createWithUserInput(NewsSource oldSource)
	{		
		Scanner input = new Scanner(System.in);
		
		if (oldSource != null)
		{
			System.out.println("modify this:");
			oldSource.print();
			System.out.println("leave blank for no modification");
		}

		System.out.println("Please enter source name :");
		String name = input.nextLine();
		if ((oldSource!= null) && (name.isEmpty()))
		{
			name = oldSource.getSourceName();
			System.out.println("leave old: "+name);
		}

		System.out.println("Please enter source description : ");
		String descr = input.nextLine();
		if ((oldSource!= null) && (descr.isEmpty()))
		{
			descr = oldSource.getDescription();
			System.out.println("leave old: "+descr);
		}
		
		System.out.println("Please enter source uri : ");
		String uri = input.nextLine();
		if ((oldSource!= null) && (uri.isEmpty()))
		{
			uri = oldSource.getUri();
			System.out.println("leave old: "+uri);
		}

		System.out.println("Please enter source xml field for summary : ");
		String xmlSum = input.nextLine();
		if ((oldSource!= null) && (xmlSum.isEmpty()))
		{
			xmlSum = oldSource.getXmlField_summary();
			System.out.println("leave old: "+xmlSum);
		}

		System.out.println("Please enter xml field for text : ");
		String xmlText = input.nextLine();
		if ((oldSource!= null) && (xmlText.isEmpty()))
		{
			xmlText = oldSource.getXmlField_text();
			System.out.println("leave old: "+xmlText);
		}
		
		//input.close();
		
		return new NewsSource(name, descr, uri, xmlSum, xmlText);
	}		
	

	public String getSourceName()
	{
		return sourceName;
	}


	public void setSourceName(String sourceName)
	{
		this.sourceName = sourceName;
	}


	public String getDescription()
	{
		return description;
	}


	public void setDescription(String description)
	{
		this.description = description;
	}


	public String getUri()
	{
		return uri;
	}


	public void setUri(String uri)
	{
		this.uri = uri;
	}


	public String getXmlField_summary()
	{
		return xmlField_summary;
	}


	public void setXmlField_summary(String xmlField_summary)
	{
		this.xmlField_summary = xmlField_summary;
	}


	public String getXmlField_text()
	{
		return xmlField_text;
	}


	public void setXmlField_text(String xmlField_text)
	{
		this.xmlField_text = xmlField_text;
	}
}
