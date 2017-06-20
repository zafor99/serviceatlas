package utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;


/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  June 13, 2014
 */
public  final class JmxProxy 
{
    /**
     * @return . getUsername .
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * @param username
     *            . setUsername .
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * @return . getPassword .
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @param password
     *            . setPassword .
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    private String username;
    private String password;

    private JMXConnector jmxConnector;
    private static final String JMX_SERVICE_URL = "service:jmx:rmi://%s:1299/jndi/rmi://%s:1099/jmxrmi";

    public MBeanServerConnection getMBeanServerConnection(final String host) throws Exception {

        try {
            if (jmxConnector == null) {
                final Map<String, Object> env = new HashMap<String, Object>();
                if (this.username != null || this.password != null) {
                    final String[] credentials = new String[] { this.username, this.password };
                    env.put("jmx.remote.credentials", credentials);
                }

                final JMXServiceURL url = new JMXServiceURL(String.format(JMX_SERVICE_URL, host, host));
                jmxConnector = JMXConnectorFactory.connect(url, env);

            }
            return jmxConnector.getMBeanServerConnection();
        } catch (Exception e) {
            close();
            throw e;
        }

    }

    public void close() throws IOException {
        if (jmxConnector != null) {
            jmxConnector.close();
            jmxConnector = null;
        }
    }

}


