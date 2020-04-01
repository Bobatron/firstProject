package gui.panels;

import javax.swing.*;
import java.awt.*;

public class TextPanel extends JPanel {

    public TextPanel() {
        super();
        setLayout(new BorderLayout());
        JTextArea jTextArea = new JTextArea();
        add(jTextArea, BorderLayout.CENTER);

        JToolBar textToolbar = new JToolBar();
        textToolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        textToolbar.setFloatable(false);

        JButton saveTextButton = new JButton("SAVE");
        JButton loadTextButton = new JButton("LOAD");
        textToolbar.add(saveTextButton);
        textToolbar.add(loadTextButton);
        add(textToolbar, BorderLayout.SOUTH);
    }

}

