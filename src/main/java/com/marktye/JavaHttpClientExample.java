package com.marktye;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * This example demonstrates the use of the {@link ResponseHandler} to simplify 
 * the process of processing the HTTP response and releasing associated resources.
 */
public class JavaHttpClientExample {

    public final static void main(String[] args) throws Exception {
        
        HttpClient httpclient = new DefaultHttpClient();

        HttpGet httpget = new HttpGet("http://www.google.com/"); 

        // Create a response handler
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseBody = httpclient.execute(httpget, responseHandler);
        System.out.println(responseBody);
        
        // When HttpClient instance is no longer needed, 
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();        
    }
    
}