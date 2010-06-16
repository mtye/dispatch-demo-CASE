package com.marktye

import dispatch._

object GoogleExample {

  val googleapis: Request = :/ ("ajax.googleapis.com")

  val webSearch = googleapis / "ajax" / "services" / "search" / "web"

  val dispatchQuery = webSearch <<? Map("v" -> "1.0", "q" -> "scala+dispatch")

  val withReferer = dispatchQuery <:< Map("Referer" -> "http://blog.marktye.com")

  val consoleHandler: Handler[java.io.PrintStream] = withReferer >>> System.out

  def main(args: Array[String]) {
    val http: HttpExecutor = new Http
    http(consoleHandler)
  }
}
