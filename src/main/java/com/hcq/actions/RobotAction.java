package com.hcq.actions;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hcq.bean.Message;
import com.hcq.utils.Aes;
import com.hcq.utils.LuceneUtil;
import com.hcq.utils.Md5;
import com.hcq.utils.PostServer;
import com.hcq.web.model.RobotWord;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;


@Controller
@Scope(value = "prototy")
@Namespace("/")
public class RobotAction extends BaseAction implements ModelDriven<RobotWord>{
	private static final long serialVersionUID = 2173143932879307016L;
	private RobotWord robotWord;
	
	@Action(value="robotchat")
	public void robot() throws IOException{
		String cmd= robotWord.getWord();
		//图灵网站上的secret
		String secret = "fcdc2bd9ad366973";
		//图灵网站上的apiKey
		String apiKey = "33a536afc74809e1f30d230a2e8853f8";
		//待加密的json数据
		String data = "{\"key\":\""+apiKey+"\",\"info\":\""+cmd+"\"}";
		//获取时间戳
		String timestamp = String.valueOf(System.currentTimeMillis());
		
		//生成密钥
		String keyParam = secret+timestamp+apiKey;
		String key = Md5.MD5(keyParam);
		
		//加密
		Aes mc = new Aes(key);
		data = mc.encrypt(data);	
		
		JSONObject json = new JSONObject();
		json.put("key", apiKey);
		json.put("timestamp", timestamp);
		json.put("data", data);
		
		//请求图灵api
		String result = PostServer.SendPost(json.toString(), "http://www.tuling123.com/openapi/api");
		JSONObject jObject = new JSONObject();
		JSONObject jsonObject=jObject.fromObject(result);
		
		
		super.outJson(jsonObject, ServletActionContext.getResponse());
	}
	
	@Action(value="searchhot")
	public void hot() throws IOException, InvalidTokenOffsetsException{
		LuceneUtil luceneUtil = new LuceneUtil();
		List<Message>list=luceneUtil.search(robotWord.getWord(), 5);
		
		super.outJson(list, ServletActionContext.getResponse());
	}
	
	
	public RobotWord getModel() {
		robotWord=new RobotWord();
		return robotWord;
	}
	
}
