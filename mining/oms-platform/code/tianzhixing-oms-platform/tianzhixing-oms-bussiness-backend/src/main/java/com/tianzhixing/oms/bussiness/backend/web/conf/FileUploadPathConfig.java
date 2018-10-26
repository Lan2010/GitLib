package com.tianzhixing.oms.bussiness.backend.web.conf;

import com.tianzhixing.oms.utils.ResourceBundleUtil;

/**
 * Created by routine.k on 2018/6/25.
 */
public class FileUploadPathConfig {

    public static String IMAGEPATH = ResourceBundleUtil.getStringValue("file.upload.image", "system-config");
    public static String IMAGEREQUESTPATH = ResourceBundleUtil.getStringValue("image.request.url", "system-config");
    public static String VIDEOPATH = ResourceBundleUtil.getStringValue("file.upload.video", "system-config");
    public static String VIDEOREQUESTPATH = ResourceBundleUtil.getStringValue("video.request.url", "system-config");
}
