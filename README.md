# URL-shortener
URL shortener API similiar to those known as TinyURL or Bitly. The application creates and stores a shorter version of original URL. It also provides an interface to obtain original URL in exchange of the shorter URL.

## Author
- [shorv](https://github.com/shorv)

## TechStack
- SpringBoot
- Redis
- Gradle
- Docker

# API Endpoints

## Shorten URL
```
POST /api/v1
Accept: application/json
Content-Type: application/json

{
    "url": "https://github.com/shorv"
}

RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8080/api/v1/{hash}
```
##### Response
```
{
    "hash": "f8a22ca8"
}
```

## Retrieve URL
```
http://localhost:8080/f8a22ca8

Response:
  Success: HTTP 301 (Moved permamently)
  Failure: HTTP 404 (Not found)
```
