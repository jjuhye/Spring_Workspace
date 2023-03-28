package com.myspring.test.upload;

import java.io.File;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class Upload {

	public boolean fileUpload(MultipartHttpServletRequest mRequest) {
	
		boolean isUpload = false;
		
		ServletContext context = mRequest.getSession().getServletContext();
		String saveFolder = "/resources/";
		String uploadPath = context.getRealPath(saveFolder);
		System.out.println("uploadPath="+uploadPath);
		
		// # getParameterNames() : form태그 안의 name값 불러오기
		Enumeration<String> keyList = mRequest.getParameterNames();
		while(keyList.hasMoreElements()) {
			String key = keyList.nextElement();
			System.out.print("key = " + key);
			String value = mRequest.getParameter(key);
			System.out.println(", value = " + value);
		}
		
		// # getFileNames() : form태그에서 type=file로 지정한 태그의 name값 불러오기
		Iterator<String> iterator = mRequest.getFileNames();
		
		while(iterator.hasNext()){
			String fileName = iterator.next();
			
			System.out.println("fileName = " + fileName);
			
			MultipartFile mFile = mRequest.getFile(fileName);
			String originFileName = mFile.getOriginalFilename();
			
			System.out.println("originFileName = " + originFileName);
			
			String saveFileName = originFileName;
			
			// 업로드된 파일 이 존재하면
			if(saveFileName != null && !saveFileName.equals("")){
				// 그 파일의 이름이 기존에 있던 파일들과 이름이 중복되면
				if(new File(uploadPath + saveFileName).exists()){
					// 파일명 앞에 uuid 고유 이름값 생성 해서 붙에주기 
					
					UUID uuid = UUID.randomUUID();
					
					saveFileName = uuid.toString()+"_"+saveFileName;
					
					System.out.println("saveFileName = " + saveFileName);
				}
				
				try {
					// 파일 업로드
					mFile.transferTo(new File(uploadPath+saveFileName));
					
					isUpload=true;
				} catch (Exception e) {
					e.printStackTrace();
					isUpload=false;
				} 				
			}
		}
		
		return isUpload;
	}
	
}
