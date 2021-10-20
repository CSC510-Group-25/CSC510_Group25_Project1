import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;

/*
 * MIT License
 *
 * Copyright (c) 2021. CSC510-Group-25
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/**************************
 * Originally authored by: Leila Moran
 * GitHub ID: snapcat
 *
 * CSC510 Fall 2021
 * North Carolina State University
 *
 * File name: QtyCalcTests.java
 **************************/

/**
 * Class for unit tests.
 *
 * Unimplemented methods will not be tested.
 */
public class VolumeConverterTests {

    // in case we need to capture printed output
    private ByteArrayOutputStream output;
    private final InputStream stdIn = System.in;
    private final PrintStream stdOut = System.out;

    //@Before
    public void setupIO() {
        // to help manage IO
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    //@After
    public void restoreIO() {
        // restores standard input/output
        System.setIn(stdIn);
        System.setOut(stdOut);
    }

    ////////// UNIT CONVERSION TESTS //////////

    @Test
    public void convertTo_testSame() {
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
    public void convertTo_test_oz() {
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
    public void convertTo_test_routing() {

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
    public void g_to_g(){
        String gtg = UnitConverter.grams(1,"grams");
        assertEquals("msg1",gtg);
        // msg1: "equal units, no conversion needed";
    }
    @Test
    public void g_to_oz() {
        String to_oz = UnitConverter.oz(1,"grams");
        double goz = Double.parseDouble(to_oz);
        String result = String.format("%.5f", goz);
        assertEquals("0.03527",result);
    }
    @Test
    public void g_to_kgs() {
        String to_kgs = UnitConverter.kgs(1000,"grams");
        assertEquals("1.0",to_kgs);
    }
    @Test
    public void g_to_lbs() {
        String to_lbs = UnitConverter.lbs(1,"grams");
        double val = Double.parseDouble(to_lbs);
        String result = String.format("%.5f", val);
        assertEquals("0.00220",result);
    }
    @Test
    public void oz_to_g() {
        String to_g = UnitConverter.grams(1,"oz");
        assertEquals("28.35",to_g);
    }
    @Test
    public void oz_to_oz(){
        String gtg = UnitConverter.oz(1,"oz");
        assertEquals("msg1",gtg);
        // msg1: "equal units, no conversion needed";
    }
    @Test
    public void oz_to_lbs() {
        String to_lbs = UnitConverter.lbs(16,"oz");
        // double tolbs = Double.parseDouble(to_lbs);
        assertEquals("1.0",to_lbs);
    }
    @Test
    public void oz_to_kgs() {
        String to_kgs = UnitConverter.kgs(10,"oz");
        double tok = Double.parseDouble(to_kgs);
        String result = String.format("%.5f", tok);
        assertEquals("0.28349",result);
    }
    @Test
    public void lbs_to_oz() {
        String to_oz = UnitConverter.oz(1,"lbs");
        assertEquals("16.0",to_oz);
    }
    @Test
    public void lbs_to_g() {
        String to_g = UnitConverter.grams(1,"lbs");
        assertEquals("454.0",to_g);
    }
    @Test
    public void lbs_to_lbs(){
        String gtg = UnitConverter.lbs(1,"lbs");
        assertEquals("msg1",gtg);
        // msg1: "equal units, no conversion needed";
    }
    @Test
    public void lbs_to_kgs() {
        String to_kgs = UnitConverter.kgs(1,"lbs");
        double tok = Double.parseDouble(to_kgs);
        String result = String.format("%.5f", tok);
        assertEquals("0.45351",result);
    }
    @Test
    public void kgs_to_oz() {
        String to_oz = UnitConverter.oz(1,"kgs");
        assertEquals("35.274",to_oz);
    }
    @Test
    public void kgs_to_g() {
        String to_g = UnitConverter.grams(1,"kgs");
        assertEquals("1000.0",to_g);
    }
    @Test
    public void kgs_to_lbs(){
        String to_lbs = UnitConverter.lbs(1,"kgs");
        assertEquals("2.205",to_lbs);
    }
    @Test
    public void kgs_to_kgs(){
        String gtg = UnitConverter.kgs(1,"kgs");
        assertEquals("msg1",gtg);
        // msg1: "equal units, no conversion needed";
    }

    ////// THESE CONVERSIONS HAVE NOT BEEN IMPLEMENTED YET //////
    @Test
    public void tsp_to_kgs(){
        String gtg = UnitConverter.kgs(1,"tsps");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    public void tsp_to_g(){
        String gtg = UnitConverter.grams(1,"tsps");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    public void tsp_to_lbs(){
        String gtg = UnitConverter.lbs(1,"tsps");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    public void tsp_to_oz(){
        String gtg = UnitConverter.oz(1,"tsps");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    public void tbsp_to_kgs(){
        String gtg = UnitConverter.kgs(1,"tbsps");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    public void tbsp_to_g(){
        String gtg = UnitConverter.grams(1,"tbsps");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    public void tbsp_to_lbs(){
        String gtg = UnitConverter.lbs(1,"tbsps");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    public void tbsp_to_oz(){
        String gtg = UnitConverter.oz(1,"tbsps");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    public void cup_to_kgs(){
        String gtg = UnitConverter.kgs(1,"cups");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    public void cup_to_g(){
        String gtg = UnitConverter.grams(1,"cups");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    public void cup_to_lbs(){
        String gtg = UnitConverter.lbs(1,"cups");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    public void cup_to_oz(){
        String gtg = UnitConverter.oz(1,"cups");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
}
