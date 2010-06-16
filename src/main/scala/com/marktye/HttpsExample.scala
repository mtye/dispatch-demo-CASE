package com.marktye

import dispatch._

object HttpsExample {

  val fsecure = :/ ("msp.f-secure.com") / "web-test"

  val secure = fsecure.secure
  
  val consoleHandler: Handler[java.io.PrintStream] = secure >>> System.out
  
  def main(args: Array[String]) {

    val http: HttpExecutor = new Http
    http(consoleHandler)
  }
}
