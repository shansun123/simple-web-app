package com.shansun.webapp.velocity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;

/**
 * @author: lanbo <br>
 * @version: 1.0 <br>
 * @date: 2012-8-10
 */
public class HelloVelocityServlet extends ClassVelocityServlet {
	private static final long	serialVersionUID	= 97107065117657385L;

	protected Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context ctx) {
		ctx.put("name", "xujun");
		return getTemplate("hello.vm");
	}
}
