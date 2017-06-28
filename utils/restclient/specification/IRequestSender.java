package com.bn.qa.webservice.restclient.specification;



public interface IRequestSender {
	 /**
	   * Perform a GET request to a <code>path</code>. Normally the path doesn't have to be fully-qualified e.g. you don't need to
	   * specify the path as <tt>http://localhost:8080/path</tt>. In this case it's enough to use <tt>/path</tt>.
	   *
	   * @param path The path to send the request to.
	   * @return The response of the request. The response can only be returned if you don't use any REST Assured response expectations.
	   */
	  IResponse get(String path);

	  /**
	   * Perform a POST request to a <code>path</code>. Normally the path doesn't have to be fully-qualified e.g. you don't need to
	   * specify the path as <tt>http://localhost:8080/path</tt>. In this case it's enough to use <tt>/path</tt>.
	   *
	   * @param path The path to send the request to.
	   * @return The response of the request. The response can only be returned if you don't use any REST Assured response expectations.
	   */
	  IResponse post(String path);

	  /**
	   * Perform a PUT request to a <code>path</code>. Normally the path doesn't have to be fully-qualified e.g. you don't need to
	   * specify the path as <tt>http://localhost:8080/path</tt>. In this case it's enough to use <tt>/path</tt>.
	   *
	   * @param path The path to send the request to.
	   * @return The response of the request. The response can only be returned if you don't use any REST Assured response expectations.
	   */
	  IResponse put(String path);

	  /**
	   * Perform a DELETE request to a <code>path</code>. Normally the path doesn't have to be fully-qualified e.g. you don't need to
	   * specify the path as <tt>http://localhost:8080/path</tt>. In this case it's enough to use <tt>/path</tt>.
	   *
	   * @param path The path to send the request to.
	   * @return The response of the request. The response can only be returned if you don't use any REST Assured response expectations.
	   */
	  IResponse delete(String path);

	  /**
	   * Perform a HEAD request to a <code>path</code>. Normally the path doesn't have to be fully-qualified e.g. you don't need to
	   * specify the path as <tt>http://localhost:8080/path</tt>. In this case it's enough to use <tt>/path</tt>.
	   *
	   * @param path The path to send the request to.
	   * @return The response of the request. The response can only be returned if you don't use any REST Assured response expectations.
	   */
	  IResponse head(String path);
}
