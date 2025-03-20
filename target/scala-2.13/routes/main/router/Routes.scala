// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:9
  SimulationController_0: controllers.SimulationController,
  // @LINE:11
  Assets_1: controllers.Assets,
  // @LINE:15
  HomeController_2: controllers.HomeController,
  val prefix: String
) extends GeneratedRouter {

  @javax.inject.Inject()
  def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:9
    SimulationController_0: controllers.SimulationController,
    // @LINE:11
    Assets_1: controllers.Assets,
    // @LINE:15
    HomeController_2: controllers.HomeController
  ) = this(errorHandler, SimulationController_0, Assets_1, HomeController_2, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, SimulationController_0, Assets_1, HomeController_2, prefix)
  }

  private val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """simulation""", """controllers.SimulationController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """evaluate""", """controllers.SimulationController.evaluate(date:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    ("""GET""", this.prefix, """controllers.HomeController.index()"""),
    Nil
  ).foldLeft(Seq.empty[(String, String, String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String, String, String)]
    case l => s ++ l.asInstanceOf[List[(String, String, String)]]
  }}


  // @LINE:9
  private lazy val controllers_SimulationController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("simulation")))
  )
  private lazy val controllers_SimulationController_index0_invoker = createInvoker(
    SimulationController_0.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SimulationController",
      "index",
      Nil,
      "GET",
      this.prefix + """simulation""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )

  // @LINE:10
  private lazy val controllers_SimulationController_evaluate1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("evaluate")))
  )
  private lazy val controllers_SimulationController_evaluate1_invoker = createInvoker(
    SimulationController_0.evaluate(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SimulationController",
      "evaluate",
      Seq(classOf[String]),
      "GET",
      this.prefix + """evaluate""",
      """""",
      Seq()
    )
  )

  // @LINE:11
  private lazy val controllers_Assets_versioned2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""", encodeable=false)))
  )
  private lazy val controllers_Assets_versioned2_invoker = createInvoker(
    Assets_1.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """""",
      Seq()
    )
  )

  // @LINE:15
  private lazy val controllers_HomeController_index3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private lazy val controllers_HomeController_index3_invoker = createInvoker(
    HomeController_2.index(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:9
    case controllers_SimulationController_index0_route(params@_) =>
      call { 
        controllers_SimulationController_index0_invoker.call(SimulationController_0.index)
      }
  
    // @LINE:10
    case controllers_SimulationController_evaluate1_route(params@_) =>
      call(params.fromQuery[String]("date", None)) { (date) =>
        controllers_SimulationController_evaluate1_invoker.call(SimulationController_0.evaluate(date))
      }
  
    // @LINE:11
    case controllers_Assets_versioned2_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned2_invoker.call(Assets_1.versioned(path, file))
      }
  
    // @LINE:15
    case controllers_HomeController_index3_route(params@_) =>
      call { 
        controllers_HomeController_index3_invoker.call(HomeController_2.index())
      }
  }
}
