// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:7
package controllers {

  // @LINE:7
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:7
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }

  // @LINE:10
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:10
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }

  // @LINE:13
  class ReverseUserController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:13
    def register: Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "register")
    }
  
    // @LINE:14
    def login: Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "login")
    }
  
  }

  // @LINE:17
  class ReversePortfolioControllerTest(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:18
    def addAsset(userId:String, symbol:String, quantity:Int): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "portfolio/add/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("userId", userId)) + "/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("symbol", symbol)) + "/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("quantity", quantity)))
    }
  
    // @LINE:20
    def getCryptoPrice(symbol:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "crypto/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("symbol", symbol)))
    }
  
    // @LINE:22
    def getHistoricalData(symbol:String, from:String, to:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "history/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("symbol", symbol)) + "/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("from", from)) + "/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("to", to)))
    }
  
    // @LINE:19
    def removeAsset(userId:String, symbol:String, quantity:Int): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "portfolio/remove/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("userId", userId)) + "/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("symbol", symbol)) + "/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("quantity", quantity)))
    }
  
    // @LINE:17
    def getPortfolio(userId:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "portfolio/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("userId", userId)))
    }
  
    // @LINE:21
    def getForexPrice(symbol:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "forex/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("symbol", symbol)))
    }
  
  }


}
