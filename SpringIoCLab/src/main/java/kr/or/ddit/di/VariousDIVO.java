package kr.or.ddit.di;

import java.io.File;
import java.util.Properties;

import org.springframework.core.io.Resource;

import lombok.Data;

@Data
public class VariousDIVO {
	private int numnber;
	private double dbNumber;
	private boolean boolData;
	private char ch;
	private String text;
	
	private Resource fsfile;
	private Resource cpfile;
	
	private Properties dbInfo;
}
