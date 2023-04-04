import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random ;

public class WindowQuestion extends AlarmClock {
    private JButton btn;
    private JFrame frame ;
    private JLabel label1, label2;
    private JTextField text1;
    private Font font = new Font("Digital-7 Mono", Font.BOLD, 16);
    private Random ramdom = new Random();
    private Container c;
    private int ans;

    AlarmClock alarmClock = new AlarmClock();

    WindowQuestion() {
        w1();
        random();
    }
    //GUIทั้งหมดของหน้าต่างตอบคำถาม
    private void w1() {

        frame = new JFrame();
        frame.setTitle("Question1");
        frame.setBounds(1070, 70, 560, 320);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        c = frame.getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(45, 221, 155));

        label1 = new JLabel("จงหาคิดเลขหาคำตอบ");
        label1.setBounds(216, 5, 200, 60);
        label1.setForeground(new Color(0, 0, 0));
        label1.setFont(font);
        c.add(label1);

        label2 = new JLabel();
        label2.setBounds(234, 50, 200, 60);
        label2.setForeground(new Color(0, 0, 0));
        label2.setFont(font);
        c.add(label2);

        text1 = new JTextField();
        text1.setBounds(249, 100, 60, 45);
        text1.setFont(new Font("Tahoma", Font.BOLD, 20));
        text1.setVisible(true);
        c.add(text1);

        btn = new JButton("Send");
        btn.setBounds(240, 150, 80, 75);
        btn.setFont(new Font("Tahoma", Font.BOLD, 16));
        c.add(btn);

        //เล่นเพลง
        playSound();

        //เมื่อกดปุ่มSend
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Integer.parseInt(text1.getText()) == ans) {
                    text1.setText("collect");
                    frame.setVisible(false);
                    dispose();
                    AlarmClock alarmClock = new AlarmClock();
                    stopSound();
                }
            }
        });
    }
    //สุ่มตัวเลข
    private int random(){
        int x = ramdom.nextInt(49);
        int y = ramdom.nextInt(49);
        int z = ramdom.nextInt(10);
        ans = x+(y*z) ;
        label2.setText(x+" + "+y+" x "+z);
        return x+(y*z) ;
    }
}