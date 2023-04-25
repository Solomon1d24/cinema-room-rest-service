# Cinema Room REST SERVICE


### Background

This REST service is used to receive get and post request for different 
kinds of action related to buying and returning tickets from customers.
Besides, managers can also use this rest service to retrieve the statistics
of the sold tickets.

### User Requirements

| Requirement     | Description                                                                                         |
|-----------------|-----------------------------------------------------------------------------------------------------|
 | Book ticket     | User can buy ticket with the REST service                                                           |
 | Return ticket   | User can refund the ticket with the REST service                                                    |
 | Read Statistics | Manager of the Cinema can read the statistics of the sold tickets with the REST service             |
 | Enquire seats   | Customer can read the information about a particular seat or all available seats with a GET request |

### System Requirements 

| Requirement         | Description                                                                                                        |
|---------------------|--------------------------------------------------------------------------------------------------------------------|
| GET Ticket Request  | Implement a response for the GET request and return a JSON body about the ticket                                   |
| POST Refund Request | Implement a response for the POST request with JSON body of the token to refund a ticket related to that seat      |
| GET Statistics      | Implement a response for the POST request with JSON body of the password to read the statistics of the sold ticket. |
 | GET seats information| Implement a response for the GET request and return a JSON body about all the seat information                     |


### Technology Dependency
org.springframework.boot <br />
com.fasterxml.jackson.core <br />
org.modelmapper:modelmapper


### Examples of usage
#### Get information of the seats
Request : GET request with /seats end point <br/>
Response : JSON body of the information about the movie theatre
```json
{
   "total_rows":5,
   "total_columns":6,
   "available_seats":[
      {
         "row":1,
         "column":1
      },

      ........

      {
         "row":5,
         "column":5
      },
      {
         "row":5,
         "column":6
      }
   ]
}
```

#### Buy ticket
Request : POST request with /purchase end point <br />
Response : JSON body of the information about the ticket with the unique token
 
```json
{
    "token": "00ae15f2-1ab6-4a02-a01f-07810b42c0ee",
    "ticket": {
        "row": 1,
        "column": 1,
        "price": 10
    }
}
```
If the seat is taken, respond with a 400 (Bad Request) status code. The response body should contain the following:


```json
{
    "error": "The ticket has been already purchased!"
}
```

If users pass a wrong row/column number, respond with a 400 status code and the following line:

```json
{
    "error": "The number of a row or a column is out of bounds!"
}
```
#### Refund ticket

Request: POST request with /return end point which allows customer to refund their tickets, there should be a body of the token
```json
{
    "token": "e739267a-7031-4eed-a49c-65d8ac11f556"
}
```
Response: The body that contains the information of the refund ticket
```json
{
    "returned_ticket": {
        "row": 1,
        "column": 1,
        "price": 10
    }
}
```

If token cannot be identified, the program responds with a 400 status code and the following response body:
```json
{
    "error": "Wrong token!"
}
```

#### Get statistics

Request : POST request with /stats end point with URL parameters of password, the correct value should be "super_secret" <br/>
Response : movie theatre statistics in the following format:
```json
{
    "current_income": 0,
    "number_of_available_seats": 81,
    "number_of_purchased_tickets": 0
}
```


If the parameters don't contain a password key or a wrong value has been passed, respond with a 401 status code. The response body should contain the following:

```json
{
    "error": "The password is wrong!"
}
```
