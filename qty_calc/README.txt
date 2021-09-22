Add jars in libs folder to classpath if you're using an IDE!!








----------------------------------------------

file name: recipe_[RECIPE_ID].txt
Replace [RECIPE_ID] with the recipe's ID as recorded in the database.
Contents of each file:

recipe_name
recipe_id
num_ingredients
[ingredient]

ex)
recipe_0002.txt

Contents:
mystery blob
0002
N
[cheese, 5534, 8, lbs]
[rice, 7881, 2, cups dry]
[ingr3, db_id, local_qty, local_unit]
...
[ingrN, db_id, local_qty, local_unit]

One ingredient per line.

Consider using json.

NOTE: these are for TESTING.
num_ingredients is needed to reduce processing time.

-------------------

recipe_0001.txt: test reading in strings and object construction.
Expected result: TBD

recipe_0002.txt: test duplicate ingredients with different local_qty and local_unit, e.g, volume and weight.
Expected result: confirm with user which one is correct and (num_ingredients - 1 ) OR throw an error.

