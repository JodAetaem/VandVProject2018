import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;


public class MathOperationTest {

    MathOperation operation;


    @Before
    public void init(){
        operation = new MathOperation();
    }

    @After
    public void after(){
    }

    @Test
    public void shouldAddCorrectly(){
        assertTrue("function add is incorrect: "  + operation.add(1,2) + " was received when 3 was expected"
                ,operation.add(1,2)==3);
    }

    @Test
    public void shouldSubCorrectly(){
        assertTrue("function substract is incorrect: "  + operation.substract(2,1) + " was received when 1 was expected",
                operation.substract(2,1)==1);
    }

    @Test
    public void shouldAdd3TimesAndSub1Correctly(){
        assertTrue("function Add 3x -1 is incorrect : " + operation.add3TimesAndSub1(2,1) + " was received when 4 was expected",
                operation.add3TimesAndSub1(2,1)==4);
    }

}
