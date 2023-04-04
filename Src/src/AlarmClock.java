import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.Calendar;
import static java.lang.Thread.sleep;

public class AlarmClock extends JFrame {
    private Container ct;
    private Font f1, f2, f3, f4,f5;
    private JLabel jLabel1, jLabel2, jLabel3, jLabel4 ,jLabel5 ,jLabel6,jLabel7,jLabel8,jLabel9,jLabel10;
    private JTextField tfh, tfm, tfam;
    private JButton btnSet, btnStart, btnStop;
    private int temp_h, temp_m;
    private String temp_am;
    private int flag = 1;
    private Clip clip;
    private int mase ;

    public AlarmClock() {
        createUI();
        displayClock();
    }
    //GUIทั้งหมดของนาฬิกาปลุกในหน้าปกติ
    private void createUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBounds(470, 70, 760, 520);
        this.setResizable(false);

        ct = this.getContentPane();
        ct.setLayout(null);
        ct.setBackground(Color.lightGray);

        f1 = new Font("Arial", Font.BOLD, 20);
        f2 = new Font("Digital-7 Mono", Font.BOLD, 46);
        f3 = new Font("Digital-7", Font.PLAIN, 90);
        f4 = new Font("Tahoma", Font.BOLD, 36);
        f5 = new Font("Tahoma", Font.BOLD, 15);

        jLabel1 = new JLabel();
        jLabel1.setBounds(60, 5, 640, 130);
        jLabel1.setFont(f3);
        jLabel1.setForeground(new Color(120, 0, 193));
        ct.add(jLabel1);

        jLabel2 = new JLabel();
        jLabel2.setBounds(60, 130, 260, 50);
        jLabel2.setFont(f4);
        jLabel2.setForeground(new Color(120, 0, 193));
        ct.add(jLabel2);

        jLabel3 = new JLabel();
        jLabel3.setBounds(530, 30, 100, 110);
        jLabel3.setFont(f2);
        jLabel3.setForeground(new Color(120, 0, 193));
        ct.add(jLabel3);

        jLabel4 = new JLabel();
        jLabel4.setBounds(530, 30, 230, 110);
        jLabel4.setFont(f1);
        jLabel4.setForeground(new Color(120, 0, 193));
        ct.add(jLabel4);

        jLabel5 = new JLabel("Click");
        jLabel5.setBounds(160, 395, 70, 55);;
        jLabel5.setFont(f5);
        jLabel5.setForeground(new Color(255, 255, 255));
        ct.add(jLabel5);

        jLabel6 = new JLabel("Click");
        jLabel6.setBounds(260, 395, 70, 55);
        jLabel6.setFont(f5);
        jLabel6.setForeground(new Color(255, 255, 255));
        ct.add(jLabel6);

        jLabel7 = new JLabel("Click");
        jLabel7.setBounds(360, 395, 70, 55);
        jLabel7.setFont(f5);
        jLabel7.setForeground(new Color(255, 255, 255));
        ct.add(jLabel7);

        jLabel8 = new JLabel();
        jLabel8.setBounds(60, 120, 260, 50);
        jLabel8.setFont(f4);
        jLabel8.setForeground(new Color(120, 0, 193));
        ct.add(jLabel8);

        jLabel9 = new JLabel();
        jLabel9.setBounds(73, 180, 260, 50);
        jLabel9.setFont(f4);
        jLabel9.setForeground(new Color(120, 0, 193));
        ct.add(jLabel9);

        jLabel10 = new JLabel();
        jLabel10.setBounds(70, 315,450, 50);
        jLabel10.setFont(f5);
        jLabel10.setForeground(new Color(120, 0, 193));
        jLabel10.setVerticalTextPosition(btnStart.TOP);
        ct.add(jLabel10);

        btnSet = new JButton("ReSet");
        btnSet.setBounds(50, 350, 80, 95);
        btnSet.setFont(new Font("Tahoma", Font.BOLD, 16));
        ct.add(btnSet);

        btnStart = new JButton("Ok");
        btnStart.setBounds(450, 350, 80, 95);
        btnStart.setFont(new Font("Tahoma", Font.BOLD, 16));
        ct.add(btnStart);

        btnStop = new JButton("Stop");
        btnStop.setBounds(550, 350, 80, 95);
        btnStop.setFont(new Font("Tahoma", Font.BOLD, 16));
        ct.add(btnStop);
        //ช่องกรอกชั่วโมง
        tfh = new JTextField();
        tfh.setBounds(150, 350, 80, 95);
        tfh.setFont(new Font("Tahoma", Font.BOLD, 20));
        tfh.setHorizontalAlignment(JTextField.CENTER);
        ct.add(tfh);
        //ช่องกรอกนาที
        tfm = new JTextField();
        tfm.setBounds(250, 350, 80, 95);
        tfm.setFont(new Font("Tahoma", Font.BOLD, 20));
        tfm.setHorizontalAlignment(JTextField.CENTER);
        ct.add(tfm);
        //ช่องกรอกAMหรือPM
        tfam = new JTextField();
        tfam.setBounds(350, 350, 80, 95);
        tfam.setFont(new Font("Tahoma", Font.BOLD, 20));
        tfam.setHorizontalAlignment(JTextField.CENTER);
        ct.add(tfam);

        //เมื่อกดปุ่มReSet
        btnSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfm.setText("");
                tfh.setText("");
                tfam.setText("");
                flag=0;
            }
        });

        //เมื่อกดปุ่มStart
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    temp_h = Integer.parseInt(tfh.getText());
                    temp_m = Integer.parseInt(tfm.getText());
                    temp_am = tfam.getText();
                    flag = 1;
                    if (temp_h < 0 || temp_h > 12) {
                        JOptionPane.showMessageDialog(null, "Hour have only between1-12)");
                    } else if (temp_m < 0 || temp_m > 60) {
                        JOptionPane.showMessageDialog(null, "Minute have only (pick between1-60)");
                    } else if (!temp_am.equals("AM") && !temp_am.equals("PM"))  {
                        JOptionPane.showMessageDialog(null, "You can enter only PM or AM");
                    }
                } catch (NumberFormatException l){
                    JOptionPane.showMessageDialog(null, "hour and minute have integer only!");
                }
            }
        });

        //เมื่อกดปุ่มStop
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flag = 0;
                jLabel4.setText("");
                stopSound();
                mase = 0 ;
                dispose();
                WindowQuestion windowQuestion = new WindowQuestion() ;
            }
        });
    }
    //เล่นเพลง
    protected void playSound() {
        try {
            File soundFile = new File("Havestmoon1.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();

        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }
    //หยุดเพลง
    protected void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
    //ให้แสดงวันเดือนปีและเวลาปัจจุบัน และตั้งปลุก
    private void displayClock() {
        Thread clock = new Thread() {
            public void run() {
                for (; ; ) {
                    Calendar c = new GregorianCalendar();
                    int hour = c.get(Calendar.HOUR);
                    int minute = c.get(Calendar.MINUTE);
                    int day = c.get(Calendar.DAY_OF_MONTH);
                    int month = c.get(Calendar.MONTH) + 1;
                    int year = c.get(Calendar.YEAR);

                    Calendar datetime = Calendar.getInstance();
                    String am_pm = "";
                    if (datetime.get(Calendar.AM_PM) == Calendar.AM) {
                        am_pm = "AM";
                    }
                    else if (datetime.get(Calendar.AM_PM) == Calendar.PM) {
                        am_pm = "PM" ;
                    }

                    String[] strDays = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thusday",
                            "Friday", "Saturday"};
                    String wd;
                    wd = strDays[datetime.get(Calendar.DAY_OF_WEEK) - 1];

                    jLabel9.setText(day + " - " + month + " - " + year);
                    jLabel8.setText(" " + wd);
                    jLabel10.setText("                         Hour       :     Minute      :   PM or AM");

                    //ตั้งปลุก
                    jLabel1.setText(hour + " : " + minute + " " + am_pm);
                    if (temp_h == hour && temp_m == minute && temp_am.equals(am_pm) && flag == 1 ) {
                        jLabel4.setText("Wake up!!!");
                        mase = 2 ;
                    }
                    try {
                        sleep(1000);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                    Mase();
                }
            }
        };
        clock.start();
    }
    //วนเพลงซำ้ถ้าวนซำ้เกิน4รอบจะทำการรันเพลงทับกันจนรำคาญไปเรื่อยๆ
    protected void Mase(){

        for(int i = 1; i > 0; i++) {
            if (mase == 2) {
                playSound();
                try{
                    sleep(124000);
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }i++;
            }
            if(i == 4 && mase == 2){
                playSound();
                try{
                    sleep(7000);
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
    }
}