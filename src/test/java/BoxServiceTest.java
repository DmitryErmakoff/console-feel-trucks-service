import org.junit.Test;
import ru.d3m4k.entity.Box;
import ru.d3m4k.service.BoxService;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class BoxServiceTest {

    @Test
    public void testLoadBoxesFromFile() {
        BoxService service = new BoxService();
        ArrayList<Box> boxes = service.loadBoxesFromFile("test.txt");
        assertNotNull(boxes);
    }

    @Test
    public void testArrangeBoxesByVolume() {
        BoxService service = new BoxService();
        ArrayList<Box> boxes = new ArrayList<>();
        ArrayList<Box> arrangedBoxes = service.arrangeBoxesByVolume(boxes);
        assertNotNull(arrangedBoxes);
    }
}