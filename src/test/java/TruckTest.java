import org.junit.Test;
import ru.d3m4k.entity.Box;
import ru.d3m4k.entity.Truck;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TruckTest {

    @Test
    public void testTruckCreation() {
        Truck truck = new Truck();
        assertNotNull(truck);
    }

    @Test
    public void testToString() {
        Truck truck = new Truck();
        ArrayList<ArrayList<Integer>> space = new ArrayList<>();
        space.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
        space.add(new ArrayList<>(Arrays.asList(4, 5, 6)));
        Box box = new Box(space);
        truck.addBox(box, 0, 0);
        String expected = "+1 2 3+\n+4 5 6+\n++++++";
        assertEquals(expected, truck.toString());
    }
}
