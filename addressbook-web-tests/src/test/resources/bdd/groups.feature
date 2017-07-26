Feature: Groups

    Scenario Outline: Group creation
      Given a set of groups
      When I create a new group with name <name>, header <header> and footer <footer>
      Then the new set of groups is equal to old set with the added group


      Examples:
      | name     | header          | footer          |
      | Test1    | Test1_header    | Test1_footer    |