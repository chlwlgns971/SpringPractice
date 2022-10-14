package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ImageUploadController {
	
	@Inject
	private ServletContext application;
	@Value("#{appInfo.imageFolderURL}")
	private String imageFolderURL;
	@Value("#{appInfo.imageFolderURL}")
	private Resource imageFolderRes;
	private File saveFolder;
	
	/**
	 * PostConstruct는 기본생성자와 Inject를 실행한 이후에 실행하라는 의미!
	 * @throws IOException 
	 */
	@PostConstruct
	public void init() throws IOException {
		saveFolder = imageFolderRes.getFile();	
		if(!saveFolder.exists()) saveFolder.mkdirs();
		log.info("image folder url : {}", imageFolderURL);
		log.info("로딩된 saveFolder : {}",saveFolder.getCanonicalPath());
	}
	
	@RequestMapping(value="/board/imageUpload.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> imageUpload(@RequestPart(required=true) MultipartFile upload) throws IOException{
		Map<String, Object> target = new HashMap<String, Object>();
//		fileName
//		uploaded
//		url
//		error : {message, number}

		if(!upload.isEmpty()) {
			String saveName = UUID.randomUUID().toString();
			try(
				InputStream is = upload.getInputStream();	
			){
				File saveFile = new File(saveFolder, saveName);
				FileUtils.copyInputStreamToFile(is, saveFile);
				String fileName = upload.getOriginalFilename();
				int uploaded = 1;
				String url = String.format("%s%s/%s", application.getContextPath(), imageFolderURL, saveName);
				target.put("fileName", fileName);
				target.put("uploaded", uploaded);
				target.put("url", url);
			}
		}else {
			Map<String, Object> error = new HashMap<>();
			target.put("error", error);
			error.put("number", 400);
			error.put("message", "업로드된 이미지가 비어있음");
		}
		return target;
	}
}