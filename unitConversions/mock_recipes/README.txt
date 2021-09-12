
file name: recipe_[RECIPE_ID].txt
Replace [RECIPE_ID] with the recipe's ID as recorded in the database.

ex)
recipe_0001.txt

Contents of each file:

recipe_name
recipe_id
[ingredients]

ex)

mystery blob
0001
[cheese, 5534, 8, lbs]
[rice, 7881, 2, cups dry]
[ingr3, db_id, local_qty, local_unit]
...
[ingrN, db_id, local_qty, local_unit]


One ingredient per line.

Consider using json.

NOTE: these are for TESTING.
