package kr.or.ddit.di.collection;

import org.springframework.beans.factory.FactoryBean;

public class StringArrayFacotryBean implements FactoryBean<String[]>{

	@Override
	public String[] getObject() throws Exception {
		// TODO Auto-generated method stub
		return new String[] {"arrayValue1", null, "arrayValue2"};
	}

	@Override
	public Class<?> getObjectType() {
		return String[].class;
	}

	@Override
	public boolean isSingleton() { // scope설정. 기본은 return false이고, 이 경우 prototype
		return true;
	}

}
