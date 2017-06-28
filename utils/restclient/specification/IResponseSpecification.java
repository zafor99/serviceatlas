package com.bn.qa.webservice.restclient.specification;

import groovyx.net.http.ContentType;

import java.util.Map;

import org.hamcrest.Matcher;


public interface IResponseSpecification{

	/**
     * Expect that the response content conforms to one or more Hamcrest matchers. E.g.
     * <pre>
     * // Expect that the response content (body) contains the string "winning-numbers"
     * expect().content(containsString("winning-numbers")).when().get("/lotto");
     *
     * // Expect that the response content (body) contains the string "winning-numbers" and "winners"
     * expect().content(containsString("winning-numbers"), containsString("winners")).when().get("/lotto");
     * </pre>
     * @param matcher The hamcrest matcher that must response content must match.
     * @param additionalMatchers Optionally additional hamcrest matchers that must return <code>true</code>.
     * @return the response specification
     */
	IResponseSpecification content(Matcher<?> matcher, Matcher<?>...additionalMatchers);

    /**
     * Expect that the JSON or XML response content conforms to one or more Hamcrest matchers.<br>
     * <h3>JSON example</h3>
     * <p>
     * Assume that a GET request to "/lotto" returns a JSON response containing:
     * <pre>
     * { "lotto":{
     *   "lottoId":5,
     *   "winning-numbers":[2,45,34,23,7,5,3],
     *   "winners":[{
     *     "winnerId":23,
     *     "numbers":[2,45,34,23,3,5]
     *   },{
     *     "winnerId":54,
     *     "numbers":[52,3,12,11,18,22]
     *   }]
     *  }}
     * </pre>
     *
     * You can verify that the lottoId is equal to 5 like this:
     * <pre>
     * expect().content("lotto.lottoId", equalTo(5)).when().get("/lotto");
     * </pre>
     *
     * You can also verify that e.g. one of the the winning numbers is 45.
     * <pre>
     * expect().content("lotto.winning-numbers", hasItem(45)).when().get("/lotto");
     * </pre>
     *
     * Or both at the same time:
     * <pre>
     * expect().content("lotto.lottoId", equalTo(5)).and().content("lotto.winning-numbers", hasItem(45)).when().get("/lotto");
     * </pre>
     *
     * or a slightly short version:
     * <pre>
     * expect().content("lotto.lottoId", equalTo(5), "lotto.winning-numbers", hasItem(45)).when().get("/lotto");
     * </pre>
     * </p>
     * <h3>XML example</h3>
     * <p>
     * Assume that a GET request to "/xml" returns a XML response containing:
     * <pre>
     * &lt;greeting&gt;
     *    &lt;firstName&gt;John&lt;/firstName&gt;
     *    &lt;lastName&gt;Doe&lt;/lastName&gt;
     * &lt;/greeting&gt;
     * </pre>
     * </p>
     *
     * You can now verify that the firstName is equal to "John" like this:
     * <pre>
     * expect().content("greeting.firstName", equalTo("John")).when().get("/xml");
     * </pre>
     *
     * To verify both the first name and last name you can do like this:
     * <pre>
     * expect().content("greeting.firstName", equalTo("John")).and().content("greeting.lastName", equalTo("Doe")).when().get("/xml");
     * </pre>
     *
     * Or the slightly shorter version of:
     * <pre>
     * expect().content("greeting.firstName", equalTo("John"), "greeting.lastName", equalTo("Doe")).when().get("/xml");
     * </pre>
     * <h3>Notes</h3>
     * <p>
     * Note that if the response content type is not of type <tt>application/xml</tt> or <tt>application/json</tt> you
     * <i>cannot</i> use this verification.
     * </p>
     *
     * <p>
     * The only difference between the <code>content</code> and <code>body</code> methods are of syntactic nature.
     * </p>
     *
     * @param matcher The hamcrest matcher that must response content must match.
     * @param additionalKeyMatcherPairs Optionally additional hamcrest matchers that must return <code>true</code>.
     * @return the response specification
     */
	IResponseSpecification content(String key, Matcher<?> matcher, Object...additionalKeyMatcherPairs);

    /**
     * Expect that the response status code matches the given Hamcrest matcher. E.g.
     * <pre>
     * expect().statusCode(equalTo(200)).when().get("/something");
     * </pre>
     *
     * @param expectedStatusCode The expected status code matcher.
     * @return the response specification
     */
	IResponseSpecification statusCode(Matcher<Integer> expectedStatusCode);

    /**
     * Expect that the response status code matches an integer. E.g.
     * <pre>
     * expect().statusCode(200).when().get("/something");
     * </pre>
     *
     * This is the same as:
     * <pre>
     * expect().statusCode(equalTo(200)).when().get("/something");
     * </pre>
     * @param expectedStatusCode The expected status code.
     * @return the response specification
     */
	IResponseSpecification statusCode(int expectedStatusCode);

    /**
     * Expect that the response status line matches the given Hamcrest matcher. E.g.
     * <pre>
     * expect().statusLine(equalTo("No Content")).when().get("/something");
     * </pre>
     *
     * @param expectedStatusLine The expected status line matcher.
     * @return the response specification
     */
	IResponseSpecification statusLine(Matcher<String> expectedStatusLine);

    /**
     * Expect that the response status line matches the given String. E.g.
     * <pre>
     * expect().statusLine("No Content").when().get("/something");
     * </pre>
     *
     * This is the same as:
     * <pre>
     * expect().statusLine(equalTo("No Content")).when().get("/something");
     * </pre>
     *
     * @param expectedStatusLine The expected status line.
     * @return the response specification
     */
	IResponseSpecification statusLine(String expectedStatusLine);

    /**
     * Expect that response headers matches those specified in a Map.
     * <p>
     *  E.g. expect that the response of the GET request to "/something" contains header <tt>headerName1=headerValue1</tt>
     * and <tt>headerName2=headerValue2</tt>:
     * <pre>
     * Map expectedHeaders = new HashMap();
     * expectedHeaders.put("headerName1", "headerValue1"));
     * expectedHeaders.put("headerName2", "headerValue2");
     *
     * expect().response().headers(expectedHeaders).when().get("/something");
     * </pre>
     * </p>
     *
     * <p>
     * You can also use Hamcrest matchers:
     * <pre>
     * Map expectedHeaders = new HashMap();
     * expectedHeaders.put("Content-Type", containsString("charset=UTF-8"));
     * expectedHeaders.put("Content-Length", "160");
     *
     * expect().headers(expectedHeaders).when().get("/something");
     * </pre>
     * </p>
     *
     * @param expectedHeaders The Map of expected response headers
     * @return the response specification
     */
	IResponseSpecification headers(Map<String, Object> expectedHeaders);

    /**
     * Expect that response headers matches the supplied headers and values.
     * <p>
     * E.g. expect that the response of the GET request to "/something" contains header <tt>Pragma=no-cache</tt>
     * and <tt>Content-Encoding=gzip</tt>:
     * <pre>
     * expect().headers("Pragma", "no-cache", "Content-Encoding", "gzip").when().get("/something");
     * </pre>
     * </p>
     *
     * <p>
     * You can also use Hamcrest matchers:
     * <pre>
     * expect().response().headers("Content-Type", containsString("application/json"), "Pragma", equalsTo("no-cache")).when().get("/something");
     * </pre>
     *
     * and you can even mix string matching and hamcrest matching:
     * <pre>
     * expect().headers("Content-Type", containsString("application/json"), "Pragma", "no-cache").when().get("/something");
     * </pre>
     * </p>
     *
     * @param expectedHeaders A list of expected "header name" - "header value" pairs.
     * @return the response specification
     */
	IResponseSpecification headers(String firstExpectedHeaderName, Object...expectedHeaders);

    /**
     * Expect that a response header matches the supplied header name and hamcrest matcher.
     * <p>
     * E.g. expect that the response of the GET request to "/something" contains header <tt>Pragma=no-cache</tt>:
     * <pre>
     * expect().header("Pragma", containsString("no")).when().get("/something");
     * </pre>
     * </p>
     *
     * <p>
     * You can also expect several headers:
     * <pre>
     * expect().header("Pragma", equalsTo("no-cache")),and().header("Content-Encoding", containsString("zip")).when().get("/something");
     * </pre>
     * Also take a look at {@link #headers(String, Object...))} for a short version of passing multiple headers.
     * </p>
     *
     * @param headerName The name of the expected header
     * @param expectedValueMatcher The Hamcrest matcher that must conform to the value
     * @return the response specification
     */
	IResponseSpecification header(String headerName, Matcher<String> expectedValueMatcher);

    /**
     * Expect that a response header matches the supplied name and value.
     * <p>
     * E.g. expect that the response of the GET request to "/something" contains header <tt>Pragma=no-cache</tt>:
     * <pre>
     * expect().header("Pragma", "no-cache").when().get("/something");
     * </pre>
     * </p>
     *
     * <p>
     * You can also expect several headers:
     * <pre>
     * expect().header("Pragma", "no-cache"),and().header("Content-Encoding", "gzip").when().get("/something");
     * </pre>
     * Also take a look at {@link #headers(String, Object...))} for a short version of passing multiple headers.
     * </p>
     *
     * @param headerName The name of the expected header
     * @param expectedValue The value of the expected header
     * @return the response specification
     */
	IResponseSpecification header(String headerName, String expectedValue);

    /**
     * Expect that response cookies matches those specified in a Map.
     * <p>
     * E.g. expect that the response of the GET request to "/something" contains cookies <tt>cookieName1=cookieValue1</tt>
     * and <tt>cookieName2=cookieValue2</tt>:
     * <pre>
     * Map expectedCookies = new HashMap();
     * expectedCookies.put("cookieName1", "cookieValue1"));
     * expectedCookies.put("cookieName2", "cookieValue2");
     *
     * expect().response().cookies(expectedCookies).when().get("/something");
     * </pre>
     * </p>
     *
     * <p>
     * You can also use Hamcrest matchers:
     * <pre>
     * Map expectedCookies = new HashMap();
     * expectedCookies.put("cookieName1", containsString("Value1"));
     * expectedCookies.put("cookieName2", "cookieValue2");
     *
     * expect().cookies(expectedCookies).when().get("/something");
     * </pre>
     * </p>
     *
     * @param expectedCookies A Map of expected response cookies
     * @return the response specification
     */
	IResponseSpecification cookies(Map<String, Object> expectedCookies);

    /**
     * Expect that response cookies matches the supplied cookie names and values.
     * <p>
     * E.g. expect that the response of the GET request to "/something" contains cookies <tt>cookieName1=cookieValue1</tt>
     * and <tt>cookieName2=cookieValue2</tt>:
     * <pre>
     * expect().cookies("cookieName1", "cookieValue1", "cookieName2", "cookieValue2").when().get("/something");
     * </pre>
     * </p>
     *
     * <p>
     * You can also use Hamcrest matchers:
     * <pre>
     * expect().response().cookies("cookieName1", containsString("Value1"), "cookieName2", equalsTo("cookieValue2")).when().get("/something");
     * </pre>
     *
     * and you can even mix string matching and hamcrest matching:
     * <pre>
     * expect().cookies("cookieName1", containsString("Value1"), "cookieName2", "cookieValue2").when().get("/something");
     * </pre>
     * </p>
     *
     * @param expectedCookieNameValuePairs A list of expected "cookie name" - "cookie value" pairs.
     * @return the response specification
     */
	IResponseSpecification cookies(String firstExpectedCookieName, Object...expectedCookieNameValuePairs);

    /**
     * Expect that a response cookie matches the supplied cookie name and hamcrest matcher.
     * <p>
     * E.g. expect that the response of the GET request to "/something" contain cookie <tt>cookieName1=cookieValue1</tt>
     * <pre>
     * expect().cookie("cookieName1", containsString("Value1")).when().get("/something");
     * </pre>
     * </p>
     *
     * <p>
     * You can also expect several cookies:
     * <pre>
     * expect().cookie("cookieName1", equalsTo("cookieValue1")),and().cookie("cookieName2", containsString("Value2")).when().get("/something");
     * </pre>
     * Also take a look at {@link #cookies(String, Object...))} for a short version of passing multiple cookies.
     * </p>
     *
     * @param cookieName The name of the expected cookie
     * @param expectedValueMatcher The Hamcrest matcher that must conform to the value
     * @return the response specification
     */
	IResponseSpecification cookie(String cookieName, Matcher<String> expectedValueMatcher);

    /**
     * Expect that a response cookie matches the supplied name and value.
     * <p>
     * E.g. expect that the response of the GET request to "/something" contain cookie <tt>cookieName1=cookieValue1</tt>:
     * <pre>
     * expect().cookie("cookieName1", "cookieValue1").when().get("/something");
     * </pre>
     * </p>
     *
     * <p>
     * You can also expect several cookies:
     * <pre>
     * expect().cookie("cookieName1", "cookieValue1"),and().cookie("cookieName2", "cookieValue2").when().get("/something");
     * </pre>
     * Also take a look at {@link #cookies(String, Object...))} for a short version of passing multiple cookies.
     * </p>
     *
     * @param cookieName The name of the expected cookie
     * @param expectedValue The value of the expected cookie
     * @return the response specification
     */
	IResponseSpecification cookie(String cookieName, String expectedValue);

    /**
     * Set the root path of the response body so that you don't need to write the entire path for each expectation.
     * E.g. instead of writing:
     *
     * <pre>
     * expect().
     *          body("x.y.firstName", is(..)).
     *          body("x.y.lastName", is(..)).
     *          body("x.y.age", is(..)).
     *          body("x.y.gender", is(..)).
     * when().
     *          get(..);
     *</pre>
     *
     * you can use a root path and do:
     * <pre>
     * expect().
     *          rootPath("x.y").
     *          body("firstName", is(..)).
     *          body("lastName", is(..)).
     *          body("age", is(..)).
     *          body("gender", is(..)).
     * when().
     *          get(..);
     * </pre>
     *
     * Note that this method is exactly the same as {@link #root(String)}.
     *
     * @param rootPath The root path to use.
     */
	IResponseSpecification rootPath(String rootPath);

    /**
     * Set the root path of the response body so that you don't need to write the entire path for each expectation.
     * E.g. instead of writing:
     *
     * <pre>
     * expect().
     *          body("x.y.firstName", is(..)).
     *          body("x.y.lastName", is(..)).
     *          body("x.y.age", is(..)).
     *          body("x.y.gender", is(..)).
     * when().
     *          get(..);
     *</pre>
     *
     * you can use a root and do:
     * <pre>
     * expect().root("x.y").
     *          body("firstName", is(..)).
     *          body("lastName", is(..)).
     *          body("age", is(..)).
     *          body("gender", is(..)).
     * when().
     *          get(..);
     * </pre>
     *
     * Note that this method is exactly the same as {@link #rootPath(String)} but slightly shorter.
     *
     * @param rootPath The root path to use.
     */
	IResponseSpecification root(String rootPath);

    /**
     * Set the response content type to be <code>contentType</code>.
     * <p>Note that this will affect the way the response is decoded.
     * E,g. if you can't use JSON/XML matching (see e.g. {@link #body(String, Matcher, Object...)}) if you specify a
     * content-type of "text/plain". If you don't specify the response content type REST Assured will automatically try to
     * figure out which content type to use.</p>
     *
     * @param contentType The content type of the response.
     * @return the response specification
     */
	IResponseSpecification contentType(ContentType contentType);

    /**
     * Expect that the response body conforms to one or more Hamcrest matchers. E.g.
     * <pre>
     * // Expect that the response body (content) contains the string "winning-numbers"
     * expect().body(containsString("winning-numbers")).when().get("/lotto");
     *
     * // Expect that the response body (content) contains the string "winning-numbers" and "winners"
     * expect().body(containsString("winning-numbers"), containsString("winners")).when().get("/lotto");
     * </pre>
     * @param matcher The hamcrest matcher that must response body must match.
     * @param additionalMatchers Optionally additional hamcrest matchers that must return <code>true</code>.
     * @return the response specification
     */
	IResponseSpecification body(Matcher<?> matcher, Matcher<?>...additionalMatchers);

    /**
     * Expect that the JSON or XML response body conforms to one or more Hamcrest matchers.<br>
     * <h3>JSON example</h3>
     * <p>
     * Assume that a GET request to "/lotto" returns a JSON response containing:
     * <pre>
     * { "lotto":{
     *   "lottoId":5,
     *   "winning-numbers":[2,45,34,23,7,5,3],
     *   "winners":[{
     *     "winnerId":23,
     *     "numbers":[2,45,34,23,3,5]
     *   },{
     *     "winnerId":54,
     *     "numbers":[52,3,12,11,18,22]
     *   }]
     *  }}
     * </pre>
     *
     * You can verify that the lottoId is equal to 5 like this:
     * <pre>
     * expect().body("lotto.lottoId", equalTo(5)).when().get("/lotto");
     * </pre>
     *
     * You can also verify that e.g. one of the the winning numbers is 45.
     * <pre>
     * expect().body("lotto.winning-numbers", hasItem(45)).when().get("/lotto");
     * </pre>
     *
     * Or both at the same time:
     * <pre>
     * expect().body("lotto.lottoId", equalTo(5)).and().body("lotto.winning-numbers", hasItem(45)).when().get("/lotto");
     * </pre>
     *
     * or a slightly short version:
     * <pre>
     * expect().body("lotto.lottoId", equalTo(5), "lotto.winning-numbers", hasItem(45)).when().get("/lotto");
     * </pre>
     * </p>
     * <h3>XML example</h3>
     * <p>
     * Assume that a GET request to "/xml" returns a XML response containing:
     * <pre>
     * &lt;greeting&gt;
     *    &lt;firstName&gt;John&lt;/firstName&gt;
     *    &lt;lastName&gt;Doe&lt;/lastName&gt;
     * &lt;/greeting&gt;
     * </pre>
     * </p>
     *
     * You can now verify that the firstName is equal to "John" like this:
     * <pre>
     * expect().body("greeting.firstName", equalTo("John")).when().get("/xml");
     * </pre>
     *
     * To verify both the first name and last name you can do like this:
     * <pre>
     * expect().body("greeting.firstName", equalTo("John")).and().body("greeting.lastName", equalTo("Doe")).when().get("/xml");
     * </pre>
     *
     * Or the slightly shorter version of:
     * <pre>
     * expect().body("greeting.firstName", equalTo("John"), "greeting.lastName", equalTo("Doe")).when().get("/xml");
     * </pre>
     * <h3>Notes</h3>
     * <p>
     * Note that if the response body type is not of type <tt>application/xml</tt> or <tt>application/json</tt> you
     * <i>cannot</i> use this verification.
     * </p>
     *
     * <p>
     * The only difference between the <code>content</code> and <code>body</code> methods are of syntactic nature.
     * </p>
     *
     * @param matcher The hamcrest matcher that must response body must match.
     * @param additionalKeyMatcherPairs Optionally additional hamcrest matchers that must return <code>true</code>.
     * @return the response specification
     */
	IResponseSpecification body(String key, Matcher<?> matcher, Object...additionalKeyMatcherPairs);

    /**
     * Syntactic sugar, e.g.
     * <pre>
     * expect().body(containsString("OK")).when().get("/something");
     * </pre>
     *
     * is that same as:
     * <pre>
     * expect().body(containsString("OK")).get("/something");
     * </pre>
     *
     * @return the response specification
     */
	IResponseSpecification when();

    /**
     * Returns the request com.jayway.restassured.specification so that you can define the properties of the request.
     * <pre>
     * expect().body(containsString("OK")).given().parameters("param1", "value1").when().get("/something");
     * </pre>
     *
     * @return the request com.jayway.restassured.specification
     */
	IResponseSpecification given();

    /**
     * Syntactic sugar, e.g.
     * <pre>
     * expect().that().body(containsString("OK")).when().get("/something");
     * </pre>
     *
     * is that same as:
     * <pre>
     * expect().body(containsString("OK")).get("/something");
     * </pre>
     *
     * @return the response specification
     */
	IResponseSpecification that();

    /**
     * Returns the request com.jayway.restassured.specification so that you can define the properties of the request.
     * <pre>
     * expect().body(containsString("OK")).and().request().parameters("param1", "value1").when().get("/something");
     * </pre>
     *
     * @return the request com.jayway.restassured.specification
     */
	IResponseSpecification request();

    /**
     * Syntactic sugar, e.g.
     * <pre>
     * expect().response().body(containsString("OK")).when().get("/something");
     * </pre>
     *
     * is that same as:
     * <pre>
     * expect().body(containsString("OK")).get("/something");
     * </pre>
     *
     * @return the response specification
     */
	IResponseSpecification response();

    /**
     * Syntactic sugar, e.g.
     * <pre>
     * expect().body(containsString("OK")).and().body(containsString("something else")).when().get("/something");
     * </pre>
     *
     * is that same as:
     * <pre>
     * expect().body(containsString("OK")).body(containsString("something else")).when().get("/something");
     * </pre>
     *
     * @return the response specification
     */
	IResponseSpecification and();

    /**
     * Returns the request com.jayway.restassured.specification so that you can define the properties of the request.
     * <pre>
     * expect().body(containsString("OK")).and().with().request().parameters("param1", "value1").get("/something");
     * </pre>
     *
     * @return the request com.jayway.restassured.specification
     */
	IResponseSpecification with();

    /**
     * Syntactic sugar, e.g.
     * <pre>
     * expect().body(containsString("OK")).then().get("/something");
     * </pre>
     *
     * is that same as:
     * <pre>
     * expect().body(containsString("OK")).get("/something");
     * </pre>
     *
     * @return the response specification
     */
	IResponseSpecification then();

    /**
     * Syntactic sugar, e.g.
     * <pre>
     * expect().body(containsString("OK")).and().expect().body(containsString("something else")).when().get("/something");
     * </pre>
     *
     * is that same as:
     * <pre>
     * * expect().body(containsString("OK")).and().body(containsString("something else")).when().get("/something");
     * </pre>
     *
     * @return the response specification
     */
	IResponseSpecification expect();

    /**
     * Expect that the response matches an entire specification.
     * <pre>
     * ResponseSpecification responseSpec = new ResponseSpecBuilder().expectStatusCode(200).build();
     *
     * expect().
     *         spec(responseSpec).
     *         body("x.y.z", equalTo("something")).
     * when().
     *        get("/something");
     * </pre>
     *
     * This is useful when you want to reuse multiple different expectations in several tests.
     * <p>
     * The specification passed to this method is merged with the current specification. Note that the supplied specification
     * can overwrite data in the current specification. The following settings are overwritten:
     * <ul>
     *     <li>Content type</li>
     *     <li>Root path</
     *     <li>Status code</li>
     *     <li>Status line</li>
     * </ul>
     * The following settings are merged:
     * <ul>
     *     <li>Response body expectations</li>
     *     <li>Cookies</li>
     *     <li>Headers</li>
     * </ul>
     *
     * This method is the same as {@link #specification(ResponseSpecification)} but the name is a bit shorter.
     *
     * @param responseSpecificationToMerge The specification to merge with.
     * @return the response specification
     */
	IResponseSpecification spec(IResponseSpecification responseSpecificationToMerge);

    /**
     * Expect that the response matches an entire specification.
     * <pre>
     * ResponseSpecification responseSpec = new ResponseSpecBuilder().expectStatusCode(200).build();
     *
     * expect().
     *         specification(responseSpec).
     *         body("x.y.z", equalTo("something")).
     * when().
     *        get("/something");
     * </pre>
     *
     * This is useful when you want to reuse multiple different expectations in several tests.
     * <p>
     * The specification passed to this method is merged with the current specification. Note that the supplied specification
     * can overwrite data in the current specification. The following settings are overwritten:
     * <ul>
     *     <li>Content type</li>
     *     <li>Root path</
     *     <li>Status code</li>
     *     <li>Status line</li>
     * </ul>
     * The following settings are merged:
     * <ul>
     *     <li>Response body expectations</li>
     *     <li>Cookies</li>
     *     <li>Headers</li>
     * </ul>
     *
     *
     * This method is the same as {@link #spec(ResponseSpecification)} but the name is a bit longer.
     *
     * @param responseSpecificationToMerge The specification to merge with.
     * @return the response specification
     */
	IResponseSpecification specification(IResponseSpecification responseSpecificationToMerge);

}
