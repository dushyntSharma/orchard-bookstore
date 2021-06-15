package com.aem.orchard.core.services;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
/*import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;*/
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = ApacheHttp.class, immediate = true)
public class ApacheHttpImpl implements ApacheHttp {
	private final Logger logger = LoggerFactory.getLogger(ApacheHttpImpl.class);

	@Activate
	public void activate() {
		logger.info("ApacheHttpImpl is activated");
	}

	@Override
	public String getData(String country) {
		// Create an HttpClient object
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// instantiate the response handler
		ResponseHandler<String> responseHandler = new MyResponseHandler();

		// Create an HttpGet object
		HttpGet httpget = new HttpGet("https://restcountries.eu/rest/v2/name/" + country + "?fullText=true");

		// Execute the Get request by passing the response handler object and HttpGet
		// object
		String httpresponse = null;
		try {
			httpresponse = httpclient.execute(httpget, responseHandler);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return httpresponse;
	}

	/*
	 * private Country jsonToObj(String httpresponse) { JSONParser jsonParser = new
	 * JSONParser(); String jsonData = httpresponse; try { JSONArray jsonArray =
	 * (JSONArray) jsonParser.parse(jsonData); Iterator<JSONObject> countries =
	 * jsonArray.iterator(); JSONObject country; if (countries.hasNext()) { country
	 * = countries.next(); String name = (String) country.get("name"); String
	 * capital = (String) country.get("capital"); String image = (String)
	 * country.get("image"); logger.info(name + " " + capital + " " + image); return
	 * new Country(name, capital, image); } } catch (ParseException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } return null; }
	 */
}

class MyResponseHandler implements ResponseHandler<String> {

	public String handleResponse(final HttpResponse response) throws IOException {

		// Get the status of the response
		int status = response.getStatusLine().getStatusCode();
		if (status >= 200 && status < 300) {
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				return "";
			} else {
				return EntityUtils.toString(entity);
			}

		} else {
			return "" + status;
		}
	}
}
