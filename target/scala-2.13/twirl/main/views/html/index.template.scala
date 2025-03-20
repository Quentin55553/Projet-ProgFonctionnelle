
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
/*1.2*/import data.FinancialAsset
/*2.2*/import java.time.format.DateTimeFormatter

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[FinancialAsset,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*4.2*/(financialAsset: FinancialAsset):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.34*/("""


"""),format.raw/*7.1*/("""<!DOCTYPE html>
<html>
    <head>
        <title>Index de la plateforme</title>
    </head>

    <body>
        <h1>Ceci n'est qu'un test de l'API sur la page d'index, cela pourra être retiré à l'avenir</h1>
        <h3>Informations boursières pour l'action """),_display_(/*15.52*/financialAsset/*15.66*/.symbol),format.raw/*15.73*/(""" """),format.raw/*15.74*/("""(Apple)</h3>

        <table>
            <tr>
                <td>Prix actuel :</td>
                <td>$ """),_display_(/*20.24*/financialAsset/*20.38*/.currentPrice),format.raw/*20.51*/("""</td>
            </tr>
            <tr>
                <td>Variation :</td>
                <td>$ """),_display_(/*24.24*/financialAsset/*24.38*/.priceChange),format.raw/*24.50*/("""</td>
            </tr>
            <tr>
                <td>Variation (%) :</td>
                <td>"""),_display_(/*28.22*/financialAsset/*28.36*/.percentChange),format.raw/*28.50*/("""</td>
            </tr>
            <tr>
                <td>Prix le plus haut :</td>
                <td>$ """),_display_(/*32.24*/financialAsset/*32.38*/.highPrice),format.raw/*32.48*/("""</td>
            </tr>
            <tr>
                <td>Prix le plus bas :</td>
                <td>$ """),_display_(/*36.24*/financialAsset/*36.38*/.lowPrice),format.raw/*36.47*/("""</td>
            </tr>
            <tr>
                <td>Prix d'ouverture :</td>
                <td>$ """),_display_(/*40.24*/financialAsset/*40.38*/.openPrice),format.raw/*40.48*/("""</td>
            </tr>
            <tr>
                <td>Prix de clôture précédent :</td>
                <td>$ """),_display_(/*44.24*/financialAsset/*44.38*/.closePrice),format.raw/*44.49*/("""</td>
            </tr>
            <tr>
                <td>Date et heure :</td>
                <td>"""),_display_(/*48.22*/financialAsset/*48.36*/.dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy 'à' HH'h'mm"))),format.raw/*48.107*/("""</td>
            </tr>
        </table>
    </body>
</html>
"""))
      }
    }
  }

  def render(financialAsset:FinancialAsset): play.twirl.api.HtmlFormat.Appendable = apply(financialAsset)

  def f:((FinancialAsset) => play.twirl.api.HtmlFormat.Appendable) = (financialAsset) => apply(financialAsset)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/index.scala.html
                  HASH: 03c5af27456426b8e3148b0866689cb13e195461
                  MATRIX: 432->1|466->30|820->76|947->108|979->114|1273->381|1296->395|1324->402|1353->403|1494->517|1517->531|1551->544|1683->649|1706->663|1739->675|1873->782|1896->796|1931->810|2071->923|2094->937|2125->947|2264->1059|2287->1073|2317->1082|2456->1194|2479->1208|2510->1218|2658->1339|2681->1353|2713->1364|2847->1471|2870->1485|2963->1556
                  LINES: 17->1|18->2|23->4|28->4|31->7|39->15|39->15|39->15|39->15|44->20|44->20|44->20|48->24|48->24|48->24|52->28|52->28|52->28|56->32|56->32|56->32|60->36|60->36|60->36|64->40|64->40|64->40|68->44|68->44|68->44|72->48|72->48|72->48
                  -- GENERATED --
              */
          