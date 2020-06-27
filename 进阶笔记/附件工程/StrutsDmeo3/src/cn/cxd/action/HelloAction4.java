package cn.cxd.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

//1.获取servlet-api
public class HelloAction4 extends ActionSupport 
		implements ServletResponseAware,
				StrutsStatics{
	
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	
	public String showApi() { 
		/*
		
		// 1.ServletActionContext
		// ServletActionContext内部封装了ActionContext,StrutsStatic,便于我们调用
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		PageContext pageContext = ServletActionContext.getPageContext();
		HttpSession session = request.getSession();
		System.out.println("request: "+request);
		System.out.println("response: "+response);
		System.out.println("pageContext: "+pageContext);
		System.out.println("session: "+session);
		return NONE;
		
		*/
		
		
		HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(HTTP_REQUEST);
		System.out.println("request: "+request);
		
		
		/*
		 * 2.ServletResponseAware..等依赖于Struts拦截器
		 * <interceptor-ref name="servletConfig"/>	
	   	 * <interceptor name="servletConfig"     
	   	 * class="org.apache.struts2.interceptor.ServletConfigInterceptor"/> 
		 * 
		 */
		
	    
		System.out.println("response: "+response);
		return NONE;
	}

	

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}


	
}
