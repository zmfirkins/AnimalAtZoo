import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import controller.ListItemHelper;
import model.ListItem;

public class StartProgram {

		static Scanner in = new Scanner(System.in);
		static ListItemHelper lih = new ListItemHelper();

		private static void addAnItem() {
			// TODO Auto-generated method stub
			System.out.print("Enter an animal: ");
			String animal = in.nextLine();
			System.out.print("Enter a classification: ");
			String item = in.nextLine();
			ListItem toAdd = new ListItem(animal, item);
			lih.insertItem(toAdd);
		}


		private static void deleteAnItem() {
			// TODO Auto-generated method stub
			System.out.print("Enter the animal to delete: ");
			String animal = in.nextLine();
			System.out.print("Enter the classification to delete: ");
			String item = in.nextLine();
			ListItem toDelete = new ListItem(animal, item);
			lih.deleteItem(toDelete);

		}

		private static void editAnItem() {
		    System.out.println("How would you like to search? ");
		    System.out.println("1 : Search by Animal");
		    System.out.println("2 : Search by Classification");
		    int searchBy = in.nextInt();
		    in.nextLine();
		    
		    List<ListItem> foundItems = new ArrayList<ListItem>();
		    
		    if (searchBy == 1) {
		        System.out.print("Enter the animal name: ");
		        String animalName = in.nextLine();
		        foundItems = lih.searchForItemByStore(animalName);
		    } else if (searchBy == 2) {
		        System.out.print("Enter the classification name: ");
		        String className = in.nextLine();
		        foundItems = lih.searchForItemByItem(className);
		    } else {
		        System.out.println("Invalid choice. Please enter 1 or 2 to search.");
		        return; // Exit the method if the choice is invalid.
		    }

		    if (!foundItems.isEmpty()) {
		        System.out.println("Found Results.");
		        for (ListItem l : foundItems) {
		            System.out.println(l.getId() + " : " + l.toString());
		        }
		        System.out.print("Which ID to edit: ");
		        int idToEdit = in.nextInt();
		        in.nextLine();

		        // Find the selected item to edit by its ID
		        ListItem toEdit = null;
		        for (ListItem item : foundItems) {
		            if (item.getId() == idToEdit) {
		                toEdit = item;
		                break; // Exit the loop once the item is found.
		            }
		        }

		        if (toEdit != null) {
		            System.out.println("Retrieved " + toEdit.getClass() + " from " + toEdit.getAnimal());
		            System.out.println("1 : Update Animal");
		            System.out.println("2 : Update Classification");
		            int update = in.nextInt();
		            in.nextLine();

		            if (update == 1) {
		                System.out.print("New Animal: ");
		                String newAnimal = in.nextLine();
		                toEdit.setAnimal(newAnimal);
		            } else if (update == 2) {
		                System.out.print("New Classification: ");
		                String newClass = in.nextLine();
		                toEdit.setItem(newClass);
		            }

		            lih.updateItem(toEdit);
		        } else {
		            System.out.println("Classification not found with the specified ID.");
		        }
		    } else {
		        System.out.println("No results found.");
		    }
		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			runMenu();

		}

		public static void runMenu() {
			boolean goAgain = true;
			System.out.println("--- Zoo Directory! ---");
			while (goAgain) {
				System.out.println("*  Select an item:");
				System.out.println("*  1 -- Add an animal");
				System.out.println("*  2 -- Edit an animal type");
				System.out.println("*  3 -- Delete an animal type");
				System.out.println("*  4 -- View the zoo list");
				System.out.println("*  5 -- Exit the awesome program");
				System.out.print("*  Your selection: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addAnItem();
				} else if (selection == 2) {
					editAnItem();
				} else if (selection == 3) {
					deleteAnItem();
				} else if (selection == 4) {
					viewTheList();
				} else {
					lih.cleanUp();
					System.out.println("   Goodbye!   ");
					goAgain = false;
				}

			}

		}

		private static void viewTheList() {
			List<ListItem> allItems = lih.showAllItems();
			for(ListItem singleItem: allItems){
				System.out.println(singleItem.returnItemDetails());
	
		}

	}
}