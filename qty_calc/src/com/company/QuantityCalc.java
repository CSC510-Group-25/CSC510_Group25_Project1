package com.company;

public class QuantityCalc {

    /**
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
     *
     *
     *
     * convert to DB unit.
     *
     *
     */


    /**
     * (key, val)
     *
     * (ingredient, Pair)
     * (ingredient, (db_unit, aggregate))
     *
     */
    public class Pair {

        private String db_unit;
        private double agg; //aggregate

        // empty constructor for testing
        public Pair(){ }

        public Pair(String db_unit, double agg) {
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



}
