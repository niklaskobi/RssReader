package rome;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;



public class PageParser
{

	public static void getAllImages(String url)
	{
		Document doc;
		try
		{
			// get all images
			doc = Jsoup.connect(url).get();
			Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
			for (Element image : images)
			{
				System.out.println("\nsrc : " + image.attr("src"));
				System.out.println("height : " + image.attr("height"));
				System.out.println("width : " + image.attr("width"));
				System.out.println("alt : " + image.attr("alt"));
			}

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}


	public static void getText(String url)
	{
		Document doc;
		try
		{
			doc = Jsoup.connect(url).get();
			// get meta keyword content
			// Elements els = doc.getElementsByClass("bg_block_info").not(".pad_10").not(".pad_20");
			String summary = doc.getElementsByClass("article__summary").text();
			String text = doc.getElementsByClass("article__text").text();
			System.out.println("SUMMARY : " + summary);
			System.out.println("TEXT : " + text);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
