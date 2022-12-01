# My Personal Project

## Library Management System Proposal 

This is a library management system application that is responsible for managing the libraries’ borrowing system online.
Users can see the books available, borrow books, return them. They can also search for their desired books by genre and 
see details about the book they are getting online including author, name and genre. The administrators will be given access
to the user accounts and have the option to create a user account for a customer, deleting accounts, and adding or 
updating data in the system. To keep everything organized, managers can keep a secure record online that tracks where 
the books are. Public libraries that want to operate online and have the option of lending out books will be the main
users of this application.

This project is of interest to me since I am a bookworm myself and really enjoy reading books and seeing reviews. I would 
love the option to conduct a book search online and view the options a library has to offer without physically visiting it. 
I believe this would be way more efficient and save a lot of time for both customers and managers.  Additionally, it is 
also an excellent way to monitor books, lower the risk of error and reduce the chance of losing paper-based documents. 
Moreover, it makes the whole process of getting the books more accessible and available to a wider variety of people.


## User stories:
-   As a user, I want to be able to be able to see the list of books available in the library.
-	As a user, I want to be able to add a book to my cart.
-	As a user, I want to be able to view the list of books in my cart.
-	As a user, I want to be able to return a book. 
-	As a user, I want to be able to search for books by genre.
-   As a user, I want to be able to save my books in my checkout cart and new books added to library.
-   As a user, I want to be able to be given the option to load my previous user history (book cart and new books added) from file. 

# Instructions for Grader
- You can see the list of Xs added to a Y by clicking the "View Books" button.
- You can generate the first required event related to adding Xs to a Y by clicking on the "Checkout Book" button to check out
a book from the library, or "Add Book" button to add a book to the library. 
- These changes are displayed by clicking "View Books" button or "View Cart" button. 
- You can generate the second required event related to adding Xs to a Y by clicking on the "Search By Genre" button to see the
filtered books available in the library according to the genre input by user. 
- You can locate my visual component by seeing the splash screen when the application starts or seeing icons in dialog boxes. 
- You can save the state of my application by trying to close the application by clicking on the x button and clicking "yes" for wanting 
to save the current user data. 
- You can reload the state of my application by clicking on the "Load" button when the application starts in the button panel on the left.

# Phase 4: Task 2 Logged Events:
Thu Nov 24 09:38:23 PST 2022
<br> Searched for genre Fantasy
<br> Thu Nov 24 09:38:30 PST 2022
<br> Checked out book: Harry Potter
<br> Thu Nov 24 09:38:35 PST 2022
<br> Returned book: Harry Potter
<br> Thu Nov 24 09:38:49 PST 2022
<br> Added book: November 9 

# Phase 4: Task 3 UML Diagram

# Phase 4: Task 4 Refactoring:
- If I had more time I would do the following refactoring to my code:
- Since there are repetitive methods in my UI class, all using the dialog boxes, I would refactor
