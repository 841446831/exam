package com.exam.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IdenticonController{
	@RequestMapping("identicon/{id}")
	public void generate(@PathVariable Integer id,HttpServletRequest request,HttpServletResponse response) throws IOException{
		 try {
	            int width = 30;//每个小块块的大小
	            int grid = 5; // 5 x 5
	            int padding = width/2;//四边填充
	            int size = width*grid + width;
	            
	            BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
	            Graphics2D _2d = img.createGraphics();
	            _2d.setColor(new Color(240,240,240));
	            _2d.fillRect(0,0, size, size);
	            _2d.setColor(RandomColor(80,200));
	            char[] idchars = createIdent(id);
	            int i=idchars.length;
	            for(int x =0; x <Math.ceil(grid/2.0); x++){
	                for (int y =0; y <grid; y++) {
	                    if(idchars[--i]<53){
	                        _2d.fillRect((padding+x*width),(padding+y*width), width, width);
	                        if(x < Math.floor(grid/2)){
	                            _2d.fillRect((padding+((grid-1) -x)*width),(padding+y*width), width, width);
	                        }
	                    }
	                }
	            }
	            _2d.dispose();
	            response.setContentType("image/png");
	            ImageIO.write(img, "png", response.getOutputStream());
	        } catch (Exception e) {
	            response.setContentType("text/plain");
	            PrintWriter out = response.getWriter();
	            out.println("id error");
	            out.flush();
	            out.close();
	            return;
	        }
	}
	
	
	
	  private Color RandomColor(int fc,int bc){
	        Random random = new Random();
	        if (fc > 255) fc = 255;
	        if (bc > 255) bc = 255;
	        int r = fc + random.nextInt(Math.abs(bc - fc));
	        int g = fc + random.nextInt(Math.abs(bc - fc));
	        int b = fc + random.nextInt(Math.abs(bc - fc));
	        return new Color(r, g, b);
	    }

	    private char[] createIdent(int id) {
	        BigInteger bi_content = new BigInteger((id+"").getBytes());
	        BigInteger bi = new BigInteger(id+"identicon"+id,36);
	        bi = bi.xor(bi_content);
	        return bi.toString(10).toCharArray();
	    }
}
