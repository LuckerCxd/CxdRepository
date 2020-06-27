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
		//0.获取request获取URL的参数，文件路径filepath和文件名filename
		String filepath = request.getParameter("filepath");
		String filename = request.getParameter("filename");
		
		//1.使用ServletContext获取文件名filename的MIME类型,并设置为响应头的ContentType
		ServletContext servletContext = this.getServletContext();
		response.setContentType(servletContext.getMimeType(filename));
		
		//2.使用ServletContext获取文件路径filepath的真实路径realpath,并为之创建出File对象
		String realPath = servletContext.getRealPath(filepath);
		File file = new File(realPath);
		
		//3.设置响应头的content-disposition为 attachment;filename=文件名,相当于弹窗附件下载,命为文件名
		response.setHeader("content-disposition", "attachment;filename="+filename);
		
		//4.使用BufferedInputStream，将文件读入内存，输入到byte[],并同时使用response字节输出流输出
		BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
		ServletOutputStream outputStream = response.getOutputStream();
		byte[] bs = new byte[4096];
		while(bufferedInputStream.read(bs) != -1) {
			outputStream.write(bs);
			
		}
		
		
		
		
		
	}
}
