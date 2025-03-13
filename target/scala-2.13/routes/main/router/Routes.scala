// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:7
  HomeController_0: controllers.HomeController,
  // @LINE:10
  Assets_1: controllers.Assets,
  // @LINE:13
  PortfolioControllerTest_2: controllers.PortfolioControllerTest,
  val prefix: String
) extends GeneratedRouter {

  @javax.inject.Inject()
  def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:7
    HomeController_0: controllers.HomeController,
    // @LINE:10
    Assets_1: controllers.Assets,
    // @LINE:13
    PortfolioControllerTest_2: controllers.PortfolioControllerTest
  ) = this(errorHandler, HomeController_0, Assets_1, PortfolioControllerTest_2, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_0, Assets_1, PortfolioControllerTest_2, prefix)
  }

  private val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """portfolio/""" + "$" + """userId<[^/]+>""", """controllers.PortfolioControllerTest.getPortfolio(userId:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """portfolio/add/""" + "$" + """userId<[^/]+>/""" + "$" + """symbol<[^/]+>/""" + "$" + """quantity<[^/]+>""", """controllers.PortfolioControllerTest.addAsset(userId:String, symbol:String, quantity:Int)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """portfolio/remove/""" + "$" + """userId<[^/]+>/""" + "$" + """symbol<[^/]+>/""" + "$" + """quantity<[^/]+>""", """controllers.PortfolioControllerTest.removeAsset(userId:String, symbol:String, quantity:Int)"""),
    Nil
  ).foldLeft(Seq.empty[(String, String, String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String, String, String)]
    case l => s ++ l.asInstanceOf[List[(String, String, String)]]
  }}


  // @LINE:7
  private lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_0.index(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:10
  private lazy val controllers_Assets_versioned1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""", encodeable=false)))
  )
  private lazy val controllers_Assets_versioned1_invoker = createInvoker(
    Assets_1.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )

  // @LINE:13
  private lazy val controllers_PortfolioControllerTest_getPortfolio2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("portfolio/"), DynamicPart("userId", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_PortfolioControllerTest_getPortfolio2_invoker = createInvoker(
    PortfolioControllerTest_2.getPortfolio(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PortfolioControllerTest",
      "getPortfolio",
      Seq(classOf[String]),
      "GET",
      this.prefix + """portfolio/""" + "$" + """userId<[^/]+>""",
      """ Portfolio API""",
      Seq()
    )
  )

  // @LINE:14
  private lazy val controllers_PortfolioControllerTest_addAsset3_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("portfolio/add/"), DynamicPart("userId", """[^/]+""", encodeable=true), StaticPart("/"), DynamicPart("symbol", """[^/]+""", encodeable=true), StaticPart("/"), DynamicPart("quantity", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_PortfolioControllerTest_addAsset3_invoker = createInvoker(
    PortfolioControllerTest_2.addAsset(fakeValue[String], fakeValue[String], fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PortfolioControllerTest",
      "addAsset",
      Seq(classOf[String], classOf[String], classOf[Int]),
      "POST",
      this.prefix + """portfolio/add/""" + "$" + """userId<[^/]+>/""" + "$" + """symbol<[^/]+>/""" + "$" + """quantity<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:15
  private lazy val controllers_PortfolioControllerTest_removeAsset4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("portfolio/remove/"), DynamicPart("userId", """[^/]+""", encodeable=true), StaticPart("/"), DynamicPart("symbol", """[^/]+""", encodeable=true), StaticPart("/"), DynamicPart("quantity", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_PortfolioControllerTest_removeAsset4_invoker = createInvoker(
    PortfolioControllerTest_2.removeAsset(fakeValue[String], fakeValue[String], fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PortfolioControllerTest",
      "removeAsset",
      Seq(classOf[String], classOf[String], classOf[Int]),
      "POST",
      this.prefix + """portfolio/remove/""" + "$" + """userId<[^/]+>/""" + "$" + """symbol<[^/]+>/""" + "$" + """quantity<[^/]+>""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:7
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_0.index())
      }
  
    // @LINE:10
    case controllers_Assets_versioned1_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned1_invoker.call(Assets_1.versioned(path, file))
      }
  
    // @LINE:13
    case controllers_PortfolioControllerTest_getPortfolio2_route(params@_) =>
      call(params.fromPath[String]("userId", None)) { (userId) =>
        controllers_PortfolioControllerTest_getPortfolio2_invoker.call(PortfolioControllerTest_2.getPortfolio(userId))
      }
  
    // @LINE:14
    case controllers_PortfolioControllerTest_addAsset3_route(params@_) =>
      call(params.fromPath[String]("userId", None), params.fromPath[String]("symbol", None), params.fromPath[Int]("quantity", None)) { (userId, symbol, quantity) =>
        controllers_PortfolioControllerTest_addAsset3_invoker.call(PortfolioControllerTest_2.addAsset(userId, symbol, quantity))
      }
  
    // @LINE:15
    case controllers_PortfolioControllerTest_removeAsset4_route(params@_) =>
      call(params.fromPath[String]("userId", None), params.fromPath[String]("symbol", None), params.fromPath[Int]("quantity", None)) { (userId, symbol, quantity) =>
        controllers_PortfolioControllerTest_removeAsset4_invoker.call(PortfolioControllerTest_2.removeAsset(userId, symbol, quantity))
      }
  }
}
