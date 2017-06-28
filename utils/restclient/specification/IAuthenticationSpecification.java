package com.bn.qa.webservice.restclient.specification;


public interface IAuthenticationSpecification {
	 /**
     * Use http basic authentication.
     *
     * @param userName The user name.
     * @param password The password.
     * @return The Request specification
     */
    IRequestSpecification basic(String userName, String password);

    /**
     * Use http digest authentication.
     *
     * @param userName The user name.
     * @param password The password.
     * @return The Request specification
     */
    IRequestSpecification digest(String userName, String password);

    /**
     * Sets a certificate to be used for SSL authentication. See {@link Class#getResource(String)}
     * for how to get a URL from a resource on the classpath.
     *
     * @param certURL URL to a JKS keystore where the certificate is stored.
     * @param password  password to decrypt the keystore
     * @return Request specification
     */
    IRequestSpecification certificate(String certURL, String password);

    /**
     * Excerpt from the HttpBuilder docs:<br>
     * OAuth sign the request. Note that this currently does not wait for a WWW-Authenticate challenge before sending the the OAuth header.
     * All requests to all domains will be signed for this instance.
     * This assumes you've already generated an accessToken and secretToken for the site you're targeting.
     * For More information on how to achieve this, see the <a href="http://code.google.com/p/oauth-signpost/wiki/GettingStarted#Using_Signpost">Signpost documentation</a>.
     *
     * @param consumerKey
     * @param consumerSecret
     * @param accessToken
     * @param secretToken
     * @return The request com.jayway.restassured.specification
     */
    IRequestSpecification oauth(String consumerKey, String consumerSecret, String accessToken, String secretToken);

    /**
     * Returns the preemptive authentication view. This means that the authentication details are sent in the request
     * header regardless if the server has challenged for authentication or not.
     *
     * @return The preemptive authentication specification.
     */
    IPreemptiveAuthSpec preemptive();

    /**
     * Explicitly state that you don't which to use any authentication in this request. This is useful only in cases where you've
     * specified a default authentication scheme and you wish to override it for a single request.
     * @return The Request specification
     */
    IRequestSpecification none();
}
