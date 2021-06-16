package com.aem.orchard.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(service = Servlet.class)
@SlingServletPaths(value = { "/bin/aemTestServlet"})
public class RegisterData extends SlingAllMethodsServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(RegisterData.class);

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		String data = request.getParameter("data");
		System.out.println(data);
		JSONParser parser = new JSONParser();
		JSONObject jobj = null;
		try {
			jobj = (JSONObject) parser.parse(data);
			String firstname = (String) jobj.get("firstname");
			String address = (String) jobj.get("address");
			String gender = (String) jobj.get("gender");

			System.out.println(firstname + "-" + address + "-" + gender);
			LOG.info(firstname, address, gender);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
