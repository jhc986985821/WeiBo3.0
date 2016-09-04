package com.hcq.biz.impl;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hcq.bean.Users;
import com.hcq.biz.BaseBiz;
import com.hcq.biz.UsersBiz;
import com.hcq.utils.Encrypt;
import com.hcq.web.model.ManagerModel;

@Service
public class UsersBizImpl extends BaseBiz implements UsersBiz{
	
	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,noRollbackForClassName={"java.lang.RuntimeException"},propagation=Propagation.NOT_SUPPORTED)
	public Users getUser(Integer uid){
		Users u=null;
		u=(Users) basedao.findById(Users.class, uid, "getMyUser");
		return u;
	}

	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackForClassName={"java.lang.RuntimeExceptipon"},propagation=Propagation.REQUIRED)
	public boolean register(Users user) {
		user.setUpassword(Encrypt.md5(user.getUpassword()));
		basedao.save(user, "saveUser");
		return true;
	}

	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,rollbackForClassName={"java.lang.RuntimeExceptipon"},propagation=Propagation.NOT_SUPPORTED)
	public boolean validate(Users user) {
		user = (Users) basedao.find(user, "getUserByUlogon");
		if(user != null){
			return true;
		}
		return false;
	}

	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,rollbackForClassName={"java.lang.RuntimeExceptipon"},propagation=Propagation.NOT_SUPPORTED)
	public Users login(Users user) {
		user.setUpassword(Encrypt.md5(user.getUpassword()));
		user = (Users) basedao.find(user, "getUserByLogin");
		return user;
	}

	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackForClassName={"java.lang.RuntimeExceptipon"},propagation=Propagation.REQUIRED)
	public boolean update(Users user) {
		basedao.update(user, "updateUser");
		return true;
	}
	
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackForClassName={"java.lang.RuntimeExceptipon"},propagation=Propagation.REQUIRED)
	public boolean update2(Users user) {
		basedao.update(user, "updateUser2");
		return true;
	}

	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackForClassName={"java.lang.RuntimeExceptipon"},propagation=Propagation.REQUIRED)
	public boolean updateImage(Users user) {
		basedao.update(user, "updateUserImage");
		return false;
	}

	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackForClassName={"java.lang.RuntimeExceptipon"},propagation=Propagation.REQUIRED)
	public boolean modifypwd(Users user) {
		user.setPassword(Encrypt.md5(user.getPassword()));
		basedao.update(user, "updatePwd");
		return true;
	}

	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,rollbackForClassName={"java.lang.RuntimeExceptipon"},propagation=Propagation.NOT_SUPPORTED)
	public Users findAll(Users user) {
		user = (Users) basedao.find(user, "getUser");
		if(user != null){
			return user;
		}
		return null;
	}

	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,rollbackForClassName={"java.lang.RuntimeExceptipon"},propagation=Propagation.NOT_SUPPORTED)
	public List<Users> showAllUser() {
		List<Users> userslist=null;
		userslist=(List<Users>)basedao.findAll(Users.class,"showAllUser");
		return userslist;
	}

}
