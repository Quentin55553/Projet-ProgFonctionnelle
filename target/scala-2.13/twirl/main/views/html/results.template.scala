
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
/*1.2*/import java.time.LocalDate

object results extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[LocalDate,Double,String,String,Double,Double,RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*2.2*/(date: LocalDate, price: Double, rsiResult: String, macdResult: String, volatility: Double, sharpeRatio: Double)(implicit request: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.147*/("""

"""),format.raw/*4.1*/("""<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <title>Résultats</title>
        <style>
                body """),format.raw/*10.22*/("""{"""),format.raw/*10.23*/("""
                    """),format.raw/*11.21*/("""font-family: Arial, sans-serif;
                    background-color: #f4f4f9;
                    color: #333;
                    margin: 0;
                    padding: 20px;
                """),format.raw/*16.17*/("""}"""),format.raw/*16.18*/("""
                """),format.raw/*17.17*/("""h1 """),format.raw/*17.20*/("""{"""),format.raw/*17.21*/("""
                    """),format.raw/*18.21*/("""color: #4CAF50;
                """),format.raw/*19.17*/("""}"""),format.raw/*19.18*/("""
                """),format.raw/*20.17*/("""p """),format.raw/*20.19*/("""{"""),format.raw/*20.20*/("""
                    """),format.raw/*21.21*/("""font-size: 1.1em;
                    margin: 10px 0;
                """),format.raw/*23.17*/("""}"""),format.raw/*23.18*/("""
                """),format.raw/*24.17*/("""a """),format.raw/*24.19*/("""{"""),format.raw/*24.20*/("""
                    """),format.raw/*25.21*/("""display: inline-block;
                    margin-top: 20px;
                    padding: 10px 15px;
                    background-color: #4CAF50;
                    color: white;
                    text-decoration: none;
                    border-radius: 5px;
                """),format.raw/*32.17*/("""}"""),format.raw/*32.18*/("""
                """),format.raw/*33.17*/("""a:hover """),format.raw/*33.25*/("""{"""),format.raw/*33.26*/("""
                    """),format.raw/*34.21*/("""background-color: #45a049;
                """),format.raw/*35.17*/("""}"""),format.raw/*35.18*/("""
                """),format.raw/*36.17*/("""strong """),format.raw/*36.24*/("""{"""),format.raw/*36.25*/("""
                    """),format.raw/*37.21*/("""color: #4CAF50;
                """),format.raw/*38.17*/("""}"""),format.raw/*38.18*/("""
        """),format.raw/*39.9*/("""</style>
    </head>
    <body>
        <h1>Résultats pour la date : """),_display_(/*42.39*/date),format.raw/*42.43*/("""</h1>
        <p>Prix : <strong>"""),_display_(/*43.28*/price),format.raw/*43.33*/(""" """),format.raw/*43.34*/("""€</strong></p>
        <p>"""),_display_(/*44.13*/rsiResult),format.raw/*44.22*/("""</p>
        <p>"""),_display_(/*45.13*/macdResult),format.raw/*45.23*/("""</p>
        <p>Volatilité : <strong>"""),_display_(/*46.34*/volatility),format.raw/*46.44*/("""</strong></p>
        <p>Ratio de Sharpe : <strong>"""),_display_(/*47.39*/sharpeRatio),format.raw/*47.50*/("""</strong></p>

        <a href=""""),_display_(/*49.19*/routes/*49.25*/.HomeController.simulationPage),format.raw/*49.55*/("""">Retour à la simulation</a>
    </body>
</html>"""))
      }
    }
  }

  def render(date:LocalDate,price:Double,rsiResult:String,macdResult:String,volatility:Double,sharpeRatio:Double,request:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(date,price,rsiResult,macdResult,volatility,sharpeRatio)(request)

  def f:((LocalDate,Double,String,String,Double,Double) => (RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (date,price,rsiResult,macdResult,volatility,sharpeRatio) => (request) => apply(date,price,rsiResult,macdResult,volatility,sharpeRatio)(request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/results.scala.html
                  HASH: e89514cbd3e5bad4ee3f87409bfb54894f513076
                  MATRIX: 432->1|817->30|1058->175|1088->179|1267->330|1296->331|1346->353|1573->552|1602->553|1648->571|1679->574|1708->575|1758->597|1819->630|1848->631|1894->649|1924->651|1953->652|2003->674|2103->746|2132->747|2178->765|2208->767|2237->768|2287->790|2603->1078|2632->1079|2678->1097|2714->1105|2743->1106|2793->1128|2865->1172|2894->1173|2940->1191|2975->1198|3004->1199|3054->1221|3115->1254|3144->1255|3181->1265|3281->1338|3306->1342|3367->1376|3393->1381|3422->1382|3477->1410|3507->1419|3552->1437|3583->1447|3649->1486|3680->1496|3760->1549|3792->1560|3854->1595|3869->1601|3920->1631
                  LINES: 17->1|22->2|27->2|29->4|35->10|35->10|36->11|41->16|41->16|42->17|42->17|42->17|43->18|44->19|44->19|45->20|45->20|45->20|46->21|48->23|48->23|49->24|49->24|49->24|50->25|57->32|57->32|58->33|58->33|58->33|59->34|60->35|60->35|61->36|61->36|61->36|62->37|63->38|63->38|64->39|67->42|67->42|68->43|68->43|68->43|69->44|69->44|70->45|70->45|71->46|71->46|72->47|72->47|74->49|74->49|74->49
                  -- GENERATED --
              */
          