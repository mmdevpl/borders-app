## TheBorders - land route finder application

This simple app exposes simple endpoint `/routing/{origin}/{destination}` allowing to request for list of country borders that you cross on the land route between origin and destination.
Countries have to be provided and will be returned using cca3 format.

If you want to run this project, please run following Maven Wrapper command in project's root catalogue:
`./mvnw spring-boot:run  `

If you want to run this project as a built jar artifact, your have to build it with:
`./mvnw package`
and then run:
`java -jar .\target\{artifact_name}.jar`
where artifact_name for example may be according to setup like `TheBorders-0.0.1-SNAPSHOT`

If you just want to test it run:
`./mvnw test`
