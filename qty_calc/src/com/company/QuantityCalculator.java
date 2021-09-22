package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class QuantityCalculator {

    MockDB db;

    HashMap<String,Aggregator> aggregate;

    ArrayList<String> dbList;

    // acts as a list of keys for the aggregate map
    ArrayList<String> ingredientKeys;

    public void setDb(MockDB db) {
        this.db = db;
        this.dbList = db.getKeys();
        this.aggregate=new HashMap<>();
        this.ingredientKeys = new ArrayList<>();
    }

    public QuantityCalculator(MockDB db) {
        this.db = db;
        this.dbList = db.getKeys();
        this.aggregate=new HashMap<>();
        this.ingredientKeys = new ArrayList<>();
    }

    /*
     * Given <recipe, num_orders, num_failed>:
     *
     * Maintain a list of all UNIQUE ingredients in all given recipes.
     * HashMap<...><...> <ingr, aggregate, db_unit>
     *
     * For each ingredient in the recipe:
     *     Check if in DB.
     *     If ANY ingredient is NOT in the DB:
     *        EXCLUDE THE RECIPE FROM THE CALCULATOR ENTIRELY.
     *        DISPLAY A MESSAGE THAT SAYS:
     *        "The recipe XYZ excluded from the calculation because one or more ingredients were not listed in inventory: "
     *        "LIST OF ALL MISSING INGREDIENTS"
     *
     *        ALTERNATIVELY, list all recipes that were excluded AFTER the calculation is complete?
     *
     *
     *     If in DB:
     *         Fetch DB unit.
     *
     *         Convert:
     *         String thing = UnitConverter.convertTo(local_qty,local_unit,db_unit);
     *
     *         double thing2 = UnitConverter.convertToDouble(local_qty,local_unit,db_unit);
     *
     *
     *         If the ingredient is NOT in the map: add it and the converted value.
     *         Pair nuPair = new Pair(db_unit, thing2)
     *         add to map: ('cheese', nuPair)
     *
     *         ('cheese', 'db_unit', aggregate_qty)   key = 'cheese', value = <'db_unit, agg>
     *         (str, str, double)
     *
     *         If it IS in the map:
     *
     *         Double agg = map.get(ingr_name).getAgg()
     *
     *         Add converted val to agg:
     *         agg = agg + thing2;
     *
     *         set new val:
     *         map.get(ingr_name).setAgg(agg);
     *
     * convert to DB unit.
     */



    // void for now.
    public void convertIngredient(Ingredient ingredient, String dbU){
        double qty = ingredient.getLocal_qty();
        String localU = ingredient.getLocal_unit();
        String convert = UnitConverter.convertTo(qty,localU,dbU);
    }


    /*
    TEST SUCCESSFUL ORDERS
     */
    public void calcTestSuccess(OrderTracker ot){

        //For each order, get list of ingredients.
        //    For each ingredient,
        //         get db_unit / check if in DB
        //
        //         IF NOT IN DB:
        //                SKIP ORDER COMPLETELY.
        //
        //         multiply local_qty by number of successful orders,
        //         convert to db_unit,
        //         add to aggregate.
        //
        //
        //
        //If an order doesn't have any missing ingredients, add each ingredient name to a list.


        ArrayList<String> recipeList = new ArrayList<>();

        ArrayList<String> ingList = new ArrayList<>();

        //Arr

        ArrayList<Order> os = ot.getOrdersArray();

        for(Order o : os){

            //MOVE ALL THIS INTO SEPARATE METHOD

            Recipe r = o.getRecipe();

            ArrayList<Ingredient> ings = r.getIngredientList();

            ArrayList<String> inames = new ArrayList<>();

            boolean missingIngredient = false;

            for(Ingredient i : ings){
                String key = i.getIngredientName();
                if(!dbList.contains(key)){
                    //TODO: Don't add to aggregate
                    // OR skip this order completely and notify user
                    // AND disable updating DB for this order
                    // OR ensure this doesn't happen at all by checking the database inside OrderTracker.java

                    // For now, skip completely and alert user.
                    System.out.println(key + " missing from database. This order of " +r.getRecipeName()+ " will be discarded.");

                    missingIngredient = true;
                    break;
                }
                else {
                    if(!inames.contains(key)){
                        inames.add(key);
                    }
                }
            }

            // if no ingredients are missing...
            if(!missingIngredient){
                // add any ingredients that aren't already in the list.
                for(String name: inames){
                    if(!ingList.contains(name)){
                        ingList.add(name);

                        // if it's not in the list, then it doesn't have an aggregator.
                        //TODO: ENSURE THAT ingredientName equals itemName!

                        String unit = this.db.getDBU(name);

                        //         multiply local_qty by number of successful orders,
                        //         convert to db_unit,
                        //         add to aggregate.

                        //TODO: WIP



                        //Aggregator ag = new Aggregator()


                    }
                }





            }






        }



    }


    //TODO: NOT YET IMPLEMENTED
    /**
     * Method that takes an order, and for each ingredient in the order,
     * prompts the user to input a db unit.
     *
     * Does not look into the database at all.
     *
     * Ex)
     *
     * disgusting dinner: ID 7777
     * Number of successful orders: 10
     * Number of failed orders: 0
     *
     * Ingredients:
     * cheese, 16 oz
     * rice, 1000 grams
     * milk, 1000 mL
     *
     * Convert 160 oz of cheese to: lbs
     * -->     10 lbs of cheese
     *
     * Convert 10000 grams of rice to: kgs
     * -->     10 kgs of rice
     *
     * Convert 1000 mL of milk to: mL
     * -->     no conversion necessary
     *
     *
     * @param o
     * @return
     */
    public String EasyCalc(Order o){


        return "";
    }



    //public String getDBU(String ingrName){ return this.db.getDBU(ingrName); }


    //public void aggregator(Ingredient ingr, )




/*
    public class Aggregator {

        private Item item;
        private Ingredient ingredient;

        private double agg;

        public Aggregator(Item item, Ingredient ingredient, double agg) {
            this.item = item;
            this.ingredient = ingredient;
            this.agg = agg;
        }

        public Aggregator(Item item, Ingredient ingredient) {
            this.item = item;
            this.ingredient = ingredient;
            this.agg = 0;
        }

        public Item getItem() {
            return item;
        }

        public Ingredient getIngredient() {
            return ingredient;
        }


        public double getAgg() {
            return agg;
        }

        public void setAgg(double agg) {
            this.agg = agg;
        }
    }
    */


    /**
     * (key, val)
     *
     * (ingredientName, Pair)
     * (ingredientName, (db_unit, aggregate))
     *
     **/
    public class Aggregator {

        private String db_unit;
        private double agg; //aggregate

        // empty constructor for testing
        public Aggregator(){ }

        public Aggregator(String db_unit, double agg) {
            this.db_unit = db_unit;
            this.agg = agg;
        }


        public double getAgg() {
            return agg;
        }

        public void setAgg(double agg) {
            this.agg = agg;
        }

        public String getdb_unit() {
            return db_unit;
        }

        //set db_unit used for testing ONLY
        public void setdb_unit(String db_unit) {
            this.db_unit = db_unit;
        }
    }


    //////////////////////////////////////////


    public static void main(String[] args){

    }



}
