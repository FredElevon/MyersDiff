import at.aau.DiffInfo;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static at.aau.Main.diffFiles;

public class SimpleTests {
    @Test
    public void simpleUpdateTest() { // Files: testoriginal.txt; testrevised.txt
        List<DiffInfo> myList = diffFiles("testoriginal.txt", "testrevised.txt");

        Assert.assertEquals("UPDATE", myList.get(0).getActionType());
        Assert.assertEquals(0, myList.get(0).getSrcID());
        Assert.assertEquals(0, myList.get(0).getSrcStartLine());
        Assert.assertEquals(2, myList.get(0).getSrcEndLine());
        Assert.assertEquals(4, myList.get(0).getSrcEndLineOffset());
        Assert.assertEquals(0, myList.get(0).getDstID());
        Assert.assertEquals(0, myList.get(0).getDstStartLine());
        Assert.assertEquals(2, myList.get(0).getDstEndLine());
        Assert.assertEquals(5, myList.get(0).getDstEndLineOffset());
        
    }

    @Test
    public void mySecondTest() {
        List<DiffInfo> myList = diffFiles("originalFile.txt", "revisedFile.txt");
        Assert.assertEquals(2, 1 + 1);
        Assert.assertEquals(2, myList.size());
    }
}
