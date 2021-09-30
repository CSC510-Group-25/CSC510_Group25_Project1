//package com.qtycalc;

/**
 * Order class; used by OrderTracker
 */
public class Order {
    /**
     * the recipe / ordered dish
     */
    Recipe recipe;
    /**
     * number of successful orders
     */
    int num_orders; //
    /**
     * number of failed orders
     */
    int num_failures;

    /**Constructor
     * @param recipe Recipe
     * @param num_orders int
     * @param num_failures int
     */
    public Order(Recipe recipe, int num_orders, int num_failures) {
        this.recipe = recipe;
        this.num_orders = num_orders;
        this.num_failures = num_failures;
    }

    /**
     * @return Recipe
     */
    public Recipe getRecipe() {
        return recipe;
    }

    /**
     * @param recipe Recipe
     */
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    /**
     * @return int
     */
    public int getNum_orders() {
        return num_orders;
    }

    /**
     * @param num_orders int
     */
    public void setNum_orders(int num_orders) {
        this.num_orders = num_orders;
    }

    /**
     * @return int
     */
    public int getNum_failures() {
        return num_failures;
    }

    /**
     * @param num_failures int
     */
    public void setNum_failures(int num_failures) { this.num_failures = num_failures; }

    /**
     * A lazy 'override' of .equals()
     *
     * @param o Object
     * @return boolean
     */
    public boolean isEqual(Object o){
        if(o==null){ return false; }
        if (!(o instanceof Order)) { return false; }
        Order nu = (Order) o;
        // the lazy way

        if(this.recipe.getRecipeName().equals(nu.getRecipe().getRecipeName())
                && this.num_orders==nu.getNum_orders()
                && this.num_failures==nu.getNum_failures()){
            return true;
        }
        else {
            return false;
        }

       // return (this.toString().equals(nu.toString()));
    }

    @Override
    public String toString(){
        String returnMe = this.recipe.getRecipeName() + ": ID " + this.recipe.getRecipeID() + "\n"
                + "Number of successful orders: " +this.num_orders +"\n"
                + "Number of failed orders: " + this.num_failures;
        return returnMe;
    }
}