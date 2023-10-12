@REGTST-197 @UATSCH-15 
Feature: REGTST-8298___bdd_test_mobile_trading_buy_stopbuymarket_stopbuylimit

    #Create order stop buy market
    #Wertpapiertyp - Aktie,Anleihe,Zertifikat,Fond and Optionsschein
    Scenario Outline: <TestCase>
    Given User logins into Consorsbank mobile application with "Trading_user"
    When User clicks on Searchicon_MeineFinanzen
    And User enters "Name_WKN_ISIN" in SearchField_SearchPage
    And User clicks on Search_SearchPage
    And User clicks on FirstSearchResult_SearchPage
    And User clicks on "Orderart"_OrderSnapshot
    And User selects "Handelsplatz" in AmWelchenHandelsplatz_Orderwindow
    And User enters "Stück" in Stück_Orderwindow
    And User selects "Ordertyp" in Orderzusatz_Orderwindow
    And User enters "Limit" in Limit_Orderwindow
    And User selects "StopBuy" in StopBuy_Orderwindow
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
      | TestCase                              |
      | KaufOrder_Stopbuymarket_Aktie         |
      | KaufOrder_Stopbuymarket_Anleihe       |
      | KaufOrder_Stopbuymarket_Fond          |
      | KaufOrder_Stopbuymarket_Zertifikat    |
      | KaufOrder_Stopbuymarket_Optionsschein | 
    
    #Edit order stop buy market
    #Wertpapiertyp - Aktie,Anleihe,Zertifikat,Fond and Optionsschein
    #Dependancy to create order
    Scenario Outline: <TestCase>
    Given User logins into Consorsbank mobile application with "Trading_user"
    When User clicks on Depot_MeineFinanzen 
    And User navigates to Orderinfo_Depots
    And User clicks on row for "Name_WKN_ISIN" were NominalStatus_OrderInfo is Offen
    And User verifies "Ordertyp" in Orderzusatz_Orderinfos,"Orderart" in Kaufvon_Orderinfos,"Handelsplatz" in Handelsplatz_Orderinfos and "Limit" in Orderzusatz_Orderinfos
    And User clicks on Limitändern_Orderinfos
    And User enters "StopBuy" in Limit_OrderändernWindow
    And User clicks on WeiterZurTANEingabe_OrderändernWindow
    And User verifies the presence of link Kostenausweis_OrderändernConfirmationWindow
    And User does the tan authentication
    And User clicks Orderändern_OrderändernTANwindow
    And Capture order modify confirmation message on Order angenommen page
    And Capture entered limit_Orderangenommen and Ordernummer_Orderangenommen
    And User close orderangenommen page
    Then Verify "StopBuy" in limit_Orderangenommen and Ordernummer_Orderangenommen in Orderinfo_depots page
    And Verify "Message" from Order angenommen page
    
    Examples:
      | TestCase                                     |
      | KaufOrder_ändern_Stopbuymarket_Aktie         |
      | KaufOrder_ändern_Stopbuymarket_Anleihe       |
      | KaufOrder_ändern_Stopbuymarket_Fond          |
      | KaufOrder_ändern_Stopbuymarket_Zertifikat    |
      | KaufOrder_ändern_Stopbuymarket_Optionsschein |
      
    #Delete order stop buy market
    #Wertpapiertyp - Aktie,Anleihe,Zertifikat,Fond and Optionsschein
    #Dependancy to edit order
    Scenario Outline: <TestCase>
    Given User logins into Consorsbank mobile application with "Trading_user"
    When User clicks on Depot_MeineFinanzen 
    And User navigates to Orderinfo_Depots
    And User clicks on row for "Name_WKN_ISIN" were NominalStatus_OrderInfo is Offen
    And User verifies "Ordertyp" in Orderzusatz_Orderinfos,"Orderart" in Kaufvon_Orderinfos,"Handelsplatz" in Handelsplatz_Orderinfos and "StopBuy" in Orderzusatz_Orderinfos
    And User clicks on Orderlöschen_Orderinfos
    And User does the tan authentication
    And User clicks on Orderlöschen_OrderlöschenTANentrypage
    And Capture Ordernummer_Orderangenommen and order cancelation confirmation message on Order angenommen page
    And User close orderangenommen page
    Then verify "NominalStatus"in NominalStatus_Orderinfo and Ordernummer_Orderangenommen in Orderinfo_depots page
    And Verify "Message" from Order angenommen page
    
        Examples:
      | TestCase                                      |
      | KaufOrder_löschen_Stopbuymarket_Aktie         |
      | KaufOrder_löschen_Stopbuymarket_Anleihe       |
      | KaufOrder_löschen_Stopbuymarket_Fond          |
      | KaufOrder_löschen_Stopbuymarket_Zertifikat    |
      | KaufOrder_löschen_Stopbuymarket_Optionsschein |