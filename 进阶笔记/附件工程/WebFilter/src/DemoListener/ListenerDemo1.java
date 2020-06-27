package DemoListener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


//@WebListener
public class ListenerDemo1 implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce)  { 
         System.out.println("destroy");
    }

	
    public void contextInitialized(ServletContextEvent sce)  { 
    	 System.out.println("init");
    	 ServletContext servletContext = sce.getServletContext();
    	 String initParameter = servletContext.getInitParameter("ResourceLocation");
    	 String realPath = servletContext.getRealPath(initParameter);
    	 
    	 List<String> list = new ArrayList<String>();
    	 
    	 try(BufferedReader bufferedReader = new BufferedReader(new FileReader(realPath));) {
             String line = null;
             while((line = bufferedReader.readLine())!= null) {
                 list.add(line);
             }
             for (String string : list) {
                 System.out.println("listener:"+string);
             }
         } catch (Exception e) {
             e.printStackTrace();
         }

     
    
    }
	
}
