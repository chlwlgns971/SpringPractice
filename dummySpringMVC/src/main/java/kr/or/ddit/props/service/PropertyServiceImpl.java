package kr.or.ddit.props.service;

import java.util.List;

import javax.inject.Inject;

import kr.or.ddit.props.dao.FileSystemPropertyDaoImpl;
import kr.or.ddit.props.dao.PropertyDAO;
import kr.or.ddit.props.vo.PropertyVO;

public class PropertyServiceImpl implements PropertyService {
	private PropertyDAO dao;
	
	@Inject// 생성자가 하나뿐이라 생략 가능함
	public PropertyServiceImpl(PropertyDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public PropertyVO readProperty(String propertyName) {
		PropertyVO vo = dao.selectProperty(propertyName);
		if(vo == null) throw new RuntimeException();
		return vo;
	}

	@Override
	public List<PropertyVO> readProperties() {
		List<PropertyVO> dataList = dao.selectProperties();
		dataList.forEach((vo) -> {
			if(vo.getDescription()==null) vo.setDescription("라볶이 만들었음.");
		});
		/*
		 * if(dataList.size() != 0) { boolean checkedValue =
		 * dataList.contains("description"); if(!checkedValue) { PropertyVO vo =new
		 * PropertyVO(); vo.setPropertyName("description");
		 * vo.setPropertyValue("라볶이 먹었음."); dao.insertProperty(vo); dataList.add(vo); }
		 * 
		 * }
		 */
		return dataList;
	}

	@Override
	public void createProperty(PropertyVO vo) {
		dao.insertProperty(vo);
	}

}
