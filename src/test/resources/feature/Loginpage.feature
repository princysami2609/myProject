Feature: Login Functionality for OpenCart E-commerce website
  
  As a user of the OpenCart website
  I want to able to login with my account
  so that I can access my control-related features and manage my orders

  Background: 
    Given I am the OpenCart login page

  Scenario: Successful login with vaild credentails
    Given I have entered a vaild username "princyp6@gmail.com" and password "Princy@119"
    When I click on login button
    Then I should be logged in sucessfully

  #Scenario Outline: Unsuccessfull login with invaild credentails
    #Given I entered invaild "<username>" and "<password>"
    #When I click on login button
    #Then I should see an error message indicating "<error message>"
#
    #Examples: 
      #| username          | password  | error message |
      #| invaild@gmail.com | password1 | Warning       |
      #| abccetser         | password2 | Warning       |
      #| princys           | password3 | Warning       |
