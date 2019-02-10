import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertFalse;

public class VoidClassTest {

    VoidClass voidClass;

    @Before
    public void init(){
        voidClass = new VoidClass();
    }

    @After
    public void after(){
    }

    @Test
    public void shouldBeFalse(){
        voidClass.testFunction();
        assertFalse("Function testFunction is incorrect, the TestBoolean is " + voidClass.isBooleanTest() + " and should be false",voidClass.isBooleanTest());
    }
}
