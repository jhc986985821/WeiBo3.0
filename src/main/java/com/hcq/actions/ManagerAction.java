package com.hcq.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.google.gson.Gson;
import com.hcq.bean.Users;
import com.hcq.biz.UsersBiz;
import com.hcq.utils.SiteInfo;
import com.hcq.utils.SystemInfo;
import com.hcq.web.model.ManagerModel;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope(value = "prototy")
@Namespace("/")
public class ManagerAction extends BaseAction implements ModelDriven<ManagerModel>{
	private static final long serialVersionUID = 3330264823106340215L;
	private UsersBiz usersBiz;
	private ManagerModel managerModel;
	
	@Action(value="showUser")
	public void showAllUser() throws IOException{
		List<Users> userslist=usersBiz.showAllUser();
		jsonModel.setObj(userslist);
		super.outJson(userslist, ServletActionContext.getResponse());
	}
	
	
	@Action(value="systemInfo")
	public void SystemInfo() throws IOException{
		SiteInfo info = new SiteInfo();
		super.outJson(info, ServletActionContext.getResponse());
	}

	@Resource(name = "usersBizImpl")
	public void setUsersBiz(UsersBiz usersBiz) {
		this.usersBiz = usersBiz;
	}


	public ManagerModel getModel() {
		managerModel=new ManagerModel();
		return managerModel;
	}
	
}
