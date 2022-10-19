package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import kr.or.ddit.TestContextConfiguration;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@TestContextConfiguration
public class ProdDAOImplTest {
	@Inject
	ProdDAO dao;

	@Test
	public void testInsertProd() {
//		ProdVO prod = new ProdVO();
//		prod.setProdId("P302000024");
//		prod.setProdName("핸드크림");
//		prod.setProdLgu("P302");
//		prod.setProdBuyer("제로스킨");
//		int rowcnt = dao.insertProd(prod);
//		log.info("prod : {}",prod);
//		log.info("rowcnt : {}",rowcnt);
	}

	@Test
	public void testSelectProd() {
//		ProdVO prod = dao.selectProd("sdfasdasdf");
//		assertNull(prod);
//		prod = dao.selectProd("P101000001");
//		assertNotNull(prod);
//		log.info("prod: {}", prod);
	}

	@Test
	public void testSelectProdList() {
//		List<ProdVO> prodList = dao.selectProdList();
//		log.info("prodList: {}", prodList);
	}

	@Test
	public void testUpdateProd() {
		fail("Not yet implemented");
	}

}
