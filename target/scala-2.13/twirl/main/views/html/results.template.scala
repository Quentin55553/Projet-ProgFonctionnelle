
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

object results extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[Double,Double,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(predictedPriceRegression: Double, predictedPriceMA: Double):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.62*/("""
"""),format.raw/*2.1*/("""<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Résultats</title>
    </head>
    <body>
        <h1>Résultats de la Prédiction</h1>
        <p>Prix prédit (Régression Linéaire): """),_display_(/*10.48*/predictedPriceRegression),format.raw/*10.72*/(""" """),format.raw/*10.73*/("""€</p>
        <p>Prix prédit (Moyenne Mobile - 7 jours): """),_display_(/*11.53*/predictedPriceMA),format.raw/*11.69*/(""" """),format.raw/*11.70*/("""€</p>
        <a href=""""),_display_(/*12.19*/routes/*12.25*/.SimulationController.index),format.raw/*12.52*/("""">Retour</a>
    </body>
</html>"""))
      }
    }
  }

  def render(predictedPriceRegression:Double,predictedPriceMA:Double): play.twirl.api.HtmlFormat.Appendable = apply(predictedPriceRegression,predictedPriceMA)

  def f:((Double,Double) => play.twirl.api.HtmlFormat.Appendable) = (predictedPriceRegression,predictedPriceMA) => apply(predictedPriceRegression,predictedPriceMA)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/results.scala.html
                  HASH: 55de904865c0a399e23704980f9f6209c3e5e201
                  MATRIX: 738->1|893->61|921->63|1178->293|1223->317|1252->318|1338->377|1375->393|1404->394|1456->419|1471->425|1519->452
                  LINES: 21->1|26->1|27->2|35->10|35->10|35->10|36->11|36->11|36->11|37->12|37->12|37->12
                  -- GENERATED --
              */
          