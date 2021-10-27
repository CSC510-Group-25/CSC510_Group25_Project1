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
 * File name: ItemTests.java
 **************************/

/**
 * Class for unit tests.
 *
 * Unimplemented methods will not be tested.
 */

 public class ItemTests {

     // in case we need to capture printed output
     private ByteArrayOutputStream output;
     private final InputStream stdIn = System.in;
     private final PrintStream stdOut = System.out;
     Util util = new Util();
 
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
     
     @Test
     public void itemAsJsonTest() {
         Item i1 = new Item("thing1","9891",1000.0,"fl.oz");
 
         JsonObject jo = i1.itemAsJson();
 
         String name = (String) jo.get("name");
         String dbid = (String) jo.get("ID");
         String qty = (String) jo.get("quantity").toString();
         String dbu = (String) jo.get("unit");
 
         assertEquals("thing1",name);
         assertEquals("9891",dbid);
         assertEquals("1000.0",qty);
         assertEquals("fl.oz",dbu);
     }
 
     @Test
     public void Item_toString() {
         Item i1 = new Item("thing1","9891",1000.0,"fl.oz");
         Item i2 = new Item("thing1","9891",1000.0,"fl.oz");
         Item i3 = new Item("thing3","1234",100.0,"liters");
         assertTrue(i1.isEqual(i2));
         assertFalse(i1.isEqual(i3));
     }
 
     @Test
     public void Item_getters() {
         Item i1 = new Item("thing1","9891",1000.0,"fl.oz");
         assertEquals("thing1",i1.getItemName());
         assertEquals("9891",i1.getDbID());
         assertEquals("1000.0",i1.getQty().toString());
         assertEquals("fl.oz",i1.getDbUnit());
     }
 
}
