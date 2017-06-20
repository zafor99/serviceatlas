package framework;

import com.bn.qa.seo.client.BNHttpClientRequest;
import com.bn.qa.seo.client.IHttpClientRequest;
import com.bn.qa.seo.client.WebPage;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  April 30, 2013
 */
public class MQBrowserMassegeQueues 
{
	private String mqBrowserURL = "http://injlistqueue01:8161/admin/queues.jsp";
	private String m_Content;
	private WebPage body = null;
	protected IHttpClientRequest httpClientRequest = new BNHttpClientRequest();
	
	public MQBrowserMassegeQueues(){		
		body = httpClientRequest.get(mqBrowserURL);
		m_Content = body.content();
	}
	
	public QueuesRowType ehcacheGetQue()
	{
		return new QueuesRowType(m_Content.substring(m_Content.lastIndexOf("ehcacheGetQueue</a></td>"),
				                    m_Content.indexOf("<a href=\"browse.jsp?JMSDestination=ehcacheGetQueue\">Browse</a>" )));
	}
	
	public QueuesRowType exampleA()
	{
		return new QueuesRowType(m_Content.substring(m_Content.lastIndexOf("example.A</a></td>"),
				                    m_Content.indexOf("<a href=\"browse.jsp?JMSDestination=example.A\">Browse</a>" )));
	}
	
	public QueuesRowType invalidQ()
	{
		return new QueuesRowType(m_Content.substring(m_Content.lastIndexOf("invalidQ</a></td>"),
				                    m_Content.indexOf("<a href=\"browse.jsp?JMSDestination=invalidQ\">Browse</a>" )));
	}
	
	public QueuesRowType listings()
	{
		return new QueuesRowType(m_Content.substring(m_Content.lastIndexOf("listings</a></td>"),
				                    m_Content.indexOf("<a href=\"browse.jsp?JMSDestination=listings\">Browse</a>" )));
	}
	
	public QueuesRowType listingsException()
	{
		return new QueuesRowType(m_Content.substring(m_Content.lastIndexOf("listingsExceptions</a></td>"),
				                    m_Content.indexOf("<a href=\"browse.jsp?JMSDestination=listingsExceptions\">Browse</a>" )));
	}
	
	public QueuesRowType listingsPriority1()
	{
		return new QueuesRowType(m_Content.substring(m_Content.lastIndexOf("listingsPriority1</a></td>"),
				                    m_Content.indexOf("<a href=\"browse.jsp?JMSDestination=listingsPriority1\">Browse</a>" )));
	}
	
	public QueuesRowType listingsPriority2()
	{
		return new QueuesRowType(m_Content.substring(m_Content.lastIndexOf("listingsPriority1</a></td>"),
				                    m_Content.indexOf("<a href=\"browse.jsp?JMSDestination=listingsPriority1\">Browse</a>" )));
	}
	
	public QueuesRowType listingsPriority3()
	{
		return new QueuesRowType(m_Content.substring(m_Content.lastIndexOf("listingsPriority3</a></td>"),
				                    m_Content.indexOf("<a href=\"browse.jsp?JMSDestination=listingsPriority3\">Browse</a> " )));
	}
	
	public class QueuesRowType
	{
		private String m_queuesContent = null;
		public QueuesRowType(String queuesContent){
			m_queuesContent = queuesContent;
		}
		
		public int getNumberOfPendingMessages(){
			int number;
			String[] list = m_queuesContent.split("\n");
			number = Integer.valueOf(list[1].substring(list[1].indexOf("<td>") + 4, list[1].indexOf("</td>")));
			System.out.println("Number of Pending Messages : " + number);
			return number;
		}
		
		public int getNumberOfConsumer(){
			int number;
			String[] list = m_queuesContent.split("\n");
			number = Integer.valueOf(list[2].substring(list[2].indexOf("<td>") + 4, list[2].indexOf("</td>")));
			System.out.println("Number Of Consumer : " + number);
			return number;
		}
		
		public int getMessageEnqueued(){
			int number;
			String[] list = m_queuesContent.split("\n");
			number = Integer.valueOf(list[3].substring(list[3].indexOf("<td>") + 4, list[3].indexOf("</td>")));
			System.out.println("Message Enqueued : " + number);
			return number;
		}
		
		public int getMessageDequeued(){
			int number;
			String[] list = m_queuesContent.split("\n");
			number = Integer.valueOf(list[4].substring(list[4].indexOf("<td>") + 4, list[4].indexOf("</td>")));
			System.out.println("Message Dequeued : " + number);
			return number;
		}
	}
	
}
