package core.service;

import java.io.IOException;

import org.apache.commons.fileupload.FileItem;

public interface ImageService {

	/**
	 * 保存原图到磁盘 获取上传后原图的相对地址
	 * 
	 * @param realUploadPath
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	String uploadRawImage(FileItem picture, String realUploadPath, String fileName) throws IOException;

	/**
	 * 保存缩略图到磁盘 获取生成的缩略图的相对地址
	 * 
	 * @param realUploadPath
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	String uploadThumbImage(FileItem picture, String realUploadPath, String fileName) throws IOException;

	/**
	 * 删除用户照片
	 * 
	 * @param fileName
	 * @param userId
	 * @return
	 */
	boolean delImage(String fileName, String userId);
	


}
