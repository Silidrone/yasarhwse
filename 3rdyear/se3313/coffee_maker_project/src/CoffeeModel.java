import java.util.ArrayList;

public class CoffeeModel {
    protected ArrayList<ViewObserver> viewObservers;
    protected int totalCount;

    CoffeeModel() {
        viewObservers = new ArrayList<>();
        totalCount = Repo.getSumOfCount();
    }

    void finishBrew(int count) {
        Repo.addBrewRecord(count);
        totalCount += count;
        notifyObservers();
    }
    void registerViewObserver(ViewObserver viewObserver) {
        viewObservers.add(viewObserver);
    }

    void notifyObservers() {
        for(ViewObserver viewObserver : viewObservers) {
            viewObserver.update(totalCount);
        }
    }

    public void updateTotalCount() {
        totalCount = Repo.getSumOfCount();
        notifyObservers();
    }
}
