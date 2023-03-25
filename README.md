# HackerNewsApi
Fetching real time  stories and comments of Hacker News using its APIs.

## APIs  for Fetching stories and comments
1. **/top-stories** - Rreturn the top 10 stories ranked by the score in the last 15 minutes. Each story have a title, URL, score, time of submission, and the user who submitted it.
2. **/past-stories** - Returns all the stories that were served previously from the 1st endpoint (/top-stories).
3. **/comments** - Returns 10 comments (max) on a given story sorted by a total number of child comments. Each comment contains comment text, the user’s hacker news handle.

## Additional Information
1. Using Swagger and Postman for API testing. 
2. Using 3 tier Architecture - Controller, Service, Repository
3. Database Used - MYSQL
4. Hazelcast cache is Used.
