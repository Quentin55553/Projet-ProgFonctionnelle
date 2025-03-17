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
  UserController_2: controllers.UserController,
  // @LINE:17
  PortfolioControllerTest_3: controllers.PortfolioControllerTest,
  val prefix: String
) extends GeneratedRouter {

  @javax.inject.Inject()
  def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:7
    HomeController_0: controllers.HomeController,
    // @LINE:10
    Assets_1: controllers.Assets,
    // @LINE:13
    UserController_2: controllers.UserController,
    // @LINE:17
    PortfolioControllerTest_3: controllers.PortfolioControllerTest
  ) = this(errorHandler, HomeController_0, Assets_1, UserController_2, PortfolioControllerTest_3, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_0, Assets_1, UserController_2, PortfolioControllerTest_3, prefix)
  }

  private val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """register""", """controllers.UserController.register"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """login""", """controllers.UserController.login"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """portfolio/""" + "$" + """userId<[^/]+>""", """controllers.PortfolioControllerTest.getPortfolio(userId:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """portfolio/add/""" + "$" + """userId<[^/]+>/""" + "$" + """symbol<[^/]+>/""" + "$" + """quantity<[^/]+>""", """controllers.PortfolioControllerTest.addAsset(userId:String, symbol:String, quantity:Int)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """portfolio/remove/""" + "$" + """userId<[^/]+>/""" + "$" + """symbol<[^/]+>/""" + "$" + """quantity<[^/]+>""", """controllers.PortfolioControllerTest.removeAsset(userId:String, symbol:String, quantity:Int)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """crypto/""" + "$" + """symbol<[^/]+>""", """controllers.PortfolioControllerTest.getCryptoPrice(symbol:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """forex/""" + "$" + """symbol<[^/]+>""", """controllers.PortfolioControllerTest.getForexPrice(symbol:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """history/""" + "$" + """symbol<[^/]+>/""" + "$" + """from<[^/]+>/""" + "$" + """to<[^/]+>""", """controllers.PortfolioControllerTest.getHistoricalData(symbol:String, from:String, to:String)"""),
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

  // @LINE:17
  private lazy val controllers_PortfolioControllerTest_getPortfolio4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("portfolio/"), DynamicPart("userId", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_PortfolioControllerTest_getPortfolio4_invoker = createInvoker(
    PortfolioControllerTest_3.getPortfolio(fakeValue[String]),
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

  // @LINE:18
  private lazy val controllers_PortfolioControllerTest_addAsset5_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("portfolio/add/"), DynamicPart("userId", """[^/]+""", encodeable=true), StaticPart("/"), DynamicPart("symbol", """[^/]+""", encodeable=true), StaticPart("/"), DynamicPart("quantity", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_PortfolioControllerTest_addAsset5_invoker = createInvoker(
    PortfolioControllerTest_3.addAsset(fakeValue[String], fakeValue[String], fakeValue[Int]),
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

  // @LINE:19
  private lazy val controllers_PortfolioControllerTest_removeAsset6_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("portfolio/remove/"), DynamicPart("userId", """[^/]+""", encodeable=true), StaticPart("/"), DynamicPart("symbol", """[^/]+""", encodeable=true), StaticPart("/"), DynamicPart("quantity", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_PortfolioControllerTest_removeAsset6_invoker = createInvoker(
    PortfolioControllerTest_3.removeAsset(fakeValue[String], fakeValue[String], fakeValue[Int]),
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

  // @LINE:20
  private lazy val controllers_PortfolioControllerTest_getCryptoPrice7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("crypto/"), DynamicPart("symbol", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_PortfolioControllerTest_getCryptoPrice7_invoker = createInvoker(
    PortfolioControllerTest_3.getCryptoPrice(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PortfolioControllerTest",
      "getCryptoPrice",
      Seq(classOf[String]),
      "GET",
      this.prefix + """crypto/""" + "$" + """symbol<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:21
  private lazy val controllers_PortfolioControllerTest_getForexPrice8_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("forex/"), DynamicPart("symbol", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_PortfolioControllerTest_getForexPrice8_invoker = createInvoker(
    PortfolioControllerTest_3.getForexPrice(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PortfolioControllerTest",
      "getForexPrice",
      Seq(classOf[String]),
      "GET",
      this.prefix + """forex/""" + "$" + """symbol<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:22
  private lazy val controllers_PortfolioControllerTest_getHistoricalData9_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("history/"), DynamicPart("symbol", """[^/]+""", encodeable=true), StaticPart("/"), DynamicPart("from", """[^/]+""", encodeable=true), StaticPart("/"), DynamicPart("to", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_PortfolioControllerTest_getHistoricalData9_invoker = createInvoker(
    PortfolioControllerTest_3.getHistoricalData(fakeValue[String], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PortfolioControllerTest",
      "getHistoricalData",
      Seq(classOf[String], classOf[String], classOf[String]),
      "GET",
      this.prefix + """history/""" + "$" + """symbol<[^/]+>/""" + "$" + """from<[^/]+>/""" + "$" + """to<[^/]+>""",
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
    case controllers_UserController_register2_route(params@_) =>
      call { 
        controllers_UserController_register2_invoker.call(UserController_2.register)
      }
  
    // @LINE:14
    case controllers_UserController_login3_route(params@_) =>
      call { 
        controllers_UserController_login3_invoker.call(UserController_2.login)
      }
  
    // @LINE:17
    case controllers_PortfolioControllerTest_getPortfolio4_route(params@_) =>
      call(params.fromPath[String]("userId", None)) { (userId) =>
        controllers_PortfolioControllerTest_getPortfolio4_invoker.call(PortfolioControllerTest_3.getPortfolio(userId))
      }
  
    // @LINE:18
    case controllers_PortfolioControllerTest_addAsset5_route(params@_) =>
      call(params.fromPath[String]("userId", None), params.fromPath[String]("symbol", None), params.fromPath[Int]("quantity", None)) { (userId, symbol, quantity) =>
        controllers_PortfolioControllerTest_addAsset5_invoker.call(PortfolioControllerTest_3.addAsset(userId, symbol, quantity))
      }
  
    // @LINE:19
    case controllers_PortfolioControllerTest_removeAsset6_route(params@_) =>
      call(params.fromPath[String]("userId", None), params.fromPath[String]("symbol", None), params.fromPath[Int]("quantity", None)) { (userId, symbol, quantity) =>
        controllers_PortfolioControllerTest_removeAsset6_invoker.call(PortfolioControllerTest_3.removeAsset(userId, symbol, quantity))
      }
  
    // @LINE:20
    case controllers_PortfolioControllerTest_getCryptoPrice7_route(params@_) =>
      call(params.fromPath[String]("symbol", None)) { (symbol) =>
        controllers_PortfolioControllerTest_getCryptoPrice7_invoker.call(PortfolioControllerTest_3.getCryptoPrice(symbol))
      }
  
    // @LINE:21
    case controllers_PortfolioControllerTest_getForexPrice8_route(params@_) =>
      call(params.fromPath[String]("symbol", None)) { (symbol) =>
        controllers_PortfolioControllerTest_getForexPrice8_invoker.call(PortfolioControllerTest_3.getForexPrice(symbol))
      }
  
    // @LINE:22
    case controllers_PortfolioControllerTest_getHistoricalData9_route(params@_) =>
      call(params.fromPath[String]("symbol", None), params.fromPath[String]("from", None), params.fromPath[String]("to", None)) { (symbol, from, to) =>
        controllers_PortfolioControllerTest_getHistoricalData9_invoker.call(PortfolioControllerTest_3.getHistoricalData(symbol, from, to))
      }
  }
}
