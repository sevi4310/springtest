Spring Boot and Rest with Maven

Building the project

Clone the repository:
git clone https://github.com/sevi4310/springassignment

Navigate to the newly created folder:
cd springtest

Run the project with:
mvn clean install

Run:
java -jar target/springassignment-0.0.1-SNAPSHOT.jar

WEB :
Spring Boot Security dependency is used , Spring Boot application automatically requires the Basic Authentication for all HTTP Endpoints.
 User: test   Password: test

Open web browser
http://localhost:8080/view-grproducts - View products
Click "BUY" to navigate to a product page.
Product page is protected, you'll be navigated to a login page if user is not authorized.
If product is viewed more then 10 times with in an hour the price will be increased by 10%
Increased price would be displayed in the product list as well.
Increased price would be displayed until number of views within of hours more then 10.

http://localhost:8080/hello   -  logout Page
http://localhost:8080/login   -  login Page

The application assumes that inventory is unlimited. 

JSON data format is used at the application.
Examples:
Requests:
http://localhost:8080/grproducts
curl http://localhost:8080/grproducts
Responce:
[{"name":"Coin","description":"Italian silver coin XVII century","price":1000.99,"id":"5001"},{"name":"Dimond ring","description":"Dimond ring XVII century","price":2000.0,"id":"5002"},{"name":"Emerald pendant","description":"Emerald pendant XVII century","price":3000.0,"id":"5003"}]

Protected request:
http://localhost:8080/grproduct/5003
Responce:
{"name":"Emerald pendant","description":"Emerald pendant XVII century","price":3000.0,"id":"5003"}

curl http://localhost:8080/
Hello World from TomCat

Basic HTTP authentication was chosen to secure web pages. It is a quick and reliable method protect the content.
HTTP Authentication means that the HTTP layer (apache, nginx, IIS, etc) will responsible for maintaining authentication. Application authorization means that the web application (java, php, django, etc) would control authentication and authorization.
r Basic Auth does offer maximum interoperability and downward compatibility. Any client (even a shell script with curl) could consume  service easily, as long as they had valid credentials.

