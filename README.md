# My Personal Project

## Library Management System Proposal 

This is a library management system application that is responsible for managing the librariesâ€™ borrowing system online.
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
<br> Note: Event for viewing the books in the library is implemented entirely in GUI, not in model packages therefore it is not in the logged events. 

# Phase 4: Task 3 UML Diagram
Attached in the root folder of the project
# Phase 4: Task 4 Refactoring
<strong> If I had more time I would do the following refactoring to my code: </strong>

- My original plan was to include more actions for a librarian to know more information about users and manage the list of books in the library, 
so I could make a new abstract class called User and have both Librarian and StudentUser extend it since they will probably have a lot of similar behavior.
Note: Now, the librarian class is redundant (since it doesn't have any implementations) and can be removed both from my classes and UML Diagram, but I left it there as a possibility of further implementation and development of the project. 
- Due to the presence of repetitive code in the UI class that hides the panel in all methods, I can extract this behavior into one method and replace the repetitive code with a single call to this method,
  (which improves coupling) or another solution to this is making the main window a JDesktopPane instead of JFrame to include other Java Swing components in it, and they can close by themselves without the need for a method to do so.
- In order for my design to follow the single responsibility principle, I can move some of the methods that have to do with adding books to the library to the librarian class for less coupling and
the library class can then only be responsible for the methods directly related to it like searching for books by genres and titles.
- In expanding my project to include more features in the user interface, I would use the composite design pattern to design the frame and have a Component class
and a composite class where I can add panels and other children including buttons, internal frames, dialog boxes and child panels to it, in order to improve cohesion. This can also remove the presence
of nested classes in the UI which is making the class quite long.
