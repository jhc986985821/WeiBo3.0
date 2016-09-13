package com.hcq.actions;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hcq.bean.Users;
import com.hcq.biz.MailSend;
import com.hcq.biz.UsersBiz;
import com.hcq.biz.impl.MailSendImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope
@Namespace("/")
@ParentPackage("struts-default")
public class UsersAction extends BaseAction implements ModelDriven<Users> {

	private static final long serialVersionUID = -1536339961252670596L;

	private Users user;
	private UsersBiz userBiz;

	// 会在Register前运行
	public void validateRegister() throws IOException {
		if (userBiz.validate(user)) {
			jsonModel.setCode(0);
			jsonModel.setMsg("user exists...");
			// super.outJson(jsonModel, ServletActionContext.getResponse());
			// return;
		}
	}

	// 会在login前运行
	public void validateLogin() throws IOException {
		if (user.getUlogon() == null || "".equals(user.getUlogon())) {
			jsonModel.setCode(0);
			jsonModel.setMsg("email should not be null");
			// super.outJson(jsonModel, ServletActionContext.getResponse());
			// return;
		} else if (user.getUpassword() == null
				|| "".equals(user.getUpassword())) {
			jsonModel.setCode(0);
			jsonModel.setMsg("password should not be null");
			// super.outJson(jsonModel, ServletActionContext.getResponse());
			// return;
		}

	}

	@Action(value = "/user_register")
	public void register() throws IOException {
		if (jsonModel.getCode() == null) {
			userBiz.register(user);
			//MailSend mailSend = new MailSendImpl();
			jsonModel.setCode(1);
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}

	@Action(value = "/user_login")
	public void login() throws IOException {
		if (jsonModel.getCode() == null) {
			Users user = userBiz.login(this.user);
			Map<String, Object> session = ActionContext.getContext()
					.getSession();
			if (null != user) {
				session.put("loginuser", user);
				jsonModel.setCode(1);
				user.setUpassword(null);
				jsonModel.setObj(user);
			} else {
				jsonModel.setCode(0);
				jsonModel.setMsg("error email name or password");
			}
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}

	@Action(value = "/user_logout")
	public void logout() throws IOException {

		if (null != user) {
			Map<String, Object> session = ActionContext.getContext()
					.getSession();
			session.remove("loginuser");
			jsonModel.setCode(1);
		} else {
			jsonModel.setCode(0);
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}

	@Action(value = "/user_checklogin")
	public void checkLogin() throws IOException {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (session.get("loginuser") != null) {
			user = (Users) session.get("loginuser");
			jsonModel.setCode(1);
			user.setUpassword(null);
			jsonModel.setObj(user);
		} else {
			jsonModel.setCode(0);
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}

	@Action(value = "/user_update")
	public void update() throws IOException {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Users user1 = (Users) session.get("loginuser");
		if (session.get("loginuser") != null) {

			user.setUid(user1.getUid());
		} else {
			jsonModel.setCode(0);
		}
		if (jsonModel.getCode() == null) {
			userBiz.update(user);
			Users user2 = userBiz.findAll(user);
			session.remove("loginuser");
			session.put("loginuser", user2);
			jsonModel.setCode(1);
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}

	@Action(value = "/user_update1")
	public void update1() throws IOException {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Users user1 = (Users) session.get("loginuser");
		if (session.get("loginuser") != null) {

			user.setUid(user1.getUid());
		} else {
			jsonModel.setCode(0);
		}
		if (jsonModel.getCode() == null) {
			userBiz.update2(user);
			jsonModel.setCode(1);
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}

	public void validatePwd() throws IOException {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Users user1 = (Users) session.get("loginuser");
		if (user1.getUlogon() == null || "".equals(user1.getUlogon())) {
			System.out.println(user1.getUlogon());
			jsonModel.setCode(0);
			jsonModel.setMsg("email should not be null");
		} else if (user.getUpassword() == null
				|| "".equals(user.getUpassword())) {
			System.out.println(user1.getUpassword());
			jsonModel.setCode(0);
			jsonModel.setMsg("password should not be null");
		}
		
		
		if (jsonModel.getCode() == null) {
			user1.setUpassword(user.getUpassword());
			
			System.out.println(user.getUpassword());
			System.out.println("pwd " +  user1.getUpassword());
			System.out.println("sdad         asd     " +  user1.getUlogon());
			Users user5 = userBiz.login(user1);
			if (user5 != null) {
				user.setUpassword(null);
				jsonModel.setObj(user);
			} else {
				jsonModel.setCode(0);
				jsonModel.setMsg("error email name or password");
			}
		}
	}
	
	@Action(value = "/user_pwd")
	public void pwd() throws IOException {
		if (jsonModel.getCode() == null) {
			Map<String, Object> session = ActionContext.getContext().getSession();
			Users user1 = (Users) session.get("loginuser");
			if (session.get("loginuser") != null) {
				user.setUid(user1.getUid());
			} else {
				jsonModel.setCode(0);
			}
				System.out.println(user.getPassword());
				userBiz.modifypwd(user);
				jsonModel.setCode(1);
		
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}

	@Action(value = "/update_image")
	public void updateImage() throws IOException {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Users user1 = (Users) session.get("loginuser");
		if (session.get("loginuser") != null) {
			user.setUid(user1.getUid());
		} else {
			jsonModel.setCode(0);
		}
		if (jsonModel.getCode() == null) {
			userBiz.updateImage(user);
			jsonModel.setObj(user.getUimage());
			Users user2 = userBiz.findAll(user);
			session.remove("loginuser");
			session.put("loginuser", user2);
			jsonModel.setCode(1);
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}

	
	@Resource(name = "usersBizImpl")
	public void setUserBiz(UsersBiz userBiz) {
		this.userBiz = userBiz;
	}

	public Users getModel() {
		user = new Users();
		return user;
	}

}
