import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;

public class run 
{
    public static void main(String[] args) {
        final int width = 1000, height = 800;
        final int buttonHeight = 30, buttonWidth = 120, buttonStart = 200;
        int buttonCount = 0;
        ArrayList<Component> gatherComp = new ArrayList<>();
        Windows gui = new Windows("Airplane Seat Management", width, height);
        new createSeating();
        AirplaneManager data = new AirplaneManager();
        
        JTable table = new JTable()
        {
            public boolean isCellEditable(int row, int col)
            {
                return false;
            }
        };
        

        DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
        tableModel.addColumn("Airplane");
        for(Airplane obj : data.getAirplanesData())
            tableModel.addRow(new Object[]{obj.getAirplaneName()});

        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(0, 0, 200, height);
        scroll.setViewportView(table);
        gatherComp.add(scroll);

        //  1
        JButton createSeat = new JButton("Create Seat");
        createSeat.setBounds(buttonStart, buttonHeight * buttonCount++, buttonWidth, buttonHeight);
        createSeat.addActionListener((e) -> {
            gui.switchPanel("create seating");
        });
        gatherComp.add(createSeat);

        // 2
        JButton viewSeating = new JButton("View Seatings");
        viewSeating.setBounds(buttonStart, buttonHeight * buttonCount++, buttonWidth, buttonHeight);
        gatherComp.add(viewSeating);

        // 3
        JButton refresh = new JButton("Refresh");
        refresh.setBounds(buttonStart, buttonHeight * buttonCount++, buttonWidth, buttonHeight);
        refresh.addActionListener(e->{
            for (int i = tableModel.getRowCount() - 1; i > -1; i--) {
                tableModel.removeRow(i);
                gui.refresh();
            }
            data.forceUpdate();
            for(Airplane obj : data.getAirplanesData())
                tableModel.addRow(new Object[]{obj.getAirplaneName()});
            gui.refresh();
        });
        gatherComp.add(refresh);

        JButton exit = new JButton("Exit");
        exit.addActionListener(e ->{
            gui.endProgram();
        });
        exit.setBounds(198, 732, 120, 30);
        exit.setBackground(new Color(255, 0, 0));
        gatherComp.add(exit);

        JLabel airplaneName = new JLabel("Airplane Name");
        airplaneName.setBounds(555, 115, 200, 30);
        gatherComp.add(airplaneName);
        
        JLabel airplaneSeat = new JLabel("Airplane Weight Capacity");
        airplaneSeat.setBounds(535, 260, 200, 30);
        gatherComp.add(airplaneSeat);

        JTextField nameForAirplane = new JTextField();
        nameForAirplane.setEditable(false);
        nameForAirplane.setBorder(new LineBorder(Color.black, 1));
        nameForAirplane.setBounds(500, 150, 200, 25);
        gatherComp.add(nameForAirplane);

        JTextField capacityAirplane = new JTextField();
        capacityAirplane.setEditable(false);
        capacityAirplane.setBorder(new LineBorder(Color.black, 1));
        capacityAirplane.setBounds(500, 300, 200, 25);
        gatherComp.add(capacityAirplane);

        table.addMouseListener(new MouseListener()
        {
            public void mouseClicked(MouseEvent e) 
            {
                nameForAirplane.setText(data.getAirplanesData().get(table.getSelectedRow()).getAirplaneName());
                capacityAirplane.setText(Integer.toString(data.getAirplanesData().get(table.getSelectedRow()).getKGcapacity()));;
            }
            public void mouseReleased(MouseEvent e) 
            {
            }
            public void mouseEntered(MouseEvent e) 
            {
            }
            public void mouseExited(MouseEvent e) 
            {
            }
            public void mousePressed(MouseEvent e) 
            {
            }
            
        });

        JPanel mainPanel = new JPanel()
        {
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawLine(440, 90, 440, 400);
                g.drawLine(440, 400, 775, 400);
                g.drawLine(775, 400, 775, 90);
                g.drawLine(440, 90, 775, 90);
            }
        };
        mainPanel.addMouseListener(new MouseListener()
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
            }
            public void mouseEntered(MouseEvent e) 
            {
            }
            public void mouseExited(MouseEvent e) 
            {
            }
            
        });

        mainPanel.setLayout(null);  
        for(Component obj : gatherComp)
            mainPanel.add(obj);

        gui.insertJPanel("main", mainPanel);
        gui.switchPanel("main");
    }
}