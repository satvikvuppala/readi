@demo 
Feature: Demo_Pay2All_Mobile_Recharge

  Scenario Outline: <TestCase>
    Given User Open Pay2AllDemo mobile application
    When User enters "User_Name" in User_Name_Field
    And User enters "Password" in Password_Field
    And User clicks on Login_Button
    And User clicks on addMoney
    And User selects "BankName" in bank_name_field
    And User enters "Amount" in amount_Field
    And User enters "RefNo" in ref_Field
    And User selects "PaymentMode" in PaymentMode_field
    And user selects payment date
    And User clicks on proceed
    Then User verify "status" of addMoneyRequest


    Examples:
      | TestCase               |
      | Add_Money        |




