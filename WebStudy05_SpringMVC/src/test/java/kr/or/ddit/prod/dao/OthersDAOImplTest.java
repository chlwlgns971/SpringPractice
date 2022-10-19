package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import kr.or.ddit.TestContextConfiguration;

@RunWith(SpringRunner.class)
@TestContextConfiguration
public class OthersDAOImplTest {
	@Inject
	OthersDAO dao;

	@Test
	public void testSelectLprodList() {
		System.out.println(dao.selectLprodList());
	}

	@Test
	public void testSelectBuyerList() {
		System.out.println(dao.selectBuyerList("P101"));
		
	}

}
