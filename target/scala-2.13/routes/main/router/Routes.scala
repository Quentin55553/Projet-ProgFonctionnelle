// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:10
  HomeController_0: controllers.HomeController,
  val prefix: String
) extends GeneratedRouter {

  @javax.inject.Inject()
  def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:10
    HomeController_0: controllers.HomeController
  ) = this(errorHandler, HomeController_0, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_0, prefix)
  }

  private val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """simulation""", """controllers.HomeController.simulationPage"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """evaluate-date""", """controllers.HomeController.evaluateDate"""),
    Nil
  ).foldLeft(Seq.empty[(String, String, String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String, String, String)]
    case l => s ++ l.asInstanceOf[List[(String, String, String)]]
  }}


  // @LINE:10
  private lazy val controllers_HomeController_simulationPage0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("simulation")))
  )
  private lazy val controllers_HomeController_simulationPage0_invoker = createInvoker(
    HomeController_0.simulationPage,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "simulationPage",
      Nil,
      "GET",
      this.prefix + """simulation""",
      """ Map static resources from the /public folder to the /assets URL path
 Routes""",
      Seq()
    )
  )

  // @LINE:11
  private lazy val controllers_HomeController_evaluateDate1_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("evaluate-date")))
  )
  private lazy val controllers_HomeController_evaluateDate1_invoker = createInvoker(
    HomeController_0.evaluateDate,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "evaluateDate",
      Nil,
      "POST",
      this.prefix + """evaluate-date""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:10
    case controllers_HomeController_simulationPage0_route(params@_) =>
      call { 
        controllers_HomeController_simulationPage0_invoker.call(HomeController_0.simulationPage)
      }
  
    // @LINE:11
    case controllers_HomeController_evaluateDate1_route(params@_) =>
      call { 
        controllers_HomeController_evaluateDate1_invoker.call(HomeController_0.evaluateDate)
      }
  }
}
