
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


Seq[Any](format.raw/*5.1*/("""

"""),format.raw/*7.1*/("""<!DOCTYPE html>
<html>
    <head>
        <title>Index de la plateforme</title>
        <link rel="icon" type="image/x-icon" href=""""),_display_(/*11.53*/routes/*11.59*/.Assets.versioned("images/favicon.ico")),format.raw/*11.98*/("""">
    </head>

    <body>
        <h1>Ceci n'est qu'un test de l'API sur la page d'index, cela pourra être retiré à l'avenir</h1>
        <h3>Informations boursières pour l'action """),_display_(/*16.52*/financialAsset/*16.66*/.symbol),format.raw/*16.73*/(""" """),format.raw/*16.74*/("""(Apple)</h3>

        <table>
            <tr>
                <td>Prix actuel :</td>
                <td>$ """),_display_(/*21.24*/financialAsset/*21.38*/.currentPrice),format.raw/*21.51*/("""</td>
            </tr>
            <tr>
                <td>Variation :</td>
                <td>$ """),_display_(/*25.24*/financialAsset/*25.38*/.priceChange),format.raw/*25.50*/("""</td>
            </tr>
            <tr>
                <td>Variation (%) :</td>
                <td>"""),_display_(/*29.22*/financialAsset/*29.36*/.percentChange),format.raw/*29.50*/("""</td>
            </tr>
            <tr>
                <td>Prix le plus haut :</td>
                <td>$ """),_display_(/*33.24*/financialAsset/*33.38*/.highPrice),format.raw/*33.48*/("""</td>
            </tr>
            <tr>
                <td>Prix le plus bas :</td>
                <td>$ """),_display_(/*37.24*/financialAsset/*37.38*/.lowPrice),format.raw/*37.47*/("""</td>
            </tr>
            <tr>
                <td>Prix d'ouverture :</td>
                <td>$ """),_display_(/*41.24*/financialAsset/*41.38*/.openPrice),format.raw/*41.48*/("""</td>
            </tr>
            <tr>
                <td>Prix de clôture précédent :</td>
                <td>$ """),_display_(/*45.24*/financialAsset/*45.38*/.closePrice),format.raw/*45.49*/("""</td>
            </tr>
            <tr>
                <td>Date et heure :</td>
                <td>"""),_display_(/*49.22*/financialAsset/*49.36*/.dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy 'à' HH'h'mm"))),format.raw/*49.107*/("""</td>
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
                  HASH: 5943f519b5ba8c4bb07a39d97fee29fc68cc236b
                  MATRIX: 432->1|466->29|820->73|946->106|974->108|1133->240|1148->246|1208->285|1417->467|1440->481|1468->488|1497->489|1633->598|1656->612|1690->625|1818->726|1841->740|1874->752|2004->855|2027->869|2062->883|2198->992|2221->1006|2252->1016|2387->1124|2410->1138|2440->1147|2575->1255|2598->1269|2629->1279|2773->1396|2796->1410|2828->1421|2958->1524|2981->1538|3074->1609
                  LINES: 17->1|18->2|23->4|28->5|30->7|34->11|34->11|34->11|39->16|39->16|39->16|39->16|44->21|44->21|44->21|48->25|48->25|48->25|52->29|52->29|52->29|56->33|56->33|56->33|60->37|60->37|60->37|64->41|64->41|64->41|68->45|68->45|68->45|72->49|72->49|72->49
                  -- GENERATED --
              */
          