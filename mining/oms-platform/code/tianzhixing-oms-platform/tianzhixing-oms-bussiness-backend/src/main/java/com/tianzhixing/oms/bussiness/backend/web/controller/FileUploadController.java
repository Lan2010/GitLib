package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.FileUploadPathConfig;
import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by routine.k on 2018/6/25.
 */
@Controller
@Scope(RequestConf.REQUEST_SCOPE_PROTOTYPE)
@RequestMapping(value = "/file")
public class FileUploadController extends BaseController {

    private static Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);


    private static Map<String, String> TYPEMAP = new HashMap<String, String>();
    private static List<String> UPLOADIMGPATH = new ArrayList<>();
    private static Map<String, String> VIDEOTYPEMAP = new HashMap<String, String>();
    private static List<String> UPLOADVIDEOPATH = new ArrayList<>();

    static {
        TYPEMAP.put("image/png", "png");
        TYPEMAP.put("image/gif", "gif");
        TYPEMAP.put("image/jpeg", "jpeg");
        TYPEMAP.put("image/jpg", "jpeg");
        //{"png","gif","jpeg"};
        UPLOADIMGPATH.add("png");
        UPLOADIMGPATH.add("gif");
        UPLOADIMGPATH.add("jpeg");
        UPLOADIMGPATH.add("jpg");
        
        VIDEOTYPEMAP.put("video/avi", "avi");
        VIDEOTYPEMAP.put("video/mp4", "mp4");
        VIDEOTYPEMAP.put("video/rmvb", "rmvb");
        VIDEOTYPEMAP.put("video/mov", "mov");
        VIDEOTYPEMAP.put("video/flv", "flv");
        VIDEOTYPEMAP.put("video/wmv", "wmv");

        UPLOADVIDEOPATH.add("avi");
        UPLOADVIDEOPATH.add("mp4");
        UPLOADVIDEOPATH.add("rmvb");
        UPLOADVIDEOPATH.add("mov");
        UPLOADVIDEOPATH.add("flv");
        UPLOADVIDEOPATH.add("wmv");
    }


    @RequestMapping(value = "/upload")
    @ResponseBody
    public ResponseEntity imageUpload(@RequestParam(value = "file") MultipartFile multipartFile, HttpServletRequest request) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return responseEntity(301, "upload.file.empty");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        UUID uuid = UUID.randomUUID();
        String dateStr = simpleDateFormat.format(new Date());
        //读取数据流
        try {
            //文件类型
            String type = TYPEMAP.get(multipartFile.getContentType().toLowerCase());
            if (StringUtils.isEmpty(type)) {
                String historyName = multipartFile.getOriginalFilename();
                if (historyName.lastIndexOf(".") > 0) {
                    type = historyName.substring(historyName.lastIndexOf(".") + 1);
                }
            }
            if (!UPLOADIMGPATH.contains(type)) {
                return responseEntity(301, "upload.failed");
            }
            String filePath = FileUploadPathConfig.IMAGEPATH;
            File dirFile = new File(filePath + dateStr);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            String fileName = dateStr + "/" + uuid + "." + (type == null ? "" : type);
            multipartFile.transferTo(new File(filePath + fileName));
            LOGGER.info("upload file to:" + filePath + fileName);
//                outputStream = new FileOutputStream(file);
//                byte[] bytes = new byte[1024];
//                int bytesWritten = 0;
//                int byteCount = 0;
//                while ((byteCount = inputStream.read(bytes)) != -1) {
//                    outputStream.write(bytes, bytesWritten, byteCount);
//                }
//                outputStream.flush();
            return responseEntity(200, fileName, "upload.suc");
//            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }//finally {
//            try {
//                if (inputStream != null) {
//                    inputStream.close();
//                }
//                if (outputStream != null) {
//                    outputStream.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        // }
        return responseEntity(301, "upload.failed");
    }
    
    @RequestMapping(value = "/videoUpload")
    @ResponseBody
    public ResponseEntity videoUpload(@RequestParam(value = "file") MultipartFile multipartFile, HttpServletRequest request) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return responseEntity(301, "upload.file.empty");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        UUID uuid = UUID.randomUUID();
        String dateStr = simpleDateFormat.format(new Date());
        //读取数据流
        try {
            //文件类型
            String type = VIDEOTYPEMAP.get(multipartFile.getContentType().toLowerCase());
            if (StringUtils.isEmpty(type)) {
                String historyName = multipartFile.getOriginalFilename();
                if (historyName.lastIndexOf(".") > 0) {
                    type = historyName.substring(historyName.lastIndexOf(".") + 1);
                }
            }
            if (!UPLOADVIDEOPATH.contains(type)) {
                return responseEntity(301, "upload.failed");
            }
            String filePath = FileUploadPathConfig.VIDEOPATH;
            File dirFile = new File(filePath + dateStr);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            String fileName = dateStr + "/" + uuid + "." + (type == null ? "" : type);
            multipartFile.transferTo(new File(filePath + fileName));
            String videoPath =  filePath + fileName;
            LOGGER.info("upload file to:" + videoPath);
            return responseEntity(200, videoPath, "upload.suc");
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return responseEntity(301, "upload.failed");
    }

    @RequestMapping(value = "/imageUpload")
    @ResponseBody
    public ResponseEntity upload(@RequestParam(value = "file") MultipartFile multipartFile, HttpServletRequest request) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return responseEntity(301, "upload.file.empty");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        UUID uuid = UUID.randomUUID();
        String dateStr = simpleDateFormat.format(new Date());
        //读取数据流
        try {
            //文件类型
            String type = TYPEMAP.get(multipartFile.getContentType().toLowerCase());
            if (StringUtils.isEmpty(type)) {
                String historyName = multipartFile.getOriginalFilename();
                if (historyName.lastIndexOf(".") > 0) {
                    type = historyName.substring(historyName.lastIndexOf(".") + 1);
                }
            }
            if (!UPLOADIMGPATH.contains(type)) {
                return responseEntity(301, "upload.failed");
            }
            String filePath = FileUploadPathConfig.IMAGEPATH;
            File dirFile = new File(filePath + dateStr);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            String fileName = dateStr + "/" + uuid + "." + (type == null ? "" : type);
            multipartFile.transferTo(new File(filePath + fileName));
            String imagePath =  filePath + fileName;
            LOGGER.info("upload file to:" + imagePath);
            return responseEntity(200, imagePath, "upload.suc");
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return responseEntity(301, "upload.failed");
    }
    
}
