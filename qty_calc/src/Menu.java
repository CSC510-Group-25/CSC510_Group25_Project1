import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;

import java.io.File;
import java.io.FileNotFoundException;

/*
 * MIT License
 *
 * Copyright (c) 2021. CSC510-Group-25
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/**************************
 * Originally authored by: Leila Moran
 * GitHub ID: snapcat
 *
 * CSC510 Fall 2021
 * North Carolina State University
 *
 * File name: Menu.java
 **************************/

public class Menu {

    /////// INCOMPLETE CLASS ///////

    /*
    Methods:
    TODO: set up recipe folder
    add new dish/recipe
    remove dish/recipe
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
        return null;
    }



}
