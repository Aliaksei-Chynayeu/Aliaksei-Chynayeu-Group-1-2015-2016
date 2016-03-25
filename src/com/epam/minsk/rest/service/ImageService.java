package com.epam.minsk.rest.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

/**
 * Service class which allows to download/upload image
 * @author Dzina_Andreyeva
 *
 */
@Path("/image")
public class ImageService {
	
	private static final String FILE_PATH_DOWNLOAD = "d:\\mine\\01012015510.jpg";
	private static final String FILE_PATH_UPLOAD = "d:\\uploaded\\";

	@GET
	@Path("/downloadImage")
	@Produces("image/png")
	public Response getFile() {

		File file = new File(FILE_PATH_DOWNLOAD);

		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition", "attachment; filename=image_from_server.png");
		return response.build();
	}

	 @POST
	    @Path("/uploadImage")
	    @Consumes(MediaType.MULTIPART_FORM_DATA)
	    @Produces(MediaType.TEXT_PLAIN)
	    public String uploadFile(@FormDataParam("file") InputStream fis,
	                    @FormDataParam("file") FormDataContentDisposition fdcd) {
	  
	        OutputStream outpuStream = null;
	        String fileName = fdcd.getFileName();
	        System.out.println("File Name: " + fdcd.getFileName());
	        String filePath = FILE_PATH_UPLOAD + fileName;
	         
	        try {
	            int read = 0;
	            byte[] bytes = new byte[1024];
	            outpuStream = new FileOutputStream(new File(filePath));
	            while ((read = fis.read(bytes)) != -1) {
	                outpuStream.write(bytes, 0, read);
	            }
	            outpuStream.flush();
	            outpuStream.close();
	        } catch(IOException iox){
	            iox.printStackTrace();
	        } finally {
	            if(outpuStream != null){
	                try{outpuStream.close();} catch(Exception ex){}
	            }
	        }
	        return "File Upload Successfully !!";
	    }
	
}
