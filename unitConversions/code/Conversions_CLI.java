import java.util.Scanner;

public class Conversions_CLI {

    // reads in the command line input. Used for manual testing.
    private static String readInput(Scanner sc, String arg) {
        System.out.print("Enter "+arg+": ");
        return(sc.nextLine());
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double qty = 0.0;
        //double noVal = -0.0;
        String local_unit = "";
        String db_unit = "";

        boolean goodVal = false;
        boolean goodLU = false;
        boolean goodDU = false;

        String[] goodLUs =
                {"lbs","kgs","grams","oz",
                        "fl.oz","liters","gallons","mL",
                        "cups dry","cups liquid", "tbsps dry",
                        "tbsps liquid", "tsps dry", "tsps liquid"};

        String[] goodDUs =
                {"lbs","kgs","grams","oz",
                "fl.oz","liters","gallons","mL"};

        while(!goodVal) {

            String thing = readInput(sc, "qty");

            if (thing.startsWith("-")) {
                System.out.println("Please enter a positive value.");
            } else {
                try {
                    qty = Double.parseDouble(thing);
                    if (qty > 0) {
                        goodVal = true;
                    }
                } catch (Exception NumberFormatException) {
                    System.out.println("Please enter a valid number.");
                }
            }
        }

        while(!goodLU) {

            local_unit = readInput(sc, "local unit");

            boolean found = false;

            for (String unit : goodLUs) {
                if (local_unit.equals(unit)) {
                    found = true;
                    goodLU = true;
                    break;
                }

            }
            if (!found) {
                System.out.println("Please enter a valid unit. Valid units are: lbs, kgs, grams, oz, \n" +
                        "                fl.oz, liters, gallons, mL, \n" +
                        "                cups dry, cups liquid, \n" +
                        "                tbsps dry, tbsps liquid, \n" +
                        "                tsps dry, tsps liquid");
            }
        }

        while(!goodDU) {

            boolean found = false;

            db_unit = readInput(sc, "database unit");

            for (String unit : goodDUs) {
                if (db_unit.equals(unit)) {
                    goodDU = true;
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Please enter a valid unit. Valid units are: lbs, kgs, grams, oz, \n" +
                        "                fl.oz, liters, gallons, mL");
            }
        }

        unit_conversion nuConversion = new unit_conversion();

       /* String printMe = "converted " + Double.toString(qty) + " " + local_unit + " to: "
                + nuConversion.convertTo(qty,local_unit,db_unit) + " " + db_unit;*/

        //System.out.println(printMe);

        System.out.println(nuConversion.convertTo(qty,local_unit,db_unit));
    }

}
