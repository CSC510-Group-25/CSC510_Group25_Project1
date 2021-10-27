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
 * Originally authored by: Asha Khatri
 * GitHub ID: ashakhatri007
 *
 * CSC510 Fall 2021
 * North Carolina State University
 *
 * File name: VolumeConverterTests.java
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

     ///// VOLUME CONVERSION TESTS ////////

     @Test
     public void tsp_to_mL(){
         String convert = UnitConverter.mL(1,"tsps");
         assertEquals("4.929",convert);
     }
     @Test
     public void tsp_to_floz(){
         String convert = UnitConverter.floz(6,"tsps");
         assertEquals("1.0",convert);
     }
     @Test
     public void tsp_to_gal(){
         String convert = UnitConverter.gal(100,"tsps");
         double val = Double.parseDouble(convert);
         String result = String.format("%.5f", val);
         assertEquals("0.13021",result);
     }
 
     @Test
     public void tsp_to_liter(){
         String convert = UnitConverter.liters(100,"tsps");
         double val = Double.parseDouble(convert);
         String result = String.format("%.5f", val);
         assertEquals("0.49261",result);
     }
     @Test
     public void tbsp_to_mL(){
         String convert = UnitConverter.mL(1,"tbsps");
         assertEquals("14.787",convert);
     }
     @Test
     public void tbsp_to_floz(){
         String convert = UnitConverter.floz(1,"tbsps");
         assertEquals("0.5",convert);
     }
     @Test
     public void tbsp_to_gal(){
         String convert = UnitConverter.gal(256,"tbsps");
         assertEquals("1.0",convert);
     }
     @Test
     public void tbsp_to_liter(){
         String convert = UnitConverter.liters(1,"tbsps");
         double val = Double.parseDouble(convert);
         String result = String.format("%.5f", val);
         assertEquals("0.01479",result);
     }
     @Test
     public void cup_to_mL(){
         String convert = UnitConverter.mL(1,"cups");
         assertEquals("237.0",convert);
     }
     @Test
     public void cup_to_floz(){
         String convert = UnitConverter.floz(1,"cups");
         assertEquals("8.0",convert);
     }
     @Test
     public void cup_to_gal(){
         String convert = UnitConverter.gal(1,"cups");
         assertEquals("0.0625",convert);
     }
     @Test
     public void cup_to_liter(){
         String convert = UnitConverter.liters(1,"cups");
         double val = Double.parseDouble(convert);
         String result = String.format("%.5f", val);
         assertEquals("0.23657",result);
     }
     @Test
     public void pint_to_mL(){
         String convert = UnitConverter.mL(1,"pints");
         assertEquals("568.0",convert);
     }
     @Test
     public void pint_to_floz(){
         String convert = UnitConverter.floz(1,"pints");
         assertEquals("16.0",convert);
     }
     @Test
     public void pint_to_gal(){
         String convert = UnitConverter.gal(1,"pints");
         assertEquals("0.125",convert);
     }
     @Test
     public void pint_to_liter(){
         String convert = UnitConverter.liters(1,"pints");
         double val = Double.parseDouble(convert);
         String result = String.format("%.5f", val);
         assertEquals("0.47326",result);
     }
     @Test
     public void quart_to_mL(){
         String convert = UnitConverter.mL(1,"quarts");
         assertEquals("946.0",convert);
     }
 
     @Test
     public void quart_to_floz(){
         String convert = UnitConverter.floz(1,"quarts");
         assertEquals("32.0",convert);
     }
 
     @Test
     public void quart_to_gal(){
         String convert = UnitConverter.gal(1,"quarts");
         assertEquals("0.25",convert);
     }
 
     @Test
     public void quart_to_liter(){
         String convert = UnitConverter.liters(1,"quarts");
         double val = Double.parseDouble(convert);
         String result = String.format("%.5f", val);
         assertEquals("0.94607",result);
     }
 
     @Test
     public void floz_to_mL(){
         String convert = UnitConverter.mL(1,"fl.oz");
         assertEquals("29.574",convert);
     }
 
     @Test
     public void floz_to_floz(){
         String gtg = UnitConverter.floz(1,"fl.oz");
         assertEquals("msg1",gtg);
         // msg1: "equal units, no conversion needed";
     }
 
     @Test
     public void floz_to_gal(){
         String convert = UnitConverter.gal(128,"fl.oz");
         assertEquals("1.0",convert);
     }
     @Test
     public void floz_to_liter(){
         String convert = UnitConverter.liters(1,"fl.oz");
         double val = Double.parseDouble(convert);
         String result = String.format("%.5f", val);
         assertEquals("0.02957",result);
     }
 
     @Test
     public void gal_to_mL(){
         String convert = UnitConverter.mL(1,"gallons");
         assertEquals("3785.0",convert);
     }
     @Test
     public void gal_to_floz(){
         String convert = UnitConverter.floz(1,"gallons");
         assertEquals("128.0",convert);
     }
 
     @Test
     public void gal_to_gal(){
         String gtg = UnitConverter.gal(1,"gallons");
         assertEquals("msg1",gtg);
         // msg1: "equal units, no conversion needed";
     }
 
     @Test
     public void gal_to_liter(){
         String convert = UnitConverter.liters(1,"gallons");
         assertEquals("3.785",convert);
     }
 
     @Test
     public void liter_to_mL(){
         String convert = UnitConverter.mL(1,"liters");
         assertEquals("1000.0",convert);
     }
 
     @Test
     public void liter_to_floz(){
         String convert = UnitConverter.floz(1,"liters");
         assertEquals("33.814",convert);
     }
     @Test
     public void liter_to_gal(){
         String convert = UnitConverter.gal(1,"liters");
         double val = Double.parseDouble(convert);
         String result = String.format("%.5f", val);
         assertEquals("0.26420",result);
     }
 
     @Test
     public void liter_to_liter(){
         String gtg = UnitConverter.liters(1,"liters");
         assertEquals("msg1",gtg);
         // msg1: "equal units, no conversion needed";
     }
 
     @Test
     public void mL_to_mL(){
         String gtg = UnitConverter.mL(1,"mL");
         assertEquals("msg1",gtg);
         // msg1: "equal units, no conversion needed";
     }
 
     @Test
     public void mL_to_floz(){
         String convert = UnitConverter.floz(10,"mL");
         double val = Double.parseDouble(convert);
         String result = String.format("%.5f", val);
         assertEquals("0.33813",result);
     }
     @Test
     public void mL_to_gal(){
         String convert = UnitConverter.gal(4000,"mL");
         double val = Double.parseDouble(convert);
         String result = String.format("%.4f", val);
         assertEquals("1.0568",result);
     }
 
     @Test
     public void mL_to_liter(){
         String convert = UnitConverter.liters(1000,"mL");
         assertEquals("1.0",convert);
     }
}
