package gui;

import com.github.sarxos.webcam.WebcamPanel;
import gui.interfaces.IActionListener;
import util.WebcamUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CameraPanel extends JPanel {

    public static final String IMAGE_LOCATION = "src/main/resources/image/";
    public static final int PREF_H = 800;
    public static final int PREF_V = 600;

    JList jList;
    File imageFolder = new File(IMAGE_LOCATION);
    JLabel image = new JLabel();

    JToolBar imageToolbar = new JToolBar();
    JButton newImageButton = new JButton("NEW IMAGE");
    JButton deleteImageButton = new JButton("DELETE");

    WebcamPanel webcamPanel;
    JButton cameraButton = new JButton("SAVE IMAGE");

    Component focusPanel;

    IActionListener iActionListener;

    public CameraPanel() {
        super();
        setLayout(new BorderLayout());

        imageToolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        imageToolbar.setFloatable(false);

        cameraButton.addActionListener(cameraButtonAction());

        newImageButton.addActionListener(newImageButtonAction());
        deleteImageButton.addActionListener(deleteImageButtonAction());
        imageToolbar.add(newImageButton);
        imageToolbar.add(deleteImageButton);

        initImageList();
        jList.addListSelectionListener(listSelectionAction());

        setFocusPanel(new JPanel());
        add(jList, BorderLayout.WEST);
        add(imageToolbar, BorderLayout.SOUTH);
    }

    private ActionListener cameraButtonAction() {
        return actionEvent -> {
            WebcamUtil.takePhoto();
            refreshJList();
            refreshUI();
            jList.addListSelectionListener(listSelectionAction());
            jList.setSelectedIndex(jList.getLastVisibleIndex());
        };
    }

    public void restartWebcam(){
        webcamPanel = new WebcamPanel(WebcamUtil.initWebcam());
        webcamPanel.setImageSizeDisplayed(true);
        webcamPanel.setMirrored(false);
        webcamPanel.setLayout(new BorderLayout());
        webcamPanel.add(cameraButton, BorderLayout.SOUTH);
    }

    private ActionListener deleteImageButtonAction() {
        return actionEvent -> {
            if (jList.getSelectedValue() != null) {
                File selectedFile = new File(IMAGE_LOCATION + jList.getSelectedValue().toString());
                selectedFile.delete();
                setFocusPanel(new JPanel());
                refreshJList();
                refreshUI();
                jList.addListSelectionListener(listSelectionAction());
            }
        };
    }

    private ActionListener newImageButtonAction() {
        return actionEvent -> {
            if(webcamPanel == null || !webcamPanel.getWebcam().isOpen()) {
                restartWebcam();
                setFocusPanel(webcamPanel);
                refreshUI();
            }
        };
    }


    private void initImageList(){
        if (!imageFolder.exists()) imageFolder.mkdir();
        jList = new JList(getImageFileNames(imageFolder.listFiles()));
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.setPreferredSize(new Dimension(PREF_H/6, PREF_V));
    }

    public String[] getImageFileNames(File[] files) {
        if (files == null) return new String[]{};
        String[] fileNames = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            fileNames[i] = files[i].getName();
        }
        return fileNames;
    }

    private ListSelectionListener listSelectionAction() {
        return listSelectionEvent -> {
            String selectedValue = jList.getSelectedValue().toString();
            if (selectedValue != null) {
                BufferedImage bufferedImage = null;
                try {
                    bufferedImage = ImageIO.read(new File(IMAGE_LOCATION+selectedValue));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(webcamPanel != null && webcamPanel.getWebcam().isOpen()){
                    WebcamUtil.closeWebcam();
                }
                image = new JLabel(new ImageIcon(bufferedImage));
                setFocusPanel(image);
                refreshUI();
            }
        };
    }

    private void setFocusPanel(Component newPanel) {
        if(focusPanel != null)
            remove(focusPanel);
        focusPanel = newPanel;
        focusPanel.setPreferredSize(new Dimension((PREF_H/6)*5, PREF_V));
        add(focusPanel, BorderLayout.EAST);
    }

    private void refreshUI() {
        iActionListener.action("refresh");
    }

    void refreshJList() {
        remove(jList);
        initImageList();
        add(jList, BorderLayout.WEST);
    }

    public void setActionListener(IActionListener iActionListener) {
        this.iActionListener = iActionListener;
    }
}
