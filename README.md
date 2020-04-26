# Runtime Environment:
1. Linux
2. Java 11
3. sbt 1.3.10
4. A PostgreSQL 9.6 database filled with the time zones data from 

http://www.naturalearthdata.com/downloads/10m-cultural-vectors/timezones/

in database "timezones_db" and a user "timezones_user" having access to it with password "pass".

# To run:
1. sbt run


# To view:
Open a browser and type in
"http://127.0.0.1:9000/v1/timeForLatLng/{latutude},{longitude}"
e.g.
"http://127.0.0.1:9000/v1/timeForLatLng/-22,-43.17" (Rio de Janeiro),
"http://127.0.0.1:9000/v1/timeForLatLng/22.39,114.11" (Hong Kong),
"http://127.0.0.1:9000/v1/timeForLatLng/53.55,9.99" (Hamburg)
for the time zone information.
