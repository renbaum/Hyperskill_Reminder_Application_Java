package reminderapplication;

import javax.swing.*;

public class Reminders{
    String text;
    int delay;
    int period;
    int counter = 0;
    Timer timer;
    
    public Reminders(String text, int delay, int period) {
        this.text = text;
        this.delay = delay;
        this.period = period;
        startTimer();
    }
    
    @Override
    public String toString() {
        return String.format("Reminder Text: %s; Delay: %d; Period: %d;",
                text, delay, period);
    }

    public void startTimer() {
        timer = new Timer(delay * 1000, e -> {
            ReminderDialog dialog = new ReminderDialog(null, this);
            counter++;
            if(counter >= period){
                timer.stop();
            }

        });
        timer.start();
    }
}
