import org.junit.Test;
import ru.d3m4k.entity.Box;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class BoxTest {

    @Test
    public void testBoxCreation() {
        ArrayList<ArrayList<Integer>> space = new ArrayList<>();
        space.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
        space.add(new ArrayList<>(Arrays.asList(4, 5, 6)));
        Box box = new Box(space);
        assertEquals(2, box.getLength());
        assertEquals(3, box.getWidth());
        assertEquals(6, box.getVolume());
    }

    @Test
    public void testGetSpace() {
        ArrayList<ArrayList<Integer>> space = new ArrayList<>();
        space.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
        space.add(new ArrayList<>(Arrays.asList(4, 5, 6)));
        Box box = new Box(space);
        assertEquals(space, box.getSpace());
    }
}