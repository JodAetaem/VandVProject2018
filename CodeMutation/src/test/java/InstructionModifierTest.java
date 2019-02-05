import javassist.NotFoundException;
import main.java.InstructionModifier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class InstructionModifierTest {

    String ABSOLUTE_PATH_TO_PROJECT = "/home/istic-larzilliere/Documents/VV/Project2019/";

    String defaultPathToTestClasses = "SourceCode/target/test-classes/";
    String defaultPathToImplemClasses = "SourceCode/target/classes/";

    String defaultPathToModifiedTestClasses = "SourceModifiedCode/target/test-classes/";
    String defaultPathToModifiedImplemClasses = "SourceModifiedCode/target/classes/";

    public InstructionModifier IM = new InstructionModifier();

    @Before
    public void init(){
        IM = new InstructionModifier();
    }

    @After
    public void after(){
    }

    @Test
    public void countOperationTest() throws NotFoundException {
        assertTrue(IM.countOperationInClass(ABSOLUTE_PATH_TO_PROJECT + defaultPathToModifiedImplemClasses,
                "MathOperation", "add3TimesAndSub1", "sub") ==1);
        assertTrue(IM.countOperationInClass(ABSOLUTE_PATH_TO_PROJECT + defaultPathToModifiedImplemClasses,
                "MathOperation", "add3TimesAndSub1", "add")==3);

        assertTrue(IM.countOperationInClass(ABSOLUTE_PATH_TO_PROJECT + defaultPathToModifiedImplemClasses,
                "MathOperation", "add3TimesAndSub1", "mul")==0);
    }
}
