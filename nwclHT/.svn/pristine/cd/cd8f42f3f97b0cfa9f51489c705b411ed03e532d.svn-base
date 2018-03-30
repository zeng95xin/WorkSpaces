package com.bola.nwcl.web;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FreePatchUtil {

	public static String patchFile="D:/nwclHT.patch";//补丁文件,由eclipse svn plugin生成
	
	public static String projectPath="C:/workspaces/workspaces2017/nwclHT";//项目文件夹路径
	
	public static String webContent="src/main/webapp";//web应用文件夹名
	
	public static String classPath="C:/workspaces/workspaces2017/nwclHT/target/classes";//class存放路径
	
	public static String desPath="C:/Users/Administrator/Desktop";//补丁文件包存放路径
	
	public static String version="nwclHT_svn_update";//补丁版本
	
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		deleteAllFilesOfDir(new File(desPath + "/" + version));
		copyFiles(getPatchFileList());
	}
	
	public static void deleteAllFilesOfDir(File path) {
	    if (null == path || !path.exists())  
	        return;  
	    if (path.isFile()) {  
	        path.delete();
	        System.out.println("删除文件" + path.getName());
	        return;  
	    }  
	    File[] files = path.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        deleteAllFilesOfDir(files[i]);  
	    }  
	    path.delete();  
	    System.out.println("删除文件夹" + path.getName());
	}
	
	public static List<String> getPatchFileList() throws Exception{
		List<String> fileList=new ArrayList<String>();
		FileInputStream f = new FileInputStream(patchFile); 
		BufferedReader dr=new BufferedReader(new InputStreamReader(f,"utf-8"));
		String line;
		while((line=dr.readLine())!=null){ 
			if(line.indexOf("Index: src")!=-1){
				line=line.replaceAll(" ","");
				line=line.substring(line.indexOf(":")+1,line.length());
				fileList.add(line);
			}
		}
		dr.close();
		return fileList;
	}
	
	public static void copyFiles(List<String> list){
		
		for(String fullFileName:list){
			if(fullFileName.indexOf("src/main/java")!=-1 || fullFileName.indexOf("src/main/resources")!=-1){//对源文件目录下的文件处理
				String fileName=fullFileName.replace("src/main/java","").replace("src/main/resources","");
				fullFileName=classPath+fileName;
				if(fileName.endsWith(".java")){
					fileName=fileName.replace(".java",".class");
					fullFileName=fullFileName.replace(".java",".class");
				}
				String tempDesPath=fileName.substring(0,fileName.lastIndexOf("/"));
				String desFilePathStr=desPath+"/"+version+"/WEB-INF/classes"+tempDesPath;
				String desFileNameStr=desPath+"/"+version+"/WEB-INF/classes"+fileName;
				File desFilePath=new File(desFilePathStr);
				if(!desFilePath.exists()){
					desFilePath.mkdirs();
				}
				copyFile(fullFileName, desFileNameStr);
				System.out.println(fullFileName+"复制完成");
			}else if(fullFileName.indexOf("src/test/java")!=-1 || fullFileName.indexOf("src/test/resources")!=-1){//对test目录的处理
				System.out.println(fullFileName+"__test目录直接跳过");
			}else{//对普通目录的处理
				String desFileName=fullFileName.replaceAll(webContent,"");
				fullFileName=projectPath+"/"+fullFileName;//将要复制的文件全路径
				String fullDesFileNameStr=desPath+"/"+version+desFileName;
				String desFilePathStr=fullDesFileNameStr.substring(0,fullDesFileNameStr.lastIndexOf("/"));
				File desFilePath=new File(desFilePathStr);
				if(!desFilePath.exists()){
					desFilePath.mkdirs();
				}
				copyFile(fullFileName, fullDesFileNameStr);
				System.out.println(fullDesFileNameStr+"复制完成");
			}
			
		}
		
	}

	private static void copyFile(String sourceFileNameStr, String desFileNameStr) {
		File srcFile=new File(sourceFileNameStr);
		File desFile=new File(desFileNameStr);
		try {
			copyFile(srcFile, desFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
	
	public static void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }
	
}
