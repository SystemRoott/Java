package HomeWork_0203;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;


public class MainClass {

	static StackLinkedList stack = new StackLinkedList();

	public static void main(String[] args) {
		MainMenu();
	}
	private static void PressEnter() {
		try {
			System.out.println("Press Enter for continue");
			System.in.read();
		}catch (Exception err) {
			System.out.println(err.getMessage());
		}
	}
	
	private static void MainMenu() {
		try {
			Scanner input = new Scanner(System.in);
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			int choose = 0;
			do {
				System.out.println("******** StackLinkedList Operations ********");
				System.out.println("1 - Addition Operations ");
				System.out.println("2 - Remove Operations ");
				System.out.println("3 - Count Elements ");
				System.out.println("4 - Find Element ");
				System.out.println("5 - Print");
				System.out.println("6 - Help");
				System.out.println("7 - Exit");
				System.out.print("-> ");
				choose = input.nextInt();
				switch (choose) {
				case 1: {
					System.out.println("***************");
					System.out.print("Name: ");
					String name = input.next();
					System.out.print("Surname: ");
					String surname = input.next();
					System.out.print("Birthdate: ");
					String birthDate = input.next();
					System.out.print("Phone Number: ");
					long phoneNumber = input.nextLong();
					Date thedate = format.parse(birthDate);
					stack.add(new Element(name, surname, thedate, phoneNumber));
					System.out.println("***************");
					break;
				}
				case 2: {
					stack.remove();
					break;
				}
				case 3: {
					System.out.println("Stack Count:");
					System.out.println(stack.Size());
					PressEnter();
					break;
				}
				case 4: {
					MenuFind();
					PressEnter();
					break;
				}
				case 5: {
					stack.printData();
					break;
				}
				case 6: {
					System.out.println("Date Format is dd-MM-yyyy");
					PressEnter();
					break;
				}
				case 7: {
					System.out.println("Good Bye.. ");
					System.exit(0);
				}

				default: {
					System.out.println("Wrong Choose. Try Again");
					break;
				}
				}
			} while (choose != 7);

		} catch (Exception err) {
			System.out.println(err.getMessage());
		}
	}


	private static void MenuFind() {
		try {
			Scanner input = new Scanner(System.in);
			int choose = 0;
			do {
				System.out.println("******** Search Operations ********");
				System.out.println("1 - Search for Name ");
				System.out.println("2 - Search for BirthDate ");
				System.out.println("3 - Search for Phone Number ");
				System.out.println("4 - Print ");
				System.out.println("5 - Back ");
				System.out.print("-> ");
				choose = input.nextInt();
				switch (choose) {
				case 1: {
					System.out.print("Name: ");
					String name = input.next();
					int index =stack.findElement(name);
					if(index == -1)
					{
						System.out.println("Error: '" + name + "' Could not be found");
					}
					else {
						System.out.println("The serched name is " + index + " in the stack");
					}
					
					break;
				}
				case 2: {
					System.out.print("Birthdate: ");
					String birthDate = input.next();
					Date thedate = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(birthDate);
					int index =stack.findElement(birthDate);
					if(index == -1)
					{
						System.out.println("Error: '" + birthDate + "' Could not be found");
					}
					else {
						System.out.println("The serched birthdate is " + index + " in the stack");
					}
					break;
				}
				case 3: {
					System.out.print("Phone Number: ");
					long phoneNumber = input.nextLong();
					int index =stack.findElement(phoneNumber);
					if(index == -1)
					{
						System.out.println("Error: '" + phoneNumber + "' Could not be found");
					}
					else {
						System.out.println("The serched phone number is " + index + " in the stack");
					}
					break;
				}
				case 4: {
					stack.printData();
					break;
				}
				case 5: {
					break;
				}
				default: {
					System.out.println("Wrong Choose. Try Again");
					break;
				}
				}
			} while (choose != 5);

		} catch (Exception err) {
			System.out.println(err.getMessage());
		}
	}
}


