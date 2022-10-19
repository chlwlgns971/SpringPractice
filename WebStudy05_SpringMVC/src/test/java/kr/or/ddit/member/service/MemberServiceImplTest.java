package kr.or.ddit.member.service;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import kr.or.ddit.TestContextConfiguration;

@RunWith(SpringRunner.class)
@TestContextConfiguration
public class MemberServiceImplTest {
	@Inject
	MemberService service;

	@Test
	public void testCreateMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveMember() {
		System.out.println(service.retrieveMember("sdosaslifhseilfhisefhl"));
	}

	@Test
	public void testRetrieveMemberCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveMemberList() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifyMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveMember() {
		fail("Not yet implemented");
	}

}
