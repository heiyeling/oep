package com.zr.service.impl;

import java.util.List;



import com.zr.dao.RemoveUserDao;
import com.zr.dao.impl.RemoveUserDaoImpl;
import com.zr.service.RemoveUserService;

public class RemoveUserServiceImpl implements RemoveUserService{
	RemoveUserDao rdao = new RemoveUserDaoImpl();

	@Override
	public List RemoveUserByUid(List ids) {
		List l = rdao.RemoveUserByUid(ids);
		return l;
	}
	
	
	

	
	

}
