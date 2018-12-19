package com.baihua.common;

import android.os.Environment;

import java.io.File;


public interface Constants {

    String PACKET_NAME = "cn.joymates.golf";
    String SD_CARD_FOLDER_PATH = Environment.getExternalStorageDirectory()
            + File.separator + PACKET_NAME;// 在sd卡上建立的文件夹
    String IMAGE_CACHE_FOLDER = "/img_cache";// 图片缓存文
    String IMAGE_RES_FOLDER = "/img_res";// 图片资源文件
    String VIDEO_RES_DOWNLOAD = "/video";// 视频下载文件
    String INSTRUCTIONS_DOWNLOAD = "/book";// 说明书下载文件

    String VIDEO_COMPRESS_FILE = SD_CARD_FOLDER_PATH + "/compress";//压缩视频放置的位置

    String UPLOAD_PATH = SD_CARD_FOLDER_PATH + "/upload";

    String VISIT_STATUS_WAIT = "1"; // 等待抢单
    String VISIT_STATUS_CHECKED = "2"; // 已抢单
    String VISIT_STATUS_REPLIED = "3"; // 已回复

}
