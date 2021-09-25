Add jars in libs folder to classpath if you're using an IDE!!

Each .java file should have a main method if you want to manually test.
(Examples are included)
Manual testing should be done through an IDE for sanity's sake and for easy debugging.

Also, if you're using an IDE, you can easily follow the TODO trail in each class.

json-simple docs: https://cliftonlabs.github.io/json-simple/target/apidocs/index.html


------

recipe_folder contains recipes in either .txt or .json format.
mock_dbs      contains mock databases in either .txt or .json format.
mock_menus    contains mock menus in either .txt or .json format.

These folders are mostly for testing purposes and to make object construction easier.

NOTE: THESE FOLDERS MUST BE INSIDE YOUR WORKING DIRECTORY. Again, USE AN IDE.

ex)
Recipe r1 = new Recipe("recipe_folder/recipe_0001.txt");
MockDB mdb = new MockDB("mock_dbs/db1.txt");

Recipe r1 = new Recipe("recipe_folder/recipe_0001.json");
MockDB mdb = new MockDB("mock_dbs/db1.json");
Construct their objects based on the contents of the files, if they exist.

To generate a .json file:
MockDB mdb = new MockDB("mock_dbs/db1.txt");
mdb.saveAsJson("mock_dbs","db1.json");

Recipe r1 = new Recipe("recipe_folder/recipe_0001.txt");
r1.saveRecipeAsJson("recipe_folder");

----------------------------------------------

For recipe_folder: look inside for examples!

file name: recipe_[RECIPE_ID].txt
Replace [RECIPE_ID] with the recipe's ID as recorded in the database.
Contents of each file:

recipe_name
recipe_id
[ingredient]

ex)
recipe_0002.txt

Contents:
mystery blob
0002
[cheese, 5534, 8, lbs]
[rice, 7881, 2, cups dry]
[ingr3, db_id, local_qty, local_unit]
...
[ingrN, db_id, local_qty, local_unit]

One ingredient per line.

-------------------

recipe as json:

{"recipeName":"horrific mush",
 "ingredient_list": [{"ing_DBID":"5534","ingredientName":"cheese","local_qty":8.0,"local_unit":"lbs"},       
                     {"ing_DBID":"7881","ingredientName":"rice","local_qty":5.0,"local_unit":"kgs"},
                     {"ing_DBID":"2003","ingredientName":"milk","local_qty":9000.0,"local_unit":"mL"},
                     {"ing_DBID":"2001","ingredientName":"butter","local_qty":20.0,"local_unit":"oz"},
                     {"ing_DBID":"0019","ingredientName":"blended sardines","local_qty":60.0,"local_unit":"fl.oz"}],
  "recipeID":"0001"}
  
  
  {"recipeName:"blahblahdeblah",
   "ingredient_list": [{"ING_DBID":"blah", "ingredientName":"blah","local_qty:":0.0,"local_unit":"blah"},
                        ....]
   "recipeID":"blah"}


.json construction is handled automatically.

If you want to print json:

Use json-simple's toJson and prettyPrint methods.

------------------

mock database .txt example:

[cheese, 5534, 100, lbs]
[rice, 7881, 100, kgs]
[milk, 2003, 100, liters]
[butter, 2001, 100, lbs]
[blended sardines, 0019, 100, gallons]
[thing1, 9891, 1000, fl.oz]
[thing2, 7777, 100, kgs]
[thing3, 1234, 100, liters]
[thing4, 9999, 100, gallons]

