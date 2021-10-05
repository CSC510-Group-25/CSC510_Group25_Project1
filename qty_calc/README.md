# Quantity Calculator #

[![unit_tests](https://github.com/CSC510-Group-25/CSC510_Group25_Project1/actions/workflows/QtyCalcTests.yml/badge.svg)](https://github.com/CSC510-Group-25/CSC510_Group25_Project1/actions/workflows/QtyCalcTests.yml)

This directory contains the code and test cases for a major component of this project, the Quantity Calculator.  

- - - -

(9/27/2021):  
Number of unit tests: 96  

This component is a completely standalone ***feature demo*** and has not yet been incorporated into the main project.  
It's intended to be run **inside an IDE** in order to allow developers to explore and add functionality as needed. **Not terminal friendly!**


- - - -

### About this component ###

See https://github.com/CSC510-Group-25/CSC510_Group25_Project1/issues/47#issue-996371593

- - - -

To run:
1. create a new project in your IDE and copy/paste the .java files from the src/ directory.
2. Inside your project, create two directories: **recipe_folder** and **mock_dbs**. These should be OUTSIDE your src directory.
3. Add the jars in the lib folder to your classpath (json-simple)
4. Create a .txt file for a recipe or mock database using the formats shown [here](#filef)
5. Run/modify the main method inside any class you want to test.

Each .java file should have a main method if you want to manually test.  
(Examples are included)  
Manual testing should be done through an IDE for sanity's sake and for easy debugging.  

**DO NOT USE THE POM.XML FILE INSIDE THIS DIRECTORY. It's only there to make github actions work.**

... unless you want to build with maven.

Also, if you're using an IDE, you can easily follow the TODO trail in each class.  

**In order to run QtyCalcTests.java without failures:**
1. Copy/paste the following .txt files from the recipe_folder inside this repository to your recipe_folder:  
    1. recipe_0001.txt
    2. recipe_8000.txt
    3. recipe_9000.txt
2. Copy/paste **db1.txt** from the mock_dbs directory in this repository into your mock_dbs folder.
3. Add jUnit 5 to your classpath
4. Run!

json-simple docs: https://cliftonlabs.github.io/json-simple/target/apidocs/index.html  

- - - -

recipe_folder contains recipes in either .txt or .json format.  
mock_dbs      contains mock databases in either .txt or .json format.  
mock_menus    contains mock menus in either .txt or .json format. **NOT IMPLEMENTED YET.**

These folders are mostly for testing purposes and to make object construction easier.

NOTE: THESE FOLDERS MUST BE INSIDE YOUR WORKING DIRECTORY. Again, USE AN IDE.

ex)  

Constructing a recipe or database from .txt file:

Recipe r1 = new Recipe("recipe_folder/recipe_0001.txt");  
MockDB mdb = new MockDB("mock_dbs/db1.txt");  

Constructing a recipe or database from .json file:

Recipe r1 = new Recipe("recipe_folder/recipe_0001.json");  
MockDB mdb = new MockDB("mock_dbs/db1.json");  

To generate a .json file:  

MockDB mdb = new MockDB("mock_dbs/db1.txt");  
mdb.saveAsJson("mock_dbs","db1.json");  

Recipe r1 = new Recipe("recipe_folder/recipe_0001.txt");  
r1.saveRecipeAsJson("recipe_folder");  

- - - -

## File Formats ## <a name="filef"></a>

For recipe_folder: look inside this repository for more examples!

Recipe .txt file

file name: recipe_\[ID].txt  

Replace \[ID] with the recipe's ID as recorded in the database.

    recipe_name  
    recipe_id  
    [ingredient]

ex)  
recipe_1234.txt

    mystery blob  
    1234  
    [cheese, 5534, 8, lbs]  
    [rice, 7881, 2, cups dry]  
    ingr3, db_id, local_qty, local_unit]  
    ...  
    [ingrName, db_id, local_qty, local_unit]  
            
One ingredient per line.


**mock database .txt example:**

    [cheese, 5534, 100, lbs]
    [rice, 7881, 100, kgs]
    [milk, 2003, 100, liters]
    [butter, 2001, 100, lbs]
    [blended sardines, 0019, 100, gallons]
    [thing1, 9891, 1000, fl.oz]
    [thing2, 7777, 100, kgs]
    [thing3, 1234, 100, liters]
    [thing4, 9999, 100, gallons]

- - - -

recipe as json:

    {"recipeName":"horrific mush","ingredient_list": 
    [{"ing_DBID":"5534","ingredientName":"cheese","local_qty":8.0,"local_unit":"lbs"},       
                     {"ing_DBID":"7881","ingredientName":"rice","local_qty":5.0,"local_unit":"kgs"},
                     {"ing_DBID":"2003","ingredientName":"milk","local_qty":9000.0,"local_unit":"mL"},
                     {"ing_DBID":"2001","ingredientName":"butter","local_qty":20.0,"local_unit":"oz"},
                     {"ing_DBID":"0019","ingredientName":"blended sardines","local_qty":60.0,"local_unit":"fl.oz"}],
                     "recipeID":"0001"}
  

    {"recipeName:"blahblahdeblah","ingredient_list": 
        [{"ING_DBID":"blah", "ingredientName":"blah","local_qty:":0.0,"local_unit":"blah"},
        ....]
        "recipeID":"blah"}


.json construction is handled automatically so you don't have to deal with the above unless you really want to.

If you want to print json:

Use json-simple's toJson and prettyPrint methods.

- - - -


