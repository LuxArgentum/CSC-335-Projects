import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class StudentTest {

    @Test
    public void testInState() {

        AbstractStudent inState = new InState("Kim", 15);

        assertEquals(6000.00, inState.getTuitionDue(), 0.01);
    }

    @Test
    public void testOutOfState() {

        AbstractStudent outOfState = new OutOfState("Chris", 15);
        assertEquals(12150.00, outOfState.getTuitionDue(), 0.01);
    }

}