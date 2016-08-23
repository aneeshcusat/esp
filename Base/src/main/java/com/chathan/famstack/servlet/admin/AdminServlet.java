package com.chathan.famstack.servlet.admin;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;

import com.chathan.famstack.rest.services.BaseRestFamStackService;

public class AdminServlet extends HttpServlet {

	private static final Logger logger = LogManager.getLogger();
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse respose) throws ServletException, IOException {
		ServletOutputStream out = respose.getOutputStream();
		out.println("<html><title>");
		out.println(new StringBuilder().append("FamStack Service ").toString());
		out.println("</title>");
		String path = request.getRequestURI();
		Class componentClass = null;
		String compPath = request.getParameter("component");
		String mehtodName = request.getParameter("method");
		ClassLoader classLoader = this.getClass().getClassLoader();
		if (compPath != null && compPath.length() > 0) {
			try {
				componentClass = classLoader.loadClass(compPath);
			} catch (ClassNotFoundException e) {
				logger.error("class not found");
			}
		}
		/*
		 * int pathLength = path.indexOf("admin") + "admin".length();
		 * 
		 * if (pathLength < path.length()) { compPath =
		 * path.substring(pathLength, path.length()); compPath =
		 * compPath.replaceAll("/", ""); }
		 */
		if (componentClass != null && mehtodName != null && mehtodName.length() > 0) {
			try {
				out.println(mehtodName);
			} catch (SecurityException e) {
				e.printStackTrace();
			}

		} else if (componentClass != null) {

			try {

				Method[] methods = componentClass.getMethods();
				if (methods != null && methods.length > 0) {
					out.println("<body><h1>");
					out.println(new StringBuilder().append("FamStack Service ").toString());
					out.println("<ul>");
					for (Method method : methods) {
						if (method.isAnnotationPresent(Path.class)) {
							out.println("<li>");
							out.println("<a href=\"" + request.getContextPath() + "/admin/?component="
									+ componentClass.getName() + "&method=" + method.getName() + "\">");
							out.println(method.getName());
							out.println("</a>");
							out.println("</li>");
						}
					}
					out.println("</ul>");
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			return;
		} else {
			Reflections reflections = new Reflections("com.chathan");

			Set<Class<? extends BaseRestFamStackService>> allClasses = reflections
					.getSubTypesOf(BaseRestFamStackService.class);

			if (allClasses != null && !allClasses.isEmpty()) {
				out.println("<body><h1>");
				out.println(new StringBuilder().append("FamStack Service ").toString());
				out.println("</h1>");
				for (Class component : allClasses) {
					out.println("<a href=\"" + request.getContextPath() + "/admin/?component=" + component.getName()
							+ "\">");
					out.println(component.getSimpleName());
					out.println("</a><br/>");
				}
			} else {
				out.println("<body><h1>");
				out.println(new StringBuilder().append("FamStack Service ").append("Not Available.").toString());
				out.println("</h1>");
			}

			out.println("</body></html>");
		}
	}

}
