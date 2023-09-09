## Design:

### Java 17
- I've never tried, so I want to try

### Spring Boot
- I've never tried, so I want to try

### PostgreSQL
- I am more familiar with postgreSQL, as I already have a container running for postgres 12 

Design Patterns:

The decision to use both Order and OrderLine entities is based on the modeling of the real-world shopping and e-commerce processes.
Reasons:
- ### Multiple Products in a Single Order
    Each product in that order can have a different quantity.
An OrderLine represents a single line item in an order. For example, "3 units of Product A" would be one order line, and "2 units of Product B" would be another order line within the same order.

- ### Normalization:
  Relational database design to minimize redundancy and dependency by organizing fields and table of a database

- ### Historical Accuracy
  Capture the price of the product at the exact time the order was placed

- ### Flexibility and Scalability
  Allows for greater flexibility in adding or changing features. For instance, if in the future, there's a requirement to apply a discount on a particular product in an order, you can do so at the OrderLine level

### MVC: 
- Model for data and logic, View is not included since it's backend, and Controller for API endpoints.
### Repository Pattern: 
- For simplifying database operations.

### Shortcomings and Limitations due to Time Pressure:
- No authentication or authorization added for admin operations.
- Error handling and validations are minimal.
- Lack of unit and integration tests.
- Not designed with high scalability in mind.
- When transitioning to PostgreSQL, remember to set up a PostgreSQL instance, either locally or on a server, create the hardwarestore database, and ensure that the app can connect to it using the credentials provided in the application.properties file.


Run:
`gradlew clean build`
`gradlew bootRun`


#### Requests:

Orders: 
-  All Orders `GET` `/api/orders`
- Create order `POST` `/api/orders` 
request body example:
```json
{
    "user": {
        "id": 1,
        "username": "test",
        "role": "CUSTOMER"
    },
    "orderLines": [
        {
            "product": {
                "id": 1,
                "name": "item1",
                "price": 1.99
            },
            "quantity": 1
        },
        {
            "product": {
                "id": 2,
                "name": "item2",
                "price": 2.11
            },
            "quantity": 69
        }
    ]
}
```
- Get Order by ID `GET` `/api/orders/{id}`
- Get Order line by Order ID `GET` `/api/orders/{id}/orderlines`
- Update Order `PUT` `/api/orders/{id}` (Broken)
- Delete Order `DELETE` `/api/orders/{id}`

Products: 
- Get All Products `GET` `/api/products`
- Create Product (Admin only)`POST` `/api/products`
Example request body:
```json
{
    "name": "hammer",
    "price": 900
}
```
- Delete Product by ID (Admin only)`DELETE` `/api/products/{id}`
- Update Product by ID (Admin ony)`PUT` `/api/products/{id}`

Users:
- Get User by ID `GET` `/api/users/{id}`
- Get User by username (Kind of redundant) `GET` `/api/users/username/{username}` 
- Create User `POST` `/api/users`
Example request body
```json
{
    "username": "hammer",
    "password": "c",
    "role": "ADMIN"
}
```
- Delete User by Id `DELETE` `/api/users/{id}`
- Update User by ID `PUT` `/api/users/{id}`
