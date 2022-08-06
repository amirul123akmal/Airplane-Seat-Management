import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Component;
import java.awt.Color;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JLabel;


public class createSeating {
    Windows gui = new Windows();
    private ArrayList<JButton> buttons = new ArrayList<>();
    private ArrayList<int[]> buttonsData = new ArrayList<>();
    private AirplaneManager database = new AirplaneManager();
    private ArrayList<Component> gatherComp = new ArrayList<>();

    private boolean clickable = false;

    public createSeating()
    {
        final int buttonWidth = 120, buttonHeight = 30;
        JPanel seating = new JPanel()
        {
            /*
             * - Need to reserve an area for the airplane's seating
             * https://stackoverflow.com/a/34596297/10613167
             */

            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawLine(310, 50, 1000, 50);
                g.drawLine(310, 50, 310, 800);
                g.drawLine(311, 210, 0, 210);
                g.drawLine(311, 235, 0, 235);
            }
        };
        seating.addMouseListener(new MouseListener()
        {
            public void mouseClicked(MouseEvent e) 
            {
                System.out.println(e.getPoint());
            }
            public void mousePressed(MouseEvent e) 
            {
            }
            public void mouseReleased(MouseEvent e) 
            {
                if(clickable)
                {
                    JButton b = new JButton();
                    if(e.getX() < 311 || e.getY() < 52)
                    {
                        JOptionPane.showMessageDialog(seating, "Seating Generation is Out Of The Border Reserced", "Seating Input", JOptionPane.WARNING_MESSAGE);
                    }
                    else
                    {
                        b.setBounds(e.getX() - 15, e.getY()-15, 30, 30);
                        b.addMouseMotionListener(new MouseMotionListener() {
                            public void mouseDragged(MouseEvent e ) {
                                b.setBounds(b.getX() + e.getX() - 15, b.getY()+e.getY()-15, b.getWidth(), b.getHeight());
                            }
                            public void mouseMoved(MouseEvent e) {
                            }
                        });
                        buttonsData.add(new int[]{b.getX(), b.getY()});
                        buttons.add(b);
                        seating.add(b);
                        gui.refresh();
                    }
                }
            }
            public void mouseEntered(MouseEvent e) 
            {
            }
            public void mouseExited(MouseEvent e) 
            {
            }
            
        });

        /*
         * https://stackoverflow.com/a/5767825/10613167
         * - need to create word wrap
         * - just use html + css (if needed)
         */
        JToggleButton seat = new JToggleButton(String.format("<html><body width = %d><center>%s</center></body></html>", buttonWidth - 40, "Create New Seating"));
        seat.setBounds(0, buttonHeight, buttonWidth, buttonHeight * 2);
        seat.addActionListener(e->{
            if(clickable)
            {
                clickable = false;
                seat.setBackground(null);
                
                /*
                 * =====================
                 * RESERVED CODE AREA
                 * =====================
                 * - Stores all button coordinates 
                 * - Stores button's data
                 */
                JTextField airplaneName = new JTextField(5);
                JTextField airplaneCapacity = new JTextField(5);
                JPanel temp = new JPanel();
                temp.add(new JLabel("Airplane's Name: "));
                temp.add(airplaneName);
                temp.add(new JLabel("Airplane's Capacity: "));
                temp.add(airplaneCapacity);
                
                JOptionPane.showInputDialog(null, temp, "Enter Airplane's Name and Capacity", JOptionPane.OK_CANCEL_OPTION);
                
                if(airplaneCapacity == null || airplaneCapacity == null)
                    return;

                database.addAirplane(airplaneName.getText(), Integer.parseInt(airplaneCapacity.getText()), buttonsData);
                // saved = true;
                for(JButton a : buttons)
                    seating.remove(a);
                buttonsData.clear();
                buttons.clear();
            }
            else
            {
                clickable = true;
                // saved = false;
                seat.setBackground(new Color(100, 100, 100));
                buttons.clear();
                buttonsData.clear();
            }
            gui.refresh();
        });
        /*
         * - After the user clicked, there are border around the text
         * https://stackoverflow.com/a/9361994/10613167
         * - This will remove the border
         */
        seat.setFocusPainted(false);
        gatherComp.add(seat);

        JButton back = new JButton("Back");
        back.addActionListener((e) -> {
            gui.switchPanel("main");
        });
        back.setBounds(0, 0, buttonWidth, buttonHeight);
        gatherComp.add(back);

        // JTEXTFIELD
        JLabel title = new JLabel("Airplane's Data");
        gatherComp.add(title);
        title.setBounds(105, 212, 200, 20);
        JLabel airplaneName = new JLabel("Airplane Name:");
        gatherComp.add(airplaneName);
        airplaneName.setBounds(105, 240, 200, 20);
        JLabel weight = new JLabel("Airplane Weight Capacity:");
        gatherComp.add(weight);
        weight.setBounds(105, 280, 200, 20);
        
        for(Component OBJ : gatherComp)
            seating.add(OBJ);
        seating.setLayout(null);

        gui.insertJPanel("create seating", seating);
    }

}
