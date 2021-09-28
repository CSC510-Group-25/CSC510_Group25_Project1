import com.github.cliftonlabs.json_simple.JsonObject;
//import com.qtycalc.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTests {

    @Test
    void itemAsJsonTest() {
        Item i1 = new Item("thing1","9891",1000.0,"fl.oz");

        JsonObject jo = i1.itemAsJson();

        String name = (String) jo.get("name");
        String dbid = (String) jo.get("ID");
        Double qty = Double.parseDouble(jo.get("quantity").toString());
        String dbu = (String) jo.get("unit");

        assertEquals("thing1",name);
        assertEquals("9891",dbid);
        assertEquals(1000.0,qty);
        assertEquals("fl.oz",dbu);
    }

    @Test
    void Item_toString() {
        Item i1 = new Item("thing1","9891",1000.0,"fl.oz");
        Item i2 = new Item("thing1","9891",1000.0,"fl.oz");
        Item i3 = new Item("thing3","1234",100.0,"liters");
        assertTrue(i1.isEqual(i2));
        assertFalse(i1.isEqual(i3));
    }

    @Test
    void Item_getters() {
        Item i1 = new Item("thing1","9891",1000.0,"fl.oz");
        assertEquals("thing1",i1.getItemName());
        assertEquals("9891",i1.getDbID());
        assertEquals(1000.0,i1.getQty());
        assertEquals("fl.oz",i1.getDbUnit());
    }
}