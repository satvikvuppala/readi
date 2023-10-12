
Feature: REGTST-191___bdd_test_mobile_trading_buy_limithandel_market

    #Create order limit handel buy
    #Wertpapiertyp - Aktie,Anleihe,Zertifikat,Fond and Optionsschein
    Scenario Outline: <TestCase>
    Given User logins into Consorsbank mobile application with "Trading_user"

    Examples:
      | TestCase                           |
      | KaufOrder_Limithandel_Aktie        |

