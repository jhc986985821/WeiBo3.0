package com.hcq.actions;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.hcq.web.model.JsonModel;
import com.opensymphony.xwork2.ActionSupport;
public abstract class BaseAction extends ActionSupport{
	private static final long serialVersionUID = -4317364837108902302L;
	protected JsonModel jsonModel=new JsonModel();
	//protected HttpServletResponse  response = ServletActionContext.getResponse();
	
	public String parseJson(JsonModel jsonModel){
		Gson gson = new Gson();
		return gson.toJson(jsonModel);
	}
	
	public void outJson(JsonModel jsonModel,HttpServletResponse response) throws IOException{
		String json =parseJson(jsonModel);
		//跨域
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json;");
		response.setCharacterEncoding("utf-8");
	
		PrintWriter out=response.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}
}
