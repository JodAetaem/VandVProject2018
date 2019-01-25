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
        assertTrue(operation.add(1,2)==3);
    }

    @Test
    public void shouldSubCorrectly(){
        assertTrue(operation.substract(2,1)==1);
    }

}
