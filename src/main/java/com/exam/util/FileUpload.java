package com.exam.util;

import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class FileUpload {
	 UploadManager uploadManager = new UploadManager();
	 String bucketname = "face";
	public static void main(String... args)  {
		Auth auth = Auth.create("46qeohKhCTAhjGYyy7jJtJ5JGvyDAEYU-N3Vl7x1", "lUC5wUKPo_evHkJQ0c43vhAGIGmWK_rov9HQ-DAC");
		
	}
}
