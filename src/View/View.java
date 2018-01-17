package View;

import Controller.ControllerInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class View extends JFrame {
    private JTextField jTextField;
    private ControllerInterface controllerInterface;

    public View(){
        JPanel panel=new JPanel();
        JPanel eastPanel=new JPanel();
        JPanel centerPanel=new JPanel();

        createPanel(panel, eastPanel, centerPanel);
        createTextField(centerPanel);
        createButtons(eastPanel);

        setTitle("Page Scraper");
        setSize(400, 120);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createPanel(JPanel panel, JPanel eastPanel, JPanel centerPanel){
            panel.setLayout(new BorderLayout());

            eastPanel.setLayout(new BoxLayout(eastPanel,BoxLayout.PAGE_AXIS));

            add(panel);
            panel.add(eastPanel,BorderLayout.EAST);
            panel.add(centerPanel,BorderLayout.CENTER);
    }

    private void createTextField(JPanel panel){
        jTextField =new JTextField(25);
        JLabel label=new JLabel("write url:");

        panel.add(label);
        panel.add(jTextField);
    }

    private void createButtons(JPanel panel){
        JButton buttons[]=new JButton[4];
        buttons[0]=new JButton("add in db");
        buttons[1]=new JButton("add in file");
        buttons[2]=new JButton("search");
        buttons[3]=new JButton("delete");

        buttons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerInterface.buttonPressed(e.getActionCommand(), jTextField.getText());
            }
        });
        buttons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFrame(e.getActionCommand(), jTextField.getText());
            }
        });
        buttons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFrame(e.getActionCommand(), jTextField.getText());
            }
        });
        buttons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerInterface.buttonPressed(e.getActionCommand(), jTextField.getText());
            }
        });
        for (JButton button:buttons) {
            panel.add(button);
        }
    }

    private void addFrame(String btnName, String url){
        JFrame frame=new JFrame();
        JPanel panel=new JPanel(new FlowLayout());

        JButton button=new JButton(btnName);
        JTextField textField=new JTextField(15);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerInterface.buttonPressed(e.getActionCommand(),url, textField.getText());
                frame.dispose();
            }
        });

        panel.add(textField);
        panel.add(button);
        frame.add(panel);

        frame.setTitle("Page");
        frame.setSize(200, 120);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void setControllerInterface(ControllerInterface controllerInterface) {
        this.controllerInterface = controllerInterface;
    }
}
