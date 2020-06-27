package DemoResponse;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ResponseDemo1")
public class ResponseDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int width = 120;
		int height = 100;
		int x = 0;
		int y = 0;
		BufferedImage im = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = im.getGraphics();
		graphics.setColor(Color.pink);
		graphics.fillRect(x, y, width, height);
		graphics.setColor(Color.green);
		graphics.drawRect(x, y, width-1,height-1);
		String string = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		graphics.setColor(Color.BLACK);
		int w_padding = width/6;
		int h_padding = height/2;
		for (int i = 1; i < 6; i++) {
			Random random = new Random();
			int index = random.nextInt(string.length());
			String str = string.charAt(index)+"";
			graphics.drawString(str,w_padding*i,h_padding);
		}
		ServletOutputStream outputStream = response.getOutputStream();
		ImageIO.write(im, "jpg", outputStream);
	}
	
	

}
