package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  May 31, 2013
 */
public class BNTimer 
{
	private  int second = 0;
	private  int m_WaitTime = -1;
	private  int m_IntervalTime = -1;
	
	private Timer myTimer = new Timer();
	
	public BNTimer()
	{
		m_WaitTime = 0;
		m_IntervalTime = 1000;
	}

	public BNTimer(int p_EndTime, int p_intervalTime)
	{
		m_WaitTime = p_EndTime;
		m_IntervalTime = p_intervalTime;

	}

	public void start()
	{
		myTimer.schedule(new BNTimerTask(), m_WaitTime, m_IntervalTime);

	}		
	
	public void delay(int sec){
		
		while(second <= sec){
			
		}
	}

	public int getElapsedTime()
	{
		return second;
	}

	public void stop()
	{
		myTimer.cancel();
	}
	
	public void reset()
	{
		second = 0;
	}

	class BNTimerTask extends TimerTask
	{
		public void run() 
		{
			second = second + 1;	
		}

	}
	 public String getCurrentDate(){
		 	String currentDate;
		 	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date date = new Date();
			currentDate = dateFormat.format(date);
			System.out.println(dateFormat.format(date));
			return currentDate;
			
	 }
	 public static String getCurrentDate(String format){
		 	String currentDate;
		 	DateFormat dateFormat = new SimpleDateFormat(format);
			Date date = new Date();
			currentDate = dateFormat.format(date);
			System.out.println(dateFormat.format(date));
			return currentDate;
			
	 }
	 public String getCurrentTime(){
		 	String currentDate;
		 	DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			Date date = new Date();
			currentDate = dateFormat.format(date);
			System.out.println(dateFormat.format(date));
			return currentDate;
	 }
	 public static String getCurrentTime(String format){
		 	String currentDate;
		 	DateFormat dateFormat = new SimpleDateFormat(format);
			Date date = new Date();
			currentDate = dateFormat.format(date);
			System.out.println(dateFormat.format(date));
			return currentDate;
	 }

}
