import org.junit.Test;
import ru.d3m4k.service.FileSelectorService;

import java.util.List;

import java.io.File;

import static org.junit.Assert.assertNotNull;

public class FileSelectorServiceTest {

    @Test
    public void testListTxtFiles() {
        FileSelectorService service = new FileSelectorService();
        List<File> files = service.listTxtFiles("src/main/resources");
        assertNotNull(files);
    }

}
