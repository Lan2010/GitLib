package core.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.mapper.ImageMapper;
import core.service.ImageService;
import core.util.ImageUtil;
 /**
  * 
  * @author Administrator
  *
  */
@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageMapper imageMapper;

	private static SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");

	/**
	 * 保存原图到磁盘
	 * 获取上传后原图的相对地址
	 * @param file
	 * @param realUploadPath
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public String uploadRawImage(FileItem file, String realUploadPath, String fileName) throws IOException {
		String imageUrl = ImageUtil.uploadImage(file, realUploadPath, fileName);
		return imageUrl;
	}
	
	/**
	 * 
	 * 保存缩略图到磁盘
	 *  获取生成的缩略图的相对地址
	 * @param file
	 * @param realUploadPath
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public String uploadThumbImage(FileItem file, String realUploadPath, String fileName) throws IOException {
		
		String thumbImageUrl = ImageUtil.generateThumbnail(file, realUploadPath, fileName);
		System.out.println("缩略图：" + thumbImageUrl);
		return thumbImageUrl;
	}

	public boolean delImage(String fileName, String userId) {
		Integer result = imageMapper.delImage(fileName, userId);
		return false;
	}

}
