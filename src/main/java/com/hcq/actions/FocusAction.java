package com.hcq.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.hcq.bean.AttenGroup;
import com.hcq.bean.Users;
import com.hcq.biz.AttenGroupBiz;
import com.hcq.biz.UsersBiz;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Namespace("/")
@Scope(value="prototy")
public class FocusAction  extends BaseAction implements ModelDriven<AttenGroup>{

	private static final long serialVersionUID = 1L;
	private AttenGroupBiz attenGroupBiz;
	private UsersBiz usersBiz;
	private AttenGroup attenGroup;
	
	
	@Resource(name="attenGroupBizImpl")
	public void setAttenGroupBiz(AttenGroupBiz attenGroupBiz) {
		this.attenGroupBiz = attenGroupBiz;
	}
	
	
	@Resource(name="usersBizImpl")
	public void setUsersBiz(UsersBiz usersBiz) {
		this.usersBiz = usersBiz;
	}



	@Action(value="findUid")
	public void findUid() throws IOException{
		List<String> uidlist = attenGroupBiz.findUid(attenGroup.getUid());
		List<Users> userslist = new ArrayList<Users>();
		Users users = usersBiz.getUser(attenGroup.getUid());
		for (String uid : uidlist) {
			Users u = usersBiz.getUser(Integer.valueOf(uid));
			userslist.add(u);
		}

		jsonModel.setUserslist(userslist);
		jsonModel.setCode(1);
		jsonModel.setUsers(users);
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}

	
	@Action(value="findUidQueue")
	public void findUidQueue() throws IOException{
		List<String> queelist = attenGroupBiz.findUidQueue(attenGroup.getUid());
		List<Users> qlist = new ArrayList<Users>();

		for (String uid : queelist) {
			Users u = usersBiz.getUser(Integer.valueOf(uid));
			qlist.add(u);
		}

		jsonModel.setUserslist(qlist);
		jsonModel.setCode(1);

		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	
	
	public AttenGroup getModel() {
		attenGroup=new AttenGroup();
		return attenGroup;
	}
}
