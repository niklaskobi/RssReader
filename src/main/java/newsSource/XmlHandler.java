package newsSource;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import main.Main;



public class XmlHandler
{

	public final static String	XML_ROOT	= "NewsSources";
	public final static String	SOURCE		= "Source";
	public final static String	NAME		= "Name";
	public final static String	DESCR		= "Description";
	public final static String	URI			= "Uri";
	public final static String	XML_SUMMARY	= "XMLFielSummary";
	public final static String	XML_TEXT	= "XMLFieldText";


	public static void writeXML(ArrayList<NewsSource> sourceList, String fileName)
	{

		try
		{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement(XML_ROOT);
			doc.appendChild(rootElement);

			for (NewsSource source : sourceList)
			{
				// source elements
				Element newEl = doc.createElement(SOURCE);
				rootElement.appendChild(newEl);

				// set attribute to staff element
				/*
				 * Attr attr = doc.createAttribute(NAME); attr.setValue(source.getSourceName()); staff.setAttributeNode(attr);
				 */

				// shorten way
				newEl.setAttribute(NAME, source.getSourceName());

				Element descr = doc.createElement(DESCR);
				descr.appendChild(doc.createTextNode(source.getDescription()));
				newEl.appendChild(descr);

				Element uri = doc.createElement(URI);
				uri.appendChild(doc.createTextNode(source.getUri()));
				newEl.appendChild(uri);

				Element sum = doc.createElement(XML_SUMMARY);
				sum.appendChild(doc.createTextNode(source.getXmlField_summary()));
				newEl.appendChild(sum);

				Element text = doc.createElement(XML_TEXT);
				text.appendChild(doc.createTextNode(source.getXmlField_text()));
				newEl.appendChild(text);
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(fileName));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");

		}
		catch (ParserConfigurationException pce)
		{
			pce.printStackTrace();
		}
		catch (TransformerException tfe)
		{
			tfe.printStackTrace();
		}
	}


	public static void readXML(String fileName)
	{
		try
		{

			File fXmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			// optional, but recommended
			// read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName(SOURCE);
			int cntOK = 0;
			int cntFail = 0;
			
			for (int temp = 0; temp < nList.getLength(); temp++)
			{
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					try
					{
						Element eElement = (Element) nNode;
						Main.sourceController.addSource(new NewsSource(eElement.getAttribute(NAME),
								eElement.getElementsByTagName(DESCR).item(0).getTextContent(),
								eElement.getElementsByTagName(URI).item(0).getTextContent(),
								eElement.getElementsByTagName(XML_SUMMARY).item(0).getTextContent(),
								eElement.getElementsByTagName(XML_TEXT).item(0).getTextContent()
								));
						cntOK++;
					}
					catch(Exception e)
					{
						cntFail++;
						e.printStackTrace();
					}
				}
			}
			System.out.println("Read succesfuly: "+cntOK+", failed: "+cntFail);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
