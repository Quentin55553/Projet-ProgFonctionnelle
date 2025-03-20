
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
    </head>
    <body>
        <h1>Résultats pour la date : """),_display_(/*11.39*/date),format.raw/*11.43*/("""</h1>
        <p>Prix : """),_display_(/*12.20*/price),format.raw/*12.25*/(""" """),format.raw/*12.26*/("""€</p>
        <p>"""),_display_(/*13.13*/rsiResult),format.raw/*13.22*/("""</p>
        <p>"""),_display_(/*14.13*/macdResult),format.raw/*14.23*/("""</p>
        <p>Volatilité : """),_display_(/*15.26*/volatility),format.raw/*15.36*/("""</p>
        <p>Ratio de Sharpe : """),_display_(/*16.31*/sharpeRatio),format.raw/*16.42*/("""</p>

        <a href=""""),_display_(/*18.19*/routes/*18.25*/.HomeController.simulationPage),format.raw/*18.55*/("""">Retour à la simulation</a>
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
                  HASH: ec37259a09f1348b8b414e64017f44bb126b2aa0
                  MATRIX: 432->1|817->30|1058->175|1088->179|1291->355|1316->359|1369->385|1395->390|1424->391|1470->410|1500->419|1545->437|1576->447|1634->478|1665->488|1728->524|1760->535|1813->561|1828->567|1879->597
                  LINES: 17->1|22->2|27->2|29->4|36->11|36->11|37->12|37->12|37->12|38->13|38->13|39->14|39->14|40->15|40->15|41->16|41->16|43->18|43->18|43->18
                  -- GENERATED --
              */
          