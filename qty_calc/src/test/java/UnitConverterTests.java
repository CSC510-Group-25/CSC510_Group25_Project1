//package com.qtycalc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitConverterTests {

    @Test
    void convertTo_testSame() {
        // msg1: "equal units, no conversion needed";
        assertEquals("msg1", UnitConverter.convertTo(1, "grams", "grams"));
        assertEquals("msg1", UnitConverter.convertTo(1, "kgs", "kgs"));
        assertEquals("msg1", UnitConverter.convertTo(1, "lbs", "lbs"));
        assertEquals("msg1", UnitConverter.convertTo(1, "oz", "oz"));
        assertEquals("msg1", UnitConverter.convertTo(1, "mL", "mL"));
        assertEquals("msg1", UnitConverter.convertTo(1, "liters", "liters"));
        assertEquals("msg1", UnitConverter.convertTo(1, "fl.oz", "fl.oz"));
        assertEquals("msg1", UnitConverter.convertTo(1, "gallons", "gallons"));
    }

    // tests routing AND liquid/dry handling
    @Test
    void convertTo_test_oz() {
        String to_oz = UnitConverter.convertTo(1,"grams","oz");
        double val = Double.parseDouble(to_oz);
        String result = String.format("%.5f", val);
        assertEquals("0.03527",result);

        to_oz = UnitConverter.convertTo(1, "lbs", "oz");
        assertEquals("16.0",to_oz);

        to_oz = UnitConverter.convertTo(1, "kgs", "oz");
        assertEquals("35.274",to_oz);

        to_oz = UnitConverter.convertTo(1, "cups liquid", "oz");
        assertEquals("msg2",to_oz);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;

        to_oz = UnitConverter.convertTo(1, "cups dry", "oz");
        assertEquals("msg3",to_oz);
        // msg3: "NYI"

        to_oz = UnitConverter.convertTo(1, "mL", "oz");
        assertEquals("msg2",to_oz);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
    }

    // test to ensure correct routing in convertTo
    @Test
    void convertTo_test_routing() {

        String to_oz = UnitConverter.convertTo(1, "lbs", "oz");
        assertEquals("16.0",to_oz);

        String to_lbs = UnitConverter.convertTo(16,"oz","lbs");
        assertEquals("1.0",to_lbs);

        String to_kg = UnitConverter.convertTo(1000, "grams", "kgs");
        assertEquals("1.0",to_kg);

        String to_g = UnitConverter.convertTo(1, "kgs", "grams");
        assertEquals("1000.0",to_g);

        String to_mL = UnitConverter.convertTo(1, "liters", "mL");
        assertEquals("1000.0",to_mL);

        String to_liters = UnitConverter.convertTo(1000, "mL", "liters");
        assertEquals("1.0",to_liters);

        String to_floz = UnitConverter.convertTo(1, "gallons", "fl.oz");
        assertEquals("128.0",to_floz);

        String to_gal = UnitConverter.convertTo(128, "fl.oz", "gallons");
        assertEquals("1.0",to_gal);
    }

    /////// WEIGHT CONVERSION TESTS //////

    @Test
    void g_to_g(){
        String gtg = UnitConverter.grams(1,"grams");
        assertEquals("msg1",gtg);
        // msg1: "equal units, no conversion needed";
    }
    @Test
    void g_to_oz() {
        String to_oz = UnitConverter.oz(1,"grams");
        double goz = Double.parseDouble(to_oz);
        String result = String.format("%.5f", goz);
        assertEquals("0.03527",result);
    }
    @Test
    void g_to_kgs() {
        String to_kgs = UnitConverter.kgs(1000,"grams");
        assertEquals("1.0",to_kgs);
    }
    @Test
    void g_to_lbs() {
        String to_lbs = UnitConverter.lbs(1,"grams");
        double val = Double.parseDouble(to_lbs);
        String result = String.format("%.5f", val);
        assertEquals("0.00220",result);
    }
    @Test
    void oz_to_g() {
        String to_g = UnitConverter.grams(1,"oz");
        assertEquals("28.35",to_g);
    }
    @Test
    void oz_to_oz(){
        String gtg = UnitConverter.oz(1,"oz");
        assertEquals("msg1",gtg);
        // msg1: "equal units, no conversion needed";
    }
    @Test
    void oz_to_lbs() {
        String to_lbs = UnitConverter.lbs(16,"oz");
        // double tolbs = Double.parseDouble(to_lbs);
        assertEquals("1.0",to_lbs);
    }
    @Test
    void oz_to_kgs() {
        String to_kgs = UnitConverter.kgs(10,"oz");
        double tok = Double.parseDouble(to_kgs);
        String result = String.format("%.5f", tok);
        assertEquals("0.28349",result);
    }
    @Test
    void lbs_to_oz() {
        String to_oz = UnitConverter.oz(1,"lbs");
        assertEquals("16.0",to_oz);
    }
    @Test
    void lbs_to_g() {
        String to_g = UnitConverter.grams(1,"lbs");
        assertEquals("454.0",to_g);
    }
    @Test
    void lbs_to_lbs(){
        String gtg = UnitConverter.lbs(1,"lbs");
        assertEquals("msg1",gtg);
        // msg1: "equal units, no conversion needed";
    }
    @Test
    void lbs_to_kgs() {
        String to_kgs = UnitConverter.kgs(1,"lbs");
        double tok = Double.parseDouble(to_kgs);
        String result = String.format("%.5f", tok);
        assertEquals("0.45351",result);
    }
    @Test
    void kgs_to_oz() {
        String to_oz = UnitConverter.oz(1,"kgs");
        assertEquals("35.274",to_oz);
    }
    @Test
    void kgs_to_g() {
        String to_g = UnitConverter.grams(1,"kgs");
        assertEquals("1000.0",to_g);
    }
    @Test
    void kgs_to_lbs(){
        String to_lbs = UnitConverter.lbs(1,"kgs");
        assertEquals("2.205",to_lbs);
    }
    @Test
    void kgs_to_kgs(){
        String gtg = UnitConverter.kgs(1,"kgs");
        assertEquals("msg1",gtg);
        // msg1: "equal units, no conversion needed";
    }

    ////// THESE CONVERSIONS HAVE NOT BEEN IMPLEMENTED YET //////
    @Test
    void tsp_to_kgs(){
        String gtg = UnitConverter.kgs(1,"tsps");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    void tsp_to_g(){
        String gtg = UnitConverter.grams(1,"tsps");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    void tsp_to_lbs(){
        String gtg = UnitConverter.lbs(1,"tsps");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    void tsp_to_oz(){
        String gtg = UnitConverter.oz(1,"tsps");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    void tbsp_to_kgs(){
        String gtg = UnitConverter.kgs(1,"tbsps");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    void tbsp_to_g(){
        String gtg = UnitConverter.grams(1,"tbsps");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    void tbsp_to_lbs(){
        String gtg = UnitConverter.lbs(1,"tbsps");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    void tbsp_to_oz(){
        String gtg = UnitConverter.oz(1,"tbsps");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    void cup_to_kgs(){
        String gtg = UnitConverter.kgs(1,"cups");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    void cup_to_g(){
        String gtg = UnitConverter.grams(1,"cups");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    void cup_to_lbs(){
        String gtg = UnitConverter.lbs(1,"cups");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    void cup_to_oz(){
        String gtg = UnitConverter.oz(1,"cups");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }

    ///// VOLUME CONVERSION TESTS ////////

    @Test
    void tsp_to_mL(){
        String convert = UnitConverter.mL(1,"tsps");
        assertEquals("4.929",convert);
    }
    @Test
    void tsp_to_floz(){
        String convert = UnitConverter.floz(6,"tsps");
        assertEquals("1.0",convert);
    }
    @Test
    void tsp_to_gal(){
        String convert = UnitConverter.gal(100,"tsps");
        double val = Double.parseDouble(convert);
        String result = String.format("%.5f", val);
        assertEquals("0.13021",result);
    }

    @Test
    void tsp_to_liter(){
        String convert = UnitConverter.liters(100,"tsps");
        double val = Double.parseDouble(convert);
        String result = String.format("%.5f", val);
        assertEquals("0.49261",result);
    }
    @Test
    void tbsp_to_mL(){
        String convert = UnitConverter.mL(1,"tbsps");
        assertEquals("14.787",convert);
    }
    @Test
    void tbsp_to_floz(){
        String convert = UnitConverter.floz(1,"tbsps");
        assertEquals("0.5",convert);
    }
    @Test
    void tbsp_to_gal(){
        String convert = UnitConverter.gal(256,"tbsps");
        assertEquals("1.0",convert);
    }
    @Test
    void tbsp_to_liter(){
        String convert = UnitConverter.liters(1,"tbsps");
        double val = Double.parseDouble(convert);
        String result = String.format("%.5f", val);
        assertEquals("0.01479",result);
    }
    @Test
    void cup_to_mL(){
        String convert = UnitConverter.mL(1,"cups");
        assertEquals("237.0",convert);
    }
    @Test
    void cup_to_floz(){
        String convert = UnitConverter.floz(1,"cups");
        assertEquals("8.0",convert);
    }
    @Test
    void cup_to_gal(){
        String convert = UnitConverter.gal(1,"cups");
        assertEquals("0.0625",convert);
    }
    @Test
    void cup_to_liter(){
        String convert = UnitConverter.liters(1,"cups");
        double val = Double.parseDouble(convert);
        String result = String.format("%.5f", val);
        assertEquals("0.23657",result);
    }
    @Test
    void pint_to_mL(){
        String convert = UnitConverter.mL(1,"pints");
        assertEquals("568.0",convert);
    }
    @Test
    void pint_to_floz(){
        String convert = UnitConverter.floz(1,"pints");
        assertEquals("16.0",convert);
    }
    @Test
    void pint_to_gal(){
        String convert = UnitConverter.gal(1,"pints");
        assertEquals("0.125",convert);
    }
    @Test
    void pint_to_liter(){
        String convert = UnitConverter.liters(1,"pints");
        double val = Double.parseDouble(convert);
        String result = String.format("%.5f", val);
        assertEquals("0.47326",result);
    }
    @Test
    void quart_to_mL(){
        String convert = UnitConverter.mL(1,"quarts");
        assertEquals("946.0",convert);
    }

    @Test
    void quart_to_floz(){
        String convert = UnitConverter.floz(1,"quarts");
        assertEquals("32.0",convert);
    }

    @Test
    void quart_to_gal(){
        String convert = UnitConverter.gal(1,"quarts");
        assertEquals("0.25",convert);
    }

    @Test
    void quart_to_liter(){
        String convert = UnitConverter.liters(1,"quarts");
        double val = Double.parseDouble(convert);
        String result = String.format("%.5f", val);
        assertEquals("0.94607",result);
    }

    @Test
    void floz_to_mL(){
        String convert = UnitConverter.mL(1,"fl.oz");
        assertEquals("29.574",convert);
    }

    @Test
    void floz_to_floz(){
        String gtg = UnitConverter.floz(1,"fl.oz");
        assertEquals("msg1",gtg);
        // msg1: "equal units, no conversion needed";
    }

    @Test
    void floz_to_gal(){
        String convert = UnitConverter.gal(128,"fl.oz");
        assertEquals("1.0",convert);
    }
    @Test
    void floz_to_liter(){
        String convert = UnitConverter.liters(1,"fl.oz");
        double val = Double.parseDouble(convert);
        String result = String.format("%.5f", val);
        assertEquals("0.02957",result);
    }

    @Test
    void gal_to_mL(){
        String convert = UnitConverter.mL(1,"gallons");
        assertEquals("3785.0",convert);
    }
    @Test
    void gal_to_floz(){
        String convert = UnitConverter.floz(1,"gallons");
        assertEquals("128.0",convert);
    }

    @Test
    void gal_to_gal(){
        String gtg = UnitConverter.gal(1,"gallons");
        assertEquals("msg1",gtg);
        // msg1: "equal units, no conversion needed";
    }

    @Test
    void gal_to_liter(){
        String convert = UnitConverter.liters(1,"gallons");
        assertEquals("3.785",convert);
    }

    @Test
    void liter_to_mL(){
        String convert = UnitConverter.mL(1,"liters");
        assertEquals("1000.0",convert);
    }

    @Test
    void liter_to_floz(){
        String convert = UnitConverter.floz(1,"liters");
        assertEquals("33.814",convert);
    }
    @Test
    void liter_to_gal(){
        String convert = UnitConverter.gal(1,"liters");
        double val = Double.parseDouble(convert);
        String result = String.format("%.5f", val);
        assertEquals("0.26420",result);
    }

    @Test
    void liter_to_liter(){
        String gtg = UnitConverter.liters(1,"liters");
        assertEquals("msg1",gtg);
        // msg1: "equal units, no conversion needed";
    }

    @Test
    void mL_to_mL(){
        String gtg = UnitConverter.mL(1,"mL");
        assertEquals("msg1",gtg);
        // msg1: "equal units, no conversion needed";
    }

    @Test
    void mL_to_floz(){
        String convert = UnitConverter.floz(10,"mL");
        double val = Double.parseDouble(convert);
        String result = String.format("%.5f", val);
        assertEquals("0.33813",result);
    }
    @Test
    void mL_to_gal(){
        String convert = UnitConverter.gal(4000,"mL");
        double val = Double.parseDouble(convert);
        String result = String.format("%.4f", val);
        assertEquals("1.0568",result);
    }

    @Test
    void mL_to_liter(){
        String convert = UnitConverter.liters(1000,"mL");
        assertEquals("1.0",convert);
    }
}