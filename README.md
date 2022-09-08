# Test task for WiseBits

## Test task is implemented with RestAssured and TestNG. Make sure annotations processing option is enabled for Lombok

# Test cases: 
## Positive:

### Validate /user/get endpoint
* Call /user/get endpoint
* Check status code is 200
* Check the list with users is not empty

### Validate /user/create endpoint
* Call /user/create endpoint with unique username and email and not empty password
* Check status code is 200
* Check success status is true
* Check username and email in response are same as in request
* Check message in response is correct

### Validate user saved to the database
* Precondition: call /user/create endpoint with correct parameters
* Call /user/get endpoint and check that the list with users contains the user created in precondition


## Negative:

### Validate user with existing username is not created
* Precondition: call /user/create endpoint with correct parameters
* Call /user/create endpoint with existing username and unique email
* Check status code is 400
* Check the response message is correct

### Validate user with existing email is not created
* Precondition: call /user/create endpoint with correct parameters
* Call /user/create endpoint with existing email and unique username
* Check status code is 400
* Check the response message is correct

### Validate user with empty username is not created
* Call /user/create endpoint with empty username field and non empty email and password fields
* Check status code is 400
* Check the response message is correct

### Validate user with empty email is not created
* Call /user/create endpoint with empty email field and non empty username and password fields
* Check status code is 400
* Check the response message is correct

### Validate user with empty password is not created
* Call /user/create endpoint with empty password field and non empty username and email fields
* Check status code is 400
* Check the response message is correct

## Possible improvements:
* Create and set up testng.xml;
* It's possible to check password of created user knowing which hash algorithm is used by the backend
* It's possible to add validations for date of creation;
* It's possible to add some logging with log4J
* It's possible to create some functions for mappings response body to avoid code duplication
* Data for data provider can be stored in json file

## Found issues:
* message in response body has a grammar mistake
```"message": "User Successully created"```

### Author Andrei Sakalouski