// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:7
package controllers {

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
  
    // @LINE:16
    def getUserInfo: Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "userInfo")
    }
  
  }

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

  // @LINE:21
  class ReversePriceController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:21
    def getPrice: Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "getPrice")
    }
  
    // @LINE:23
    def getHistoricalData: Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "getHistoricalData")
    }
  
  }

  // @LINE:17
  class ReversePortfolioController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:17
    def getPortfolios(userId:Int): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "portfolios/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("userId", userId)))
    }
  
    // @LINE:18
    def createPortfolio: Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "createPortfolio")
    }
  
    // @LINE:19
    def addAsset: Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "addAsset")
    }
  
    // @LINE:20
    def removeAsset: Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "removeAsset")
    }
  
  }

  // @LINE:24
  class ReverseSimulationController2(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:24
    def simulatePortfolio: Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "simulate")
    }
  
  }


}
