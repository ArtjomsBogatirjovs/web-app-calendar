# web-app-calendar
Basic schedule to reserve time slots

## Installation

You need to install postgresql \

Create a database `calendar` 

`CREATE DATABASE calendar;`

Give the priviledges to your role or use the role 'postgres'

`GRANT ALL PRIVILEGES ON DATABASE "calendar" TO "postgres";`

Connect to the database "calendar"

`\c calendar postgres` with postgres your role 

Give a password to your role to match the one in config.properties in webcalendar/src/main/resources 

`ALTER USER postgres WITH PASSWORD 'password'`


## Config
Pick the role: 'postgres' \
And set your password for your role to : 'password' \
Or \
You can change the config.properties in webcalendar folder \
If you want to set another username or password according to your role 

## front-calendar (front end)

### `npm install`

Run this command to install the module packages needed for the front end

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser. 


## webcalendar (Back end)

Run the WebcalendarApplication.java file\
Now you can make requests on http://localhost:8080/url (url could be availabilities/get for example) \
Or simply open `Request.http` to test the requests on the backend if you use `intellij idea` \
Or use`Postman` and copy the request present in `Request.http`
