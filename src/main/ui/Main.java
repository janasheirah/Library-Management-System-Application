package ui;

import model.Book;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private List<Book> listOfBooks;

    public static void main(String[] args) {

        ArrayList listOfBooks = new ArrayList<Book>();
        Book hungerGames = new Book("The Hunger Games", "Suzanne Collins", "Fiction");
        Book harryPotter = new Book("Harry Potter", "J.K. Rowling", "Fiction");
        Book prideAndPrejudice = new Book("Pride And Prejudice", "Jane Austen", "Fiction");
        Book twilight = new Book("Twilight", "Stephanie Meyer", "Fiction");
        Book narnia = new Book("The Chronicles Of Narnia", "C.S. Lewis", "Fiction");

        listOfBooks.add(hungerGames);
        listOfBooks.add(harryPotter);
        listOfBooks.add(prideAndPrejudice);
        listOfBooks.add(twilight);
        listOfBooks.add(narnia);

        Book theFaultInOurStars = new Book("The Fault In Our Stars", "John Green", "Romance");
        Book itEndsWithUs = new Book("It Ends With Us", "Colleen Hoover", "Romance");
        Book divergent = new Book("Divergent", "Veronica Roth", "Romance");
        Book ifIStay = new Book("If I Stay", "Gayle Forman", "Romance");
        Book theNoteBook = new Book("The Notebook", "Nicholas Sparks", "Romance");

        listOfBooks.add(theNoteBook);
        listOfBooks.add(theFaultInOurStars);
        listOfBooks.add(itEndsWithUs);
        listOfBooks.add(divergent);
        listOfBooks.add(ifIStay);

        Book theSilentPatient = new Book("The Silent Patient", "Alex Michaelides", "Mystery");
        Book goneGirl = new Book("Gone Girl", "Gillian Flynn", "Mystery");
        Book verity = new Book("Verity", "Colleen Hoover", "Mystery");
        Book theThursdayMurderClub = new Book("The Thursday Murder Club", "Richard Osman", "Mystery");
        Book goodGirlsGuide = new Book("A Good Girl's Guide To Murder", "Holly Jackson", "Mystery");
        Book oneOfUsIsLying = new Book("One Of Us Is Lying", "Karen M. McManus", "Mystery");

        listOfBooks.add(theSilentPatient);
        listOfBooks.add(goneGirl);
        listOfBooks.add(verity);
        listOfBooks.add(theThursdayMurderClub);
        listOfBooks.add(goodGirlsGuide);
        listOfBooks.add(oneOfUsIsLying);

        Book inColdBlood = new Book("In Cold Blood", "Truman Capote", "Non Fiction");
        Book educated = new Book("Educated", "Tara Westover", "non Fiction");
        Book silentSpring = new Book("Silent Spring", "Rachel Carson", "Non Fiction");
        Book intoThinAir = new Book("Into Thin Air", "Jon Krakauer", "Non Fiction");

        listOfBooks.add(inColdBlood);
        listOfBooks.add(educated);
        listOfBooks.add(silentSpring);
        listOfBooks.add(intoThinAir);

        Book steveJobs = new Book("Steve Jobs", "Walter Isaacson", "Biography");
        Book diaryOfAYoungGirl = new Book("The Diary Of A Young Girl", "Anne Frank", "Biography");
        Book glassCastle = new Book("The Glass Castle", "Jeannette Walls", "Biography");
        Book eatPrayLove = new Book("Eat, Pray, Love", "Elizabeth Gilbert", "Biography");
        Book johnAdams = new Book("John Adams", "David McCullough", "Biography");
        Book beautifulMind = new Book("A Beautiful Mind", "Sylvia Nasar", "Biography");


        listOfBooks.add(steveJobs);
        listOfBooks.add(diaryOfAYoungGirl);
        listOfBooks.add(glassCastle);
        listOfBooks.add(eatPrayLove);
        listOfBooks.add(johnAdams);
        listOfBooks.add(beautifulMind);

        new LibraryApp();

    }
}
