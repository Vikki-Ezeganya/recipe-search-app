import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private FileReader fileReader;


    public UserInterface(Scanner scanner, FileReader fileReader) {
        this.scanner = scanner;
        this.fileReader = fileReader;
    }

    public void start() {

        String file = null;
        while (true) {
            System.out.print("File to read: ");

            file = scanner.nextLine();
            System.out.println();


            System.out.println("Commands:" + "\n" + "list - lists the recipes" + "\n" + "stop - stops the program "
                + "\n" + "find name - searches recipes by name" + "\n" + "find cooking time - searches the recipes by" +
                    " cooking time" + "\n" + "find ingredient - searches recipes by ingredient");
            System.out.println();
            System.out.println(fileReader.readFile(file));
            issueCommand(file);
            break;

        }

    }

    public void printRecipeAndTimeOfPreparation() {

        String[] recipeAndTime = fileReader.getRecipeAndTimeOfPreparation();
        System.out.println(recipeAndTime[0] + ", " + "cooking time:" + recipeAndTime[1]);
        System.out.println(recipeAndTime[2] + ", " + "cooking time:" + recipeAndTime[3]);
        System.out.println(recipeAndTime[4] + ", " + "cooking time:" + recipeAndTime[5]);

    }

    public void printRecipeAndTimeOfPreparation(List list) {

        System.out.println(list.get(0)+ ", " + "cooking time:" + list.get(1));
        System.out.println(list.get(2)+ ", " + "cooking time:" + list.get(3));
        System.out.println(list.get(4)+ ", " + "cooking time:" + list.get(5));

    }

    public void issueCommand(String file) {

        System.out.println();
        System.out.print("Enter command: ");
        String command = scanner.nextLine();
        System.out.println();

        if(command.equals("stop")) {
            System.exit(0);
        } else if(command.equals("list")) {

            System.out.println("Recipes:");
            List recipeAndTime = fileReader.read(file);
            printRecipeAndTimeOfPreparation(recipeAndTime);
            System.out.println();
            issueCommand(file);

        } else if(command.equals("find name")) {
            System.out.print("Searched word:");
            String recipeName = this.scanner.nextLine();
            fileReader.findRecipeByName(file, recipeName);
            System.out.println(fileReader.findRecipeByName(file, recipeName));
            issueCommand(file);

        } else if (command.equals("find cooking time")) {
            System.out.print("Max cooking time:");
            int maxCookingTime = Integer.valueOf(this.scanner.nextLine());
            List<String> recipes = fileReader.findRecipeByCookingTime(file, maxCookingTime);
            recipes.forEach(System.out ::println);
            issueCommand(file);

        } else if(command.equals("find ingredient")) {
            System.out.print("Ingredient: ");
            String ingredient = this.scanner.nextLine();
            List<Recipe> recipes = fileReader.findRecipeByIngredent(file, ingredient);
            recipes.forEach(System.out::println);
            issueCommand(file);
        }

    }


}

