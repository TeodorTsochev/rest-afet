Feature: Smoke test local PostgreSQL Database

  Scenario Outline: Smoke test local PostgreSQL DB connectivity
    Given The DB request address is set to <URL>
    And The username and password are set to <username> and <password>
    When Connection is attempted
    Then Connection is established <connected>
    Examples:
      | URL                                       | username | password    | connected |
      | jdbc:postgresql://localhost:5432/postgres | postgres | Barmanadge7 | true      |
      | jdbc:postgresql://localhost:5432/postgres | username | Barmanadge7 | false     |
      | jdbc:postgresql://localhost:5432/postgres | postgres | password    | true      |
      | jdbc:postgresql://localhost:5432/postgres | username | password    | false     |

  Scenario: Smoke test local PostgreSQL DB ''Customers'' table column count
    Given The DB request address is set to jdbc:postgresql://localhost:5432/postgres
    And The username and password are set to postgres and Barmanadge7
    And The table of interest is Customers
    When Connection is attempted
    Then The number of columns returned is 7

  Scenario: Smoke test local PostgreSQL DB ''Customers'' table NOT NULL content
    Given The DB request address is set to jdbc:postgresql://localhost:5432/postgres
    And The username and password are set to postgres and Barmanadge7
    And The table of interest is Customers
    When Connection is attempted
    Then Column contents are NOT NULL
