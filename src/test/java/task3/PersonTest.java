package task3;


import itma.task3.Head;
import itma.task3.Person;
import itma.task3.states.Emotions;
import itma.task3.states.EyesState;
import itma.task3.states.MouthState;
import itma.task3.states.TeethState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PersonTest {

    private Person person;

    @BeforeEach
    public void setUp() {
         person = new Person(2);
    }

    @Test
    void testName() {
        assertEquals(null, person.getName());
        person.setName("Tolik");
        assertEquals("Tolik", person.getName());
    }




    @Test
    public void testSmile() {
        person.smile(0);
        assertEquals(person.getHeads()[0].getTeethState(), TeethState.SHOW);
        assertEquals(person.getHeads()[0].getMouthState(), MouthState.STRETCHING_OUT_IN_WIDTH);

        person.smile(1);
        assertEquals(person.getHeads()[1].getTeethState(), TeethState.SHOW);
        assertEquals(person.getHeads()[1].getMouthState(), MouthState.STRETCHING_OUT_IN_WIDTH);
        assertEquals(person.getHeads()[0].getTeethState(), TeethState.SHOW);
        assertEquals(person.getHeads()[0].getMouthState(), MouthState.STRETCHING_OUT_IN_WIDTH);
    }

    @Test
    public void testSurprise() {
        person.surprise(0);
        assertEquals(person.getHeads()[0].getMouthState(), MouthState.OPEN);
        assertEquals(person.getHeads()[0].getEyesState(), EyesState.WIDE_OPEN);

        person.surprise(1);
        assertEquals(person.getHeads()[1].getMouthState(), MouthState.OPEN);
        assertEquals(person.getHeads()[1].getEyesState(), EyesState.WIDE_OPEN);
    }

    @Test
    public void testPanicAhaha() {
        person.panicAhaha();
        for (Head head : person.getHeads()) {
            assertEquals(head.getEmotionalState(), Emotions.FEAR);
            assertEquals(head.getTeethState(), TeethState.GRIN);
            assertEquals(head.getMouthState(), MouthState.STRETCHING_OUT_IN_WIDTH);
            assertEquals(head.getEyesState(), EyesState.WIDE_OPEN);
        }
    }

    @Test
    public void testSetDefaultState() {
        person.setDefaultState();
        for (Head head : person.getHeads()) {
            assertEquals(head.getEmotionalState(), Emotions.DEFAULT);
            assertEquals(head.getMouthState(), MouthState.CLOSE);
            assertEquals(head.getEyesState(), EyesState.OPEN);
            assertEquals(head.getTeethState(), TeethState.HIDE);
        }
    }

    @Test
    public void testIsPanicAhaha() {
        assertFalse(person.isPanicAhaha());
        person.panicAhaha();
        assertTrue(person.isPanicAhaha());
    }

    @Test
    public void testRequiredArgsConstructor() {
        Person person = new Person();

        assertNotNull(person.getHeads());
        assertEquals(1, person.getHeads().length);

        for (Head head : person.getHeads()) {
            assertNotNull(head);
        }
    }

    @Test
    public void testSmileWhenInPanicAhaha() {
        person.panicAhaha();
        assertTrue(person.getHeads()[0].isFearing());
        person.smile(0);
        assertTrue(person.getHeads()[0].isFearing());
    }

    @Test
    public void testSurpriseWhenInPanicAhaha() {
        person.panicAhaha();
        assertTrue(person.getHeads()[0].isFearing());
        person.surprise(0);
        assertTrue(person.getHeads()[0].isFearing());
    }


    @Test
    public void testSetHeads() {
        Head[] newHeads = new Head[2];
        newHeads[0] = new Head();
        newHeads[1] = new Head();
        person.setHeads(newHeads);

        assertEquals(2, person.getHeads().length);
        assertNotNull(person.getHeads()[0]);
        assertNotNull(person.getHeads()[1]);
    }


    @Test
    public void testIllegalAmountOfHeads(){
        assertThrows(IllegalArgumentException.class, () -> new Person(-1));
    }
}
