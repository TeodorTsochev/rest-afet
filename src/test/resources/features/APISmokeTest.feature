Feature: Smoke test "OMDB" API

  Scenario Outline: Smoke test basic API requests using valid/invalid API keys
    Given The API address is set to <URL>
    And Assign API key request parameter <api_key_parameter> with value <key>
    When We make GET request to the API
    Then The following is returned: response '<response>', message '<message>' and response code '<code>' is returned
    Examples:
      | URL                      | api_key_parameter | key      | response | message              | code |
      | https://www.omdbapi.com/ | apikey            | 9f0bd5e3 | False    | Incorrect IMDb ID.   | 200  |
      | https://www.omdbapi.com/ | apikey            |          | False    | No API key provided. | 401  |
      | https://www.omdbapi.com/ | apkey             | 9f0bd5e3 | False    | No API key provided. | 401  |
      | https://www.omdbapi.com/ | apiky             | 9f0bd5e3 | False    | No API key provided. | 401  |
      | https://www.omdbapi.com/ | 123               | 9f0bd5e3 | False    | No API key provided. | 401  |
      | https://www.omdbapi.com/ | abCcBa            | abcABC   | False    | No API key provided. | 401  |
      | https://www.omdbapi.com/ |                   | 123456   | False    | No API key provided. | 401  |
      | https://www.omdbapi.com/ | apikey            | \t       | False    | Invalid API key!     | 401  |
      | https://www.omdbapi.com/ | apikey            | \r       | False    | Invalid API key!     | 401  |
      | https://www.omdbapi.com/ | apikey            | abcABC   | False    | Invalid API key!     | 401  |
      | https://www.omdbapi.com/ | apikey            | 123456   | False    | Invalid API key!     | 401  |
      | https://www.omdbapi.com/ | apikey            | (-__-)   | False    | Invalid API key!     | 401  |
