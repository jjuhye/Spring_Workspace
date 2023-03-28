package com.myspring.test.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileController {

	@Autowired
	Upload upload;
	
	@RequestMapping(value="/fileUpload", method=RequestMethod.POST)
	public String fileUpload(MultipartHttpServletRequest mRequest, Model model) {
		
		String result = "파일 업로드 실패!!";
		if(upload.fileUpload(mRequest)){
			result = "파일 업로드 성공!!";
		} 
		
		model.addAttribute("result", result);
		
		return "uploadResult";
	}
	
}
