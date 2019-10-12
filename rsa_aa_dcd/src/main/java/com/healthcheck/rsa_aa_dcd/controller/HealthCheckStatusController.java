package com.healthcheck.rsa_aa_dcd.controller;

import java.sql.Timestamp;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.HealthCheckDto;

@RestController
public class HealthCheckStatusController {

	@RequestMapping(value="/")
	public String hello12(){
		return "Spring project is running";
	}

	@RequestMapping(value="/healthCheck")
	public HealthCheckDto checkStatus(){
		HealthCheckDto dto =new HealthCheckDto();

		String url ="https://bitbucket.org/cashedev/ipaybridge_prod/src/master/";
		boolean isServerRunning =executeHttpGetReq(url);

		dto.setServerStatus(isServerRunning ? "UP":"Down");
		dto.setDataCenter("LRK");
		dto.setTime(new Timestamp(System.currentTimeMillis()));
		dto.setUrl(url);

		return dto;
	}


	@SuppressWarnings("deprecation")
	@Deprecated
	public boolean executeHttpGetReq(String url){
		HttpGet request = new HttpGet(url);
		ResponseHandler<String> handler = new BasicResponseHandler();
		final HttpParams httpParams = new BasicHttpParams();
	    HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
	    HttpClient httpClient = new DefaultHttpClient(httpParams);
	    //HttpClient httpClient = new DefaultHttpClient();
		HttpResponse response;
		try {
			response = httpClient.execute(request);
			System.out.println("Status "+response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode()==200){
				String responseText = handler.handleResponse(response);
				if(responseText.toLowerCase().contains("successfully acquired a database connection")) {
					return true;	
				}
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			httpClient.getConnectionManager().shutdown();
		}
		return false;
	}

}
