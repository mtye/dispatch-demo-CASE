package com.marktye

import org.apache.http.client.{ResponseHandler, HttpClient}
import org.apache.http.client.methods.{HttpGet, HttpUriRequest}
import org.apache.http.impl.client.{BasicResponseHandler, DefaultHttpClient}

object ScalaHttpClientExample {
  
  def main(args: Array[String]) {

    val httpClient: HttpClient = new DefaultHttpClient()
    val httpGet: HttpGet = new HttpGet("http://www.google.com")
    val responseHandler = new BasicResponseHandler()
    
//    val body = httpClient.execute(httpGet, responseHandler) Doesn't compile
    val response = httpClient.execute(httpGet)
    
    println(responseHandler.handleResponse(response))
    
    httpClient.getConnectionManager().shutdown()
  }

}