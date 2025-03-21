package task3;

import itma.task3.Head;
import itma.task3.states.Emotions;
import itma.task3.states.EyesState;
import itma.task3.states.MouthState;
import itma.task3.states.TeethState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HeadTest {

    private Head head;

    @BeforeEach
    public void setUp() {
        head = new Head();
    }

    @Test
    void testGetName() {
        assertEquals(null, head.getName());
        head.setName("Tolik");
        assertEquals("Tolik", head.getName());
    }

    @Test
    public void testSmile() {
        head.smile();
        assertEquals(TeethState.SHOW, head.getTeethState());
        assertEquals(MouthState.STRETCHING_OUT_IN_WIDTH, head.getMouthState());
    }

    @Test
    public void testSurprise() {
        head.surprise();
        assertEquals(MouthState.OPEN, head.getMouthState());
        assertEquals(EyesState.WIDE_OPEN, head.getEyesState());
    }

    @Test
    public void testFear() {
        head.fear();
        assertEquals(Emotions.FEAR, head.getEmotionalState());
        assertEquals(MouthState.STRETCHING_OUT_IN_WIDTH, head.getMouthState());
        assertEquals(TeethState.GRIN, head.getTeethState());
        assertEquals(EyesState.WIDE_OPEN, head.getEyesState());
    }

    @Test
    public void testIsFearing() {
        head.fear();
        assertTrue(head.isFearing());

        head.setDefault();
        assertFalse(head.isFearing());
    }

    @Test
    public void testSetDefault() {
        head.setDefault();
        assertEquals(Emotions.DEFAULT, head.getEmotionalState());
        assertEquals(MouthState.CLOSE, head.getMouthState());
        assertEquals(EyesState.OPEN, head.getEyesState());
        assertEquals(TeethState.HIDE, head.getTeethState());
    }



    @Test
    public void testIsFearingFalseWhenMouthStateIsDifferent() {
        head.setEmotionalState(Emotions.FEAR);
        head.setMouthState(MouthState.CLOSE); // Not the expected mouth state
        head.setTeethState(TeethState.GRIN);
        head.setEyesState(EyesState.WIDE_OPEN);

        assertFalse(head.isFearing(), "The head should not be fearing because mouth state is not STRETCHING_OUT_IN_WIDTH.");
    }

    @Test
    public void testIsFearingFalseWhenTeethStateIsDifferent() {
        head.setEmotionalState(Emotions.FEAR);
        head.setMouthState(MouthState.STRETCHING_OUT_IN_WIDTH);
        head.setTeethState(TeethState.HIDE); // Not the expected teeth state
        head.setEyesState(EyesState.WIDE_OPEN);

        assertFalse(head.isFearing(), "The head should not be fearing because teeth state is not GRIN.");
    }

    @Test
    public void testIsFearingFalseWhenEyesStateIsDifferent() {
        head.setEmotionalState(Emotions.FEAR);
        head.setMouthState(MouthState.STRETCHING_OUT_IN_WIDTH);
        head.setTeethState(TeethState.GRIN);
        head.setEyesState(EyesState.CLOSE); // Not the expected eyes state

        assertFalse(head.isFearing(), "The head should not be fearing because eyes state is not WIDE_OPEN.");
    }
}
