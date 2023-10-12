@REGTST-197 @UATSCH-15 
Feature: REGTST-617___bdd_test_mobile_trading_buy_trailingstopbuy_limit_market

    #Create order trailing stop buy limit and market
    Scenario Outline: <TestCase>
    Given User logins into Consorsbank mobile application with "Trading_user"
    When User clicks on Searchicon_MeineFinanzen
    And User enters "Name_WKN_ISIN" in SearchField_SearchPage
    And User clicks on Search_SearchPage
    And User clicks on FirstSearchResult_SearchPage
    And User clicks on "Orderart"_OrderSnapshot
    And User selects "Handelsplatz" in AmWelchenHandelsplatz_Orderwindow
    And User enters "Stück" in Stück_Orderwindow
    And User clicks on Limithandel_Orderwindow
    And User selects "Ordertyp" in Orderzusatz_Orderwindow
    And User enters "StopBuy" in StopBuy_Orderwindow
    And User enters "Abstand" in Abstand_Orderwindow
    And User selects "OrderzusatzAbstand" in OrderzusatzAbstand_Orderwindow
    And User enters "ToleranznachStop" in ToleranznachStop_Orderwindow
    And User selects "Ordergültigkeit" in Ordergültigkeit_Orderwindow
    And User clicks on WeiterZurTANEingabe_Orderwindow
    And User clicks OK in HöhereRisikoklasse_Orderwindow
    And User verifies the presence of link Kostenausweis_TAN-Eingabe
    And User does the tan authentication
    And User clicks on Kostenpflichtigkaufen_Kostenpflichtigkaufen
    And Capture order confirmation message on Order angenommen page
    And Capture entered details on Order angenommen page including Ordernummer_Orderangenommen
    And User clicks on OrderInfo_OrderAngenommen
    Then Verify Ordernummer_Orderangenommen,"Orderart","Name_WKN_ISIN","Stück","Status" in OrderInfo_Depots
    And Verify captured details,"Message" from Order angenommen page

    Examples:
      | TestCase                                  |
      | KaufOrder_TrailingStopBuyLimit_Absolut    |
      | KaufOrder_TrailingStopBuyLimit_Prozentual |
      | KaufOrder_TrailingStopBuyMarket_Absolut   |
      | KaufOrder_TrailingStopBuyMarket_Prozentual|
      
    #Delete order trailing stop buy limit and market
    #Dependancy to create order
    #For TrailingStopBuyMarket orders the ToleranznachStop field will not be available as per new CR. In order to facilitate easy maintenance rather than splitting scenario into two this will be handled in code.
    Scenario Outline: <TestCase>
    Given User logins into Consorsbank mobile application with "Trading_user"
    When User clicks on Depot_MeineFinanzen 
    And User navigates to Orderinfo_Depots
    And User clicks on row for "Name_WKN_ISIN" were NominalStatus_OrderInfo is Offen
    And User verifies "Ordertyp" in Orderzusatz_Orderinfos,"Orderart" in Kaufvon_Orderinfos,"Handelsplatz" in Handelsplatz_Orderinfos,"Limit" in Orderzusatz_Orderinfos,"Abstand" in Abstand_Orderinfos and "ToleranznachStop" in ToleranznachStop_Orderinfos
    And User clicks on Orderlöschen_Orderinfos
    And User does the tan authentication
    And User clicks on Orderlöschen_OrderlöschenTANentrypage
    And Capture Ordernummer_Orderangenommen and order cancelation confirmation message on Order angenommen page
    And User close orderangenommen page
    Then verify "NominalStatus"in NominalStatus_Orderinfo and Ordernummer_Orderangenommen in Orderinfo_depots page
    And Verify "Message" from Order angenommen page
    
        Examples:
      | TestCase                                          |
      | KaufOrder_löschen_TrailingStopBuyLimit_Absolut    |
      | KaufOrder_löschen_TrailingStopBuyLimit_Prozentual |
      | KaufOrder_löschen_TrailingStopBuyMarket_Absolut   |
      | KaufOrder_löschen_TrailingStopBuyMarket_Prozentual|