package com.marktye

import dispatch._
import dispatch.meetup._
import net.liftweb.json.JsonAST._

object MeetupExample {
  
  val case_url = "chicagoscala"
  val case_id = "1391869"
  val mtye_id = "7134015"
  val june_case = "13539530"
  val july_case = "13546999"
  
  def api_key = System.getenv("MEETUP_API_KEY")
  
  val client = APIKeyClient(api_key)
  
  implicit def http:Http = new Http
  
  def printJs(response: (List[JValue], List[JValue]) ): Unit = {
    import net.liftweb.json.JsonDSL._
    val (results, meta) = response
    printJs(results)
  }

  def printJs(js: List[JValue]): Unit = {
    import net.liftweb.json.JsonDSL._
    println(pretty(render(js)))
  }

  object G {
    
    def byUrlname(name: String) = client.call( Groups.urlname(name) )
    
    def byMember(id: String) = client.call( Groups.member_id(id) )
    
    def idsByUrlname(name: String) = byUrlname(name)._1  map { _ \ "id" }
  }
  
  object E {
    
    def byGroup(id: String) = client.call( Events.group_id(id) )
  }
  
  object M {
    
	def self = client.call(Members.self)
 
    def byMember(id: String) = client.call( Members.member_id(id) )
  }
  
  object Rsvp extends RsvpBuilder(Map())
  private[marktye] class RsvpBuilder(params: Map[String, Any]) extends WriteMethod {
    private def param(key: String)(value: Any) = new RsvpBuilder(params + (key -> value))
    
    val event_id = param("event_id")_
    def yes = param("rsvp")("yes")
    def no = param("rsvp")("no")
    def maybe = param("rsvp")("maybe")
    val guests = param("guests")_
    val comments = param("comments")_
    val member_id = param("member_id")_
    
    def product = (_ : Request) / "rsvp" <<? params
  }
  
  object R {
    
    def byEvent(id: String) = client.call( Rsvps.event_id(id) )
    
    def namesByEvent(id: String) = byEvent(id)._1 map { _ \ "name" }

    def yesToEvent(id: String) = client.call( Rsvp.event_id(id).yes )
  
    def noToEvent(id: String) = client.call( Rsvp.event_id(id).no )

    def maybeToEvent(id: String) = client.call( Rsvp.event_id(id).maybe )
  }
  
  def main(args: Array[String]) {
    printJs(R.namesByEvent(june_case) )
  }
}
