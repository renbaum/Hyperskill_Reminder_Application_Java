package reminderapplication;

import javax.swing.*;
import java.awt.*;

public class TimeReminderApplication extends JFrame {

    JButton addButton;
    JButton editButton;
    JButton deleteButton;
    JScrollPane scrollPane;
    JList jList;
    DefaultListModel<Reminders> model = new DefaultListModel<>();


    public TimeReminderApplication() throws HeadlessException {
        super("Reminder Application");

        setSize(500, 300);
        setLocation(1250, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        jList = new JList(model);
        jList.setName("List of Reminders");
        scrollPane = new JScrollPane(jList);
        scrollPane.setName("Scroll Pane");
        scrollPane.setBounds(2, 2, 480, 100);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);


        addButton = new JButton("ADD");
        addButton.setName("AddReminder");
        addButton.setBounds(50, 220, 100, 30);
        add(addButton);
        editButton = new JButton("EDIT");
        editButton.setName("EditReminder");
        editButton.setBounds(200, 220, 100, 30);
        add(editButton);
        deleteButton = new JButton("DELETE");
        deleteButton.setName("DeleteReminder");
        deleteButton.setBounds(350, 220, 100, 30);
        add(deleteButton);

        setVisible(true);
        setResizable(false);

        addListeners();
/*        jList.setModel(new DefaultListModel());
        jList.setVisibleRowCount(5);
        jList.setBounds(2, 2, 480, 100);
        jList.setFixedCellHeight(30);
        jList.setFixedCellWidth(480);
        jList.setOpaque(false);
*/
    }

    public void addListeners() {
        addButton.addActionListener(e -> {
            disableButtons();
            ReminderDialog dialog = new ReminderDialog(this, null);
            dialog.setVisible(true);

        });

        editButton.addActionListener(e -> {
            int index = jList.getSelectedIndex();
            if (index == -1) {return;}
            Reminders x = model.getElementAt(index);
            //x.timer.stop();
            ReminderDialog dialog = new ReminderDialog(this, x);
            dialog.setVisible(true);
        });

        deleteButton.addActionListener(e -> {
            int index = jList.getSelectedIndex();
            if (index == -1) {return;}
            Reminders x = model.getElementAt(index);
            x.timer.stop();
            model.remove(index);
        });
    }

    public void disableButtons() {
        addButton.setEnabled(false);
        deleteButton.setEnabled(false);
        editButton.setEnabled(false);
    }

    public void enableButtons() {
        addButton.setEnabled(true);
        deleteButton.setEnabled(true);
        editButton.setEnabled(true);
    }

}
