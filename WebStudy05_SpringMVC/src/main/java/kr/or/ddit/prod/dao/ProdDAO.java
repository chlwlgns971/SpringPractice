package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품관리 Persistence Layer
 * 
 *
 */
@Mapper
public interface ProdDAO {
	public int insertProd(ProdVO prod);
	
	/**
	 * 상품 전체 정보 + 분류명, 거래처명, 상품 구매자 목록(아이디, 구매자명, 지역, 이메일)
	 * @param prodId
	 * @return
	 */
	public ProdVO selectProd(String prodId);
	
	public int selectTotalRecord(PagingVO pagingVO);
	/**
	 * 상품 아이디, 상품명, 판매가, 구매가, 마일리지. +분류명, 거래처명
	 * @return
	 */
	public List<ProdVO> selectProdList(PagingVO pagingVO);
	
	public int updateProd(ProdVO prod);
	
//	public int deleteProd(String prodId);
}
