import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class FileReader {
    private ArrayList<String> list;


    public FileReader(ArrayList list) {
        this.list = list;
    }

    public List<String> readFile(String nameOfFile) {
        String line = "";
        int count = 1;

        try (Scanner scanner = new Scanner(Paths.get(nameOfFile))) {
            while (scanner.hasNextLine()) {

                line = scanner.nextLine();
                if (line.isEmpty()) continue;
                this.list.add(line);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.err.println("ArrayList::{}"+this.list);

        return list;
    }

    public String[] getRecipeAndTimeOfPreparation() {
        return new String[]{list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5)};
    }

    public List<String> read(String nameOfFile) {
        try (Scanner scanner = new Scanner(Paths.get(nameOfFile))) {
            int count = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isEmpty() && (count <=2)) {

                    list.add(line);
                    count++;
                } else {
                    if (line.isEmpty()) {
                        count = 1;
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String findRecipeByName(String nameOfFile, String nameOfRecipe) {
        HashMap<String, String> recipeAndTime = new HashMap<>();
        recipeAndTime.put("dough", list.get(0)+ ", " + "cooking time:" + list.get(1));
        recipeAndTime.put("meatballs", list.get(2)+ ", " + "cooking time:" + list.get(3));
        recipeAndTime.put("rolls", list.get(4)+ ", " + "cooking time:" + list.get(5));

         return recipeAndTime.get(nameOfRecipe);
    }

    public List<String> findRecipeByCookingTime(String nameOfFile, int cookingTime) {
        var listOfRecipes = read(nameOfFile);
        HashMap<Integer, String>  recipeAndTime = new HashMap<>();
        List<String> recipes = new ArrayList<>();
        System.out.println("Show me the list --> " + list);
        recipeAndTime.put(60, listOfRecipes.get(0)+ ", " + "cooking time:" + listOfRecipes.get(1));
        recipeAndTime.put(20, listOfRecipes.get(2)+ ", " + "cooking time:" + listOfRecipes.get(3));
        recipeAndTime.put(30, listOfRecipes.get(4)+ ", " + "cooking time:" + listOfRecipes.get(5));

        Iterator<Map.Entry<Integer, String>> it = recipeAndTime.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Integer, String> set = (Map.Entry<Integer, String>) it.next();
            if (set.getKey() <= cookingTime) {
               String recipe = set.getValue();
               recipes.add(recipe);

            }
        }
        return recipes;
    }

    public List<Recipe> findRecipeByIngredent(String file, String ingredient) {
       List<String> fileContents = readFile(file);
        Recipe dough = new Recipe("Pancake dough", 60, List.of(fileContents.get(2),
                fileContents.get(3), fileContents.get(4), fileContents.get(5), fileContents.get(6),
                fileContents.get(7)));

        Recipe meatballs = new Recipe("Meatballs", 20, List.of(fileContents.get(10),
                fileContents.get(11), fileContents.get(12)));

        Recipe rolls = new Recipe("Tofu rolls", 30, List.of(fileContents.get(15), fileContents.get(16),
                fileContents.get(17), fileContents.get(18), fileContents.get(19), fileContents.get(20), fileContents.get(21)));

        List<Recipe> recipes = Arrays.asList(dough, meatballs, rolls);

        List<Recipe> recipeContainingIngredient = new ArrayList<Recipe>();

        Iterator<Recipe> it = recipes.iterator();
//        while (it.hasNext()) {
//            if(it.next().getIngredients().contains(ingredient)) {
//                 recipeContainingIngredient.add(it.next());
//            }
//        }
        System.out.println(recipes);
        for(Recipe recipe: recipes) {
            for(String ing: recipe.getIngredients()) {
               if (ing.equals(ingredient)) recipeContainingIngredient.add(recipe);
            }
        }

        return recipeContainingIngredient;
    }

}




