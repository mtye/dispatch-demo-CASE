package com.marktye

import dispatch._

object DispatchExample {
  
  def main(args: Array[String]) {
    
    val http: HttpExecutor = new Http
    http(:/ ("www.google.com") >>> System.out)
  }

}
