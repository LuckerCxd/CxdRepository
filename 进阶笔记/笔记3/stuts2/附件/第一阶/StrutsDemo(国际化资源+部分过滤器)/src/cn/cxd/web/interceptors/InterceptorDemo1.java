package cn.cxd.web.interceptors;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class InterceptorDemo1 extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println(getClass().getName() + "À¹½ØÇ°");
		String result = invocation.invoke();
		System.out.println(getClass().getName() + "À¹½Øºó");
		return null;
	}

}
