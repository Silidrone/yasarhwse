import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Simulation {

    // loadU1 and loadU2 can be refactored into a single function but I followed the lab work slide which defines two separate functions
    public ArrayList<U1> loadU1(ArrayList<Item> items) {
        ArrayList<U1> u1Rockets = new ArrayList<>();
        int rocketIndex = 0;
        u1Rockets.add(new U1());
        for (Item item : items) {
            U1 u1Rocket = u1Rockets.get(rocketIndex);
            if (!u1Rockets.get(rocketIndex).canCarry(item)) {
                u1Rockets.add(u1Rocket);
                rocketIndex++;
                u1Rocket = new U1();
            }
            u1Rocket.loadItem(item);
        }
        return u1Rockets;
    }

    public ArrayList<U2> loadU2(ArrayList<Item> items) {
        ArrayList<U2> u2Rockets = new ArrayList<>();
        int rocketIndex = 0;
        u2Rockets.add(new U2());
        for (Item item : items) {
            U2 u2Rocket = u2Rockets.get(rocketIndex);
            if (!u2Rockets.get(rocketIndex).canCarry(item)) {
                u2Rockets.add(u2Rocket);
                rocketIndex++;
                u2Rocket = new U2();
            }
            u2Rocket.loadItem(item);
        }
        return u2Rockets;
    }

    public ArrayList<Item> scanItems(String filename) {
        ArrayList<Item> items = new ArrayList<>();
        try {
            BufferedReader reader;
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                String[] lineParts = line.split("=");
                if (lineParts.length > 1) {
                    items.add(new Item(lineParts[0], Integer.parseInt(lineParts[1])));
                }
                line = reader.readLine();
            }

        } catch(IOException e) {
            e.printStackTrace();
        }

        return items;
    }

    public int run(ArrayList<Rocket> rockets) {
        int totalBudgetNeeded = 0;
        int rocketIndex = 0;
        while (rocketIndex < rockets.size()) {
            Rocket rocket = rockets.get(rocketIndex);
            if (rocket.launch() || rocket.land()) {
                rocketIndex++;
            }

            totalBudgetNeeded += rocket.getRocketCost();
        }

        return totalBudgetNeeded;
    }
}
