package com.pyshankov.microservices.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by pyshankov on 01.11.16.
 */
@Component
public class LocationPostFilter extends ZuulFilter {


    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1002;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();

        response.setHeader("Location", request.getRequestURL().toString());


        return null;
    }

}
