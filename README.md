### ___Backend Java-project:___
# Railway ticket booking service
___
## What technologies were used:
- Spring IoC
- Spring MVC
- Spring Security
- Spring Data
- Spring Boot
- JPA/Hibernate(PostgreSQL, MySQL)
- Front-End(HTML/CSS/Thymeleaf)

## List of microservices:
- Registration
- Login
- Personal Area
- basket of all tickets
- list of all trains (only for admin role)
- Search
- Booking
- changing and deleting a ticket

## Description
The user goes to the initial screen, where they need to go through authorization.
If the user has not yet been created, you need to go to the registration page and 
create a new user, then the program will redirect you back to the authorization page 
and enter the data of the newly created user there. All new users are created with 
the user role. By default, the user username:admin, password:admin with the admin 
role is available.

on the admin page there is an opportunity to see a list of all trains in the system.
Users can log in to make a reservation, cancel a reservation, or inquire about train schedules.
Only on the admin page is it possible to see a list of all trains in the system.
Users can log in and enter their additional data (first name, last name, change login and password), 
these data will be used when filling out the ticket. You can also go directly to the search for a 
train at the stations of interest, followed by a choice of place, date and passenger data. The booked 
flight will appear in the basket of tickets, where they can be changed and deleted.

## Author
Verheles Mykola
