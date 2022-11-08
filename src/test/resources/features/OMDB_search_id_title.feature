Feature: Validating the OMDB api for search operations

  @search
  Scenario: Verify the OMDB api for search by string
    Given The API is generated successfully
    When User call the GET ombi api using search "stem" and using pagination
    Then Number of items ">" 30 in response
    And Response contains below titles
      |The STEM Journals|
      |Activision: STEM - in the Videogame Industry|

  @id
  Scenario: Verify the OMDB api for get by id
    Given User call the GET ombi api using search "stem" and using pagination
    And Save the "imdbID" from response for
      | Title | Activision: STEM - in the Videogame Industry |
    When User call the GET ombi api using saved "imdbID"
    Then Status code of is 200
    And Movie "release date" is "23 Nov 2010"
    And Movie "Director" is "Mike Feurstein"

  @title
  Scenario: Verify the OMDB api for get by title
    When User call the GET ombi api using title "The STEM Journals"
    Then Status code of is 200
    And Plot of movie contains "Science, Technology, Engineering and Math"
    And Movie "Runtime" is "22 min"

