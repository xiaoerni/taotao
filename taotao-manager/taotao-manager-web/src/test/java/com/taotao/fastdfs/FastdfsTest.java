package com.taotao.fastdfs;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

public class FastdfsTest {

	@Test
	public void testUpload() throws Exception{
		ClientGlobal.init("D:\\share\\JavaEE18\\taotao-manager\\taotao-manager-web\\src\\main\\resources\\properties\\client.conf");
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();
		StorageServer storageServer = null;
		StorageClient storageClient = new StorageClient(trackerServer,storageServer);
		String[] strings = storageClient.upload_file("G:\\picture\\download.jpg", "jpg", null);
		for(String string : strings){
			System.out.println(string);
		}
	}
	
	@Test
	public void testFastDfsClient() throws Exception{
		FastDFSClient client = new FastDFSClient("D:\\share\\JavaEE18\\taotao-manager\\taotao-manager-web\\src\\main\\resources\\properties\\client.conf");
		String uploadFileString = client.uploadFile("G:\\picture\\download1.jpg", "jpg");
		System.out.println(uploadFileString);
	}
}
