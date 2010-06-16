package com.marktye

import dispatch._

object TwitterExample {
  
  object Search {
    
    val searchUrl: Request = :/ ("search.twitter.com")

    val trends = searchUrl / "trends.json"
    val search = searchUrl / "search.json"
    
    def query(q: String) = search <<? Map("q" -> q)
  }
  
  object Api {
    
    val apiUrl: Request = :/ ("api.twitter.com")

    val updateStatus = apiUrl / "1" / "statuses" / "update.xml"
    
    def tweet(status: String) = updateStatus << Map("status" -> status)
  }

  val scalaQuery = Search.query("Scala")

  val tweet = Api.tweet("Tweeting with a Dispatch-based Scala client!")
  val auth = tweet as ("fakemarktye", "fakepassword")

  val consoleHandler: Handler[java.io.PrintStream] = scalaQuery >>> System.out

  def main(args: Array[String]) {
    val http: HttpExecutor = new Http
    http(consoleHandler)
  }

}
