package br.com.cmabreu.demo;

import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.List;

import com.github.sardine.DavResource;
import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;

// https://github.com/lookfirst/sardine/wiki/UsageGuide
// https://forum.owncloud.org/viewtopic.php?t=28187
// https://github.com/nextcloud/notifications/blob/master/docs/ocs-endpoint-v2.md
/*
		OCS 
		curl -u admin:admin -X GET 'http://192.168.0.6:8080/ocs/v1.php/cloud/users/admin' -H "OCS-APIRequest: true"
		curl -u admin:admin -X GET 'http://192.168.0.6:8080/ocs/v1.php/cloud/capabilities' -H "OCS-APIRequest: true"
		
		curl -u admin:admin -X GET 'http://192.168.0.6:8080/ocs/v2.php/apps/notifications/api/v2/notifications' -H "OCS-APIRequest: true"

		

*/
public class DemoClass {
	
	public void run() throws Exception {
		Sardine sardine = SardineFactory.begin();
		sardine.setCredentials("admin", "admin");		
		
		List<DavResource> resources = sardine.list("http://192.168.0.6:8080/remote.php/dav/files/admin/");
		for (DavResource res : resources) {
			System.out.println( res.getContentType() + "  " + res.getPath() );
		    //System.out.println(res); 
		}		
		
		//testDownload( sardine );
		//testUpload( sardine );
		testCreateDirectory( sardine );
		
	}
	
	private void testDownload( Sardine sardine ) throws Exception {
		InputStream initialStream = sardine.get("http://192.168.0.6:8080/remote.php/dav/files/admin/Nextcloud.mp4");
		File targetFile = new File("H:/Nextcloud.mp4");
   	    java.nio.file.Files.copy(initialStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
   	    initialStream.close();
	}
	
	
	private void testUpload( Sardine sardine ) throws Exception {
		File theFile = new File("H:/20190402_CERTIFICATE.pdf");
		//InputStream fis = new FileInputStream( theFile );
		sardine.put("http://192.168.0.6:8080/remote.php/dav/files/admin/20190402_CERTIFICATE.pdf", theFile, "application/pdf");	
		//fis.close();
	}
	
	private void testDelete( Sardine sardine ) {
		//sardine.delete("http://yourdavserver.com/adirectory/nameOfFile.jpg");
	}

	private void testCreateDirectory( Sardine sardine ) throws Exception {
		sardine.createDirectory("http://192.168.0.6:8080/remote.php/dav/files/admin/MewFolder");
	}
	
	private void testMove( Sardine sardine ) {
		// sardine.move("http://yourdavserver.com/adirectory/file1.jpg", "http://yourdavserver.com/adirectory/file2.jpg");
	}
	
	private void testCopy( Sardine sardine ) {
		// sardine.copy("http://yourdavserver.com/adirectory/file1.jpg", "http://yourdavserver.com/adirectory/file2.jpg");
	}
	
	private void testExists( Sardine sardine ) {
		// if (sardine.exists("http://yourdavserver.com/adirectory/file1.jpg")) System.out.println("got here!");		
	}

	
	private void testProps( Sardine sardine ) {
		/*
			String someUrl = "http://yourdavserver.com/adirectory/somefile.pdf";
			
			// 1. Set custom properties on a resource:
			Map<String,String> addProps = new HashMap<String,String>(2);
			addProps.put("author", "J. Diamond");
			addProps.put("title", "The Third Chimpanzee");
			
			sardine.setCustomProps(someUrl, addProps, null);
			
			// 2. Retrieve custom properties on a resource:
			List<DavResource> resources = sardine.getResources(someUrl);
			
			for (DavResource resource : resources) {
			    Map<String,String> customProps = resource.getCustomProps();
			    // Use custom properties...
			    String author = (String) customProps.get("author");
			    String title = (String) customProps.get("title");
			}
			
			// 3. Update and/or delete custom properties on a resource:
			Map<String,String> updateProps = new HashMap<String,String>(1);
			updateProps.put("author", "Jared Diamond");
			
			List<String> removeProps = new ArrayList<String>(1);
			removeProps.add("title");
			
			sardine.setCustomProps(someUrl, updateProps, removeProps);		
		*/		
	}
	
}
