public class Lift {
    private int floor ;
    private int target;
    private int position;
    String last_direction;
    private int scale;

    public int getPosition() {
        return position;
    }

    public Lift(int scale){
        this.target = 0;
        this.floor = 1;
        this.position = scale;
        this.last_direction = "up";
        this.scale = scale;
    }

    public void setTarget(int target){
        this.target = target;
    }

    public int getTarget(){
        return this.target;
    }

    public int getFloor(){
        return this.floor;
    }

    public String getDirection(){
        if (this.getTarget() == 0){
            return last_direction;
        }
        else if (this.getFloor() - this.getTarget() > 0){
            last_direction = "down";
            return "down";
        }
        else{
            last_direction = "up";
            return "up";
        }
    }

    public void move(){
        this.floor = this.position / scale;
        if (this.target == 0){
            return;
        }
        if ((float) this.position / scale == (float) this.target) {
            this.target = 0;
        } else if ((float) this.position / scale > (float) this.target) {
            this.position -= 1;
        } else {
            this.position += 1;
        }
    }
}
