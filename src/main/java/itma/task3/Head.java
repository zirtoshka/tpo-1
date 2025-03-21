package itma.task3;

import itma.task3.states.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Head {
    private String name;
    private Emotions emotionalState;
    private EyesState eyesState;
    private TeethState teethState;
    private MouthState mouthState;

    public Head() {
        setDefault();
    }

    public void smile() {
        setTeethState(TeethState.SHOW);
        setMouthState(MouthState.STRETCHING_OUT_IN_WIDTH);
    }

    public void surprise() {
        setMouthState(MouthState.OPEN);
        setEyesState(EyesState.WIDE_OPEN);
    }

    public void fear() {
        setEmotionalState(Emotions.FEAR);
        setMouthState(MouthState.STRETCHING_OUT_IN_WIDTH);
        setTeethState(TeethState.GRIN);
        setEyesState(EyesState.WIDE_OPEN);
    }

    public boolean isFearing() {
        if (getEmotionalState().equals(Emotions.FEAR)
                && getMouthState().equals(MouthState.STRETCHING_OUT_IN_WIDTH)
                && getTeethState().equals(TeethState.GRIN)
                && getEyesState().equals(EyesState.WIDE_OPEN)
        ) {
            return true;
        }
        return false;
    }

    public void setDefault() {
        setEmotionalState(Emotions.DEFAULT);
        setMouthState(MouthState.CLOSE);
        setEyesState(EyesState.OPEN);
        setTeethState(TeethState.HIDE);
    }

}
