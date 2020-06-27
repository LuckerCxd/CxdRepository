package cn.cxd.action;


import javax.servlet.ServletOutputStream;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;

import cn.dsna.util.images.ValidateCode;

public class ValidCode extends StrutsResultSupport{
	private int width;
	private int height;
	@Override
	protected void doExecute(String finalLocation, ActionInvocation invocation) throws Exception {
		ValidateCode code = new ValidateCode(width, height , 4, 2);
		ServletOutputStream sos = ServletActionContext.getResponse().getOutputStream();
		code.write(sos);
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
