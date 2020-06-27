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
���ȶ���һ��Logeer��ʵ����������log �ļ���
�������һ��fileHander �����ǰ���־д���ļ��С�
��д���ļ���ʱ�򣬶���һ�� LogFormatter����־���и�ʽ����Ⱦ�� 
Ĭ��״���£� ��־���ӡ������̨��
���filehandler �� ��ͬʱд���ļ��� 
�粻ָ��·������־�ļ���λ����Ŀ��·���¡�
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


