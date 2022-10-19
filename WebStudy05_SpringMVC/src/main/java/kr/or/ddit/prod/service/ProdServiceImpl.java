package kr.or.ddit.prod.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.or.ddit.member.service.ServiceResult;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@Service
public class ProdServiceImpl implements ProdService {
	@Inject
	ProdDAO dao;
	
	@Value("#{appInfo.prodImageURL}")
	File saveFolder;
	
	@PostConstruct
	public void init() {
		if(!saveFolder.exists()) saveFolder.mkdirs();
	}

	@Override
	public ServiceResult createProd(ProdVO prod) {
		ServiceResult result = null;
		int rowcnt = dao.insertProd(prod);
		try {
			if(rowcnt>0) {
				prod.saveTo(saveFolder);
				result=ServiceResult.OK;
			}
			else result=ServiceResult.FAIL;
		} catch (IOException e) {
				throw new RuntimeException(e);
		}
		
		return result;
	}

	@Override
	public ProdVO retrieveProd(String prodId) {
		return dao.selectProd(prodId);
	}

	@Override
	public List<ProdVO> retrieveProdList(PagingVO pagingVO) {
		return dao.selectProdList(pagingVO);
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		ServiceResult result = null;
		int rowcnt = dao.updateProd(prod);
		try {
			if(rowcnt>0) {
				prod.saveTo(saveFolder);
				result=ServiceResult.OK;
			}
			else result=ServiceResult.FAIL;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}

	@Override
	public int retrieveProdCount(PagingVO pagingVO) {
		return dao.selectTotalRecord(pagingVO);
	}

}
