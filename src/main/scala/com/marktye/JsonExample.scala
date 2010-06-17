package com.marktye

import dispatch._
import dispatch.liftjson.Js._
import net.liftweb.json.JsonAST._
import net.liftweb.json.JsonDSL._

object JsonExample {
  
  import TwitterExample.scalaQuery
  
  val writer = new java.io.PrintWriter(System.out)
  
  def prettyPrint(js: JValue) = pretty(render(js), writer)
  
  def filter(js: JValue) = pretty(render( (js \ "results") ), writer)
  
  val jsonHandler: Handler[java.io.PrintWriter] = scalaQuery ># filter

  val rawJson: Handler[JValue] = scalaQuery ># { x => x }

  def main(args: Array[String]) {
    val http: HttpExecutor = new Http
    http(jsonHandler)
  }

}