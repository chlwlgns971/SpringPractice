package kr.or.ddit.board.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 게시판 관리를 위한 Domain Layer 
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {
	private Integer boNo;
	private String boTitle;
	private String boWriter;
	private String boIp;
	private String boMail;
	private String boPass;
	private String boContent;
	private String boDate;
	private Integer boHit;
	private Integer boRec;
	private Integer boParent;
}
