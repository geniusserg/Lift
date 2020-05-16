import java.util.*;

public class Controller extends Thread{
    private Lift lift;
    SortedSet<Integer> up;
    SortedSet<Integer> down;
    public final int scale;
    private int position;

    public Controller(int scale){
        up = new TreeSet<>();
        down = new TreeSet<>();
        this.lift = new Lift(scale);
        this.scale = scale;
        position = scale;
    }

    public int getLiftPosition(){
        return position;
    }

    public void add(int target){ //todo
        if (lift.getTarget() == target){
            return;
        }
        if ((float)target*scale > lift.getPosition()){
            up.add(target);
        }
        else{
            down.add(target);
        }

    }

    public void run() {
        String last_task = "up";
        while (true){
            position = lift.getPosition();
            if (((up.size() != 0 && up.first()*scale == lift.getPosition()) || (down.size() != 0 && down.last()*scale == lift.getPosition()) ) && lift.getTarget() == 0){
                if (last_task.equals("up") && up.size() != 0) {
                    up.remove(up.first());
                }
                else{
                    down.remove(down.last());
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {}
            }
            if (last_task.equals("up")) {
                if (up.size() != 0) {
                    lift.setTarget(up.first());
                    try {
                        if (lift.getTarget() == 0) {
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    last_task = "down";
                    try {
                        if (lift.getTarget() == 0) {
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (last_task.equals("down")) {
                if (down.size() != 0) {
                    lift.setTarget(down.last());
                }
                else{
                    last_task = "up";
                }
            }
            try{
                lift.move();
                Thread.sleep(20);
            } catch (InterruptedException e) {
                while(lift.getTarget() != 0){
                    lift.move();
                }
                break;
            }
        }
    }

}
