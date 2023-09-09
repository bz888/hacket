Technologies Used:

Java 17: Stable, OOP language with extensive library support.
Spring Boot: Facilitates building production-ready applications and integrates well with JPA for data access.
PostgreSQL: Powerful, open-source object-relational database system with a strong emphasis on extensibility and standards compliance.
Trade-offs:

Spring Boot vs Micronaut/Quarkus: Chose Spring Boot for its mature ecosystem and vast community support.
PostgreSQL vs Other Databases: Chose PostgreSQL for its robustness, extensibility, and being open source. It’s a versatile RDBMS compared to others like MySQL or SQLite.
Design Patterns:

MVC: Used in the architecture, Model for data and logic, View is not included since it's backend, and Controller for API endpoints.
Repository Pattern: For simplifying database operations.
Shortcomings and Limitations due to Time Pressure:

No authentication or authorization added for admin operations.
Error handling and validations are minimal.
Lack of unit and integration tests.
Not designed with high scalability in mind.
When transitioning to PostgreSQL, remember to set up a PostgreSQL instance, either locally or on a server, create the hardwarestore database, and ensure that the app can connect to it using the credentials provided in the application.properties file.





