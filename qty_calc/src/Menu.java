import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;

import java.io.File;
import java.io.FileNotFoundException;

public class Menu {




    /*
    HANDLES ORDERS?
     */


    /*
    Methods:

    TODO: set up recipe folder
    add new dish/recipe
    remove dish/recipe

    track orders


    retrieve recipe

     */

    /**TODO
     * Searches the given directory for a recipe and returns it.
     * TODO: Blah blah blah, search by name, something something
     * This is assuming that there's a folder called "recipe_folder" somewhere...
     *
     * @param recipeName
     * @return
     */
    public static Recipe FindRecipeByName(String recipeName){



        return null;
    }


    /**TODO
     *
     * @param recipeID
     * @return
     */
    public static Recipe FindRecipeByID(String recipeID){

        return null;
    }




    //TODO
    // given a recipe ID or name, fetch the recipe from the given file.
    public static Recipe RecipeFetcher(String recipeName, String filePath) throws FileNotFoundException, JsonException {

        if(!filePath.endsWith(".json")){

            //TODO:
            // PROBLEMS.

        }

        Recipe r = null;

        JsonArray jar =  BuildFile.ReadJsonMenu(filePath);

        for (int i =0; i < jar.size(); i++){
            JsonObject jo = (JsonObject) jar.get(i);

            if(jo != null){

                jo.get(recipeName);

                //if


            }





        }


       // Recipe r = BuildFile.recipeFromJsonMenu(jar, recipeIDName);


        return null;

    }








}
