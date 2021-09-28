//package com.qtycalc;

public class Order {
    Recipe recipe; // the recipe / ordered dish
    int num_orders; // number of successful orders
    int num_failures; // number of failed orders

    public Order(Recipe recipe, int num_orders, int num_failures) {
        this.recipe = recipe;
        this.num_orders = num_orders;
        this.num_failures = num_failures;
    }
    public Recipe getRecipe() {
        return recipe;
    }
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
    public int getNum_orders() {
        return num_orders;
    }
    public void setNum_orders(int num_orders) {
        this.num_orders = num_orders;
    }
    public int getNum_failures() {
        return num_failures;
    }
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