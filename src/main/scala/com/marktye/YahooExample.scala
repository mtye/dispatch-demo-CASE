package com.marktye

import dispatch._

object YahooExample {

  val yahoo: Request = :/ ("api.search.yahoo.com")
  
  val webSearch = yahoo / "WebSearchService" / "V1" / "webSearch"
    
  val dispatchQuery = webSearch << Map("appid" -> "YahooDemo", "query" -> "dispatch")

  def console = {
    val consoleHandler: Handler[java.io.PrintStream] = dispatchQuery >>> System.out
    (new Http)(consoleHandler)
  }
  
  def asString = {
    val as_str: Handler[String] = dispatchQuery.as_str
    val result = (new Http)(as_str)
    println(result)
  }
  
  def asSource = {
    val as_source: Handler[io.Source] = dispatchQuery.as_source
    val result = (new Http)(as_source)
    result.getLines.foreach(println)
  }
  
  def stream = {
    def streamPrinter(is: java.io.InputStream): Unit = {
      val ch = is.read
      if (ch > -1) {
        print(ch.toChar)
        streamPrinter(is)
      }
      is.close
    }
    val streamHandler: Handler[Unit] = dispatchQuery >> { streamPrinter(_) }
    (new Http)(streamHandler)
  }
  
  def count {
    val stringCounter: Handler[Int] = dispatchQuery >- { _.size }
    val count: Int = (new Http)(stringCounter)
    println(count)
  }

  def source {
    def printLines(s: io.Source) = s.getLines.foreach(print)
    val sourceHandler: Handler[Unit] = dispatchQuery >~ printLines
    (new Http)(sourceHandler)
  }
  
  def xml {
    import scala.xml._
    def displayUrl(e: Elem) = e \\ "DisplayUrl"
    val xmlHandler: Handler[NodeSeq] = dispatchQuery <> displayUrl
    (new Http)(xmlHandler)
  }
  
  def main(args: Array[String]) {
    console
  }
}
