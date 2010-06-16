import sbt._ 

class DispatchDemoProject(info: ProjectInfo) extends DefaultProject(info) {
  
  val dispatch = "net.databinder" %% "dispatch-http" % "0.7.4"


}
