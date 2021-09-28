import com.github.cliftonlabs.json_simple.JsonObject;
//import com.qtycalc.Ingredient;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IngredientTests {

    @Test
    void ingredientAsJsontest() {

        Ingredient ing = new Ingredient("cheese", "5534", 8, "lbs");
        JsonObject jo = ing.ingredientAsJson();
        String name = (String) jo.get("ingredientName");
        String id = (String) jo.get("ing_DBID");
        double qty = Double.parseDouble(jo.get("local_qty").toString());
        String unit = (String) jo.get("local_unit");

        assertEquals("cheese",name);
        assertEquals("5534",id);
        assertEquals(8.0,qty);
        assertEquals("lbs",unit);
    }

    @Test
    void Ingredient_getters() {
        Ingredient ing = new Ingredient("cheese", "5534", 8, "lbs");
        assertEquals("cheese",ing.getIngredientName());
        assertEquals("5534",ing.getDbID());
        assertEquals(8.0,ing.getLocal_qty());
        assertEquals("lbs",ing.getLocal_unit());
        assertNotEquals(null,ing.getIngredientJson());
    }

    @Test
    void Ingredient_isEqual() {
        Ingredient ing1 = new Ingredient("cheese", "5534", 8, "lbs");
        Ingredient ing2 = new Ingredient("cheese", "5534", 8, "lbs");
        Ingredient ing3 = new Ingredient("rice", "7881", 5, "kgs");
        assertTrue(ing1.isEqual(ing2));
        assertFalse(ing1.isEqual(ing3));
    }
}