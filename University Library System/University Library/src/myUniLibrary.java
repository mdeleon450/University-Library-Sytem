//@Author: Maik De Leon Lopez
//@Date:   02/24/2019
//@Course: CSC 311-01

import java.util.*;

public class myUniLibrary {

	public static void main(String[] args) {
		DHArrayList<Object> uniLib = new DHArrayList<>();		//Create the Array List to store the books
		initializeBooks(uniLib);								//Create the random books with constraints
		Scanner reader = new Scanner(System.in);				//Used to receive user input
		Boolean sentinel = true;								//Until this flag is turned true we will prompt the user for input
		System.out.println("Welcome to the University Library Please Select One of the Following (1-8)");
		while(sentinel) {
			System.out.println("1. List all the books\n2. Display the books sorted according to the year of publication \n3. Sort the books according to length in pages, starting with the shortest\n4. Sort the books according to review ratings, starting with the highest rating\n5. Ask user for a subject, and display all the books belonging to that specific subject\n6. Search for a specific book by name, and display all the details if the book exists\n7. Add a book to the list of books (ask the user for all the details\n8. Exit ");
			int selector = reader.nextInt();					//Read the user's selection
			if(selector == 8) {									//Set the flag to false if 8 is selected so we quit the program
				sentinel = false;
			}
			selectOption(uniLib, selector);						//Have the option selected executed
		}
	}
	
	//This method executes the option selected once done it returns to the prompt loop
	public static void selectOption(DHArrayList uniLib, int selector) {
		String title;
		String subject;
		int year;
		int pages;
		double rating;
		Books tempBook;		//used later on to hold the books data for comparison
		Books tempBook2;
		boolean flag = false;	//Used only for option 6 in order to tell the user the book was not found
		Scanner userinput = new Scanner(System.in);	//Necessary for the user input for options 5,6,and 7

		switch(selector) {
			case 1:		
				//List all the Books
				System.out.println("Books: ");
				System.out.println(uniLib+"\n"); 	//If option 1 is selected print all books, formatting for this is in the Books class(Overwritten toString)
				break;
			case 2:
				//Display the Books Sorted According to Year of Publication, Starting with the Oldest One
				System.out.println("Sorted by Year (Acending)");
				for(int i = 0; i < uniLib.getSize(); i++) {			//Looked at the bubble sort in https://www.geeksforgeeks.org/bubble-sort/ 
					for(int j = 0; j < uniLib.getSize()-1; j++) {	//Manipulated the implementation to accommodate for book objects inside of an ArrayList
						tempBook = (Books) uniLib.get(j);
						tempBook2 = (Books) uniLib.get(j+1);		//Compare the current element to the next
						if(tempBook.getYear()>tempBook2.getYear()) {	//If current year is larger than the next one
							uniLib.remove(j);							//Then remove current and add it as to the next position (the remove method already shifts all to the left)
							uniLib.add(j+1,tempBook);					//e.g (1980 is smaller than 2019) so sorting small to big
						}
					}
				}
				System.out.println(uniLib+"\n");				//Print out the newly sorted ArrayList
				break;
			case 3:
				//Sort the Books According to Length in Pages, Starting with the Shortest
				for(int i = 0; i < uniLib.getSize(); i++) {
					for(int j = 0; j < uniLib.getSize()-1; j++) {
						tempBook = (Books) uniLib.get(j);
						tempBook2 = (Books) uniLib.get(j+1);			//The same thought process as the previous except we are comparing the pages
						if(tempBook.getPages()>tempBook2.getPages()) {
							uniLib.remove(j);
							uniLib.add(j+1,tempBook);
						}
					}
				}
				System.out.println(uniLib+"\n");
				break;
			case 4:
				//Sort the Books According to Review Ratings, Starting with the Highest Rating
				for(int i = 0; i < uniLib.getSize(); i++) {
					for(int j = 0; j < uniLib.getSize()-1; j++) {
						tempBook = (Books) uniLib.get(j);
						tempBook2 = (Books) uniLib.get(j+1);
						if(tempBook.getRating() < tempBook2.getRating()) {		//Same loop as before but we want Greatest to Least 
							uniLib.remove(j+1);					//If current rating is less than the next one move the next one to the current one's postion
							uniLib.add(j,tempBook2);
						}
					}
				}
				System.out.println(uniLib+"\n");		
				break;
			case 5:
				//Ask User for a Subject, and Display All the Books Belonging to that Specific Subject
				System.out.println("Enter the Subject: ");
				subject = userinput.nextLine();
				for(int i = 0 ; i < uniLib.getSize(); i++) {
					tempBook = (Books) uniLib.get(i);
					if(subject.equalsIgnoreCase(tempBook.getSubject())) {	//If any of the books' subjects equals the desired subject 
						System.out.println(tempBook);			//Print out only that book
						flag = true;			//We found at least one book with the subject
					}
				}
				System.out.println();
				if (flag!=true) {	//We didnt find a book with the subject
					System.out.println("Was unable to find the Subject: "+subject);
				}
				break;
			case 6:
				//Search for a Specific Book by Name, and Display All the Details if the Book Exists
				System.out.println("Enter the Book Title");
				title = userinput.nextLine();
				for(int i = 0 ; i < uniLib.getSize(); i++) {
					tempBook = (Books) uniLib.get(i);
					if(title.equalsIgnoreCase(tempBook.getTitle())) {	//If the title specified is equal to any of the book's titles 
						System.out.println(tempBook);		//Print out the book
						flag = true;	//We found the book
					}
				}
				System.out.println();
				if(flag!=true) {	//We didn't find the book
					System.out.println("Was unable to find the Book: "+title);
				}
				break;
			case 7:	
				//Add a Book to the List of Books (Ask the User for All the Details)
				System.out.println("Enter the Title: ");
				title = userinput.nextLine();
				System.out.println("Enter the Subject: ");
				subject = userinput.nextLine();
				System.out.println("Enter Year (From 1980 to 2019): ");
				year = userinput.nextInt();
				System.out.println("Enter Number of Pages (From 50 to 1000): ");
				pages = userinput.nextInt();
				System.out.println("Enter Rating: ");
				rating = userinput.nextDouble();
				if(year >= 1980 && year <=2019 && pages>=50 && pages<=1000 && rating>=0.1 && rating<=10.0) {	//If the book entered falls into the constraints add the book (any subject is fine)
					Books userBook = new Books(title,year,pages,subject,rating);		//The year is not checked for uniqueness because 
					uniLib.add(userBook);
				}
				else {
					System.out.println("Invalid Input Try Again");
				}
				System.out.println();
				break;
			case 8:
				//Quit the Program so Do Nothing
				break;
			default:
				//If the Selector is not 1-8 then Display Error Message
				System.out.println("Please Enter Options 1-8");
				break;
		}
	}
	
	//This Method Initializes the Books and adds them to the ArrayList
	public static void initializeBooks(DHArrayList uniLib) {
		DHArrayList<Integer> yearsList = new DHArrayList<>(40);	//Used an ArrayList to hold all the possible years from 1980 to 2019
		for(int i = 0 ; i < 40 ; i++ ) {
			yearsList.add(1980+i);
		}
		DHArrayList<Integer> subjectList = new DHArrayList<>(25);	//Used an ArrayList to hold all the possible subjects (they are represented by numbers and the Books class interprets the number)
		for(int i = 0 ; i < 25; i++) {
			if(i <=4) {					
				subjectList.add(0);		//Adds 5 copies of 0, 1, 2, 3, 4 to the AL (No more than 5 books in one Subject)
			}
			else if(i<=9) {
				subjectList.add(1);
			}
			else if(i<=14) {
				subjectList.add(2);
			}
			else if(i<=19) {
				subjectList.add(3);
			}
			else if(i<=24){
				subjectList.add(4);
			}
		}
		Books[] libBooks = new Books[20];	//Create an array of Books to make it easier to instantiate them 
		for(int i = 0; i < 20; i++) {		//Create 20 Books
			String s = "Book#"+(i+1);			//Name them Book#1 to Book#20
			int year=(int)(Math.random()*(39-i));	//get a random number from 0 to 39-i, since every time we remove an element we have one less to choose from 
			year = yearsList.remove(year);			//remove the year we got from the Array List making it unavailable to the other books (making it unique)
			int subjectNum = (int)(Math.random()*(25-i));	//random number from 0 to 25-i
			subjectNum = subjectList.remove(subjectNum);	//remove the subject from the AL
			libBooks[i] = new Books(s,year,subjectNum);		//Creates a new book with the title, year and subject chosen
			
			uniLib.add(libBooks[i]);		//adds the book to the main AL
		}
	}

}
