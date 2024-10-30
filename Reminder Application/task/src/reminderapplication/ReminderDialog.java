package reminderapplication;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.sql.Time;

public class ReminderDialog extends JFrame {
    JTextField textField;
    JButton cancelButton;
    JButton okButton;
    JLabel textLabel;
    JLabel delaysLabel;
    JLabel setDelayLabel;
    JComboBox<String> setDelay;
    JLabel setPeriodLabel;
    JLabel periodLabel;
    JComboBox<String> setPeriod;
    TimeReminderApplication parentFrame;
    Timer timer;
    Reminders rmd;

    public ReminderDialog(TimeReminderApplication frame, Reminders rmd) {
        super("Set Reminder");
        parentFrame = frame;
        setName("Set Reminder");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.rmd = rmd;

        setSize(500, 200);
        setLocation(1250, 600);
        setLayout(null);

        textLabel = new JLabel("Reminder Text");
        textLabel.setName("Reminder Text Label");
        textLabel.setBounds(2, 2, 150, 30);
        add(textLabel);

        textField = new JTextField();
        textField.setName("Field");
        textField.setBounds(2, 32, 300, 30);
        add(textField);

        okButton = new JButton("OK");
        okButton.setName("OK");
        okButton.setBounds(10, 100, 100, 30);
        add(okButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setName("Cancel");
        cancelButton.setBounds(150, 100, 100, 30 );
        add(cancelButton);

        setDelayLabel = new JLabel("Set Delay");
        setDelayLabel.setName("Set Delay Label");
        setDelayLabel.setBounds(320, 2, 80, 30);
        add(setDelayLabel);

        setPeriodLabel = new JLabel("Set Period");
        setPeriodLabel.setName("Set Repeat Period Label");
        setPeriodLabel.setBounds(390, 2, 80, 30);
        add(setPeriodLabel);

        delaysLabel = new JLabel("30");
        delaysLabel.setName("Delays Label");
        delaysLabel.setBounds(320, 60, 50, 30);
        add(delaysLabel);

        periodLabel = new JLabel("0");
        periodLabel.setName("Period label");
        periodLabel.setBounds(390, 60, 50, 30);
        add(periodLabel);

        String[] itemsDelay = {"30", "25", "15", "5"};
        setDelay = new JComboBox<>(itemsDelay);
        setDelay.setName("set Delay");
        setDelay.setBounds(320, 32, 50, 30);
        add(setDelay);

        String[] itemsPeriod = {"0", "5", "10", "20"};
        setPeriod = new JComboBox<>(itemsPeriod);
        setPeriod.setName("set Period");
        setPeriod.setBounds(390, 32, 50, 30);
        add(setPeriod);

        if(parentFrame == null){
            createModalListeners();
        }else {
            createListeners();
        }

        if(rmd != null) {
            textField.setText(rmd.text);
            delaysLabel.setText(String.valueOf(rmd.delay));
            periodLabel.setText(String.valueOf(rmd.period));
            setDelay.setSelectedItem(String.valueOf(rmd.delay));
            setPeriod.setSelectedItem(String.valueOf(rmd.period));
        }
        setVisible(true);
        setResizable(false);


    }

    void createListeners() {
        okButton.addActionListener(e -> {
            if(rmd != null){
                rmd.text = textField.getText();
                rmd.delay = Integer.parseInt(delaysLabel.getText());
                rmd.period = Integer.parseInt(periodLabel.getText());
                rmd.timer.stop();
                rmd.startTimer();
                parentFrame.model.setElementAt(rmd, parentFrame.model.indexOf(rmd));
            }else{
            parentFrame.model.addElement(new Reminders(textField.getText(),
                    Integer.parseInt(delaysLabel.getText()),
                    Integer.parseInt(periodLabel.getText())));
            }
            parentFrame.enableButtons();
            dispose();
        });

        cancelButton.addActionListener(e -> {
            parentFrame.enableButtons();
            dispose();
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                parentFrame.enableButtons();
                dispose();
            }
        });

        setDelay.addActionListener(e -> {
            delaysLabel.setText(setDelay.getSelectedItem().toString());
        });

        setPeriod.addActionListener(e -> {
            periodLabel.setText(setPeriod.getSelectedItem().toString());
        });

    }

    void createModalListeners() {
        okButton.setEnabled(false);

        timer = new Timer(5000, e -> {
            dispose();
        });
        timer.start();

        cancelButton.addActionListener(e -> {
            dispose();
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                dispose();
            }
        });

        setDelay.setEnabled(false);
        setPeriod.setEnabled(false);

    }

}
