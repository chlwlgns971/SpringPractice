package kr.or.ddit.board.vo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="attNo")
@ToString(exclude="adaptee")
@NoArgsConstructor
public class AttatchVO {
	private MultipartFile adaptee;
	
	public AttatchVO(MultipartFile adaptee) {
		super();
		this.adaptee = adaptee;
		this.attFilename = adaptee.getOriginalFilename();
		this.attSavename = UUID.randomUUID().toString();
		this.attMime = adaptee.getContentType();
		this.attFilesize = adaptee.getSize();
		this.attFancysize = FileUtils.byteCountToDisplaySize(attFilesize);
	}
	@NotNull
	private Integer attNo;
	private Integer boNo;
	@NotBlank
	private String attFilename;
	@NotBlank
	private String attSavename;
	private String attMime;
	@NotNull
	private long attFilesize;
	@NotBlank
	private String attFancysize;
	private Integer attDownload;
	
	public void saveTo(File saveFolder) throws IOException {
		if(adaptee==null) return;
		File saveFile = new File(saveFolder, attSavename);
		adaptee.transferTo(saveFile);
	}
	
	public void deleteTo(List<String> list ,File saveFolder) {
		for(String fileName : list) {
			File deleteFile = new File(saveFolder,fileName);
			if(deleteFile.exists()) deleteFile.delete();
		}
	}
}
