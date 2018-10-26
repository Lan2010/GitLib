package core.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

import core.exception.ErrorFileFormatException;
import net.coobird.thumbnailator.Thumbnails;

public class ImageUtil {
	
	 //设置缩略图的宽度和高度  
    public static final int witdth=100;  
    public static final int heigth=100;  
    
  
	/* 
     * 上传图片并返回图片的相对地址 
     */   
    public static String uploadImage(FileItem file, String realUploadPath, String fileName) throws IOException  
    {  
        //如果目录不存在则创建目录  
        File uploadFile=new File(realUploadPath+"/rawImages");  
        File newFile = new File(realUploadPath+"/rawImages/"+fileName);
        if(!uploadFile.exists()){  
            uploadFile.mkdirs();  
        }  
        if(newFile.exists()){
        	StringBuffer buf= new StringBuffer(fileName);
        	fileName = buf.insert(0, RandomStringUtils.randomAlphanumeric(3)).toString();
        };
          
        //创建输入流  
        InputStream inputStream=file.getInputStream();  
        //生成输出地址URL  
        String outputPath=realUploadPath+"/rawImages/"+fileName;  
        //创建输出流  
        OutputStream outputStream=new FileOutputStream(outputPath);       
        //设置缓冲区  
        byte[] buffer=new byte[1024];  
          
        //输入流读入缓冲区，输出流从缓冲区写出  
        while((inputStream.read(buffer))>0)  
        {  
          outputStream.write(buffer);  
        }  
        outputStream.close();  
        inputStream.close();       
        //返回原图上传后的相对地址  
        return outputPath;  
    }
    
      
    /*       
     * 生成缩略图并且返回相对地址 
     */  
	public static String generateThumbnail(FileItem file, String realUploadPath, String fileName) throws IOException {
		  //如果目录不存在则创建目录  
        File uploadFile=new File(realUploadPath+"/thumbImages");  
        if(!uploadFile.exists()){  
            uploadFile.mkdirs();  
        }              
        //缩略图保存的绝对地址  
        String des=realUploadPath+"/thumbImages/"+fileName;  
        File newFile = new File(fileName);
        if(newFile.exists()){
        	StringBuffer buf= new StringBuffer(fileName);
        	fileName = buf.insert(0, RandomStringUtils.randomAlphanumeric(3)).toString();
             des=realUploadPath+"/thumbImages/"+fileName;  
        };
        //生成缩略图  
        Thumbnails.of(file.getInputStream()).size(witdth, heigth).toFile(des);  
        //返回缩略图的相对地址  
        return des;  
        
	}

	 //获取文件夹下所有文件名  
    public static List<String> printFile(String path) {  
        File file = new File(path);  
        List<String> images = new ArrayList<String>();  
          
        // 是文件夹的话  
        if (file.isDirectory()) {  
            String[] filelist = file.list();  
            for (int i = 0; i < filelist.length; i++) {  
                File readfile = new File(path + "/" + filelist[i]);  
                if (!readfile.isDirectory()) {  
                    images.add(readfile.getName());  
                }  
            }  
  
        }  
        return images;  
    }

	

    /**
	 * 上传图片，返回文件名
	 * 
	 * @param path
	 * @param files
	 * @return 文件名
	 * @throws ErrorFileFormatException
	 */
	public static List<String> uploadImg(String path, MultipartFile[] files) throws ErrorFileFormatException {
		if(path==null || files==null) {
			return null;
		}
		String fileName = null;
		File file = null;
		File dir = null;
		String fileSuffix = "";//文件后缀名
		List<String> fileNames = new ArrayList<String>();
		try {
			for (int i = 0; i < files.length; i++) {
				if (files[i] != null && files[i].getSize() > 0) {
					fileName = files[i].getOriginalFilename();
					if(fileName!=null&&!fileName.isEmpty()) {
						fileSuffix=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());//获取文件后缀名
					}
					fileSuffix = fileSuffix.toLowerCase();//转小写
					System.out.println("文件后缀:"+fileSuffix);
					if (fileSuffix.equals("jpg") || fileSuffix.equals("png") || fileSuffix.equals("jpeg")) {
						fileName = RandomStringUtils.randomAlphanumeric(8) + "_" + System.currentTimeMillis()
								+ fileName.substring(fileName.lastIndexOf("."));
						dir = new File(path);
						if (!dir.exists() && !dir.isDirectory()) {
							dir.mkdirs();
						}
						file = new File(path, fileName);
						// 执行文件上传
						files[i].transferTo(file);
						fileNames.add(fileName);
					} else {
						throw new ErrorFileFormatException("错误的文件格式" + files[i].getOriginalFilename());
					}
				}
			}
			return fileNames;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

 
    
}
