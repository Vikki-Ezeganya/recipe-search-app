
import java.util.ArrayList;
import java.util.Scanner;

public class RecipeSearch {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        FileReader fileReader = new FileReader(new ArrayList());
        UserInterface userInterface = new UserInterface(scanner, fileReader);
        userInterface.start();

        System.out.println(fileReader.findRecipeByName("recipes.txt", "meatballs"));;
    }

}
