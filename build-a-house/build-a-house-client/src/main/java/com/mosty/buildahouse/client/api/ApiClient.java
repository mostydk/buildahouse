package com.mosty.buildahouse.client.api;

import java.util.function.Function;

import elemental2.core.Global;
import elemental2.dom.DomGlobal;
import elemental2.dom.Headers;
import elemental2.dom.RequestInit;
import elemental2.promise.Promise;
import jsinterop.base.JsPropertyMap;

public class ApiClient {
	public static <T> Promise<T> getResource(String path, Function<Object, T> responseMapper) {
		Headers headers = new Headers();
		headers.append("Accept", "application/json");
		
		RequestInit request = RequestInit.create();
		request.setMethod("GET");
		request.setHeaders(headers);
		
		return DomGlobal.fetch(path, request)
		.then(response -> {
			if (response.status < 200 || response.status >= 300)
				return Promise.reject("Received status code " + response.status);
			
			return response.json();
		}).then(json -> {
			return Promise.resolve(responseMapper.apply(json));
		});
	}
	
	public static <T> Promise<T> postResource(String path, JsPropertyMap<Object> body, Function<Object, T> responseMapper) {
		Headers headers = new Headers();
		headers.append("Content-Type", "application/json");
		headers.append("Accept", "application/json");
		
		RequestInit request = RequestInit.create();
		request.setMethod("POST");
		request.setHeaders(headers);
		request.setBody(Global.JSON.stringify(body));
		
		return DomGlobal.fetch(path, request)
		.then(response -> {
			if (!response.ok)
				return Promise.reject("Received status code " + response.status);
			
			return response.json();
		})
		.then(json -> {
			return Promise.resolve(responseMapper.apply(json));
		});
	}
	
	public static Promise<Void> putResource(String path, JsPropertyMap<Object> body) {
		Headers headers = new Headers();
		headers.append("Content-Type", "application/json");
		
		RequestInit request = RequestInit.create();
		request.setMethod("PUT");
		request.setHeaders(headers);
		request.setBody(Global.JSON.stringify(body));
		
		return DomGlobal.fetch(path, request)
		.then(response -> {
			if (!response.ok)
				return Promise.reject("Received status code " + response.status);
			
			return Promise.resolve((Void)null);
		});
	}
	
	public static Promise<Void> deleteResource(String path) {
		RequestInit request = RequestInit.create();
		request.setMethod("DELETE");
		
		return DomGlobal.fetch(path, request)
		.then(response -> {
			if (!response.ok)
				return Promise.reject("Received status code " + response.status);
			
			return Promise.resolve((Void)null);
		});
	}
}
