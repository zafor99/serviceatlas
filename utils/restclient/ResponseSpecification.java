package com.bn.qa.webservice.restclient;

import groovyx.net.http.ContentType;

import java.util.Map;

import org.hamcrest.Matcher;

import com.bn.qa.webservice.restclient.specification.IResponseSpecification;

public class ResponseSpecification implements IResponseSpecification {

	@Override
	public IResponseSpecification content(Matcher<?> matcher,
			Matcher<?>... additionalMatchers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification content(String key, Matcher<?> matcher,
			Object... additionalKeyMatcherPairs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification statusCode(Matcher<Integer> expectedStatusCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification statusCode(int expectedStatusCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification statusLine(Matcher<String> expectedStatusLine) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification statusLine(String expectedStatusLine) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification headers(Map<String, Object> expectedHeaders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification headers(String firstExpectedHeaderName,
			Object... expectedHeaders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification header(String headerName,
			Matcher<String> expectedValueMatcher) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification header(String headerName, String expectedValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification cookies(Map<String, Object> expectedCookies) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification cookies(String firstExpectedCookieName,
			Object... expectedCookieNameValuePairs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification cookie(String cookieName,
			Matcher<String> expectedValueMatcher) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification cookie(String cookieName, String expectedValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification rootPath(String rootPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification root(String rootPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification contentType(ContentType contentType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification body(Matcher<?> matcher,
			Matcher<?>... additionalMatchers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification body(String key, Matcher<?> matcher,
			Object... additionalKeyMatcherPairs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification when() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification given() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification that() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification request() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification response() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification and() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification with() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification then() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification expect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification spec(
			IResponseSpecification responseSpecificationToMerge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification specification(
			IResponseSpecification responseSpecificationToMerge) {
		// TODO Auto-generated method stub
		return null;
	}

}
