
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
                  HASH: 1563a8b9e51c72dd0a68856914d2156eb4ce7ded
                  MATRIX: 432->1|466->30|820->76|947->108|979->114|1142->250|1157->256|1217->295|1431->482|1454->496|1482->503|1511->504|1652->618|1675->632|1709->645|1841->750|1864->764|1897->776|2031->883|2054->897|2089->911|2229->1024|2252->1038|2283->1048|2422->1160|2445->1174|2475->1183|2614->1295|2637->1309|2668->1319|2816->1440|2839->1454|2871->1465|3005->1572|3028->1586|3121->1657
                  LINES: 17->1|18->2|23->4|28->4|31->7|35->11|35->11|35->11|40->16|40->16|40->16|40->16|45->21|45->21|45->21|49->25|49->25|49->25|53->29|53->29|53->29|57->33|57->33|57->33|61->37|61->37|61->37|65->41|65->41|65->41|69->45|69->45|69->45|73->49|73->49|73->49
                  -- GENERATED --
              */
          