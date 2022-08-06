import java.util.ArrayList;
import java.util.Scanner;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;

public class AirplaneManager {
    static ArrayList<Airplane> AirplaneList = new ArrayList<>();

    public AirplaneManager()
    {
        forceUpdate();
    }

    public ArrayList<Airplane> getAirplanesData()
    {
        return AirplaneList;
    }

    public void forceUpdate()
    {
        AirplaneList.clear();
        String name = "";
        int seat = 0, cap = 0 ;
        try
        {
            File folder = new File("Database/Airplanes/");
            File[] files = folder.listFiles();
            for(File plane : files)
            {
                name = plane.getName().replaceAll(".txt", "");
                Scanner data = new Scanner(plane);
                while(data.hasNextLine())
                {
                    String[] a = data.nextLine().split(" ");
                    if(a[0].equals("seat"))           seat = Integer.parseInt(a[2]);
                    else if(a[0].equals("capacity"))  cap = Integer.parseInt(a[2]);
                }
                AirplaneList.add(new Airplane(name, seat, cap));
                data.close();
            }
        }
        catch(Exception e)
        {
            System.err.println("\n\nError occured in Airplane.java\n");
            e.printStackTrace();
        }  
    }

    public void addAirplane(String name, int capacity, ArrayList<int[]> data)
    {
        try{
            FileWriter file = new FileWriter("Database/Airplanes/" + name + ".txt");
            PrintWriter printing = new PrintWriter(file);

            printing.println("seat = " + data.size());
            printing.println("capacity = " + capacity + "\n");

            for(int[] a : data)
                printing.println(
                    String.format("%d,%d", a[0], a[1])
                );

            printing.close();
            file.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
