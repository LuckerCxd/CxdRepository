import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class TestLog {
	//public static Log log = LogFactory.getLog(TestLog.class);
	public static void main(String[] args) {
		Logger log = Logger.getLogger(TestLog.class);  
        PropertyConfigurator.configure("./src/log4j.properties"); 
		new Thread(new Runnable() {
			@Override
			public void run() {
				long currentTimeMillis = System.currentTimeMillis();
				while (true) {
					while (System.currentTimeMillis()-currentTimeMillis<1000) {};
					String teString = "set";
					log.info("this is info "+teString);
					log.error("this is error "+teString);
					log.debug("this is debug "+teString);
					currentTimeMillis = System.currentTimeMillis();
				}
			}
		}).start();
		
		
	}
}
