package com.bn.qa.webservice.restclient.specification;


public interface IPreemptiveAuthSpec {
	/**
     * Use preemptive http basic authentication. This means that the authentication details are sent in the request
     * header regardless if the server has challenged for authentication or not.
     *
     * @param userName The user name.
     * @param password The password.
     *
     * @return The Request specification
     */
    IRequestSpecification basic(String userName, String password);
}
