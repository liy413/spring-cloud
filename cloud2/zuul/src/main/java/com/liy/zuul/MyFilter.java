package com.liy.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext cc = RequestContext.getCurrentContext();
        HttpServletRequest request = cc.getRequest();
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        if (!name.equals("liy")||!("123").equals(pwd)) {
            cc.setResponseStatusCode(401);
            cc.setResponseBody("请求非法!!");
            cc.setSendZuulResponse(false);
            cc.addZuulResponseHeader("content-type", "html/text;charset=utf-8");
        }
        return null;
    }
}
