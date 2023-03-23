# Book Catalog Client

### Spring 6, Spring Boot 3, WebFlux & HTTP Interfaces

This service consumes **book-service** and **book-rating-service** which are deployed to **Render**
cloud hosting. Note that these 2 services will be shut down if for last 15 minutes no request is sent to them. After
that they will need about 1-2 minutes to start up again. So, the first request only is expected to be slow!

*API Documentation**: http://localhost:8080/webjars/swagger-ui/index.html
