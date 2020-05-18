package com.poc.alexa.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.dispatcher.request.handler.impl.LaunchRequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;

public class InvokeDefaultPipeline implements LaunchRequestHandler {

	/**
	 * Returns true if the handler can dispatch the current request
	 * @param handlerInput input to the request handler
	 * @return true if the handler is capable of handling the current request and/or state
	 */
	@Override
	public boolean canHandle(HandlerInput handlerInput,LaunchRequest launchRequest) {
		return handlerInput.matches(requestType(LaunchRequest.class));
	}

	/**
	 * Handles the request.
	 *
	 * @param input         input to the request handler
	 * @param launchRequest LaunchRequest request
	 * @return output from the handler.
	 */
	@Override public Optional<Response> handle(HandlerInput input, LaunchRequest launchRequest) {
			String output = "";
			try {
				URL url = new URL(
						"http://ritresh:11337d405bbc978293dfd6656e247ef3a2@52.168.3.18:8080/job/alexabuild/buildWithParameters?GITHUB_PARAM=false");
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("Accept", "application/json");
				int code = con.getResponseCode();
				System.out.println(code);
				output = "Request triggered , Response code is " + code;
			} catch (Exception e) {
				e.printStackTrace();
				output = "Caught exception while triggering alexa build " + e.getMessage();
			}
			return input.getResponseBuilder().withSpeech(output).build();
	}
}
