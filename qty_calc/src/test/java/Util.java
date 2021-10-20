
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
 * File name: Util.java
 **************************/

/**
 * Class for Utilities functions required to run unit tests.
 *
 */

 public class Util {
 ///// HELPERS

    public ArrayList<Ingredient> generateList1(){
        // construct ingredients
        ArrayList<Ingredient> thing1 = new ArrayList<>();

        Ingredient cheese = new Ingredient("cheese", "5534", 8, "lbs");
        Ingredient rice = new Ingredient("rice", "7881", 5, "kgs");
        Ingredient milk = new Ingredient("milk", "2003", 9000, "mL");
        Ingredient butter = new Ingredient("butter", "2001", 20, "oz");
        Ingredient sardines = new Ingredient("blended sardines", "0019", 60, "fl.oz");

        thing1.add(cheese);
        thing1.add(rice);
        thing1.add(milk);
        thing1.add(butter);
        thing1.add(sardines);

        return thing1;
    }

    public Recipe constructRecipe1(){

        ArrayList<Ingredient> thing1 = new ArrayList<>();

        Ingredient cheese = new Ingredient("cheese", "5534", 8, "lbs");
        Ingredient rice = new Ingredient("rice", "7881", 5, "kgs");
        Ingredient milk = new Ingredient("milk", "2003", 9000, "mL");
        Ingredient butter = new Ingredient("butter", "2001", 20, "oz");
        Ingredient sardines = new Ingredient("blended sardines", "0019", 60, "fl.oz");

        thing1.add(cheese);
        thing1.add(rice);
        thing1.add(milk);
        thing1.add(butter);
        thing1.add(sardines);

        return new Recipe("recipe1","1111",thing1);
    }

    public Recipe constructRecipe2() {

        ArrayList<Ingredient> thing1 = new ArrayList<>();

        Ingredient t1 = new Ingredient("thing1", "1234", 1, "lbs");
        Ingredient t2 = new Ingredient("thing2", "5678", 2, "kgs");
        Ingredient t3 = new Ingredient("thing3", "1098", 3, "mL");

        thing1.add(t1);
        thing1.add(t2);
        thing1.add(t3);

        return new Recipe("recipe2", "2222", thing1);
    }

    public Order constructOrder1(){
        return new Order(constructRecipe1(),10,10);
    }

    public Order constructOrder2(){

        return new Order(constructRecipe2(),5,0);
    }

    public MockDB constructMDB(){

        Item i1 = new Item("cheese","5534",100.0,"lbs");
        Item i2 = new Item("rice","7881",100.0,"kgs");
        Item i3 = new Item("milk","2003",100.0,"liters");
        Item i4 = new Item("butter","2001",100.0,"lbs");
        Item i5 = new Item("blended sardines","0019",100.0,"gallons");
        Item i6 = new Item("thing1","9891",1000.0,"fl.oz");
        Item i7 = new Item("thing2","7777",100.0,"kgs");
        Item i8 = new Item("thing3","1234",100.0,"liters");
        Item i9 = new Item("thing4","9999",100.0,"gallons");

        MockDB testDB = new MockDB();

        testDB.addItem(i1);
        testDB.addItem(i2);
        testDB.addItem(i3);
        testDB.addItem(i4);
        testDB.addItem(i5);
        testDB.addItem(i6);
        testDB.addItem(i7);
        testDB.addItem(i8);
        testDB.addItem(i9);

        return testDB;
    }   
}
