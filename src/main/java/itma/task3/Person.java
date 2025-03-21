package itma.task3;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Person {
    private String name;
    private Head[] heads;

    public Person() {
        this.heads = new Head[1];
        this.heads[0] = new Head();
    }

    public Person(int amountOfHeads) {
        this.heads = new Head[amountOfHeads];
        for (int i = 0; i < amountOfHeads; i++) {
            this.heads[i] = new Head();
        }
    }

    public void smile(int headNumber){
        if(isPanicAhaha()) return;
        heads[headNumber].smile();
    }

    public void surprise(int headNumber){
        if(isPanicAhaha()) return;
        heads[headNumber].surprise();
    }

    public void panicAhaha(){
        for (Head head : heads) {
            head.fear();
        }
    }


    public void setDefaultState(){
        for (Head head : heads) {
            head.setDefault();
        }
    }

    public boolean isPanicAhaha(){
        for (Head head : heads) {
            if(!head.isFearing()) return false;
        }
        return true;
    }


}
