##TheBorders - land route finder application

This simple app exposes simple endpoint allowing to request for list of country borders that you go through on the land route between them.
`/routing/{origin}/{destination}`
Countries have to be provided using cca3 format.

If you want to run this project, please use following Maven Wrapper command from project's main catalogue:
`./mvnw spring-boot:run  `

If you want to run this project as a built jar artifact, your have to build it with:
`./mvnw package`
and then run:
`java -jar .\target\TheBorders-0.0.1-SNAPSHOT.jar`

If you just want to test it run:
`./mvnw test`
