import javax.swing.JPanel;
import javax.swing.JButton;

import java.util.ArrayList;

public class ViewSeating {
    AirplaneManager data = new AirplaneManager();
    Windows gui = new Windows();

    static ArrayList<JButton> buttonData = new ArrayList<>();

    public ViewSeating()
    {
        JPanel seating = new JPanel();

        JButton back = new JButton("Back");
        back.setBounds(0, 0, 70, 30);
        back.addActionListener((e) ->
        {
            for(JButton obj : buttonData)
                seating.remove(obj);
            gui.switchPanel("main");
        });
        
        seating.setLayout(null);
        seating.add(back);

        gui.insertJPanel("view seat", seating);
    }

    public ViewSeating(Airplane data)
    {
        DisplaySeat(data);
    }

    private void DisplaySeat(Airplane data)
    {
        buttonData.clear();
        JPanel panel = gui.getJpanel("view seat");
        
        for(int[] pos : data.getSeatData())
        {
            JButton b = new JButton();
            b.setBounds(pos[0], pos[1], 30, 30);

            buttonData.add(b);
            panel.add(b);
        }

        gui.switchPanel("view seat");
    }
}
