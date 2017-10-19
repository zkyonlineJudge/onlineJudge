package com.yhw.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUp {
	public static String Save(MultipartFile file,HttpServletRequest request, Integer id){
		String path = request.getSession().getServletContext().getRealPath("upload"); 
		String fileName = file.getOriginalFilename();
		String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
		String fileNameTwo = System.currentTimeMillis()+String.valueOf((int)((Math.random()*9+1)*100000))+"."+prefix;
		File targetFile = new File(path, fileNameTwo);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        
		return  request.getContextPath()+"/upload/"+fileNameTwo;
	}

}
