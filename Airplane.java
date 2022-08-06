import java.util.ArrayList;

public class Airplane {
    private String airplaneName;
    private int seat, KGcapacity;
    private ArrayList<int[]> posiiton = new ArrayList<>();
    public Airplane()
    {
        seat = 0;
        KGcapacity = 0;
    }
    public Airplane(String name, int seat, int capacity)
    {
        airplaneName = name;
        this.seat = seat;
        this.KGcapacity = capacity;
    }
    public Airplane(String name, int seat, int capacity, ArrayList<int[]> pos)
    {
        airplaneName = name;
        this.seat = seat;
        this.KGcapacity = capacity;
        posiiton = pos;
    }
    public String getAirplaneName() {
        return airplaneName;
    }
    public int getSeat() {
        return seat;
    }
    public int getKGcapacity() {
        return KGcapacity;
    }

    public ArrayList<int[]> getSeatData()
    {
        return posiiton;
    }
    
}
