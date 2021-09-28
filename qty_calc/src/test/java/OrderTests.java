//import com.qtycalc.Ingredient;
//import com.qtycalc.Order;
//import com.qtycalc.Recipe;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderTests {

    @Test
    void Order_getters() {
        Order o1 = new Order(constructRecipe1(),10,10);

        assertEquals("recipe1",o1.getRecipe().getRecipeName());
        assertEquals(10,o1.getNum_orders());
        assertEquals(10,o1.getNum_failures());
    }

    @Test
    void Order_toString() {
        String thing = "recipe1: ID 1111\nNumber of successful orders: 10\nNumber of failed orders: 10";
        Order o = new Order(constructRecipe1(),10,10);

        assertEquals(thing,o.toString());
    }

    @Test
    void Order_isEqual(){
        Order o1 = new Order(constructRecipe1(),10,10);
        Order o2 = new Order(constructRecipe1(),10,10);
        Order o3 = new Order(constructRecipe2(),5,5);

        assertTrue(o1.isEqual(o2));
        assertFalse(o1.isEqual(o3));
        assertFalse(o1.isEqual(10));
        assertFalse(o1.isEqual(null));
    }

    private Recipe constructRecipe1(){

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

    private Recipe constructRecipe2(){

        ArrayList<Ingredient> thing1 = new ArrayList<>();

        Ingredient t1 = new Ingredient("thing1", "1234", 1, "lbs");
        Ingredient t2 = new Ingredient("thing2", "5678", 2, "kgs");
        Ingredient t3 = new Ingredient("thing3", "1098", 3, "mL");

        thing1.add(t1);
        thing1.add(t2);
        thing1.add(t3);

        return new Recipe("recipe2","2222",thing1);
    }
}