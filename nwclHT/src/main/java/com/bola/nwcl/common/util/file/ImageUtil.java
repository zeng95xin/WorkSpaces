package com.bola.nwcl.common.util.file;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageUtil {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public static String DEFAULT_PREVFIX = "_thumbnail";
    //private static Boolean DEFAULT_FORCE = false;
    private static ImageUtil imgUtil;
    private ImageUtil(){
    }
    
	public static ImageUtil getImgUtil() {
		if(imgUtil == null){
			imgUtil = new ImageUtil();
		}
		return imgUtil;
	}

	/**
	 * <p>Title: thumbnailImage</p>
	 * <p>Description:     根据图片路径生成缩略图 </p>
	 * @param imgFile      原图片文件
	 * @param w            缩略图宽
	 * @param h            缩略图高
	 * @param prevfix      生成缩略图的前缀
	 * @param force        是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
	 * @throws IOException 
	 */
	public String thumbnailImage(File imgFile, int w, int h, boolean force) throws IOException{
        try {
        	String suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
            log.debug("target image's size, width:{}, height:{}.",w,h);
            Image img = ImageIO.read(imgFile);
            if(!force){
                // 根据原图与要求的缩略图比例，找到最合适的缩略图比例
                int width = img.getWidth(null);
                int height = img.getHeight(null);
                if((width*1.0)/w < (height*1.0)/h){
                    if(width > w){
                        h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w/(width*1.0)));
                        log.debug("change image's height, width:{}, height:{}.",w,h);
                    }
                } else {
                    if(height > h){
                        w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h/(height*1.0)));
                        log.debug("change image's width, width:{}, height:{}.",w,h);
                    }
                }
            }
            BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics g = bi.getGraphics();
            g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
            g.dispose();
            String p = imgFile.getPath();
            String p2 = p.substring(0,p.lastIndexOf("."));
            String p3 = p.substring(p.lastIndexOf("."));
            // 将图片保存在原目录并加上后缀
            File thumbnailFile = new File(p2 + DEFAULT_PREVFIX + p3);
            ImageIO.write(bi, suffix, thumbnailFile);
            return thumbnailFile.getPath();
        } catch (IOException e) {
           log.error("generate thumbnail image failed.",e);
           throw e;
        }
    }
    
    public String thumbnailImageByPath(String imagePath, int w, int h, boolean force) throws IOException{
        File imgFile = new File(imagePath);
        if(!imgFile.exists()){
        	throw new IOException("图片不存在");
        }
        return thumbnailImage(imgFile, w, h, force);
    }
    
    public static void main(String[] args) {
    	try {
			getImgUtil().thumbnailImageByPath("E:\\Workspaces\\nwcl\\src\\main\\webapp\\upload\\files\\article\\20151225c0ce8839-e99.jpg", 300, 200, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
}
