import java.util.*;
public class index {

    public static class Book {
        String name;        String author;
        boolean available;      Book next;
        Book(String name, String author, boolean available) {
            this.name = name;
            this.author = author;
            this.available = available;
            this.next = null;
        }
    }

    public static void printBooks() {
        Book temp = head;
        System.out.println("The Books registered are :- ");
        while(temp != null) {
            System.out.println("Name :- " + temp.name + "   ,   Author :- " + temp.author + "   ,   available :- " + temp.available);
            temp = temp.next;
        }
    }

    public static Book findBook(String name, int n) {
        Book temp = head;
        while(temp != null) {
            if(name.equalsIgnoreCase( ((n==1) ? temp.name : temp.author) )) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    public static void deleteBook(String name, int n) {
        Book temp = head;   
        if(name.equalsIgnoreCase( ((n==1) ? head.name : head.author ) )) {
            head = head.next;
            System.out.println("Book Deleted Successfully !");
            return;
        }

        while(temp.next != null) {
            if(name.equalsIgnoreCase( ((n==1) ? temp.next.name : temp.next.author) )) {
                temp.next = temp.next.next;
                System.out.println("Book Deleted Successfully !");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found");
    }

    public static Book head;
    public static Book tail;

    public static void main(String[]args) {

        Scanner sc = new Scanner(System.in);

        while(true) {

            System.out.println("-------------------------------------------------------------------------");
            System.out.println("Enter 1 for Adding a New Book");
            System.out.println("Enter 2 for Listing all the Books");
            System.out.println("Enter 3 for Finding a Book");
            System.out.println("Enter 4 for Deleting a Book");
            System.out.println("Enter 5 for Borrowing a Book");
            System.out.println("Enter 6 for Returning a Book");
            System.out.println("Enter 7 to Exit");
            System.out.print("Enter your choice :- ");
            int choice = sc.nextInt();
            sc.nextLine();

            if(choice == 1) {                   // Adding a Book

                System.out.print("Enter Book Name :- ");
                String bookName = sc.nextLine();
                System.out.print("Enter Book Author :- ");
                String bookAuthor = sc.nextLine();

                Book newBook = new Book(bookName , bookAuthor , true);

                if(head == null) {
                    head = newBook;
                    tail = newBook;
                } else {

                    if(bookName.compareTo(head.name) < 0) {     // Smaller than first book
                        newBook.next = head;
                        head = newBook;
                        continue;
                    }
                    else if(bookName.compareTo(tail.name) > 0 ) {   // Greater than Last Book
                        tail.next = newBook;
                        tail = newBook;
                        continue;
                    }

                    Book temp = head;
                    while(temp != null && temp.next != null) {
                        if(bookName.compareTo(temp.next.name) < 0 ) {
                            newBook.next = temp.next;
                            temp.next = newBook;
                            break;
                        }
                        temp = temp.next;
                    }
                }
            }

            else if(choice == 2) {              // Listing all the Books
                printBooks();
            }

            else if(choice == 3) {              // Finding a Book
                System.out.print("Enter 1 to Search by Name, 2 to search by Author :- ");
                int n = sc.nextInt();       sc.nextLine();
                System.out.print("Enter the " + ((n==1) ? "Book Name " : "Author Name ") + ":- ");
                String s = sc.nextLine();
                
                Book temp = findBook(s,n);

                if(temp == null) {
                    System.out.println("Book not Found");
                } else {
                    System.out.println("Book Found!");
                    System.out.println("Name :- " + temp.name + "   ,   Author :- " + temp.author + "   ,   available :- " + temp.available);
                }
            }

            else if(choice == 4) {              // Deleting a Book
                System.out.print("Enter 1 to Search by Name, 2 to search by Author :- ");
                int n = sc.nextInt();       sc.nextLine();
                System.out.print("Enter the " + ((n==1) ? "Book Name " : "Author Name ") + ":- ");
                String s = sc.nextLine();
                
                deleteBook(s,n);
            }

            else if(choice == 5) {              // Borrowing a Book
                System.out.print("Enter the name of Book to be borrowed :- ");
                String s = sc.nextLine();

                Book temp = findBook(s,1);

                if(temp == null) {
                    System.out.println("Book not Found");
                }
                else if( temp.available == false) {
                    System.out.println("Book already taken");
                }
                else {
                    temp.available = false;
                    System.out.println("Book Issued Successfully !");
                }
            }

            else if(choice == 6) {              // Returning a book
                System.out.print("Enter the name of Book to be Returned :- ");
                String s = sc.nextLine();

                Book temp = findBook(s,1);

                if(temp == null) {
                    System.out.println("Book not Found");
                }
                else {
                    temp.available = true;
                    System.out.println("Book returned Successfully !");
                }
            }
            
            else if(choice == 7) {              // Exit
                break;
            }

            else {                              // Invalid Choice
                System.out.println("Invalid Choice! Please try again.");
            }

        }
    }
}