package com.company;

import java.util.Scanner;

/**
 * TO USE:
 *
 * String thing = convertTo(6, "cups dry", db_unit)
 *
 * To call individual methods:
 *
 * String thing = lbs(6, "cups dry", db_unit)
 *
 *
 **/

/*TODO:
    implement dry conversions
    TEST RETURNING DOUBLES instead of strings
    OPTIMIZE
    Check math / improve precision



 */
public class UnitConverter {

    // NOTE: conversions ONLY occur if local_unit does not equal db_unit.
    // db_unit is extracted from the database.

    /*
    Note: lbs does not need specification for whether it's dry or liquid. For our purposes, it's always dry.

    Always dry: oz, g, lbs, kg
    Always liquid: fl.oz, mL, pint, quart, gal, liter

    Needs specification: tsp, tbsp, cup
     */



    /**
     * Converts the given qty from local_unit to db_unit.
     *
     *     double qty: the value being converted; the quantity of the ingredient in a recipe.
     *     String local_unit: the unit of the value and its type, e.g, "cups dry"
     *     String db_unit: the unit and type of the ingredient as it is recorded in the database.
     *
     * For example, a recipe might call for six cups (dry) of rice, but rice is recorded in lbs in the database.
     * Thus, db_unit is "lbs".
     *
     * sample call:
     *
     * String thing = convertTo(6, "cups dry", db_unit)
     *
     *
     * @param qty -- double the value being converted; the quantity of the ingredient in a recipe
     * @param local_unit -- String the unit of the value and its type, e.g, "cups dry"
     * @param db_unit -- String the unit and type of the ingredient as it is recorded in the database
     * @return String -- the converted qty as a String
     */
    public static String convertTo(double qty, String local_unit, String db_unit){

        //should be handled when items are being added to the list of ingredients.
        // qty should also be converted to double upon input.
        //if (qty < 0){ }: illegal qty
        //if qty = 0, null, empty: don't perform any conversions.

        // no conversions needed
        if(local_unit.equals(db_unit)){ return "equal units, no conversion needed"; }


        // NOT YET IMPLEMENTED
        //TODO: implement dry conversions
        //if (local_unit.contains("dry")){ return convertDry(qty,local_unit,db_unit); }

        if (local_unit.contains("liquid")){ return convertLiquid(qty,local_unit,db_unit); }


        if(db_unit.equals("oz")){
            return oz(qty, local_unit);
        }

        if (db_unit.equals("grams")){
            return grams(qty, local_unit);
        }

        if (db_unit.equals("lbs")){
            return lbs(qty, local_unit);
        }

        if (db_unit.equals("kgs")){
            return kgs(qty,local_unit);
        }


        if (db_unit.equals("fl.oz")){
            return floz(qty,local_unit);
        }

        if (db_unit.equals("mL")){
            return mL(qty,local_unit);
        }

        if (db_unit.equals("gallons")){
            return gal(qty, local_unit);
        }

        if (db_unit.equals("liters")){
            return liters(qty,local_unit);
        }

        return "cannot convert from: " + local_unit + " to: " + db_unit;
    }


    /**
     * Converts the String result from a call to convertTo(double qty, String local_unit, String db_unit)
     * to a Double. Returns null if the String returned from convertTo(...) does not represent a double.
     *
     * @param qty
     * @param local_unit
     * @param db_unit
     * @return Double or null
     */
    public static Double convertToDouble(double qty, String local_unit, String db_unit){

        String convertMe = convertTo(qty,local_unit,db_unit);
        Double val = null;

        try{
            val = Double.parseDouble(convertMe);

        } catch (Exception NumberFormatException){

            //System.out.println("debug UnitConverter convertToDouble");
        }

        return val;
    }




    /**
     * Converts qty from local_unit to lbs
     *
     * @param qty -- double the value being converted
     * @param local_unit -- String the unit of the value and its type, e.g, "cups dry"
     * @return String -- the converted qty as a String
     */
    public static String lbs(double qty, String local_unit){

        double val;
        String returnVal = "cannot convert from: " + local_unit + " to: lbs";

        if (local_unit.contains("liquid")){
            return "do you really need to weigh a liquid?";
        }

        if(local_unit.equals("lbs")){ return "equal units, no conversion needed"; }


        //TODO
        if (local_unit.contains("dry")){

            /* Display warnings for the following conversions due to cups, tsps, and tbsps
            measuring volume and not weight.
            These are approximations based on granulated sugar.*/

            if (local_unit.contains("cups")){
                // val = qty * 0.5;
                //  returnVal = Double.toString(val);
                return "NYI";
            }

            else if (local_unit.contains("tsps")){
                //  val = qty * 0.011;
                //  returnVal = Double.toString(val);
                return "NYI";
            }

            else if(local_unit.contains("tbsps")){
                //   val = qty / 32;
                //   returnVal = Double.toString(val);
                return "NYI";
            }
            else {
                return "illegal unit";
            }

        }


        // oz to lbs
        if (local_unit.equals("oz")){

            val = qty / 16;

            // this will be used for testing.
            //String thing = String.format("%.4f",val);

            returnVal = Double.toString(val);
        }

        // gram to lbs
        else if (local_unit.equals("grams")){

            // recursion test
            // convert gram to kgs
            //String thing = kgs(qty,local_unit);
            // extract double
            //double nuQty = Double.parseDouble(thing);
            // convert from kgs to lbs
            //String nuThing = lbs(nuQty,"kgs");
            //val = Double.parseDouble(nuThing);


            val = qty * 0.0022;

            //String thing = String.format("%.4f",val);

            returnVal = Double.toString(val);
        }

        // kgs to lbs
        else if (local_unit.equals("kgs")){
            val = qty * 2.2;
            returnVal = Double.toString(val);
        }
        else {

        }


        return returnVal;

    }

    /**
     * Converts qty from local_unit to kgs
     *
     * @param qty -- double the value being converted
     * @param local_unit -- String the unit of the value and its type, e.g, "cups dry"
     * @return String -- the converted qty as a String
     */

    public static String kgs(double qty, String local_unit){

        double val;
        String returnVal = "cannot convert from: " + local_unit + " to: kgs";

        if (local_unit.contains("liquid")){
            return "do you really need to weigh a liquid?";
        }

        if(local_unit.equals("kgs")){ return "equal units, no conversion needed"; }


        //TODO
        if (local_unit.contains("dry")){

            /* Display warnings for the following conversions due to cups, tsps, and tbsps
            measuring volume and not weight.
            These are approximations based on granulated sugar.*/

            if (local_unit.contains("cups")){

                //  returnVal = Double.toString(val);
                return "NYI";
            }

            else if (local_unit.contains("tsps")){
                //  val = qty
                //  returnVal = Double.toString(val);
                return "NYI";
            }

            else if(local_unit.contains("tbsps")){
                //   val = qty
                //   returnVal = Double.toString(val);
                return "NYI";
            }
            else {
                return "illegal unit";
            }

        }


        if (local_unit.equals("grams")){
            val = qty / 1000;
            returnVal = Double.toString(val);
        }

        else if (local_unit.equals("oz")){

            val = qty / 35.274;
            returnVal = Double.toString(val);
        }

        else if (local_unit.equals("lbs")){
            val = qty / 2.2;
            returnVal = Double.toString(val);
        }
        else {
            //TODO

        }

        return returnVal;

    }

    /**
     * Converts qty from local_unit to grams
     *
     * @param qty -- double the value being converted
     * @param local_unit -- String the unit of the value and its type, e.g, "cups dry"
     * @return String -- the converted qty as a String
     */
    public static String grams(double qty, String local_unit){

        double val;
        String returnVal = "cannot convert from: " + local_unit + " to: grams";

        if (local_unit.contains("liquid")){
            return "do you really need to weigh a liquid?";
        }

        if(local_unit.equals("grams")){ return "equal units, no conversion needed"; }

        //TODO
        if (local_unit.contains("dry")){

            /* Display warnings for the following conversions due to cups, tsps, and tbsps
            measuring volume and not weight.
            These are approximations based on granulated sugar.*/

            if (local_unit.contains("cups")){
                // val = qty * 241.23;
                // val = qty
                //  returnVal = Double.toString(val);
                return "NYI";
            }

            else if (local_unit.contains("tsps")){
                // 1 tsp = 5 g
                //  val = qty
                //  returnVal = Double.toString(val);
                return "NYI";
            }

            else if(local_unit.contains("tbsps")){
                // 1 tbsp = 15 g
                //   val = qty
                //   returnVal = Double.toString(val);
                return "NYI";
            }
            else {
                return "illegal unit";
            }

        }



        // oz to gram
        else if (local_unit.equals("oz")){
            // may be necessary to truncate
            //String thing = String.format("%.4f",val);
            val = qty * 28.35;
            returnVal = Double.toString(val);
        }

        // lbs to gram
        else if (local_unit.equals("lbs")){

            // may be necessary to truncate
            //String thing = String.format("%.4f",val);
            val = qty * 454;
            returnVal = Double.toString(val);
        }

        // kgs to gram
        else if (local_unit.equals("kgs")){
            val = qty * 1000;
            returnVal = Double.toString(val);
        }
        else {

        }


        return returnVal;

    }

    /**
     * Converts qty from local_unit to oz
     *
     * @param qty -- double the value being converted
     * @param local_unit -- String the unit of the value and its type, e.g, "cups dry"
     * @return String -- the converted qty as a String
     */
    public static String oz(double qty, String local_unit){

        double val;
        String returnVal = "cannot convert from: " + local_unit + " to: oz";

        if (local_unit.contains("liquid")){
            return "do you really need to weigh a liquid? use fl. oz!";
        }

        if(local_unit.equals("oz")){ return "equal units, no conversion needed"; }

        //TODO
        if (local_unit.contains("dry")){

            /* Display warnings for the following conversions due to cups, tsps, and tbsps
            measuring volume and not weight.
            These are approximations based on granulated sugar.*/

            if (local_unit.contains("cups")){
                // val = qty
                //  returnVal = Double.toString(val);
                return "NYI";
            }

            else if (local_unit.contains("tsps")){
                //  val = qty
                //  returnVal = Double.toString(val);
                return "NYI";
            }

            else if(local_unit.contains("tbsps")){
                //   val =
                //   returnVal = Double.toString(val);
                return "NYI";
            }
            else {
                return "illegal unit";
            }
        }

        // gram to oz
        else if (local_unit.equals("grams")){

            val = qty / 28.35;
            returnVal = Double.toString(val);
        }

        // lbs to oz
        else if (local_unit.equals("lbs")){

            val = qty * 16;
            returnVal = Double.toString(val);
        }

        // kgs to oz
        else if (local_unit.equals("kgs")){

            val = qty * 35.274;
            returnVal = Double.toString(val);
        }
        else{

        }


        return returnVal;

    }


    /**
     * Converts qty from local_unit to liters
     *
     * @param qty -- double the value being converted
     * @param local_unit -- String the unit of the value and its type, e.g, "cups dry"
     * @return String -- the converted qty as a String
     */

    public static String liters(double qty, String local_unit){

        double val;
        String returnVal = "cannot convert from: " + local_unit + " to: liters";

        if(local_unit.equals("liters")){ return "equal units, no conversion needed"; }


        if(local_unit.equals("mL")){
            val = qty / 1000;
            returnVal = Double.toString(val);

        }
        else if (local_unit.equals("fl.oz")){
            val = qty / 33.814;
            returnVal = Double.toString(val);

        }
        else if (local_unit.equals("gallons")){
            val = qty * 3.785;
            returnVal = Double.toString(val);

        }
        else if (local_unit.equals("cups")){
            val = qty / 4.227;
            returnVal = Double.toString(val);

        }

        else if (local_unit.equals("pints")){
            val = qty / 2.113;
            returnVal = Double.toString(val);

        }

        else if (local_unit.equals("quarts")){
            val = qty / 1.057;
            returnVal = Double.toString(val);

        }

        else if (local_unit.equals("tsps")){
            val = qty / 203;
            returnVal = Double.toString(val);

        }
        else if (local_unit.equals("tbsps")){
            val = qty / 67.628;
            returnVal = Double.toString(val);

        }
        return returnVal;
    }

    /**
     * Converts qty from local_unit to gallons
     *
     * @param qty -- double the value being converted
     * @param local_unit -- String the unit of the value and its type, e.g, "cups dry"
     * @return String -- the converted qty as a String
     */

    public static String gal(double qty, String local_unit){

        double val;
        String returnVal = "cannot convert from: " + local_unit + " to: gals";

        if(local_unit.equals("gallons")){ return "equal units, no conversion needed"; }

        if(local_unit.equals("mL")){
            val = qty / 3785;
            returnVal = Double.toString(val);
        }

        else if (local_unit.equals("fl.oz")){
            val = qty / 128;
            returnVal = Double.toString(val);

        }
        else if (local_unit.equals("liters")){
            val = qty / 3.785;
            returnVal = Double.toString(val);

        }
        else if (local_unit.equals("cups")){
            val = qty / 16;
            returnVal = Double.toString(val);

        }

        else if (local_unit.equals("pints")){
            val = qty / 8;
            returnVal = Double.toString(val);

        }

        else if (local_unit.equals("quarts")){
            val = qty / 4;
            returnVal = Double.toString(val);

        }

        else if (local_unit.equals("tsps")){
            val = qty / 768;
            returnVal = Double.toString(val);

        }
        else if (local_unit.equals("tbsps")){
            val = qty / 256;
            returnVal = Double.toString(val);

        }
        return returnVal;
    }

    /**
     * Converts qty from local_unit to fl. oz
     *
     * @param qty -- double the value being converted
     * @param local_unit -- String the unit of the value and its type, e.g, "cups dry"
     * @return String -- the converted qty as a String
     */
    public static String floz(double qty, String local_unit){

        double val;
        String returnVal = "cannot convert from: " + local_unit + " to: fl.oz";

        if(local_unit.equals("fl.oz")){ return "equal units, no conversion needed"; } //

        if(local_unit.equals("mL")){
            val = qty / 29.574;
            returnVal = Double.toString(val);

        }


        else if (local_unit.equals("liters")){
            val = qty * 33.814;
            returnVal = Double.toString(val);

        }
        else if (local_unit.equals("gallons")){
            val = qty * 128;
            returnVal = Double.toString(val);

        }
        else if (local_unit.equals("cups")){
            val = qty * 8;
            returnVal = Double.toString(val);

        }

        else if (local_unit.equals("pints")){
            val = qty / 16;
            returnVal = Double.toString(val);

        }

        else if (local_unit.equals("quarts")){
            val = qty / 32;
            returnVal = Double.toString(val);

        }

        else if (local_unit.equals("tsps")){
            val = qty / 6;
            returnVal = Double.toString(val);

        }
        else if (local_unit.equals("tbsps")){
            val = qty / 2;
            returnVal = Double.toString(val);

        }

        return returnVal;
    }

    /**
     * Converts qty from local_unit to mL
     *
     * @param qty -- double the value being converted
     * @param local_unit -- String the unit of the value and its type, e.g, "cups dry"
     * @return String -- the converted qty as a String
     */
    public static String mL(double qty, String local_unit){

        double val;
        String returnVal = "cannot convert from: " + local_unit + " to: mL";

        if(local_unit.equals("mL")){ return "equal units, no conversion needed"; }

        if(local_unit.equals("fl.oz")){
            val = qty * 29.574;
            returnVal = Double.toString(val);

        }
        else if (local_unit.equals("liters")){
            val = qty * 1000;
            returnVal = Double.toString(val);

        }
        else if (local_unit.equals("gallons")){
            val = qty * 3785;
            returnVal = Double.toString(val);

        }
        else if (local_unit.equals("cups")){
            val = qty * 237;
            returnVal = Double.toString(val);

        }

        else if (local_unit.equals("pints")){
            val = qty * 568;
            returnVal = Double.toString(val);

        }

        else if (local_unit.equals("quarts")){
            val = qty * 946;
            returnVal = Double.toString(val);

        }

        else if (local_unit.equals("tsps")){
            val = qty * 4.929;
            returnVal = Double.toString(val);

        }
        else if (local_unit.equals("tbsps")){
            val = qty * 14.787;
            returnVal = Double.toString(val);

        }

        return returnVal;
    }






    // TEMPORARY RETURN VALS
    //TODO: implement dry conversions
    public String convertDry(double qty, String local_unit, String db_unit){

        String returnMe = "debug convertDry";

        if(local_unit.contains("cups")){
            if(db_unit.equals("oz")){
                returnMe = oz(qty, "cups");
            }

            if (db_unit.equals("grams")){
                returnMe = grams(qty, "cups");
            }

            if (db_unit.equals("lbs")){
                returnMe = lbs(qty, "cups");
            }

            if (db_unit.equals("kgs")){
                returnMe = kgs(qty, "cups");
            }

        } else if (local_unit.contains("tsps")){

            if(db_unit.equals("oz")){
                returnMe = oz(qty, "tsps");
            }

            if (db_unit.equals("grams")){
                returnMe = grams(qty, "tsps");
            }

            if (db_unit.equals("lbs")){
                returnMe = lbs(qty, "tsps");
            }

            if (db_unit.equals("kgs")){
                returnMe = kgs(qty, "tsps");
            }


        } else if (local_unit.contains("tbsps")){

            if(db_unit.equals("oz")){
                returnMe = oz(qty, "tbsps");
            }

            if (db_unit.equals("grams")){
                returnMe = grams(qty, "tbsps");
            }

            if (db_unit.equals("lbs")){
                returnMe = lbs(qty, "tbsps");
            }

            if (db_unit.equals("kgs")){
                returnMe = kgs(qty, "tbsps");
            }

        } else {

            returnMe = "cannot convert from: " + local_unit + " to: " + db_unit;
        }

        return returnMe;

    }



    // removes the "liquid" specifier for now.
    public static String convertLiquid(double qty, String local_unit, String db_unit){

        String returnMe = "debug convertLiquid";

        if(local_unit.contains("cups")){
            if(db_unit.equals("fl.oz")){
                returnMe = floz(qty, "cups");
            }

            if (db_unit.equals("mL")){
                returnMe = mL(qty, "cups");
            }

            if (db_unit.equals("liters")){
                returnMe = liters(qty, "cups");
            }

            if (db_unit.equals("gallons")){
                returnMe = gal(qty, "cups");
            }

        } else if (local_unit.contains("tsps")){

            if(db_unit.equals("fl.oz")){
                returnMe = floz(qty, "tsps");
            }

            if (db_unit.equals("mL")){
                returnMe = mL(qty, "tsps");
            }

            if (db_unit.equals("liters")){
                returnMe = liters(qty, "tsps");
            }

            if (db_unit.equals("gallons")){
                returnMe = gal(qty, "tsps");
            }

        } else if (local_unit.contains("tbsps")){

            if(db_unit.equals("fl.oz")){
                returnMe = floz(qty, "tbsps");
            }

            if (db_unit.equals("mL")){
                returnMe = mL(qty, "tbsps");
            }

            if (db_unit.equals("liters")){
                returnMe = liters(qty, "tbsps");
            }

            if (db_unit.equals("gallons")){
                returnMe = gal(qty, "tbsps");
            }

        } else {

            returnMe = "cannot convert from: " + local_unit + " to: " + db_unit;
        }

        return returnMe;
    }



    ////////////////////////////////////////////


    // reads in the command line input. Used for manual testing.
    private static String readInput(Scanner sc, String arg) {
        System.out.print("Enter "+arg+": ");
        return(sc.nextLine());
    }

    // FOR MANUAL / TERMINAL TESTING
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double qty = 0.0;
        //double noVal = -0.0;
        String local_unit = "";
        String db_unit = "";

        boolean goodVal = false;
        boolean goodLU = false;
        boolean goodDU = false;

        String[] goodLUs =
                {"lbs","kgs","grams","oz",
                        "fl.oz","liters","gallons","mL",
                        "cups dry","cups liquid", "tbsps dry",
                        "tbsps liquid", "tsps dry", "tsps liquid"};

        String[] goodDUs =
                {"lbs","kgs","grams","oz",
                        "fl.oz","liters","gallons","mL"};

        while(!goodVal) {

            String thing = readInput(sc, "qty");

            if (thing.startsWith("-")) {
                System.out.println("Please enter a positive value.");
            } else {
                try {
                    qty = Double.parseDouble(thing);
                    if (qty > 0) {
                        goodVal = true;
                    }
                } catch (Exception NumberFormatException) {
                    System.out.println("Please enter a valid number. Valid numbers are positive and non-zero.");
                }
            }
        }

        while(!goodLU) {

            local_unit = readInput(sc, "local unit");

            // to lowercase

            boolean found = false;

            for (String unit : goodLUs) {
                if (local_unit.equals(unit)) {
                    found = true;
                    goodLU = true;
                    break;
                }

            }
            if (!found) {
                System.out.println("Please enter a valid unit. Valid units are: lbs, kgs, grams, oz, \n" +
                        "                fl.oz, liters, gallons, mL, \n" +
                        "                cups dry, cups liquid, \n" +
                        "                tbsps dry, tbsps liquid, \n" +
                        "                tsps dry, tsps liquid");
            }
        }

        while(!goodDU) {

            boolean found = false;

            db_unit = readInput(sc, "database unit");

            // to lowercase

            for (String unit : goodDUs) {
                if (db_unit.equals(unit)) {
                    goodDU = true;
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Please enter a valid unit. Valid units are: lbs, kgs, grams, oz, \n" +
                        "                fl.oz, liters, gallons, mL");
            }
        }


        String printMe = "converted " + Double.toString(qty) + " " + local_unit + " to: "
                + convertTo(qty,local_unit,db_unit) + " " + db_unit;

        System.out.println(printMe);

    }

































    //////////////////////////////////////////////
    //TODO: TEST RETURNING DOUBLES



    /**
     * Converts qty from local_unit to lbs
     *
     * @param qty -- double the value being converted
     * @param local_unit -- String the unit of the value and its type, e.g, "cups dry"
     * @return
     */
    public static Double lbsD(double qty, String local_unit){

        Double val = null;
        String returnVal = "cannot convert from: " + local_unit + " to: lbs";

        if (local_unit.contains("liquid")){
            //return "do you really need to weigh a liquid?";
        }

        if(local_unit.equals("lbs")){
           // return "equal units, no conversion needed";
        }


        //TODO
        if (local_unit.contains("dry")){

            /* Display warnings for the following conversions due to cups, tsps, and tbsps
            measuring volume and not weight.
            These are approximations based on granulated sugar.*/

            if (local_unit.contains("cups")){
                // val = qty * 0.5;
                //  returnVal = Double.toString(val);
               // return "NYI";
            }

            else if (local_unit.contains("tsps")){
                //  val = qty * 0.011;
                //  returnVal = Double.toString(val);
              //  return "NYI";
            }

            else if(local_unit.contains("tbsps")){
                //   val = qty / 32;
                //   returnVal = Double.toString(val);
               // return "NYI";
            }
            else {
               // return "illegal unit";
            }

        }


        // oz to lbs
        if (local_unit.equals("oz")){

            val = qty / 16;

            // this will be used for testing.
            //String thing = String.format("%.4f",val);

            returnVal = Double.toString(val);
        }

        // gram to lbs
        else if (local_unit.equals("grams")){

            // recursion test
            // convert gram to kgs
            //String thing = kgs(qty,local_unit);
            // extract double
            //double nuQty = Double.parseDouble(thing);
            // convert from kgs to lbs
            //String nuThing = lbs(nuQty,"kgs");
            //val = Double.parseDouble(nuThing);


            val = qty * 0.0022;

            //String thing = String.format("%.4f",val);

            returnVal = Double.toString(val);
        }

        // kgs to lbs
        else if (local_unit.equals("kgs")){
            val = qty * 2.2;
            returnVal = Double.toString(val);
        }
        else {

        }


        return val;

    }

    /**
     * Converts qty from local_unit to kgs
     *
     * @param qty -- double the value being converted
     * @param local_unit -- String the unit of the value and its type, e.g, "cups dry"
     * @return
     */

    public static Double kgsD(double qty, String local_unit){

        Double val = null;
        String returnVal = "cannot convert from: " + local_unit + " to: kgs";

        //if (local_unit.contains("liquid")){ return "do you really need to weigh a liquid?"; }


        //TODO
        if (local_unit.contains("dry")){

            /* Display warnings for the following conversions due to cups, tsps, and tbsps
            measuring volume and not weight.
            These are approximations based on granulated sugar.*/

            if (local_unit.contains("cups")){

                //  returnVal = Double.toString(val);
                //return "NYI";
            }

            else if (local_unit.contains("tsps")){
                //  val = qty
                //  returnVal = Double.toString(val);
               // return "NYI";
            }

            else if(local_unit.contains("tbsps")){
                //   val = qty
                //   returnVal = Double.toString(val);
               // return "NYI";
            }
            else {
               // return "illegal unit";
            }

        }


        if (local_unit.equals("grams")){
            val = qty / 1000;
            returnVal = Double.toString(val);
        }

        else if (local_unit.equals("oz")){

            val = qty / 35.274;
            returnVal = Double.toString(val);
        }

        else if (local_unit.equals("lbs")){
            val = qty / 2.2;
            returnVal = Double.toString(val);
        }
        else {
            //TODO

        }

        return val;

    }

    /**
     * Converts qty from local_unit to grams
     *
     * @param qty -- double the value being converted
     * @param local_unit -- String the unit of the value and its type, e.g, "cups dry"
     * @return
     */
    public static Double gramsD(double qty, String local_unit){

        Double val = null;
        String returnVal = "cannot convert from: " + local_unit + " to: grams";

       // if (local_unit.contains("liquid")){ return "do you really need to weigh a liquid?"; }

        //if(local_unit.equals("grams")){ return "equal units, no conversion needed"; }

        //TODO
        if (local_unit.contains("dry")){

            /* Display warnings for the following conversions due to cups, tsps, and tbsps
            measuring volume and not weight.
            These are approximations based on granulated sugar.*/

            if (local_unit.contains("cups")){
                // val = qty * 241.23;
                // val = qty
                //  returnVal = Double.toString(val);
               // return "NYI";
            }

            else if (local_unit.contains("tsps")){
                // 1 tsp = 5 g
                //  val = qty
                //  returnVal = Double.toString(val);
               // return "NYI";
            }

            else if(local_unit.contains("tbsps")){
                // 1 tbsp = 15 g
                //   val = qty
                //   returnVal = Double.toString(val);
               // return "NYI";
            }
            else {
               // return "illegal unit";
            }

        }



        // oz to gram
        else if (local_unit.equals("oz")){
            // may be necessary to truncate
            //String thing = String.format("%.4f",val);
            val = qty * 28.35;
            returnVal = Double.toString(val);
        }

        // lbs to gram
        else if (local_unit.equals("lbs")){

            // may be necessary to truncate
            //String thing = String.format("%.4f",val);
            val = qty * 454;
            returnVal = Double.toString(val);
        }

        // kgs to gram
        else if (local_unit.equals("kgs")){
            val = qty * 1000;
            returnVal = Double.toString(val);
        }
        else {

        }


        return val;

    }

    /**
     * Converts qty from local_unit to oz
     *
     * @param qty -- double the value being converted
     * @param local_unit -- String the unit of the value and its type, e.g, "cups dry"
     * @return
     */
    public static Double ozD(double qty, String local_unit){

        Double val = null;
        String returnVal = "cannot convert from: " + local_unit + " to: oz";

       // if (local_unit.contains("liquid")){ return "do you really need to weigh a liquid? use fl. oz!"; }


        //TODO
        if (local_unit.contains("dry")){

            /* Display warnings for the following conversions due to cups, tsps, and tbsps
            measuring volume and not weight.
            These are approximations based on granulated sugar.*/

            if (local_unit.contains("cups")){
                // val = qty
                //  returnVal = Double.toString(val);
               // return "NYI";
            }

            else if (local_unit.contains("tsps")){
                //  val = qty
                //  returnVal = Double.toString(val);
              //  return "NYI";
            }

            else if(local_unit.contains("tbsps")){
                //   val =
                //   returnVal = Double.toString(val);
             //   return "NYI";
            }
            else {
               // return "illegal unit";
            }
        }

        // gram to oz
        else if (local_unit.equals("grams")){

            val = qty / 28.35;
            returnVal = Double.toString(val);
        }

        // lbs to oz
        else if (local_unit.equals("lbs")){

            val = qty * 16;
            returnVal = Double.toString(val);
        }

        // kgs to oz
        else if (local_unit.equals("kgs")){

            val = qty * 35.274;
            returnVal = Double.toString(val);
        }
        else{

        }


        return val;

    }


    /**
     * Converts qty from local_unit to liters
     *
     * @param qty -- double the value being converted
     * @param local_unit -- String the unit of the value and its type, e.g, "cups dry"
     * @return
     */

    public static Double litersD(double qty, String local_unit){

        Double val = null;
        String returnVal = "cannot convert from: " + local_unit + " to: liters";



        if(local_unit.equals("mL")){
            val = qty / 1000;
            returnVal = Double.toString(val);

        }
        else if (local_unit.equals("fl.oz")){
            val = qty / 33.814;
            returnVal = Double.toString(val);

        }
        else if (local_unit.equals("gallons")){
            val = qty * 3.785;
            returnVal = Double.toString(val);

        }
        else if (local_unit.equals("cups")){
            val = qty / 4.227;
            returnVal = Double.toString(val);

        }

        else if (local_unit.equals("pints")){
            val = qty / 2.113;
            returnVal = Double.toString(val);

        }

        else if (local_unit.equals("quarts")){
            val = qty / 1.057;
            returnVal = Double.toString(val);

        }

        else if (local_unit.equals("tsps")){
            val = qty / 203;
            returnVal = Double.toString(val);

        }
        else if (local_unit.equals("tbsps")){
            val = qty / 67.628;
            returnVal = Double.toString(val);

        }
        return val;
    }

    /**
     * Converts qty from local_unit to gallons
     *
     * @param qty -- double the value being converted
     * @param local_unit -- String the unit of the value and its type, e.g, "cups dry"
     * @return
     */

    public static Double galD(double qty, String local_unit){

        Double val = null;

        String returnVal = "cannot convert from: " + local_unit + " to: gals";


        if(local_unit.equals("mL")){
            val = qty / 3785;
            returnVal = Double.toString(val);
        }

        else if (local_unit.equals("fl.oz")){
            val = qty / 128;
            returnVal = Double.toString(val);

        }
        else if (local_unit.equals("liters")){
            val = qty / 3.785;
            returnVal = Double.toString(val);

        }
        else if (local_unit.equals("cups")){
            val = qty / 16;
            returnVal = Double.toString(val);

        }

        else if (local_unit.equals("pints")){
            val = qty / 8;
            returnVal = Double.toString(val);

        }

        else if (local_unit.equals("quarts")){
            val = qty / 4;
            returnVal = Double.toString(val);

        }

        else if (local_unit.equals("tsps")){
            val = qty / 768;
            returnVal = Double.toString(val);

        }
        else if (local_unit.equals("tbsps")){
            val = qty / 256;
            returnVal = Double.toString(val);

        }
        return val;
    }

    /**
     * Converts qty from local_unit to fl. oz
     *
     * @param qty -- double the value being converted
     * @param local_unit -- String the unit of the value and its type, e.g, "cups dry"
     * @return
     */
    public static Double flozD(double qty, String local_unit){

        Double val = null;


        if(local_unit.equals("mL")){
            val = qty / 29.574;
        }

        else if (local_unit.equals("liters")){
            val = qty * 33.814;

        }
        else if (local_unit.equals("gallons")){
            val = qty * 128;

        }
        else if (local_unit.equals("cups")){
            val = qty * 8;
        }

        else if (local_unit.equals("pints")){
            val = qty / 16;
        }

        else if (local_unit.equals("quarts")){
            val = qty / 32;
        }

        else if (local_unit.equals("tsps")){
            val = qty / 6;

        }
        else if (local_unit.equals("tbsps")){
            val = qty / 2;
        }

        return val;
    }

    /**
     * Converts qty from local_unit to mL
     *
     * @param qty -- double the value being converted
     * @param local_unit -- String the unit of the value and its type, e.g, "cups dry"
     * @return
     */
    public static Double mLD(double qty, String local_unit){

        //double val;

        Double val = null;

        if(local_unit.equals("fl.oz")){
            val = qty * 29.574;


        }
        else if (local_unit.equals("liters")){
            val = qty * 1000;

        }
        else if (local_unit.equals("gallons")){
            val = qty * 3785;

        }
        else if (local_unit.equals("cups")){
            val = qty * 237;

        }

        else if (local_unit.equals("pints")){
            val = qty * 568;

        }

        else if (local_unit.equals("quarts")){
            val = qty * 946;

        }

        else if (local_unit.equals("tsps")){
            val = qty * 4.929;

        }
        else if (local_unit.equals("tbsps")){
            val = qty * 14.787;

        }

        return val;
    }
}