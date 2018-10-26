package com.tianzhixing.oms.bussiness.backend.web.servlet;

import com.tianzhixing.oms.utils.ResourceBundleUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet extends HttpServlet {

    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ImageServlet.class);

    private static final long serialVersionUID = 6930316102157182481L;

    private static String uploadPath;

    private static String imagesPath;

    static {
        uploadPath = ResourceBundleUtil.getStringValue("image.file.path", "system-config");
        imagesPath = ResourceBundleUtil.getStringValue("image.file.path", "system-config");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("image/jpeg");
            OutputStream os = response.getOutputStream();
            StringBuffer url = request.getRequestURL();

            int xsIndex = url.indexOf(imagesPath);
            int index = url.indexOf(uploadPath);

            if (xsIndex > 0) {
                String upload = url.substring(xsIndex);
                _outPutImgStream(request, os, upload);
            } else if (index > 0) {
                String upload = url.substring(index);
                _outPutImgStream(request, os, upload);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(" ..... screen advertising  image is not find ! ......");
        }
    }

    private void _outPutImgStream(HttpServletRequest request, OutputStream os,
                                  String upload) throws IOException {
        InputStream is = new FileInputStream(new File(upload)); // 文件输入流
        if (is != null) {
            byte[] bs = new byte[is.available()];
            int len;
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            is.close();
        }
        os.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }
}
