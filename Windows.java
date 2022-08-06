import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.Map;
import java.util.HashMap;

public class Windows {
    static JFrame frame;
    static Map<String, JPanel> JpanelData = new HashMap<String, JPanel>();

    public Windows(String name, int w, int h)
    {
        frame = new JFrame(name);
        frame.setSize(w, h);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
    public Windows()
    {

    }

    public void endProgram()
    {
        frame.dispose();
        frame.setVisible(false);
        System.exit(0);
    }

    public void insertJPanel(String PanelForWhat, JPanel newData)
    {
        JpanelData.put(PanelForWhat.toLowerCase(), newData);
    }

    public void refresh()
    {
        frame.validate();
        frame.repaint();
    }

    public void switchPanel(String PanelForWhat) 
    {
        if(JpanelData.get(PanelForWhat.toLowerCase()) == null)
            return ;
        frame.getContentPane().removeAll();
        frame.add(JpanelData.get(PanelForWhat));
        frame.validate();
        frame.repaint();
    }
}
