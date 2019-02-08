import javassist.NotFoundException;
import main.java.InstructionModifier;
import main.java.constantes.DefaultConst;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class InstructionModifierTest {

    public InstructionModifier IM = new InstructionModifier();
    public DefaultConst DC = new DefaultConst();

    @Before
    public void init(){
        IM = new InstructionModifier();
    }

    @After
    public void after(){
    }

    @Test
    public void countOperationTest() throws NotFoundException {
        assertTrue(IM.countOperationInClass(DC.ABSOLUTE_PATH_TO_PROJECT + DC.defaultPathToModifiedImplemClasses,
                "MathOperation", "add3TimesAndSub1", "sub") ==1);
        assertTrue(IM.countOperationInClass(DC.ABSOLUTE_PATH_TO_PROJECT + DC.defaultPathToModifiedImplemClasses,
                "MathOperation", "add3TimesAndSub1", "add")==3);

        assertTrue(IM.countOperationInClass(DC.ABSOLUTE_PATH_TO_PROJECT + DC.defaultPathToModifiedImplemClasses,
                "MathOperation", "add3TimesAndSub1", "mul")==0);
    }
}
