package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class QuantityCalculator {

    MockDB db;

    HashMap<String, Aggregator> aggregates;
    HashMap<String, Aggregator> excluded;
    /* if any order has just one failed conversion, then any successful
     conversions of its other ingredients will be added to the excluded map.

    ex)

    cheese:    80 lbs used total*
    rice:      10 kgs used total*

    Due to a failed conversion in one or more orders, a total of 7 lbs of cheese have been excluded.
    Due to a failed conversion in one or more orders, a total of 9 kgs of rice have been excluded.

    See the report for more info.
    Display report? y/n: ...
    */
    //TODO: FAILED ORDERS DOES NOT REFER TO FAILED CONVERSIONS!!!
    //Each order has a number of successful orders and a number of failed orders.
    // Either, but not both, can be zero.
    /*
    To clarify:
    For each  dish
    Successful orders: how many times a customer ordered the dish and successfully received it
    Failed orders: how many times a customer ordered the dish but failed to receive it due to some ingredient shortage.
     */

    ArrayList<String> dbList; // list of db contents
    ArrayList<String> ingredientKeys; // acts as a list of keys for the aggregate map
    ArrayList<String> report;//

    HashMap<String, Aggregator> aggregatesFailed; // aggregator maps for failed orders
    HashMap<String, Aggregator> excludedFailed;
    ArrayList<String> reportFailed; //


    public QuantityCalculator(MockDB db) {
        this.db = db;
        this.dbList = db.getKeys();
        this.aggregates = new HashMap<>();
        this.ingredientKeys = new ArrayList<>();
        this.excluded = new HashMap<>();
        this.report = new ArrayList<>();
        this.aggregatesFailed = new HashMap<>();
        this.excludedFailed = new HashMap<>();
        this.reportFailed = new ArrayList<>();
    }

    // setting a new DB is the same as constructing a new calculator.
    public void setDb(MockDB db) {
        this.db = db;
        this.dbList = db.getKeys();
        this.aggregates = new HashMap<>();
        this.ingredientKeys = new ArrayList<>();
        this.excluded = new HashMap<>();
        this.report = new ArrayList<>();
        this.aggregatesFailed = new HashMap<>();
        this.excludedFailed = new HashMap<>();
        this.reportFailed = new ArrayList<>();
    }

    /* Given <recipe, num_orders, num_failed>:
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


    // CHECK IF dbList is null!
    public boolean checkMissingIngredients(Recipe recipe) {

        boolean missing = false;
        ArrayList<Ingredient> ings = recipe.getIngredientList();
        for (Ingredient i : ings) {
            String key = i.getIngredientName();
            // if an ingredient is not in the database...

            String testr = this.db.getDBU(key);
            // more efficient?
            if(testr.isEmpty()){
                missing=true;
                break;
            }

            if (!dbList.contains(key)) {

                //TODO: Don't add to aggregate
                // OR skip this order completely and notify user
                // AND disable updating DB for this order
                // OR ensure this doesn't happen at all

                // For now, skip completely and alert user.
                // TODO: PRINT TO LOG.
                //System.out.println(key + " missing from database. This order of " + recipe.getRecipeName() + " will be discarded.");
                missing = true;
                break;
            }
        }
        return missing;
    }


    //TODO: Integrate with aggregate handling
    // include failed orders
    public String generateOrderReport(Order o){
        StringBuilder str = new StringBuilder();
        Recipe recipe = o.getRecipe();
        String title = "Conversion report for " + recipe.getRecipeName() + "\n";
        str.append(title);
        str.append(o.toString());
        str.append("\n");
        ArrayList<Ingredient> ings = recipe.getIngredientList();
        for (Ingredient i : ings) {
            String key = i.getIngredientName();
            // if an ingredient is not in the database...
            if (!dbList.contains(key)) {
                String msg = key + " missing from database. This order of " + recipe.getRecipeName() + " will be discarded.";
                str.append(msg);
                break;
            }
            String dbu = this.db.getDBU(key);
            str.append(conversionMsgHandler(i,dbu));
        }
        return str.toString();
    }


    //TODO: print to a file for logging.
    //TODO: Integrate with aggregate handling
    public String conversionMsgHandler(Ingredient ingredient, String dbU) {

        double qty = ingredient.getLocal_qty();
        String localU = ingredient.getLocal_unit();
        //String convert = UnitConverter.convertTo(qty, localU, dbU);

        // msg1: "equal units, no conversion needed";
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
        // msg4: "do you really need to weigh a liquid?"
        // msg5: "illegal unit"

        String convert = UnitConverter.convertTo(qty, localU, dbU);
        StringBuilder str = new StringBuilder();
        String returnMsg = "Converting " + qty + " " +localU + " to " +dbU +"\nResult: ";
        str.append(returnMsg);

        switch (convert) {
            case "msg1":
                str.append("equal units, no conversion needed");
                break;
            case "msg2":
                String nuStr = "cannot convert " + localU + " to " + dbU;
                str.append(nuStr);
                break;
            case "msg3":
                str.append("Conversion not yet implemented");
                break;
            case "msg4":
                str.append("Cannot convert liquid volume to weight");
                break;
            default:
                String printMe = "Successfully converted " + qty + " " + localU + " to: "
                        + UnitConverter.convertToDouble(qty, localU, dbU) + " " + dbU;
                str.append(printMe);
                break;
        }
        str.append("\n");
        return str.toString();
    }



    /**
     * A quick way to check if a recipe has failed conversions without generating any reports.
     *
     * Use during testing.
     *
     * @param recipe
     * @return
     */
    public boolean hasFailedConversions(Recipe recipe){

        boolean fails = false;

        ArrayList<Ingredient> ings = recipe.getIngredientList();
        for (Ingredient i : ings) {
            String key = i.getIngredientName();
            double qty = i.getLocal_qty();
            String localU = i.getLocal_unit();
            String dbu = this.db.getDBU(key);
            Double convert = UnitConverter.convertToDouble(qty, localU, dbu);

            if(convert==null) {
                fails = true;
                break;
            }
        }
        return fails;
    }


    /**
     * Check if an ingredient is in the database.
     * @param key -- ingredient name
     * @return boolean -- true or false
     */
    public boolean checkIngredient(String key){

        if(dbList.contains(key)){
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * All the work is done here.
     * TODO: IMPLEMENT FAILED ORDERS
     *
     * @param o
     * @return
     */
    public OrderAssist OrderConverter(Order o){

        boolean fails = false;
        Recipe r = o.getRecipe();
        int numS = o.getNum_orders(); // NUM SUCCESSFUL ORDERS

        StringBuilder str = new StringBuilder();

        ArrayList<Aggregator> nuAggs = new ArrayList<>();
        ArrayList<String> allkeys = new ArrayList<>();
        ArrayList<Ingredient> ings = r.getIngredientList();

        String title = "Order Report for:\n";
        str.append(title);
        str.append(o.toString());
        str.append("\n");

        for (Ingredient i : ings) {
            String key = i.getIngredientName();
            double qty = i.getLocal_qty();
            String localU = i.getLocal_unit();

            //NOTE: ALL INGREDIENTS IN A RECIPE SHOULD BE UNIQUE.
            if(!allkeys.contains(key)){
                allkeys.add(key);
            }
            String dbu = this.db.getDBU(key);

            // if(dbu.isEmpty()){ fails =true; } should not happen at all

            Double nuQty = qty * numS;  //
            Double convert = UnitConverter.convertToDouble(nuQty, localU, dbu);

            // cheese: converting 8 lbs to kgs
            // Result:
            String conversionNote = "\n"+key + ": "+ "converting " + nuQty + " " +localU + " to " +dbu +"\nResult: ";
            str.append(conversionNote);

            // second call to UnitConverter in order to generate string for logging.
            String converted = UnitConverter.convertTo(nuQty,localU,dbu);
            switch (converted) {
                case "msg1":
                    str.append("equal units, no conversion needed");
                    break;
                case "msg2":
                    String nuStr = "cannot convert " + localU + " to " + dbu;
                    str.append(nuStr);
                    break;
                case "msg3":
                    str.append("Conversion not yet implemented");
                    break;
                case "msg4":
                    str.append("Cannot convert liquid volume to weight");
                    break;
                default:
                    /*String printMe = "Successfully converted " + qty + " " + localU + " to: "
                            + UnitConverter.convertToDouble(qty, localU, dbu) + " " + dbu;
                    str.append(printMe);
                    */

                    String printMe2 = "Successfully converted " + nuQty + " " + localU + " to: "
                            + convert + " " + dbu;
                    str.append(printMe2);

                    break;
            }

            str.append("\n");
            // reason will be noted in log.
            if(convert==null) {
                fails = true;
                //failed.add(key);
            }
            else {
                Aggregator nuag = new Aggregator(key, dbu, convert);
                nuAggs.add(nuag);
            }
        }
        String nuLog = str.toString();
        OrderAssist oa = new OrderAssist(fails,nuAggs,nuLog);
        return oa;
    }




    /* If ANY conversions fail, alert the user and
    1. discard the order
    OR
    2. skip the ingredient and note in a log, and for the other ingredients in the order, exclude them from their
    respective aggregates, even if they don't fail.

    Maintain a map of FAILED aggregates.
    For now, the order will be discarded.

    ex)
    cheese:    80 lbs used total*
    rice:      10 kgs used total*
    Due to a failed conversion in one or more orders, a total of 7 lbs of cheese have been excluded.
    Due to a failed conversion in one or more orders, a total of 9 kgs of rice have been excluded.
    See the report for more info.
    Display report? y/n: ...
    */

    /**
     * A whole lot happens here. Call this on an OrderTracker.
     *
     * TODO: actually make a good doc
     *
     * @param ot
     */
    public void AggregateHandler(OrderTracker ot) {

        ArrayList<Order> os = ot.getOrdersArray();

        for (Order o : os) {
            Recipe r = o.getRecipe();
            boolean missing = checkMissingIngredients(r);

            if (!missing) {

                OrderAssist oa = OrderConverter(o);
                boolean failedConversion = oa.isFails();
                report.add(oa.getLog());

                if (failedConversion) {
                    for (Aggregator agg : oa.getAglist()) {

                        String key = agg.getName();

                        if (!ingredientKeys.contains(key)) {
                            ingredientKeys.add(key);
                        }
                        // if there already exists an aggregate for the key...
                        if (this.excluded.get(key) != null) {
                            Double old = this.excluded.get(key).getAgg();
                            Double nuVal = old + agg.getAgg();
                            this.excluded.get(key).setAgg(nuVal);
                        }

                        // if not, add it to the excluder.
                        this.excluded.putIfAbsent(key, agg);
                    }
                }

                // if there aren't any failed conversions, add to the map of aggregates or update an existing aggregate.
                if (!failedConversion) {
                    for (Aggregator agg : oa.getAglist()) {
                        String key = agg.getName();

                        if (!ingredientKeys.contains(key)) {
                            ingredientKeys.add(key);
                        }
                        // if there already exists an aggregate for the key...
                        if (this.aggregates.get(key) != null) {
                            Double old = this.aggregates.get(key).getAgg();
                            Double nuVal = old + agg.getAgg();
                            this.aggregates.get(key).setAgg(nuVal);
                        }
                        // if not, add it to the aggregates.
                        this.aggregates.putIfAbsent(key, agg);
                    }
                }

            } if(missing){
                // skip order
                // ... but add a list of missing ingredients to the report.
                StringBuilder str = new StringBuilder();
                String title = "Order Report\n";
                str.append(title);
                str.append(o.toString());
                str.append("\n");
                StringBuilder missingIngs = new StringBuilder();
                ArrayList<Ingredient> ings = r.getIngredientList();

                for(Ingredient i : ings){
                    String key = i.getIngredientName();
                    boolean check = checkIngredient(key);

                    if(check){
                        String msg = key + " missing from database.\n";
                        missingIngs.append(msg);
                    }
                }
                str.append(missingIngs.toString());
                String nustr = "This order of " + r.getRecipeName() + " will be discarded.";
                str.append(nustr);
                report.add(str.toString());
            }
        }
    }


    /**
     * No return for now.
     * TODO: handle failed orders
     * For each order, allow user to update database (IFF there are no failed conversions or missing ingredients)
     *
     * For failed orders:
     * if number of failed orders != 0:
     * For each ingredient in a dish,
     *  multiply the quantity of each by the number of failed orders,
     *  Perform conversions,
     *  and add to a total sum.
     *
     * In short, follow the same logic in OrderConverter, but use Order.num_failures instead.
     *
     * example:
     * Missing cheese = (Dish1_cheese x dish1_fails) + (Dish2_cheese x dish2_fails) + …
     * Missing ABC = (dish1_ABC x dish1_fails + (dish7_ABC x dish7_fails) + …
     *
     * The program then calculates the minimum required amount of each ingredient to prevent
     * shortages for the next dinner service.
     *
     * @param ot -- OrderTracker object
     */
    public void Calculator(OrderTracker ot) {
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

        if(dbList==null){
            System.out.println("Empty database, cannot perform any calculations.");
            return;
        }
        AggregateHandler(ot);
    }



    //TODO: NOT YET IMPLEMENTED
    /**
     * Method that takes an order, and for each ingredient in the order,
     * prompts the user to input a db unit.
     *
     * Does not look into the database at all. In fact, it doesn't even need a database!
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
        return "NYI";
    }


    /**
     * Returns the report as a string.
     *
     * @return a string
     */
    public String getReportString(){
        if((this.report!=null) && (!this.report.isEmpty())){
            StringBuilder nu = new StringBuilder();
            for(String str : this.report){
                String nuStr = str + "\n";
                nu.append(nuStr);
            }
            return nu.toString();
        }
        return "";
    }


    /**
     * Helper class.
     *
     * (key, val)
     *
     * (ingredientName, Pair)
     * (ingredientName, (db_unit, aggregate))
     *
     **/
    public class Aggregator {

        private String name;
        private String db_unit;
        private Double agg; //aggregate


        public Aggregator(String name, String db_unit,Double agg) {
            this.name = name;
            this.db_unit = db_unit;
            this.agg = agg;
        }

        public Double getAgg() { return agg; }
        public void setAgg(Double agg) { this.agg = agg; }
        public String getdb_unit() { return db_unit; }
        public String getName() { return name; }

        //Below are to be used for TESTING ONLY !!!
        // The values should NOT be modified otherwise.
        public void setdb_unit(String db_unit) {
            this.db_unit = db_unit;
        }

    }


    /* Helper class for orders.

    If an ingredient has a failed conversion, fails = true
    aglist is a list of successfully generated Aggregators
    log is... the conversion log.
     */
    public class OrderAssist{

        private boolean fails;
        private ArrayList<Aggregator> aglist;
        private String log;

        public OrderAssist(boolean fails, ArrayList<Aggregator> aglist, String log) {
            this.fails = fails;
            this.aglist = aglist;
            this.log=log;
        }

        public boolean isFails() { return fails; }
        public ArrayList<Aggregator> getAglist() { return aglist; }
        public String getLog() { return log; }
    }


    /////////////////////////////////////////////////
    //// MANUALLY TEST AND DEBUG THINGS DOWN HERE ////


    public static void main(String[] args) throws Exception{

        OrderTracker ot = new OrderTracker();
        //ot.createOrders();

        Recipe r8 = new Recipe("recipe_folder/recipe_8000.json");
        Recipe r9 = new Recipe("recipe_folder/recipe_9000.json");
       // Recipe r1 = new Recipe()

        ot.createOrder(r8, 2,0);
        ot.createOrder(r9,2,0);

        //System.out.println(ot.toString());

        MockDB mdb = new MockDB("mock_dbs/db1.txt");

        QuantityCalculator qc = new QuantityCalculator(mdb);

        qc.Calculator(ot);

        System.out.println(qc.getReportString());

    }
}
