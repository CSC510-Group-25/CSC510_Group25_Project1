//package com.qtycalc;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


/**
 * THIS IS A MOCK DB. Simulates a very rudimentary database
 *
 * It allows the user to:
 *
 *      Set up a mock database
 *      Add items to the database
 *      remove items from the database
 */

//TODO:
/*    add multiple (method)
      ENABLE ITEM CONSTRUCTION VIA I/O
      REFACTOR !!! OPTIMIZE !!! CLEAN UP !!!
      Generate unique dbIDs for every object
      MAKE COMPATIBLE WITH ITEM BATCHES (Maybe? This is JUST for the quantity calculator?)
 */

public class MockDB {

    HashMap<String, Item> db;// = new HashMap<>();
    ArrayList<String> keys;

    /**
     * Constructor
     * @param db HashMap<String, Item>
     */
    public MockDB(HashMap<String, Item> db) {
        this.db = db;
        //GET KEYS
        this.keys = new ArrayList<>(db.keySet());
    }

    /**
     * Constructor
     *
     */
    public MockDB(){
        this.db = new HashMap<>();
        this.keys = new ArrayList<>();
    }

    /**
     * Construct DB from a .json or .txt
     *
     * @param filePath -- String, must end with .txt or .json
     * @throws IOException
     * @throws JsonException
     */
    public MockDB(String filePath) throws IOException, JsonException {

        this.db = new HashMap<>();
        this.keys = new ArrayList<>();

        if(filePath.endsWith(".txt")) {
            dbFromFile(filePath);

        } else if(filePath.endsWith(".json")){
            dbFromJsonFile(filePath);

        } else {
            System.out.println("Unsupported file type. Supported: .txt, .json");
        }
    }

    ///////////////////////////////////////////////////////////


    private static String readInput(Scanner sc, String arg) {
        System.out.print(arg);
        return(sc.nextLine());
    }

    /**
     * Remove item from database
     *
     * @param item -- Item
     * @return boolean
     */
    public boolean removeItem(Item item) {

        boolean removed = false;

        if (item == null) {
            return false;
        }

        String key = item.getDbID();

        if (db.containsKey(key)) {

            System.out.println("Entry: " + db.get(key).toString());
            Scanner sc = new Scanner(System.in);
            boolean yesno = false;

            while (!yesno) {
                String thing = readInput(sc, "Remove this entry? y/n: ");
                if (thing.equals("y")) {

                    db.remove(key);
                    keys.remove(key);

                    removed = true;
                    yesno = true;
                } else if (thing.equals("n")) {
                    yesno = true;
                } else {
                    System.out.println("Please enter 'y' or 'n'.");
                }
            }

            sc.close();

        } else {
            System.out.println("Entry not in database.");
        }
        return removed;
    }


    /**
     * Add item to database
     *
     * @param item Item
     * @return boolean
     */
    public boolean addItem(Item item){

        if(item==null){
            return false;
        }

        boolean added = false;

        String key = item.getDbID();

        if(db.containsKey(key)){
            System.out.println("This item ID already exists in the database. Do you want to replace it?");
            System.out.println();
            System.out.println("Replace: " + db.get(key).toString());
            System.out.println("With: " +item.toString());

            Scanner sc = new Scanner(System.in);

            boolean yesno = false;

            while(!yesno) {
                String thing = readInput(sc, "Replace? y/n: ");
                if(thing.equals("y")){

                    db.put(key,item);

                    if(!keys.contains(key)) {
                        this.keys.add(key);
                    }

                    added=true;
                    yesno=true;
                } else if(thing.equals("n")){
                    yesno=true;
                }
                else {
                    System.out.println("Please enter 'y' or 'n'.");
                }
            }
            sc.close();

        } else {
            // add if not in db
            db.put(key,item);
            if(!keys.contains(key)) {
                this.keys.add(key);
            }
            added=true;
        }
        return added;
    }


    /**
     * to bypass CLI
     * @param item
     */
    public void quickAdd(Item item){
        if(item==null){
            System.out.println("quickAdd null");
            return;
        }
        String key = item.getDbID();
        db.put(key,item);
        if(!keys.contains(key)) {
            this.keys.add(key);
        }
    }

    /**
     * to bypass CLI
     * @param item
     */
    public void quickRemove(Item item){
        if(item==null){
            System.out.println("quickRemove null");
            return;
        }
        String key = item.getDbID();
        db.remove(key);
        keys.remove(key);
    }

    // I don't care, I just want to do things.
/*    public void addr(Item item){
        String key = item.getDbID();
        db.put(key,item);
        if(!keys.contains(key)) {
            this.keys.add(key);
        }
    }
    public void removr(Item item){
        String key = item.getDbID();
        db.remove(key);
        keys.remove(key);
    }*/


    //TODO: are you sure? y/n CLI
    public void removeItemByName(String name){ }


    //TODO
    public void addMany(){ }

    //public void readJson() {}

    /**
     *
     * @param filePath
     * @throws IOException
     */
    private void dbFromFile(String filePath) throws IOException {

        String[] dbA = readFile(filePath);

        if (dbA != null) {
            for(int i =0; i<dbA.length; i++){
                Item item = new Item(dbA[i]);
                // ADD NULL CHECK
                String nuKey = item.getItemName();
                if(!keys.contains(nuKey)) {
                    this.keys.add(nuKey);
                }
                this.db.put(nuKey,item);
            }
        }
    }


    /**
     * Constructs a JsonArray for the database.
     *
     * @return JsonArray
     */
    public JsonArray dbAsJson(){

        //TODO: NULL CHECK

        JsonArray jar = new JsonArray();

        for(String key : this.keys){
            Item item = this.db.get(key);
            JsonObject jo = item.itemAsJson();
            jar.add(jo);
        }
        return jar;
    }


    /**
     * Saves the database to the destination folder using the given filename.
     *
     * @param destinationFolder
     * @param fileName
     */
    public void saveAsJson(String destinationFolder, String fileName) throws IOException {

        //TODO: ENSURE SOMETHING LIKE "filename.txt.json" DOESN'T HAPPEN.

        if(!fileName.endsWith(".json")){
            System.out.println("file name must end with .json");
            return;
        }
        JsonArray jar = dbAsJson();
        BuildFile.SaveJsonArray(jar,destinationFolder,fileName);
    }


    /**
     *
     * @param filePath String
     * @throws FileNotFoundException
     * @throws JsonException
     */
    private void dbFromJsonFile(String filePath) throws FileNotFoundException, JsonException {

        Path path = Paths.get(filePath).toAbsolutePath(); // TODO: bug risk?

        FileReader fileReader = new FileReader(String.valueOf((path)));
        JsonArray dbj = (JsonArray) Jsoner.deserialize(fileReader);

        if (dbj != null) {

            for (int i = 0; i < dbj.size(); i++) {
                JsonObject thing = (JsonObject) dbj.get(i);

                if (thing != null) {
                    Item item = new Item(thing);
                    String key = item.getItemName();
                    if(!keys.contains(key)) {
                        this.keys.add(key);
                    }
                    this.db.put(key,item);

                } else{
                    System.out.println("null json object");
                }
            }
        } else{
            System.out.println("null json array");
        }
    }



    /**
     * Reads contents of a .txt file to construct a database.
     * @return String[] contents of a file
     */
    private String[] readFile(String filePath) throws IOException {

        List<String> db = new ArrayList<String>();
        String[] dbArr;

        // TODO: ensure no ingredient repeats

        Path path = Paths.get(filePath).toAbsolutePath();
        Scanner sc = new Scanner(new File(String.valueOf(path)));
        while (sc.hasNextLine()) {
            db.add(sc.nextLine());
        }
        dbArr = db.toArray(new String[0]);
        sc.close();
        return dbArr;
    }


    @Override
    public String toString(){

        StringBuilder str = new StringBuilder();
        boolean first = true;
        for(int i=0; i<this.keys.size(); i++){
            String key = this.keys.get(i);

            if(first){
                str.append(this.db.get(key).toString());
                first=false;
            }
            else {
                String nu = "\n" + this.db.get(key).toString();
                str.append(nu);
            }
        }
        return str.toString();
    }


    public HashMap<String, Item> getDb() { return db; }
    public ArrayList<String> getKeys() { return keys; }

    /**
     * If the DB contains the key, returns the database unit.
     * If it doesn't have the key, returns empty string.
     * @param key
     * @return String
     */
    public String getDBU(String key){
        if(this.db.get(key)==null){
            return "";
        }
        return this.db.get(key).getDbUnit();
    }


    public Item getItem(String key){ return this.db.get(key); }
    public boolean hasItem(String key){ return this.db.containsKey(key); }


    /**
     * Lazy 'override' of .equals()
     * @param o Object
     * @return boolean
     */
    public boolean isEqual(Object o){
        if(o==null){ return false; }
        if (!(o instanceof MockDB)) { return false; }
        MockDB nu = (MockDB) o;
        // the lazy way
        return (this.toString().equals(nu.toString()));
    }


    /////////////////////////////////////////
    // MANUAL TESTING DOWN HERE //


    public static void main(String[] args) throws Exception {

        MockDB mdb = new MockDB("mock_dbs/db1.txt");
        //mdb.saveAsJson("mock_dbs","db1.json");
    }
}
