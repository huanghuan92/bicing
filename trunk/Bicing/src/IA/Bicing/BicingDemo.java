package IA.Bicing;

import aima.search.framework.GraphSearch;
import aima.search.framework.Problem;
import aima.search.framework.QueueSearch;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import aima.search.uninformed.BreadthFirstSearch;
import aima.search.uninformed.DepthFirstSearch;
import aima.search.uninformed.IterativeDeepeningSearch;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Tomas Barton 
 */
public class BicingDemo {
    private static int numVan = 1;
    /**
     * how many bikes can load 1 van
     */
    private static int vanCapacity = 20;

     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BicingGenerator b = new BicingGenerator(10, 100, BicingGenerator.RUSH_HOUR, 200);
        Object initialState = new BicingState(b.getNumStations(),b.getCurrent(), b.getNext(), b.getDemand());

        try {

            Problem problem=new Problem(initialState,
                    new BicingSuccessorFunction1(numVan, vanCapacity),
                    new BicingGoalTest(),
                    new BicingHeuristicFunction1()
                    );
        /*    Search search = new IterativeDeepeningSearch();
            SearchAgent agent = new SearchAgent(problem,search);
      */
            QueueSearch qs = new GraphSearch();
            Search search = new BreadthFirstSearch(qs);
            SearchAgent agent = new SearchAgent(problem,search);

            System.out.println();
            printActions(agent.getActions());
         //   System.out.println("Search Outcome=" + search.getOutcome());
        //    System.out.println("Final State=\n" + search.getLastSearchState());
            printInstrumentation(agent.getInstrumentation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        private static void printInstrumentation(Properties properties) {
		Iterator keys = properties.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String property = properties.getProperty(key);
			System.out.println(key + " : " + property);
		}

	}

	private static void printActions(List actions) {
		for (int i = 0; i < actions.size(); i++) {
			String action = (String) actions.get(i);
			System.out.println(action);
		}
	}
}
