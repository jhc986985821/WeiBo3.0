package com.hcq.biz;

import com.hcq.bean.Users;

public interface UsersBiz {
	public Users getUser(Integer uid);
	

	/**
	 * 注册
	 * @param user
	 * @return
	 */
	public boolean register(Users user);
	
	/**
	 * 查询用户名是否存在
	 * @param user
	 * @return
	 */
	public boolean validate(Users user);
	
	/**
	 * 查询出所有信息
	 * @param user
	 * @return
	 */
	public Users findAll(Users user);
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	public Users login(Users user);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public boolean update(Users user);
	

	/**
	 * 绑定手机
	 * @param user
	 * @return
	 */
	public boolean update2(Users user);
	

	/**
	 * 图片
	 * @param user
	 * @return
	 */
	public boolean updateImage(Users user);
	

	/**
	 * 修改密码
	 * @param user
	 * @return
	 */
	public boolean modifypwd(Users user);
}
