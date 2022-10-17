package kr.or.ddit.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.AttatchVO;

@Controller
public class AttatchDownloadController {
	
	@Inject
	private BoardService service;
	
	@Value("#{appInfo.attatchFolder}")
	private File saveFolder;
	
	@RequestMapping("/board/download.do")
	public String download(
			@RequestParam(name="what", required=true) int attNo
			, HttpServletResponse resp
			, Model model
	) throws IOException {
		AttatchVO attatch = service.retrieveAttatch(attNo);
		model.addAttribute("attatch", attatch);
		return "downloadView";
		
//		String saveName = attatch.getAttSavename();
//		File file = new File(saveFolder, saveName);
//		
//		String fileName = attatch.getAttFilename();
//		fileName = URLEncoder.encode(fileName,"UTF-8");
//		fileName = fileName.replaceAll("\\+", " ");
//		resp.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
//		resp.setContentLengthLong(attatch.getAttFilesize());
//		resp.setHeader("Content-Disposition", "attatchment;filename=\""+fileName+"\"");// ';'앞에 inline을 하면 바로 실행되는 옵션. attatchment는 다운로드 하는 옵션
//		try(
//			OutputStream os = resp.getOutputStream();	
//		){
//			FileUtils.copyFile(file, os);
//		}
		
	}
}
