// ------------------------------------------------------- 
// Assignment (0)
// Question: Part I&II
// Written by: Jingnan Wang 40282296
// --------------------------------------------------------
import java.util.Scanner; //import this class

public class Book {

	//data attribute
	private String title;
	private String author;
	private long ISBN;
	private double price;
	private int index;

	private static int currentbooknum = 0;
	private static final String password = "249";
	private static int wrongTries = 0;
	private static Book[] inventory;

	//default constructor
	public Book() {
		this.title="";
		this.author="";
		this.ISBN=0;
		this.price=0;
		this.index=Book.currentbooknum;
		Book.currentbooknum+=1;
	}
	
	//setters 
	public void setTitle(String title) {
		this.title=title;
	}
	public void setAuthor(String author) {
		this.author=author;
	}
	public void setISBN(long ISBN) {
		this.ISBN=ISBN;
	}
	public void setPrice(double price) {
		this.price=price;
	}
	
	public static int findNumberOfCreatedBooks() {
		//return the number of created Book objects prior to the time the method is called
		return Book.currentbooknum;
		
	}

	public boolean equals(Book book1,Book book2) {
		return book1.price==book2.price && book1.ISBN==book2.ISBN;
	}

	public String toString() {
		return "Book # " + this.index + "\n" + "Author: " + this.author + "\n" + "Title: " + this.title + "\n" + "ISBN: " + this.ISBN + "\n" + "Price: " + this.price + "\n";
	}

	
	private static int DisplayMenu() {
		Scanner a = new Scanner(System.in);
		
		System.out.println("What do you want to do?");
		System.out.println("\t1. Enter new books (password required)");
		System.out.println("\t2. Change information of a book (password required)");
		System.out.println("\t3. Display all books by a specific author");
		System.out.println("\t4. Display all books under a certain a price");
		System.out.println("\t5. Quit");
		System.out.println("Please enter your choice > ");

		String choice = a.next();
		
		//if choice isn't 1 to 5, loop until get valid choice
		while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4") && !choice.equals("5") ) {
			System.out.println("Please re-enter a valid choice between 1 and 5 > ");
			choice = a.next();
			
		}
		
		//convert string choice to an int option
		int option=Integer.parseInt(choice);

		return option;
		
	}

	private static boolean Password3tries() {
		Scanner s = new Scanner(System.in);
		//loop for 3 tries 
		for (int attempt=1; attempt<4; attempt++) {
			System.out.println("Please enter your password: ");
			String input = s.next();

			if (input.equals(Book.password)) {
				System.out.println("CORRECT password! ");
				return true;
			}
		}
		
		return false;
		

	}

	private static void NewBooks() {
		Scanner d = new Scanner(System.in);

		//loop for 4 tries of loading menu
		for (int attempt=1; attempt<5; attempt++) {
			
			//determine if password is correct in loop for 3 tries 
			boolean passOk=Book.Password3tries();

			if (passOk) {
				System.out.println("You can create new books now!");
				//input for object loop array
				System.out.println("Enter the number of books you want to enter: ");
				int BookstoAdd=d.nextInt();

				//check if books to be added have enough space 
				if (BookstoAdd+Book.currentbooknum<=inventory.length) {
					
					//for loop to enter input for books based on BookstoAdd number
					for (int i=0; i < (BookstoAdd); i++) {

						int index = currentbooknum;

						System.out.println("Book# " + Book.currentbooknum);

						Book.inventory[index]=new Book();

						System.out.print("Enter the title: ");
						String t= d.next();
						inventory[index].setTitle(t);

						System.out.print("Enter the author: ");
						String a = d.next();
						inventory[index].setAuthor(a);

						System.out.print("Enter the ISBN: ");
						long isbn = d.nextLong();
						inventory[index].setISBN(isbn);
			
						System.out.print("Enter the price: ");
						double p = d.nextDouble();
						inventory[index].setPrice(p);

					}
					

				}else {//not enough space 
					int restBookPlaces=inventory.length-Book.currentbooknum;
					System.out.println("The maximum remaining places in your bookstore inventory is " + restBookPlaces);
				}

				break;
			}else {
				Book.wrongTries+=3;//3 wrong tries for each loop of menu  
				if (wrongTries == 12) {//12 tries and exit
				System.out.println("Program detected suspicious activities and will terminate immediately!");
				System.exit(0);
				}
				return;
			}
			
		}

		

	}

	private static void ChangeInfo() {
		Scanner f = new Scanner(System.in);

		//loop for 3 tries if want to change info for the book
		boolean passOK = Book.Password3tries();

		if (passOK) {
			System.out.println("Enter the book number that you wish to update: ");
			int index = f.nextInt(); 

			//check if book number doesn't exist
			if (inventory[index]==null) {
				System.out.println("This book number doesn't exist. Do you wish to 1. re-enter another book or 2. quit and go back to the main menu? (1 or 2) ");
				int choice = f.nextInt();

				if (choice == 1) {
					//reenter another book
					Book.NewBooks();
					return;
				}else if (choice == 2) {
					//go back to previous method (display menu)
					return;
				}
			}

			//print the book 
			System.out.println(inventory[index]);

			int infoToChange;
			
			do {
				//display fig2 update menu 
				infoToChange = Book.UpdateMenu();

				switch (infoToChange) {
					case 1: {
						System.out.println("Please enter modification for the author: ");
						String newAuthor = f.next();
						inventory[index].setAuthor(newAuthor);
						break;
					}
					case 2: {
						System.out.println("Please enter modification for the title: ");
						String newTitle = f.next();
						inventory[index].setTitle(newTitle);
						break;
					}
					case 3: {
						System.out.println("Please enter modification for the ISBN: ");
						long newISBN = f.nextLong();
						inventory[index].setISBN(newISBN);
						break;
					}
					case 4: {
						System.out.println("Please enter modification for the price: ");
						double newPrice = f.nextDouble();
						inventory[index].setPrice(newPrice);
						break;
					}
					case 5: {
						return;
					}
				}

				//print updated book 
				System.out.println(inventory[index]);

			} while (infoToChange != 5);//prompt menu until user enter 5 to exit

		}else {
			return;
		}


	}


	private static int UpdateMenu() {
		Scanner q = new Scanner(System.in);
		
		System.out.println("What information would you like to change?");
		System.out.println("\t1. author");
		System.out.println("\t2. title");
		System.out.println("\t3. ISBN");
		System.out.println("\t4. price");
		System.out.println("\t5. Quit");
		System.out.println("Enter your choice > ");

		String choice = q.next();
		
		//if choice isn't 1 to 5, loop until get valid choice
		while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4") && !choice.equals("5") ) {
			System.out.println("Please re-enter a valid choice between 1 and 5 > ");
			choice = q.next();
			
		}
		
		//convert string choice to an int option
		int option=Integer.parseInt(choice);


		return option;

	}

	private static void SpecificAuthor() {
		Scanner t = new Scanner(System.in);

		System.out.println("Enter the author's name: ");
		String name = t.next();

		//loop to search the books with the asked author's name
		for (int i=0; i<Book.currentbooknum; i++){
			if (inventory[i].author.equalsIgnoreCase(name)){
				System.out.println(inventory[i]);
			}
		}
	}

	private static void UnderPrice() {
		Scanner p = new Scanner(System.in);

		System.out.println("Enter the price: ");
		double maxPrice = p.nextDouble();

		//loop to search the books under the asked price 
		for (int i=0; i<Book.currentbooknum; i++){
			if (inventory[i].price < maxPrice){
				System.out.println(inventory[i]);
			}

		}

	}

	private static void OptionReation() {
		
		int option;
		do {
			option = Book.DisplayMenu();

			switch (option) {
			case 1: {
				System.out.println("option 1: enter new books ");
				Book.NewBooks();
				
				break;
			}
			case 2: {
				System.out.println("option 2: change info for books");
				Book.ChangeInfo();
				break;
			}
			case 3: {
				System.out.println("option 3: books of specific author");
				Book.SpecificAuthor();	
				break;
			}
			case 4: {
				System.out.println("option 4: books under certain price");
				Book.UnderPrice();
				break;
			}
			case 5: {
				System.out.println("Thank you for using this program!");
				System.exit(0);
				break;
			}
			
			}

		} while (option != 5);//loop until user enter 5 to quit program


	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);

		//welcome message
		System.out.println("Welcome to this program that keeps track of books in the bookstore! (by Jingnan Wang)");
		
		//input for maxBooks (size of array)
		System.out.print("Please enter the maximum number of books that your bookstore can contain: ");
		int maxBooks=scanner.nextInt();
		
		//set the size of inventory array 
		Book.inventory = new Book[maxBooks];
		
		//call method to determine the chosen option
		Book.OptionReation();

		scanner.close();
	}

}
