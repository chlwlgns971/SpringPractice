package kr.or.ddit.board.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
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
	@NotNull(groups={UpdateGroup.class, DeleteGroup.class})
	private Integer boNo;
	@NotBlank //NotBlank는 insert에서만 체크해야함.
	private String boTitle;
	@NotBlank(groups=InsertGroup.class)
	private String boWriter;
	@NotBlank(groups=InsertGroup.class)
	private String boIp;
	@Email
	private String boMail;
	@NotBlank(groups=DeleteGroup.class)
	private String boPass;
	private String boContent;
	private String boDate;
	private Integer boHit;
	private Integer boRec;
	private Integer boParent;
}
