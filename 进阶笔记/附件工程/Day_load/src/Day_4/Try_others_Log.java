package Day_4;

import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Try_others_Log {
//	public static void main(String[] args) throws SecurityException, IOException {
//		Logger log = Logger.getLogger("tesglog");  
//        log.setLevel(Level.ALL);  
//        FileHandler fileHandler = new FileHandler("testlog.log");  
//        fileHandler.setLevel(Level.ALL);  
//        fileHandler.setFormatter(new LogFormatter());  
//        log.addHandler(fileHandler);  
//        log.info("This is test java util log");   
//	}
}

/*
首先定义一个Logeer的实例，并设置log 的级别，
接着添加一个fileHander ，就是把日志写到文件中。
在写入文件的时候，定义一个 LogFormatter对日志进行格式的渲染。 
默认状况下， 日志会打印到控制台。
添加filehandler 后， 会同时写入文件。 
如不指定路径，日志文件将位于项目根路径下。
*/
class LogFormatter extends Formatter {  
    @Override  
    public String format(LogRecord record) {  
        Date date = new Date();  
        String sDate = date.toString();  
        return "[" + sDate + "]" + "[" + record.getLevel() + "]"  
                + record.getClass() + record.getMessage() + "\n";  
    }  
} 


