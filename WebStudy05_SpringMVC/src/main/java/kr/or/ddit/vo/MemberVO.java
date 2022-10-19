package kr.or.ddit.vo;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * 회원관리(Domain Layer)
 *
 * 한 사람의 회원에 대한 정보(구매기록이 포함)
 * 	has 관계
 * 	has A(1:1)
 * 	has Many(1:N)
 * 
 * 2개 이상의 테이블을 조인하고 결과 바인딩하는 방법.
 * 1. 대상 테이발 간의 관계 파악.
 *      MEMBER(1) : PROD(N)
 *      PROD(1) :  BUYER(1)
 * 2. 각 테이블로부터 데이터를 바인딩할 VO정의
 *      해당 VO 간의 관계를 형성.
 *      MemberVO has Many ProdVO
 * 3. 조인 쿼리 작성
 * 4. resultMap을 사용하여 결과 바인딩(수동)
 *      has Many -> collection
 *      has A -> association
 */
@Data
@EqualsAndHashCode(of = "memId")
@ToString(exclude = {"memPass", "memRegno1", "memRegno2"})
@Slf4j
public class MemberVO implements Serializable {
	@NotBlank(groups = {Default.class, DeleteGroup.class})
	private String memId;
	
	@NotBlank(groups = {Default.class, DeleteGroup.class})
	@Size(min = 4, max = 12, groups = {Default.class, DeleteGroup.class})
	@JsonIgnore
	private transient String memPass;
	
	private String memName;
	
	@NotBlank(groups = {InsertGroup.class})
	@JsonIgnore
	private transient String memRegno1;
	
	@NotBlank(groups = {InsertGroup.class})
	@JsonIgnore
	private transient String memRegno2;
	private String memBir;
	
	@NotBlank
	private String memZip;
	
	@NotBlank
	private String memAdd1;
	
	@NotBlank
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	private String memHp;
	
	@NotBlank
	@Email
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	private String memMemorialday;
	
	@Min(0)
	private Integer memMileage;
	
	private List<ProdVO> prodList; //has Many
	
	private String memRole;
	
	private byte[] memImg; //DB에 BLOB로 되어있으므로 byte타입의 배열로 받아야한다.
	private MultipartFile memImage;
	
	public void setMemImage(MultipartFile memImage) throws IOException {
		if(memImage == null || memImage.isEmpty()) return;
		this.memImage = memImage;
		this.memImg=memImage.getBytes();
	}
	
	public String getBase64Img() {
		if(memImg==null) {
			return null;
		}else {
			String base64Text = Base64.getEncoder().encodeToString(memImg);
			log.info("base64 encoded text : {}",base64Text);
			return base64Text;
		}
	}
	
	public String getMemTest() {
		return "테스트";
	}
}
