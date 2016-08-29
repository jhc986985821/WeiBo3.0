package com.hcq.actions;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@Controller
@Scope(value="prototy")
@Namespace("/")
@InterceptorRefs({
	@InterceptorRef(value="fileUpload",params={"allowedTypes","image/jpg,image/png,image/jpeg","maximumSize","1024000"}),
	@InterceptorRef("defaultStack")
})
public class FileUploadAction extends BaseAction{
	private static final long serialVersionUID = -1089117414607908041L;
	protected File files;
	
	
	@Action(value="/file_upload")
	public void picUpload() throws IOException{
		String realpath = ServletActionContext.getServletContext().getRealPath("../file"); //
		try{
			if(files!=null){
				File saveDir=new File(realpath);
				if(!saveDir.exists()){
					saveDir.mkdirs();
				}
				Long time =System.currentTimeMillis();   
				File savefile= new File(saveDir,time+".jpg");
				FileUtils.copyFile(files, savefile);
				jsonModel.setObj("..\\file\\"+time+".jpg");
				jsonModel.setCode(1);
			}
		}catch(Exception e){
			e.printStackTrace();
			jsonModel.setCode(0);
			jsonModel.setMsg(e.getMessage());
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	

	public File getFiles() {
		return files;
	}

	public void setFiles(File files) {
		this.files = files;
	}

}
