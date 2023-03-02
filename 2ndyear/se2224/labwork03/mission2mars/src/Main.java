import java.util.ArrayList;

public class Main {
    //One issue is that the items are not consumed between u1 and u2 rockets, but this was never specified in the lab work, so I create both u1 and u2 rockets that can hold the items that are provided in the text file
    public static void main(String[] args) {
        ArrayList<String> missionSources = new ArrayList<>();
        missionSources.add("phase-1.txt");
        missionSources.add("phase-2.txt");

        for (int i = 0; i < missionSources.size(); i++) {
            Simulation mission = new Simulation();
            ArrayList<Item> items = mission.scanItems(missionSources.get(i));
            ArrayList<U1> u1Rockets = mission.loadU1(items);
            ArrayList<U2> u2Rockets = mission.loadU2(items);
            ArrayList<Rocket> rockets = new ArrayList<>();
            rockets.addAll(u1Rockets);
            rockets.addAll(u2Rockets);
            int totalBudget = mission.run(rockets);
            System.out.println("For mission #" + (i + 1) + ", the total budget is: $" + totalBudget);
        }
    }
}