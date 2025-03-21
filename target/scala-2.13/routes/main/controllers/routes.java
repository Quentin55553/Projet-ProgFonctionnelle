// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseUserController UserController = new controllers.ReverseUserController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseHomeController HomeController = new controllers.ReverseHomeController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReversePriceController PriceController = new controllers.ReversePriceController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReversePortfolioController PortfolioController = new controllers.ReversePortfolioController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseSimulationController2 SimulationController2 = new controllers.ReverseSimulationController2(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseUserController UserController = new controllers.javascript.ReverseUserController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseHomeController HomeController = new controllers.javascript.ReverseHomeController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReversePriceController PriceController = new controllers.javascript.ReversePriceController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReversePortfolioController PortfolioController = new controllers.javascript.ReversePortfolioController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseSimulationController2 SimulationController2 = new controllers.javascript.ReverseSimulationController2(RoutesPrefix.byNamePrefix());
  }

}
