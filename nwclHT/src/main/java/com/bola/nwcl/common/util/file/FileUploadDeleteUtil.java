package com.bola.nwcl.common.util.file;

import java.io.File;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.global.Configure;
import com.bola.nwcl.common.util.FileuploadHelper;

public class FileUploadDeleteUtil {
	
	private static String rootPath;
	private static String rootUrl;
	private static String types;
	private static int w = 300;
	private static int h = 200;
	
	static{
		rootPath = Configure.getRootPath();
		rootUrl = Configure.getRootUrl();
		// ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
        types = Arrays.toString(ImageIO.getReaderFormatNames()).toLowerCase();
	}
	public static String upload(MultipartFile multipartFile, HttpServletRequest request,String saveDir,String fileName) throws Exception{
		return rootUrl + saveDir + "/" + FileuploadHelper.upload(multipartFile, request, rootPath + saveDir, fileName);
	}
	public static String[] upload2(MultipartFile multipartFile, HttpServletRequest request,String saveDir,String fileName) throws Exception{
		String suffix = null;
        // 获取图片后缀
		String sourseFileName = multipartFile.getOriginalFilename();
        if(sourseFileName.indexOf(".") > -1) {
            suffix = sourseFileName.substring(sourseFileName.lastIndexOf(".") + 1);
        }// 类型和图片后缀全部小写，然后判断后缀是否合法
        if(suffix == null || types.indexOf(suffix.toLowerCase()) < 0){
            //throw new PatternException("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
        }
        
		
        //文件后缀
        String fileEnd = sourseFileName.substring(sourseFileName.lastIndexOf(".") + 1).toLowerCase();
    	File tempFile = new File(rootPath + saveDir);
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }
        File img_save = new File(rootPath + saveDir + "/" + fileName+"."+fileEnd);
        multipartFile.transferTo(img_save);
        //上传成功 返回文件全名
        
        String[] strs = new String[2];
		strs[0] = rootUrl + saveDir + "/" + fileName +"."+fileEnd;
		ImageUtil.getImgUtil().thumbnailImage(img_save, w, h, false);
		strs[1] = rootUrl + saveDir + "/" + fileName + ImageUtil.DEFAULT_PREVFIX +"."+fileEnd;
		
		return strs;
	}
	public static boolean delete(String path){
		boolean flag = false;
		if(path==null){
			return flag;
		}
		path = path.replace(rootUrl, "");
        File file = new File(rootPath + path);
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
	}
}
