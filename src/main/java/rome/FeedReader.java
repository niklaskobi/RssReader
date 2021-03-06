package rome;

import java.net.URL;
import java.util.List;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;



public class FeedReader
{

	public static void Test()
	{
		boolean ok = false;
		try
		{
			URL feedUrl = new URL("https://www.rt.com/rss/news/"); // RT, rss
			// URL feedUrl = new URL("http://rss.cnn.com/rss/edition.rss"); // CNN top stories, rss
			// URL feedUrl = new URL("http://feeds.bbci.co.uk/news/rss.xml"); // BBC top stories, rss
			// URL feedUrl = new URL("http://www.aljazeera.com/xml/rss/all.xml"); // ALJAZEERA, rss
			// URL feedUrl = new URL("http://www.chinadaily.com.cn/rss/world_rss.xml"); // CHINADAILY world news, rss
			// URL feedUrl = new URL("http://allafrica.com/tools/headlines/rdf/latest/headlines.rdf"); // ALLAFRICA latest, rss
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(feedUrl));
			//System.out.println(feed);

			List<SyndEntry> syndList = feed.getEntries();
			System.out.println(syndList.get(0));
			PageParser.getText(syndList.get(0).getUri());
			ok = true;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			System.out.println("ERROR: " + ex.getMessage());
		}
		if (!ok)
		{
			System.out.println();
			System.out.println("FeedReader reads and prints any RSS/Atom feed type.");
			System.out.println("The first parameter must be the URL of the feed to read.");
			System.out.println();
		}
	}
}
