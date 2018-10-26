package core.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.RandomStringUtils;

public class FileUtil {

	/*
	 * 上传文件并返回相对地址
	 */
	public static String uploadFile(FileItem file, String realUploadPath) throws IOException {
		System.out.println("realUploadPath:" + realUploadPath);
		// 如果目录不存在则创建目录
		File uploadFile = new File(realUploadPath);
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}
		String form = file.getName().substring(file.getName().lastIndexOf("."));
		System.out.println("文件格式:" + form);
		// 定义图片名
		String fileName = RandomStringUtils.randomAlphanumeric(8) + "_" + System.currentTimeMillis() + form;
		// 创建输入流
		InputStream inputStream = file.getInputStream();
		// 生成输出地址URL
		String outputPath = realUploadPath +  fileName;
		// 创建输出流
		OutputStream outputStream = new FileOutputStream(outputPath);
		// 设置缓冲区
		byte[] buffer = new byte[1024];
		// 输入流读入缓冲区，输出流从缓冲区写出
		while ((inputStream.read(buffer)) > 0) {
			outputStream.write(buffer);
		}
		outputStream.close();
		inputStream.close();
		// 返回原图上传后的相对地址
		return fileName;
	}

	// 获取上传表单数据
	public static Map<String, Object> getData(List<FileItem> list) {
		Map<String, Object> data = new HashMap<String, Object>();
		for (FileItem item : list) {
			// 获取表单的属性名字
			String name = item.getFieldName();
			// 如果获取的表单信息是普通的文本 信息
			if (item.isFormField()) {
				String value = item.getString();
				data.put(name, value);
			} else {
				data.put("file", item);
			}
		}
		return data;
	}

	// 保存微信小程序二维码
	public static Boolean saveWxacode(String url, byte[] result) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = new ByteArrayInputStream(result);
			File file = new File(url);
			if (!file.exists()) {
				file.createNewFile();
			}
			outputStream = new FileOutputStream(file);
			int len = 0;
			byte[] buf = new byte[1024];
			while ((len = inputStream.read(buf, 0, 1024)) != -1) {
				outputStream.write(buf, 0, len);
			}
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 从url中获取图片
	 * 
	 * @param destUrl
	 *            目标文件url
	 * @param localPath
	 *            保存到本地的路径
	 */
	public static String saveToFile(String destUrl, String localPath) {
		if(destUrl==null || "".equals(destUrl)) {
			return "";
		}
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		URL url = null;
		int BUFFER_SIZE = 1024;
		byte[] buf = new byte[BUFFER_SIZE];
		int size = 0;
		try {
			File file = new File(localPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			// 定义图片名
			String fileName = RandomStringUtils.randomAlphanumeric(8) + "_" + System.currentTimeMillis() + ".jpg";
			url = new URL(destUrl);
			httpUrl = (HttpURLConnection) url.openConnection();
			httpUrl.connect();
			bis = new BufferedInputStream(httpUrl.getInputStream());
			fos = new FileOutputStream(file+File.separator+fileName);
			while ((size = bis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fos.flush();
			return fileName;
		} catch (IOException e) {
			return null;
		} catch (ClassCastException e) {
			return null;
		} finally {
			try {
				fos.close();
				bis.close();
				httpUrl.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}

	}

	// public static void main(String[] args) {
	// FileUtil fileUtil = new FileUtil();
	// fileUtil.saveToFile("https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKYB4Zib1KlUM1GMApjGlZqRPR0T6HqbrzlQKuSJZdxklAdVoAJA66gkhd86OlshkFXwuqZxZXKibeA/132","D:\\\\haha.gif");
	// }

}
