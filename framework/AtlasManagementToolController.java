package framework;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.apache.camel.api.management.mbean.ManagedSuspendableRouteMBean;

import utils.JmxProxy;

import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  June 16, 2014
 */
public  class AtlasManagementToolController 
{
	private static final String SINGLE_ROUTE_OBJECT_NAME = "org.apache.camel:context=%s/camelContext,type=routes,name=\"%s\"";
	
	public AtlasManagementToolController(){
		
	}
	
	public String getRouteState(String host,String rounteName) throws Exception{
		String rounteState = null;
		  final JmxProxy connection = new JmxProxy();
          ObjectName mbeanName = new ObjectName(String.format(SINGLE_ROUTE_OBJECT_NAME, host, rounteName ));
          final MBeanServerConnection mbeanServer = connection.getMBeanServerConnection(host);
          ManagedSuspendableRouteMBean mbeanProxy = JMX.newMBeanProxy(mbeanServer, mbeanName,  ManagedSuspendableRouteMBean.class);
          rounteState=  mbeanProxy.getState();
          System.out.println(rounteState);
		return rounteState;
	}
	public void suspendRoute(String host, String routeName) throws Exception {

        final JmxProxy connection = new JmxProxy();
        try {
            ObjectName mbeanName = new ObjectName(String.format(SINGLE_ROUTE_OBJECT_NAME, host, routeName));
            final MBeanServerConnection mbeanServer = connection.getMBeanServerConnection(host);
            ManagedSuspendableRouteMBean mbeanProxy = JMX.newMBeanProxy(mbeanServer, mbeanName,
                    ManagedSuspendableRouteMBean.class);
            // if (mbeanProxy.getState() == "Started") {
            mbeanProxy.suspend();
            // }

        } finally {
            connection.close();
        }

    }
    public void startRoute(String host, String routeName) throws Exception {

        final JmxProxy connection = new JmxProxy();
        try {
            ObjectName mbeanName = new ObjectName(String.format(SINGLE_ROUTE_OBJECT_NAME, host, routeName));
            final MBeanServerConnection mbeanServer = connection.getMBeanServerConnection(host);
            ManagedSuspendableRouteMBean mbeanProxy = JMX.newMBeanProxy(mbeanServer, mbeanName,
                    ManagedSuspendableRouteMBean.class);
            // if (mbeanProxy.getState() != "Started") {
            mbeanProxy.start();
            //configDao.updateRouteState(host, routeName, ROUTE_STATE_STARETED);
            // }

        } finally {
            connection.close();
        }

    }
}
