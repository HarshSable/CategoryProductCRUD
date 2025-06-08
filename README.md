# CategoryProductCrud

This is a Spring Boot CRUD (Create, Read, Update, Delete) REST API project that manages `Category` and `Product` entities with a one-to-many relationship using Spring Data JPA and a MySQL database.

## Features

- CRUD operations for **Category** and **Product**
- Paginated fetching of categories and products
- Retrieve products by category
- One-to-Many relationship mapping between Category and Product
- JSON response handling with infinite recursion prevention
- MySQL integration with JPA and Hibernate

## Technologies Used

- Java 17  
- Spring Boot 3.5.0  
- Spring Data JPA  
- MySQL  
- Maven  

---

## API Endpoints & Entity Relationship

###  Categories
- `GET /api/categories` – Get paginated list of categories  
- `GET /api/categories/{id}` – Get category by ID  
- `POST /api/categories` – Create a new category (along with products)  
- `PUT /api/categories/{id}` – Update a category  
- `DELETE /api/categories/{id}` – Delete a category  
- `GET /api/categories/{id}/products` – Get paginated products for a specific category  

###  Products
- `GET /api/products` – Get paginated list of products  
- `GET /api/products/{id}` – Get product by ID  
- `POST /api/products` – Create a new product  
- `PUT /api/products/{id}` – Update a product  
- `DELETE /api/products/{id}` – Delete a product  

###  Entity Relationship
- A **Category** can have multiple **Products**  
- A **Product** belongs to one **Category**
