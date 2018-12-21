package com.baihua.baihuamedical.common.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhaodongdong
 * @version V1.0
 * @Title FileUploadUtils.java
 * @Package com.baihua.baihuamedical.common.utils
 * @date 2018年12月14日 15:20:22
 */
public class FileUploadUtils {

	public static String upload(MultipartFile file,String uploadDirectory) throws IOException {
		byte[] bytes = file.getBytes();
		long time = new Date().getTime();
		String savePath = uploadDirectory  + File.separator + time + File.separator + file.getOriginalFilename();
		Path path = Paths.get(savePath);

		File file1 = new File(savePath);
		if(!file1.getParentFile().exists()){
			file1.getParentFile().mkdirs();
		}

		Files.write(path, bytes);
		return  "/" + time + "/" + file.getOriginalFilename();
	}
}

