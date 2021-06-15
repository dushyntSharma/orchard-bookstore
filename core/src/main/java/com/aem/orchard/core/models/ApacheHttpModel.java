package com.aem.orchard.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.aem.orchard.core.services.ApacheHttp;

@Model(adaptables = Resource.class)
public class ApacheHttpModel {
	@Inject
	@Default(values = "India")
	String country;
	String name = "no";
	String capital = "no";
	String image = "no";

	@OSGiService
	ApacheHttp http;

	public String getData() {
		return http.getData(country);
	}

	public String getCountry() {
		return country;
	}

	public String getName() {
		return name;
	}

	public String getCapital() {
		return capital;
	}

	public String getImage() {
		return image;
	}

}
