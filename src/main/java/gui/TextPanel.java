package gui;

import gui.interfaces.IActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextPanel extends JPanel {

    public static final String TEXT_LOCATION = "src/main/resources/text/";
    public static final int PREF_H = 800;
    public static final int PREF_V = 600;

    JList jList;
    File textFolder = new File(TEXT_LOCATION);

    JTextArea jTextArea = new JTextArea();
    JScrollPane jScrollPane = new JScrollPane(jTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    JToolBar textToolbar = new JToolBar();
    JButton newTextButton = new JButton("NEW");
    JButton saveTextButton = new JButton("SAVE");
    JButton deleteTextButton = new JButton("DELETE");

    Component focusPanel;

    IActionListener iActionListener;

    public TextPanel() {
        super();
        setLayout(new BorderLayout());
        textToolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        textToolbar.setFloatable(false);

        newTextButton.addActionListener(newTextButtonAction());
        saveTextButton.addActionListener(saveTextButtonAction());
        deleteTextButton.addActionListener(deleteTextButtonAction());

        textToolbar.add(newTextButton);
        textToolbar.add(saveTextButton);
        textToolbar.add(deleteTextButton);

        initTextList();

        jList.setPreferredSize(new Dimension(PREF_H/7, PREF_V));
        add(jList, BorderLayout.WEST);
        
        jScrollPane.setPreferredSize(new Dimension((PREF_H/6)*5, PREF_H));
        add(emptyPanel(), BorderLayout.CENTER);

        add(textToolbar, BorderLayout.SOUTH);
    }

    public void setFocusPanel(Component newPanel){
        if(focusPanel == newPanel)
            return;
        if(focusPanel != null)
            remove(focusPanel);
        focusPanel = newPanel;
        add(focusPanel, BorderLayout.EAST);
        refreshUI();
    }


    public JPanel emptyPanel(){
        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension((PREF_H/6)*5, PREF_H));
        return jPanel;
    }

    private ActionListener deleteTextButtonAction() {
        return actionEvent -> {
            if (jList.getSelectedValue() != null) {
                File selectedFile = new File(TEXT_LOCATION + jList.getSelectedValue().toString());
                selectedFile.delete();
                jTextArea.setText(null);
                refreshJList();
                setFocusPanel(emptyPanel());
            }
        };
    }

    private ActionListener newTextButtonAction() {
        return actionEvent -> {
            String fileName = "text"+System.nanoTime()+".txt";
            try {
                FileWriter newTextFile = new FileWriter(TEXT_LOCATION+fileName);
                newTextFile.close();
                jTextArea.setText(null);
            } catch (IOException e) {
                e.printStackTrace();
            }
            refreshJList();
            setFocusPanel(jScrollPane);
            jList.setSelectedValue(fileName, true);
        };
    }

    private ActionListener saveTextButtonAction() {
        return actionEvent -> {
            try {
                FileWriter fileWriter = new FileWriter(TEXT_LOCATION + jList.getSelectedValue());
                fileWriter.write(jTextArea.getText());
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    private ListSelectionListener listSelectionAction() {
        return listSelectionEvent -> {
            if (jList.getSelectedValue() != null) {
                try {
                    setFocusPanel(jScrollPane);
                    String text = new String ( Files.readAllBytes( Paths.get(TEXT_LOCATION + jList.getSelectedValue()) ) );
                    jTextArea.setText(text);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private void initTextList(){
        if (!textFolder.exists()) textFolder.mkdir();
        jList = new JList(getTextFileNames(textFolder.listFiles()));
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.addListSelectionListener(listSelectionAction());
    }

    public String[] getTextFileNames(File[] files) {
        if (files == null) return new String[]{};
        String[] fileNames = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            fileNames[i] = files[i].getName();
        }
        return fileNames;
    }

    void refreshJList() {
        remove(jList);
        initTextList();
        add(jList, BorderLayout.WEST);
    }

    private void refreshUI() {
        iActionListener.action("refresh");
    }

    public void setActionListener(IActionListener iActionListener) {
        this.iActionListener = iActionListener;
    }
}

