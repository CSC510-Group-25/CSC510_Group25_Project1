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

public class unit_conversion {

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
    public String convertTo(double qty, String local_unit, String db_unit){

        //should be handled when items are being added to the list of ingredients.
        // qty should also be converted to double upon input.
        //if (qty < 0){ }: illegal qty
        //if qty = 0, null, empty: don't perform any conversions.

        // no conversions needed
        if(local_unit.equals(db_unit)){ return "equal units, no conversion needed"; }


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


        return "debug me";

    }


    /**
     * Converts qty from local_unit to lbs
     *
     * @param qty -- double the value being converted
     * @param local_unit -- String the unit of the value and its type, e.g, "cups dry"
     * @return String -- the converted qty as a String
     */
    public String lbs(double qty, String local_unit){

        double val;
        String returnVal = "cannot convert from: " + local_unit + " to: lbs";

        if (local_unit.contains("liquid")){
            return "do you really need to weigh a liquid?";
        }

        if(local_unit.equals("lbs")){ return "equal units, no conversion needed"; }


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

    public String kgs(double qty, String local_unit){

        double val;
        String returnVal = "cannot convert from: " + local_unit + " to: kgs";

        if (local_unit.contains("liquid")){
            return "do you really need to weigh a liquid?";
        }

        if(local_unit.equals("kgs")){ return "equal units, no conversion needed"; }


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
    public String grams(double qty, String local_unit){

        double val;
        String returnVal = "cannot convert from: " + local_unit + " to: grams";

        if (local_unit.contains("liquid")){
            return "do you really need to weigh a liquid?";
        }

        if(local_unit.equals("grams")){ return "equal units, no conversion needed"; }

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
    public String oz(double qty, String local_unit){

        double val;
        String returnVal = "cannot convert from: " + local_unit + " to: oz";

        if (local_unit.contains("liquid")){
            return "do you really need to weigh a liquid? use fl. oz!";
        }

        if(local_unit.equals("oz")){ return "equal units, no conversion needed"; }

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

    public String liters(double qty, String local_unit){

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

    public String gal(double qty, String local_unit){

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
    public String floz(double qty, String local_unit){

        double val;
        String returnVal = "cannot convert from: " + local_unit + " to: fl.oz";

        if(local_unit.equals("fl.oz")){ return "equal units, no conversion needed"; }

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
    public String mL(double qty, String local_unit){

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
    public String convertLiquid(double qty, String local_unit, String db_unit){

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
}
