package Bicing.test.structure;

import IA.Bicing.BicingState;
import junit.framework.TestCase;

/**
 *
 * @author Tomas Barton 
 */
public class StateTest extends TestCase {

    private BicingState state;
    private int numVan = 2;
    private static int vanCapacity = 30;

    public StateTest(String name) {
        super(name);
    }

    @Override
    public void setUp() {
        int stations = 4;
        int[] current = new int[stations];
        current[0] = 10;
        current[1] = 0;
        current[2] = 0;
        current[3] = 0;
        int[] next = new int[stations];
        next[0] = 10;
        next[1] = 0;
        next[2] = 0;
        next[3] = 0;
        int[] demanded = new int[stations];
        demanded[0] = 0;
        demanded[1] = 0;
        demanded[2] = 2;
        demanded[3] = 8;
        int[][] coordinates = new int[stations][2];
        coordinates[0][0] = 0;
        coordinates[0][1] = 0;
        coordinates[1][0] = 0;
        coordinates[1][1] = 1;
        coordinates[2][0] = 1;
        coordinates[2][1] = 0;
        coordinates[3][0] = 1;
        coordinates[3][1] = 1;
        state = new BicingState(current, next, demanded, coordinates, numVan);
    }

    public void testAddMove(){
        state.moveBicicle(0, 1, 5, 0);
        assertEquals(5, state.getBikesNext(1));
        assertEquals(1.0, state.getTotalDistance());
        assertEquals(1, state.getActionCount());
        assertEquals(1, state.getMoveCount());
    }

    public void testCloning(){
        state.moveBicicle(0, 1, 5, 0);
        BicingState e = state.clone();
        assertEquals(state.getTotalDistance(), e.getTotalDistance());
        assertEquals(state.getStationsNum(), e.getStationsNum());
        for(int i=0; i< state.getStationsNum(); i++){
            assertEquals(state.getBikesDemanded(i), e.getBikesDemanded(i));
            assertEquals(state.getBikesNext(i), e.getBikesNext(i));
            assertEquals(state.getBikesNotMove(i), e.getBikesNotMove(i));
        }
        assertEquals(state.getActionCount(), e.getActionCount());
        assertEquals(state.getMoveCount(), e.getMoveCount());   
    }

    public void testRemoveMove(){
        state.moveBicicle(0, 1, 5, 0);
        state.removeMove(0);
        assertEquals(10, state.getBikesNext(0));
        assertEquals(10, state.getBikesNotMove(0));
        assertEquals(0.0, state.getTotalDistance());
        assertEquals(0, state.getActionCount());
        assertEquals(0, state.getMoveCount());   
    }

    public void testChangeMove(){
        state.moveBicicle(0, 1, 5, 0);
        state.changeMove(0, 2, 5);
        assertEquals(5, state.getBikesNext(2));
        assertEquals(0, state.getBikesNext(1));
        assertEquals(5, state.getBikesNotMove(0));
        assertEquals(1.0, state.getTotalDistance());
        assertEquals(1, state.getActionCount());
        assertEquals(1, state.getMoveCount());

    }
}