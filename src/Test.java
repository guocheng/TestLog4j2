
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test
{
	private static String connectURI = "jdbc:mysql://localhost:3306/smartify";
	public static Logger logger = LogManager.getRootLogger();
	
	private final DataSource dataSource;
	public static GenericObjectPool<PoolableConnection> pool;
	
	private static interface Singleton {
        final Test INSTANCE = new Test();
    }
	
	private Test() {
		Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", ""); 
        
        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(connectURI,properties);
        
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);

        GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory);

        poolableConnectionFactory.setPool(connectionPool);
        PoolingDataSource<PoolableConnection> dataSource = new PoolingDataSource<>(connectionPool);

        this.dataSource = dataSource;
	}
	
	 public static Connection getDatabaseConnection() throws SQLException {
	        return Singleton.INSTANCE.dataSource.getConnection();
	 }
	
	public static void main(String[] args)
	{
			IOException ioe = new IOException("XMPP Error");
			System.out.println("Start");
			long before = System.currentTimeMillis();
			for(int i=0;i<50;i++)
			{
				logger.debug("test", ioe);
			}
			System.out.println("Timelapse: " + (System.currentTimeMillis() - before));
	}
}
