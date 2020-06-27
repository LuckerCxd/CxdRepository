package DemoResponse;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ResponseDemo3")
public class ResponseDemo3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.��ȡrequest��ȡURL�Ĳ������ļ�·��filepath���ļ���filename
		String filepath = request.getParameter("filepath");
		String filename = request.getParameter("filename");
		
		//1.ʹ��ServletContext��ȡ�ļ���filename��MIME����,������Ϊ��Ӧͷ��ContentType
		ServletContext servletContext = this.getServletContext();
		response.setContentType(servletContext.getMimeType(filename));
		
		//2.ʹ��ServletContext��ȡ�ļ�·��filepath����ʵ·��realpath,��Ϊ֮������File����
		String realPath = servletContext.getRealPath(filepath);
		File file = new File(realPath);
		
		//3.������Ӧͷ��content-dispositionΪ attachment;filename=�ļ���,�൱�ڵ�����������,��Ϊ�ļ���
		response.setHeader("content-disposition", "attachment;filename="+filename);
		
		//4.ʹ��BufferedInputStream�����ļ������ڴ棬���뵽byte[],��ͬʱʹ��response�ֽ���������
		BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
		ServletOutputStream outputStream = response.getOutputStream();
		byte[] bs = new byte[4096];
		while(bufferedInputStream.read(bs) != -1) {
			outputStream.write(bs);
			
		}
		
		
		
		
		
	}
}
