import at.aau.DiffInfo;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static at.aau.Main.diffFiles;

public class ComplexTest {
    @Test
    public void simpleUpdateTest() { // Files: testoriginal.txt; testrevised.txt
        List<DiffInfo> myList = diffFiles("complextestfiles/Test.java", "complextestfiles/Test_new.java");

        DiffInfo updateInfo = myList.get(0);
        Assert.assertEquals("UPDATE", updateInfo.getActionType());

        Assert.assertEquals(4, updateInfo.getSrcStartLine());
        Assert.assertEquals(0, updateInfo.getSrcStartLineOffset());
        Assert.assertEquals(8, updateInfo.getSrcEndLine());
        Assert.assertEquals(5, updateInfo.getSrcEndLineOffset());
        Assert.assertEquals(0, updateInfo.getDstID());
        Assert.assertEquals(4, updateInfo.getDstStartLine());
        Assert.assertEquals(0, updateInfo.getDstStartLineOffset());
        Assert.assertEquals(4, updateInfo.getDstEndLine());
        Assert.assertEquals(36, updateInfo.getDstEndLineOffset());
        
    }
}
