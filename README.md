# coffee_task

To work with a coffee machine, you need to use this API:

# 1. State of machine.
# http://localhost:8080/coffee-task/machine 
GET /coffee-task/machine HTTP/1.1

Host: localhost:8080

Using for get information about machine state. Used to obtain information about the fullness of the ingredients:
coffee, water, milk.

Response:
```javascript
{
    "errorText": [string],
    "waterVolume": double,
    "grainVolume"double
}
```

Example:
```javascript
{
    "errorText": [
        "Water volume needed",
        "Grains volume needed"
    ],
    "waterVolume": 0,
    "grainVolume": 0,
    "milkVolume": 0.8
}
```

# 2.  Add ingridients.
# http://localhost:8080/coffee-task/machine?water=&milk=&grain=
PUT /coffee-task/machine HTTP/1.1

Host: localhost:8080

Used to add some ingredients.

Example:
http://localhost:8080/coffee-task/machine?water=0.5&milk=0.2&grain=0.015

Response:
```javascript
{
    "status": "OK"
}
```
or
```javascript
{
    "errorText": [
        "Water volume exceeded",
        "Milk volume exceeded"
    ],
    "status": "ERROR"
}
```

# 3.  Get coffee.
# http://localhost:8080/coffee-task/coffee/{coffee_type}
GET /coffee-task/machine HTTP/1.1

Host: localhost:8080

To get coffee. {coffee_type} must be capuchino or americano.

Example:
Request:
http://localhost:8080/coffee-task/coffee/capuchino
Response:
```javascript
{
    "status": "OK"
}
```
or
```javascript
{
    "errorText": [
        "NeedMoreGrain"
    ],
    "status": "ERROR"
}
```

# 4.  Clean machine.
# http://localhost:8080/coffee-task/machine
POST /coffee-task/machine HTTP/1.1

To clean machine.
Example:
Request:
http://localhost:8080/coffee-task/coffee/capuchino
Response 
HTTP Status 200.



# Addition

Do not forget before using add ingredients.

After preparing 8 cups of coffee, you need to clean the coffee machine.

Nominal volumes of ingredients:
water 	= 2.0 l
milk 	= 1.0 l;
grains = 100 gr;
