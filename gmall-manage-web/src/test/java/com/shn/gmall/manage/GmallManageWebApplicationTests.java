package com.shn.gmall.manage;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallManageWebApplicationTests {

    @Test
    public void contextLoads() {
        //客户端配置文件
        String conf_filename = "fdfs_client_mine.conf";
        //本地文件，要上传的文件
        String local_filename = "E:/图片/4ce234a2421f7fd194c37ae9dd5d8ceaa07ed11b.jpg";


            try {
                ClientGlobal.init(conf_filename);

                TrackerClient tracker = new TrackerClient();
                TrackerServer trackerServer = tracker.getConnection();
                StorageServer storageServer = null;

                StorageClient storageClient = new StorageClient(trackerServer,
                        storageServer);
                NameValuePair nvp [] = new NameValuePair[]{
                        new NameValuePair("item_id", "100010"),
                        new NameValuePair("width", "80"),
                        new NameValuePair("height", "90")
                };
                String fileIds[] = storageClient.upload_file(local_filename, null,
                        nvp);

                System.out.println(fileIds.length);
                System.out.println("组名：" + fileIds[0]);
                System.out.println("路径: " + fileIds[1]);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }


}
