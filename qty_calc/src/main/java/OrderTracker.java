import com.github.cliftonlabs.json_simple.JsonException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * OrderTracker class.<br>
 *
 * To run this, call createOrders() on an OrderTracker object or just use the main method.
 *
 * TODO: incomplete class, but still functional
 */
public class OrderTracker {


    /**
     * A map for Order objects.<br>
     * key: RecipeID<br>
     * val: Order
     */
    HashMap<String, Order> orders;
    /**
     * list of recipe IDs to make management easier.
     */
    ArrayList<String> keys;

    /**
     * Constructor, mainly for testing
     * @param orders HashMap
     */
    public OrderTracker(HashMap<String,Order> orders) {
        this.orders = orders;
        //GET KEYS
        this.keys = new ArrayList<>(orders.keySet());
    }

    /**
     * Constructor
     */
    public OrderTracker() {
        this.orders = new HashMap<>();
        this.keys = new ArrayList<>();
    }

    /**
     * @return this.orders -- HashMap
     */
    public HashMap<String, Order> getOrders() { return orders; }

    /**
     * @return this.keys -- ArrayList
     */
    public ArrayList<String> getKeys() { return keys; }


    /**
     * Returns an arraylist of orders.
     *
     * @return ArrayList
     */
    public ArrayList<Order> getOrdersArray() {

        ArrayList<Order> oAL = new ArrayList<>();
        for (String key : this.keys){
            oAL.add(this.orders.get(key));
        }
        return oAL;
    }


    /**
     * Method to remove a specific order by the given recipeID.<br>
     * NOT YET IMPLEMENTED.
     * @param recipeID String
     * @return boolean
     */
    public boolean removeOrderByID(String recipeID){
        //this.orders.remove(recipeID);
        return false;
    }


    /**
     * Method to create and add an order to a list of orders.<br><br>
     *
     * To return true: recipe is not null, success and fails not less than 0<br><br>
     *
     * Can be used to bypass the createOrders() method, which takes command line input. Also does not rely on
     * the existence of a recipe_folder directory.
     *
     * @param recipe -- Recipe
     * @param success -- int, number of successful orders
     * @param fails -- int, number of failed orders
     * @return boolean -- true if the order is added, false if not
     */
    public boolean createOrder(Recipe recipe, int success, int fails){

        if((success < 0) || (fails < 0)){
            System.out.println("cannot input negative values");
            return false;
        }

        if(success==0 && fails==0){
            System.out.println("number of successful orders and number of failed orders cannot both be zero");
            return false;
        }

        if(recipe == null){
            System.out.println("null recipe in createOrder");
            return false;
        }

        String key = recipe.getRecipeID();

        //TODO: alert to overwrites?

        Order o = new Order(recipe,success,fails);
        this.orders.put(key,o);

        if(!keys.contains(key)) {
            this.keys.add(key);
        }
        return true;
    }


    /**
     * UI for number of successful orders
     *
     * @param sc Scanner
     * @return int
     */
    private int getSuccess(Scanner sc){

        boolean goodInput = false;
        int input = -1;

        while(!goodInput) {
            try {
                System.out.print("Enter the number of successful orders: ");

                String str = sc.nextLine();
                input = Integer.parseInt(str);

                if (input >= 0) {
                    goodInput = true;
                } else {
                    System.out.println("Please enter a valid whole number greater than or equal to zero. \n");
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid non-negative whole number. \n");
            }
        }
            return input;
    }


    /**
     * UI for number of failed orders
     *
     * @param sc Scanner
     * @return int
     */
    private int getFails(Scanner sc){

        boolean goodInput = false;
        int input = -1;

        while(!goodInput) {
            try {
                System.out.print("Enter the number of failed orders: ");

                String str = sc.nextLine();
                input = Integer.parseInt(str);

                if (input >= 0) {
                    goodInput = true;
                } else {
                    System.out.println("Please enter a valid whole number greater than or equal to zero. \n");
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid whole number greater than or equal to zero. \n");
            }
        }
        return input;
    }


    //TODO: allow user to select which folder to search for recipes?
    /**
     * Helper for createOrders()<br>
     * UI for Recipe
     *
     * @param sc Scanner
     * @return Recipe
     */
    private Recipe getRecipe_Order(Scanner sc){

        boolean goodInput = false;
        Recipe r = null;
        String recipeFolder = "recipe_folder";

        while(!goodInput) {

            try {
                System.out.print("Enter recipe ID: ");
                String input = sc.nextLine();
                r =  BuildFile.RecipeFromID(recipeFolder,input);

                if (r != null){
                    goodInput = true;
                } else {

                    System.out.println("Could not locate the file associated with the recipe ID: "+input +". \n");
                    String tryAgain = YesNo(sc, "\nTry again? y/n: ");

                    System.out.println("");
                    if (tryAgain.equals("y")) {
                        //continue

                    } else if (tryAgain.equals("n")) {
                        break;
                    } else {
                        System.out.println("Oops, something went wrong with getRecipe_Order!");
                    }
                }
            } catch (Exception e) {
                //System.out.println("");
            }
        }
        return r;
    }


    /*
    TODO:
    ADD AN OPTION THAT WILL ALLOW THE USER TO GET A RECIPE'S ID BY USING THE RECIPE'S NAME.
     */

    /**
     * This is the method you call in main or QuantityCalculator to construct orders.
     */
    public void createOrders(){

        boolean done = false;

        System.out.println("Please follow the instructions as they appear to create an order.");
        Scanner sc = new Scanner(System.in);

        while(!done){

            System.out.println("Create an order");
            Recipe r = getRecipe_Order(sc);
            int success = -1;
            int fails = -1;

            if(r==null){

                System.out.println("Recipe not found.");
                String ruDoneYet = YesNo(sc, "\nContinue? y/n: ");

                if (ruDoneYet.equals("y")) {
                    // continue
                } else if (ruDoneYet.equals("n")) {
                    done = true;

                } else {
                    System.out.println("Oops, something went wrong with createOrders in (OrderTracker.java)!");
                }
            }

            if(r!=null){

                success = getSuccess(sc);
                fails = getFails(sc);

                System.out.println(""); // for readability

                String readBack = r.getRecipeName() + ": ID " + r.getRecipeID() + "\n"
                        + "Number of successful orders: " +success +"\n"
                        + "Number of failed orders: " + fails + "\n";

                System.out.println(readBack);

                String yn = YesNo(sc, "Is this correct? y/n: ");

                if (yn.equals("y")) {
                    boolean yay = createOrder(r, success, fails);
                    if(yay){
                        System.out.println("\nSuccessfully added order.");
                    }
                    else{ }

                } else if (yn.equals("n")) {
                    System.out.println("Order discarded.");
                } else {
                    System.out.println("Oops, something went wrong with createOrders in (OrderTracker.java)!");
                }

                String ruDoneYet = YesNo(sc, "Continue? y/n: ");
                if (ruDoneYet.equals("y")) {
                    // continue
                } else if (ruDoneYet.equals("n")) {
                    done = true;
                } else {
                    System.out.println("Oops, something went wrong with createOrders in (OrderTracker.java)!");
                }
            }
        }
        sc.close();
    }

    @Override
    public String toString(){
        boolean first = true;
        if( this.orders != null && !this.orders.isEmpty()){
            StringBuilder str = new StringBuilder();
            for(String key : this.keys){
                Order o = this.orders.get(key);
                if(first){
                    str.append(o.toString());
                    first=false;
                }
                else {
                    String nu = "\n" + o.toString();
                    str.append(nu);
                }
            }
            return str.toString();
        }
        return "";
    }


    /**
     * Waits for "y" or "n" input
     *
     * @param sc Scanner
     * @param arg String
     * @return String
     */
    private static String YesNo(Scanner sc, String arg) {
        // System.out.print(arg);

        boolean goodInput = false;
        String yn = "";

        System.out.print(arg);

        while (!goodInput) {

            yn = sc.nextLine();

            if (yn.equals("y")) {
                goodInput = true;
            } else if (yn.equals("n")) {
                goodInput = true;

            } else {
                System.out.println("Please enter 'y' or 'n'.");
            }
        }
        return yn;
    }

    /////// MANUAL TESTING DOWN HERE ///////

    public static void main(String[] args) throws IOException, JsonException {

        OrderTracker ot = new OrderTracker();
        // UNCOMMENT IF YOU WANT TO RUN MANUAL TESTS.
        // ot.createOrders();

        // comment out below as needed
        String f1 = "recipe_folder" + File.separator + "recipe_8000.json";
        String f2 = "recipe_folder" + File.separator + "recipe_9000.json";

        Recipe r8 = new Recipe(f1);
        Recipe r9 = new Recipe(f2);

        ot.createOrder(r8, 2,0);
        ot.createOrder(r9,2,0);

        //TO PRINT ORDERS:
         System.out.println(ot.toString());
    }
}
