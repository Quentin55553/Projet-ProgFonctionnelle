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
  HomeController_4: controllers.HomeController,
  // @LINE:10
  Assets_3: controllers.Assets,
  // @LINE:13
  UserController_2: controllers.UserController,
  // @LINE:17
  PortfolioController_5: controllers.PortfolioController,
  // @LINE:21
  PriceController_1: controllers.PriceController,
  // @LINE:24
  SimulationController2_0: controllers.SimulationController2,
  val prefix: String
) extends GeneratedRouter {

  @javax.inject.Inject()
  def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:7
    HomeController_4: controllers.HomeController,
    // @LINE:10
    Assets_3: controllers.Assets,
    // @LINE:13
    UserController_2: controllers.UserController,
    // @LINE:17
    PortfolioController_5: controllers.PortfolioController,
    // @LINE:21
    PriceController_1: controllers.PriceController,
    // @LINE:24
    SimulationController2_0: controllers.SimulationController2
  ) = this(errorHandler, HomeController_4, Assets_3, UserController_2, PortfolioController_5, PriceController_1, SimulationController2_0, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_4, Assets_3, UserController_2, PortfolioController_5, PriceController_1, SimulationController2_0, prefix)
  }

  private val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """register""", """controllers.UserController.register"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """login""", """controllers.UserController.login"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """userInfo""", """controllers.UserController.getUserInfo"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """portfolios/""" + "$" + """userId<[^/]+>""", """controllers.PortfolioController.getPortfolios(userId:Int)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """createPortfolio""", """controllers.PortfolioController.createPortfolio"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """addAsset""", """controllers.PortfolioController.addAsset"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """removeAsset""", """controllers.PortfolioController.removeAsset"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """getPrice""", """controllers.PriceController.getPrice"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """getHistoricalData""", """controllers.PriceController.getHistoricalData"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """simulate""", """controllers.SimulationController2.simulatePortfolio"""),
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
    HomeController_4.index(),
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
    Assets_3.versioned(fakeValue[String], fakeValue[Asset]),
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
  private lazy val controllers_UserController_register2_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("register")))
  )
  private lazy val controllers_UserController_register2_invoker = createInvoker(
    UserController_2.register,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "register",
      Nil,
      "POST",
      this.prefix + """register""",
      """ Authentification des utilisateurs""",
      Seq()
    )
  )

  // @LINE:14
  private lazy val controllers_UserController_login3_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("login")))
  )
  private lazy val controllers_UserController_login3_invoker = createInvoker(
    UserController_2.login,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "login",
      Nil,
      "POST",
      this.prefix + """login""",
      """""",
      Seq()
    )
  )

  // @LINE:16
  private lazy val controllers_UserController_getUserInfo4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("userInfo")))
  )
  private lazy val controllers_UserController_getUserInfo4_invoker = createInvoker(
    UserController_2.getUserInfo,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "getUserInfo",
      Nil,
      "GET",
      this.prefix + """userInfo""",
      """""",
      Seq()
    )
  )

  // @LINE:17
  private lazy val controllers_PortfolioController_getPortfolios5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("portfolios/"), DynamicPart("userId", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_PortfolioController_getPortfolios5_invoker = createInvoker(
    PortfolioController_5.getPortfolios(fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PortfolioController",
      "getPortfolios",
      Seq(classOf[Int]),
      "GET",
      this.prefix + """portfolios/""" + "$" + """userId<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:18
  private lazy val controllers_PortfolioController_createPortfolio6_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("createPortfolio")))
  )
  private lazy val controllers_PortfolioController_createPortfolio6_invoker = createInvoker(
    PortfolioController_5.createPortfolio,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PortfolioController",
      "createPortfolio",
      Nil,
      "POST",
      this.prefix + """createPortfolio""",
      """""",
      Seq()
    )
  )

  // @LINE:19
  private lazy val controllers_PortfolioController_addAsset7_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("addAsset")))
  )
  private lazy val controllers_PortfolioController_addAsset7_invoker = createInvoker(
    PortfolioController_5.addAsset,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PortfolioController",
      "addAsset",
      Nil,
      "POST",
      this.prefix + """addAsset""",
      """""",
      Seq()
    )
  )

  // @LINE:20
  private lazy val controllers_PortfolioController_removeAsset8_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("removeAsset")))
  )
  private lazy val controllers_PortfolioController_removeAsset8_invoker = createInvoker(
    PortfolioController_5.removeAsset,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PortfolioController",
      "removeAsset",
      Nil,
      "POST",
      this.prefix + """removeAsset""",
      """""",
      Seq()
    )
  )

  // @LINE:21
  private lazy val controllers_PriceController_getPrice9_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("getPrice")))
  )
  private lazy val controllers_PriceController_getPrice9_invoker = createInvoker(
    PriceController_1.getPrice,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PriceController",
      "getPrice",
      Nil,
      "GET",
      this.prefix + """getPrice""",
      """""",
      Seq()
    )
  )

  // @LINE:23
  private lazy val controllers_PriceController_getHistoricalData10_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("getHistoricalData")))
  )
  private lazy val controllers_PriceController_getHistoricalData10_invoker = createInvoker(
    PriceController_1.getHistoricalData,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PriceController",
      "getHistoricalData",
      Nil,
      "GET",
      this.prefix + """getHistoricalData""",
      """""",
      Seq()
    )
  )

  // @LINE:24
  private lazy val controllers_SimulationController2_simulatePortfolio11_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("simulate")))
  )
  private lazy val controllers_SimulationController2_simulatePortfolio11_invoker = createInvoker(
    SimulationController2_0.simulatePortfolio,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SimulationController2",
      "simulatePortfolio",
      Nil,
      "GET",
      this.prefix + """simulate""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:7
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_4.index())
      }
  
    // @LINE:10
    case controllers_Assets_versioned1_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned1_invoker.call(Assets_3.versioned(path, file))
      }
  
    // @LINE:13
    case controllers_UserController_register2_route(params@_) =>
      call { 
        controllers_UserController_register2_invoker.call(UserController_2.register)
      }
  
    // @LINE:14
    case controllers_UserController_login3_route(params@_) =>
      call { 
        controllers_UserController_login3_invoker.call(UserController_2.login)
      }
  
    // @LINE:16
    case controllers_UserController_getUserInfo4_route(params@_) =>
      call { 
        controllers_UserController_getUserInfo4_invoker.call(UserController_2.getUserInfo)
      }
  
    // @LINE:17
    case controllers_PortfolioController_getPortfolios5_route(params@_) =>
      call(params.fromPath[Int]("userId", None)) { (userId) =>
        controllers_PortfolioController_getPortfolios5_invoker.call(PortfolioController_5.getPortfolios(userId))
      }
  
    // @LINE:18
    case controllers_PortfolioController_createPortfolio6_route(params@_) =>
      call { 
        controllers_PortfolioController_createPortfolio6_invoker.call(PortfolioController_5.createPortfolio)
      }
  
    // @LINE:19
    case controllers_PortfolioController_addAsset7_route(params@_) =>
      call { 
        controllers_PortfolioController_addAsset7_invoker.call(PortfolioController_5.addAsset)
      }
  
    // @LINE:20
    case controllers_PortfolioController_removeAsset8_route(params@_) =>
      call { 
        controllers_PortfolioController_removeAsset8_invoker.call(PortfolioController_5.removeAsset)
      }
  
    // @LINE:21
    case controllers_PriceController_getPrice9_route(params@_) =>
      call { 
        controllers_PriceController_getPrice9_invoker.call(PriceController_1.getPrice)
      }
  
    // @LINE:23
    case controllers_PriceController_getHistoricalData10_route(params@_) =>
      call { 
        controllers_PriceController_getHistoricalData10_invoker.call(PriceController_1.getHistoricalData)
      }
  
    // @LINE:24
    case controllers_SimulationController2_simulatePortfolio11_route(params@_) =>
      call { 
        controllers_SimulationController2_simulatePortfolio11_invoker.call(SimulationController2_0.simulatePortfolio)
      }
  }
}
