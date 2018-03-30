package com.bola.nwcl.web.servlets;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;
/**
 * 登录验证码
 * @author Mr.ShyMe
 */
@SuppressWarnings("serial")
public class LoginCodeServlet extends HttpServlet {
	//登录验证码
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Random rd = new Random();
		//生成一张缓存图片
		BufferedImage bi = new BufferedImage(100, 48, BufferedImage.TYPE_INT_RGB);
		//得到图片上的画笔
		Graphics g = bi.getGraphics();
		//画背景
		//g.setColor(new Color(235,235,228));
		//g.fillRect(0, 0, 100, 48);
		Image img=new ImageIcon(this.getServletContext().getRealPath("images/YZM")+"/"+"yanzhengma"+rd.nextInt(17)+".png").getImage();
		g.drawImage(img, 0, 0, 100,48, null);
		//画数字
		String code = "";//验证码字符串
		g.setFont(new Font("Arial",Font.BOLD,38));
		for(int i = 0 ;i < 4;i++){
			g.setColor(new Color(110+rd.nextInt(70),110+rd.nextInt(70),110+rd.nextInt(70)));	
			int d = rd.nextInt(10);	
			code += d;
			g.drawString(d+"", 2+25*i, 40);		
		}	
		//画线
		g.setColor(new Color(202,118,207));
		for(int i = 0;i < 4;i++){	
			g.drawLine(rd.nextInt(20),rd.nextInt(48),rd.nextInt(20)+80,rd.nextInt(48));	
		}	
		req.getSession().removeAttribute("LOGINCODE");
		req.getSession().setAttribute("LOGINCODE", code);
		ImageIO.write(bi, "JPEG", resp.getOutputStream());	
	}
}
