package com.shansun.webapp.velocity;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.apache.velocity.Template;
import org.apache.velocity.tools.view.JeeConfig;
import org.apache.velocity.tools.view.JeeServletConfig;
import org.apache.velocity.tools.view.ServletUtils;
import org.apache.velocity.tools.view.VelocityView;
import org.apache.velocity.tools.view.VelocityViewServlet;

/**
 * @author: lanbo <br>
 * @version: 1.0 <br>
 * @date: 2012-8-10
 */
public class ClassVelocityServlet extends VelocityViewServlet {
	private static final long		serialVersionUID				= 3775813144801219680L;
	private transient VelocityView	view;

	private static final String		ORG_APACHE_VELOCITY_PROPERTIES	= "org.apache.velocity.properties";
	private static final String		VELOCITY_PROPERTIES_PATH		= "/velocity/velocity.properties";
	private static String			VELOCITY_BASE_PATH				= "";

	{
		Properties props = new Properties();
		try {
			props.load(getClass().getResourceAsStream(VELOCITY_PROPERTIES_PATH));
			VELOCITY_BASE_PATH = props.getProperty("class.resource.loader.path");
			if (!VELOCITY_BASE_PATH.endsWith("/")) {
				VELOCITY_BASE_PATH += "/";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static class JeeServletConfigWrapper extends JeeServletConfig implements JeeConfig {

		public JeeServletConfigWrapper(ServletConfig servlet) {
			super(servlet);
		}

		public String getInitParameter(String name) {
			if (ORG_APACHE_VELOCITY_PROPERTIES.equals(name)) {
				return VELOCITY_PROPERTIES_PATH;
			}

			return super.getInitParameter(name);
		}

		public ServletContext getServletContext() {
			return super.getServletContext();
		}
	}

	protected VelocityView getVelocityView() {
		if (this.view == null) {
			setVelocityView(ServletUtils.getVelocityView(new JeeServletConfigWrapper(getServletConfig())));
			assert (this.view != null);
		}

		return this.view;
	}

	protected void setVelocityView(VelocityView view) {
		this.view = view;
	}

	protected Template getTemplate(String name) {
		return super.getTemplate(VELOCITY_BASE_PATH + name);
	}
}
