import java.util.Scanner;

public class Menu {

    public void menuMain() {
        Scanner sc = new Scanner(System.in);
        ReqBD baseJDBC = new ReqBD();
        do {
            StringBuilder toSQL = new StringBuilder();
            System.out.println("List of all apartments");
            baseJDBC.showRezult("");
            System.out.println("Do you want to choose by district? (y / no = any key)");
            if (sc.nextLine().equalsIgnoreCase("y")) {
                baseJDBC.getStringParametr("district", "districts");
                toSQL.append(" AND (");
                toSQL.append(setStringParametr("district", "a."));
                toSQL.append(") ");
            }
            System.out.println("Do you want to choose by address? (y / no = any key)");
            if (sc.nextLine().equalsIgnoreCase("y")) {
                baseJDBC.getStringParametr("streets", "streets");
                toSQL.append(" AND (");
                toSQL.append(setStringParametr("streets", "s."));
                toSQL.append(") ");
            }
            System.out.println("Do you want to choose by space? (y / no = any key)");
            if (sc.nextLine().equalsIgnoreCase("y")) {
                toSQL.append(" AND a.");
                toSQL.append(setFromTo("space"));
            }
            System.out.println("Do you want to choose by rooms? (y / no = any key)");
            if (sc.nextLine().equalsIgnoreCase("y")) {
                toSQL.append(" AND a.");
                toSQL.append(setFromTo("rooms"));
            }
            System.out.println("Do you want to choose by price? (y / no = any key)");
            if (sc.nextLine().equalsIgnoreCase("y")) {
                toSQL.append(" AND a.");
                toSQL.append(setFromTo("price"));
            }
            System.out.println("result");
            baseJDBC.showRezult(toSQL.toString());
            System.out.println("Do you want EXIT? (y / no = any key)");
            if (sc.nextLine().equalsIgnoreCase("y")) {
                break;
            }
        } while (true);
    }

    private String setStringParametr(String nameParam, String table) {
        Scanner sc = new Scanner(System.in);
        String[] mas;
        StringBuilder sb = new StringBuilder();
        do {
            System.out.println("Enter ID one or more use space");
            mas = sc.nextLine().split(" ");
            if (isParsable(mas)) break;
        } while (true);
        for (int i = 0; i < mas.length; i++) {
            sb.append(table);
            sb.append(nameParam);
            sb.append("_id");
            sb.append("=");
            sb.append(mas[i]);
            if (i < mas.length - 1) sb.append(" OR ");
        }
        return sb.toString();
    }

    public boolean isParsable(String[] input) {
        boolean parsable = true;
        try {
            for (int i = 0; i < input.length; i++) {
                Integer.parseInt(input[i]);
            }
        } catch (NumberFormatException e) {
            parsable = false;
        }
        return parsable;
    }

    private String setFromTo(String nameParametr) {
        Scanner sc = new Scanner(System.in);
        int from;
        int to;
        do {
            System.out.println("Enter " + nameParametr + " from");
            if (sc.hasNextInt()) {
                from = sc.nextInt();
            } else {
                sc.next();
                continue;
            }
            System.out.println("Enter " + nameParametr + " to");
            if (sc.hasNextInt()) {
                to = sc.nextInt();
            } else {
                sc.next();
                continue;
            }
            break;
        } while (true);
        return nameParametr + " BETWEEN " + from + " AND " + to;
    }
}
