package newsSource;

import java.util.ArrayList;



public class NewsSourceController
{

	private ArrayList<NewsSource> newsSourceList = new ArrayList<NewsSource>();


	/**
	 * removes a given source from the list
	 * @param sourceName
	 * @return 0: element removed; 1: element not found
	 */
	public int removeSource(String sourceName)
	{
		for (NewsSource source : newsSourceList)
		{
			if (source.getSourceName().equals(sourceName))
			{
				newsSourceList.remove(source);
				return 0;
			}
		}
		return 1;
	}


	/**
	 * modifies given newsSource
	 * @param sourceName
	 * @return 0: ok; 1: element not found
	 */
	//public int editSource(int index)
	public int editSource(String sourceName)
	{
		//if ((index < 0) || (index>this.newsSourceList.size()))
		for (NewsSource source : newsSourceList)
		{
			if (source.getSourceName().equals(sourceName))
			{
				int index = newsSourceList.indexOf(source);
				//NewsSource source = this.newsSourceList.get(index);
				NewsSource newSource = NewsSource.createWithUserInput(source);
				newsSourceList.remove(index);
				this.newsSourceList.add(index, newSource);
				return 0;
			}
		}
		return 1;
	}


	public void addSource(NewsSource newSource)
	{
		this.newsSourceList.add(newSource);
	}


	public ArrayList<NewsSource> getNewsSourceList()
	{
		return newsSourceList;
	}


	public void setNewsSourceList(ArrayList<NewsSource> newsSourceList)
	{
		this.newsSourceList = newsSourceList;
	}
}
