package com.shn.gmall.manage.util;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author sss
 */
public class MyUploadUtil {

    private static final String FILE_URL = "http://192.168.155.237/";

    public static String uploadImg(MultipartFile file) {
        String[] uploadFile = null;
        try {
            //客户端配置文件
            String configFile = MyUploadUtil.class.getClassLoader().getResource("fdfs_client_mine.conf").getFile();
            //配置fastdfs的全局信息
            ClientGlobal.init(configFile);
            //或得tracker
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            //通过tracker或得storage
            StorageClient storageClient = new StorageClient(trackerServer, null);

            //获取文件全名
            String originalFilename = file.getOriginalFilename();
            //获取最后一个.
            int index = originalFilename.lastIndexOf(".");
            //获取文件拓展名
            String fileExtName = originalFilename.substring(index + 1);
            uploadFile = storageClient.upload_file(file.getBytes(), fileExtName, null);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FILE_URL + uploadFile[0] + "/" + uploadFile[1];
    }
}
