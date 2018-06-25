package com.pyshankov.microservices.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.pyshankov.microservices.UserService;
import com.pyshankov.microservices.hazelcast.cache.HazelcastClientTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by pyshankov on 01.11.16.
 */
@Component
public class TokenPreFilter extends ZuulFilter {

    public static final String HEADER_STRING = "Authorization";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HazelcastClientTemplate hazelcastClientTemplate;

    @Autowired
    private UserService userService;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1001;
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

        String header = request.getHeader(HEADER_STRING);
        ctx.addZuulRequestHeader(HEADER_STRING, header);

        if (hazelcastClientTemplate.getContainsUserInCacheByToken(header)) {
            return null;
        } else {
            setFailedRequest("unauthorized", 401);
        }
        return null;
    }

    private void setFailedRequest(String body, int code) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(code);
        ctx.setResponseBody(body);
        ctx.setSendZuulResponse(false);
    }
}
