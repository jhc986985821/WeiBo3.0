package com.hcq.actions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hcq.bean.AttenGroup;
import com.hcq.bean.Users;
import com.hcq.biz.AttenGroupBiz;
import com.hcq.biz.DianzanBiz;
import com.hcq.biz.UsersBiz;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Namespace("/")
@Scope(value = "prototy")
public class HotAction extends BaseAction implements ModelDriven<AttenGroup> {
	private static final long serialVersionUID = 1667646829068644308L;
	private AttenGroupBiz attenGroupBiz;
	private AttenGroup attenGroup;
	private DianzanBiz dianzanBiz;

	private UsersBiz usersBiz;

	@Action(value = "findHot")
	public void getHotUser() throws IOException {
		List<AttenGroup> uidlist = attenGroupBiz.selectHotPeople();
		List<Users> userslist = new ArrayList<Users>();
		List<AttenGroup> uidlist1 = new ArrayList<AttenGroup>();
		/*
		 * for (String Uider : uidlist) { Users u =
		 * usersBiz.getUser(Integer.valueOf(Uider)); userslist.add(u); }
		 */
		List<String> num = new ArrayList<String>();
		for (AttenGroup atten : uidlist) {
			// System.out.println(atten.getUider());
			Users u = usersBiz.getUser(atten.getUider());
			if (u != null) {
				userslist.add(u);
			}
			if (atten.getUid() != 0 && atten.getUider() != 0) {
				uidlist1.add(atten);
			}

			String num1 = dianzanBiz.getHashOne(
					"message_topic." + atten.getUider(), "Num");
			if (num1 == null) {
				num1 = "0";
			}
			num.add(num1);
		}

		jsonModel.setCode(1);
		jsonModel.setAttenGroups(uidlist1);
		jsonModel.setUserslist(userslist);
		jsonModel.setObj(num);
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}

	@Action(value = "findToday")
	public void getToday() throws IOException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		List<String> uidlist = dianzanBiz.HashzRevRange(df.format(new Date()));
		List<Users> userslist = new ArrayList<Users>();
		for (String uid : uidlist) {
			Integer uid1 = Integer.parseInt(uid);
			Users user = usersBiz.getUser(uid1);
			if (user != null) {
				userslist.add(user);
			}
		}
		jsonModel.setCode(1);
		jsonModel.setUserslist(userslist);
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	
	@Action(value = "guanzhu")
	public void addHotUser() throws IOException {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Users user1 = (Users) session.get("loginuser");
		if (session.get("loginuser") != null) {
			attenGroup.setUid(user1.getUid());
			List<String> uidlist = attenGroupBiz.findMyUid(attenGroup
					.getUider());
			// System.out.println(attenGroup.getUid());

			for (String uid : uidlist) {

				// System.out.println(uid);
				if (Integer.parseInt(uid) == user1.getUid()) {
					// System.out.println(atten.getUid());
					jsonModel.setCode(0);
					jsonModel.setMsg("The user has to vote ");
				} else {
					attenGroupBiz.addAtten(attenGroup);
					jsonModel.setCode(1);

				}
			}

		} else {
			jsonModel.setCode(0);
		}

		super.outJson(jsonModel, ServletActionContext.getResponse());
	}

	@Resource(name = "attenGroupBizImpl")
	public void setAttenGroupBiz(AttenGroupBiz attenGroupBiz) {
		this.attenGroupBiz = attenGroupBiz;
	}

	@Resource(name = "usersBizImpl")
	public void setUsersBiz(UsersBiz usersBiz) {
		this.usersBiz = usersBiz;
	}

	public AttenGroup getModel() {
		attenGroup = new AttenGroup();
		return attenGroup;
	}

	@Resource(name = "dianzanBizImpl")
	public void setDianzanBiz(DianzanBiz dianzanBiz) {
		this.dianzanBiz = dianzanBiz;
	}

}
