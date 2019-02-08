import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MathSupInfTest {

    MathSupInf supInf;


    @Before
    public void init(){
        supInf = new MathSupInf();
    }

    @After
    public void after(){
    }

    @Test
    public void shouldCompareIfStrictlyInferiorCorrectly(){
        assertTrue("function strictlyInf is incorrect: "  + supInf.strictlyInf(1,1) + " was received when false was expected"
                ,supInf.strictlyInf(1,1)==false);
    }

    @Test
    public void shouldCompareIfStrictlySuperiorCorrectly(){
        assertTrue("function strictlySupp is incorrect: "  + supInf.strictlySupp(1,1) + " was received when false was expected"
                ,supInf.strictlyInf(1,1)==false);
    }

    @Test
    public void shouldCompareIfEqualOrSuperiorCorrectly(){
        assertTrue("function supOrEq is incorrect: "  + supInf.supOrEq(1,1) + " was received when true was expected"
                ,supInf.strictlyInf(1,1)==true);
    }

    @Test
    public void shouldCompareIfEqualOrInferiorCorrectly(){
        assertTrue("function infOrEq is incorrect: "  + supInf.infOrEq(1,1) + " was received when true was expected"
                ,supInf.strictlyInf(1,1)==true);
    }
}

