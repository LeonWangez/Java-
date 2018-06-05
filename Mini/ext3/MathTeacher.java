import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
/**
 *the GUI should also have a “calculator key pad” with the digits 0-9.
 *   The user now has a choice of entering the answer via the keyboard or via the GUI.
 */
public class MathTeacher extends JFrame implements ActionListener {
    static JTextField jTextField;
    static JLabel jl_question;
    static JLabel jl_answer;
    static JLabel jl_record;
    JButton jb_check, jb_pad;
    static JFrame jFrame_pad;
    static int flag;
    // record the total number and correct number of click
    static int count_tot = 0, count_corr = 0;
    static int arg1;
    static int arg2;
    //    String input = "";
    static boolean judge = true;

    public static void main(String[] args) {
        MathTeacher m = new MathTeacher();
        m.Gui();
        Pad_Gui p = new Pad_Gui();
//        p.pad_gui();
    }
/**
 *
 *The Initialization of GUI interface
 */
    //The method of initializing the GUI interface
    public void Gui() {
        //Initialize the GUI component
        JPanel first_line = new JPanel();
        first_line.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 23));
        jTextField = new JTextField(13);
        //call the method getQuestion to get the question
        jl_question = new JLabel("Question " + getQuestion());
        first_line.add(jTextField);

        // keyboard listener, if the input is non-numeric, consume it
        jTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9 || keyChar == '-') {
                } else {
                    e.consume();
                }
            }
        });

        JPanel sec_line = new JPanel();
        sec_line.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        jb_check = new JButton("Press for answer");
        jb_check.addActionListener(this);
        jl_answer = new JLabel();
        sec_line.add(jb_check);

        JPanel jp_whole = new JPanel();
        jp_whole.setLayout(new GridLayout(2, 2));
        jp_whole.add(first_line);
        jp_whole.add(jl_question);
        jp_whole.add(sec_line);
        jp_whole.add(jl_answer);

        //The global variety to store the count number
        jl_record = new JLabel(count_corr + " correct out of " + count_tot, JLabel.CENTER);
        jb_pad = new JButton("calculator key pad");
        jb_pad.setSize(10, 10);
        jb_pad.addActionListener(this);
        JPanel jp_fun = new JPanel();
        jp_fun.setLayout(new FlowLayout());
        jp_fun.add(jl_record);
        jp_fun.add(jb_pad);

        JFrame jFrame = new JFrame("MathTeacher");
        jFrame.setLayout(new BorderLayout());
        jFrame.add(jp_whole, BorderLayout.CENTER);
        jFrame.add(jp_fun, BorderLayout.SOUTH);
        jFrame.setSize(400, 200);
        jFrame.setLocation(400, 200);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    /**
     *
     * @return the expression of random question generated
     */
    //get the question randomly
    public String getQuestion() {
        Random random = new Random();
        int arg1 = random.nextInt(10) + 1;
        int arg2 = random.nextInt(10) + 1;
        //pass the value to the global variety
        MathTeacher.arg1 = arg1;
        MathTeacher.arg2 = arg2;
        switch (random.nextInt(3)) {
            case 0:
                flag = 0;
                return arg1 + "+" + arg2;
            case 1:
                flag = 1;
                return arg1 + "-" + arg2;
            case 2:
                flag = 2;
                return arg1 + "*" + arg2;
            case 3:
                flag = 3;
                //When the arg1 is smaller then arg2, we can use (x*y)/y to avoid this situation
                if (arg1 < arg2) {
                    arg1 = arg1 * arg2;
                    MathTeacher.arg1 = arg1;
                }
                return arg1 + "/" + arg2;
        }
        return null;
    }

    /**
     *
     * @return the answer of question
     */
   // get the answer of  question
    public int getAnswer() {
        switch (flag) {
            case 0:
                return arg1 + arg2;
            case 1:
                flag = 1;
                return arg1 - arg2;
            case 2:
                flag = 2;
                return arg1 * arg2;
            case 3:
                flag = 3;
                return arg1 / arg2;
        }
        return 101;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == jb_check) {
            //when input nothing, there are no response
            if (!jTextField.getText().isEmpty()) {
                count_tot++;
                if (Integer.parseInt(jTextField.getText()) == (getAnswer())) {
                    jl_answer.setText("Correct");
                    jl_question.setText("Question " + getQuestion());
                    count_corr++;
                } else
                    jl_answer.setText("Wrong");
                jTextField.setText("");
                jl_record.setText(count_corr + " correct out of " + count_tot);
            }
        }
        Pad_Gui p = new Pad_Gui();
        if (e.getSource() == jb_pad) {
            if (!judge) {
                jFrame_pad.dispose();
                judge=!judge;
            }else {
                p.pad_gui();
                judge=!judge;
            }
        }

    }
}

/**
 *The GUI interface of calculator key pad ,
 * The user now has a choice of entering the answer via the keyboard or via the GUI.
 */
class Pad_Gui extends JFrame implements ActionListener {

    private JTextField jt_pad;
    //    private JButton[] jb_num;
    private JButton jb_num[] = new JButton[10];
    private String input = "";
    private JButton jb_sign;
    private JButton jb_clear;
    private JButton jb_press;
    MathTeacher m = new MathTeacher();

    /**
     *
     *The Initialization of calculator key pad GUI interface
     */
    public void pad_gui() {
        jt_pad = new JTextField(24);
        jt_pad.setEditable(false);
        JPanel jp_text = new JPanel();
        jp_text.add(jt_pad);


        String[] num = {"1", "2", "3", "4", "5", "6", "7", "8", "9","0"};
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(4, 3));
        for (int i = 0; i < num.length; i++) {
            jb_num[i] = new JButton(num[i]);
            jPanel.add(jb_num[i]);
            jb_num[i].addActionListener(this);
        }
        jb_sign = new JButton("-");
        jPanel.add(jb_sign);
        jb_sign.addActionListener(this);

        jb_clear = new JButton("C");
        jPanel.add(jb_clear);
        jb_clear.addActionListener(this);

        JPanel jp_press = new JPanel();
        jb_press = new JButton("Press for Answer");
        jp_press.add(jb_press);
        jb_press.addActionListener(this);


        MathTeacher.jFrame_pad = new JFrame("Calculator key pad");
        MathTeacher.jFrame_pad.setLayout(new BorderLayout());
        MathTeacher.jFrame_pad.add(jp_text, BorderLayout.NORTH);
        MathTeacher.jFrame_pad.add(jPanel, BorderLayout.CENTER);
        MathTeacher.jFrame_pad.add(jb_press, BorderLayout.SOUTH);
        MathTeacher.jFrame_pad.setSize(250, 200);
        MathTeacher.jFrame_pad.setLocation(700, 400);
        MathTeacher.jFrame_pad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MathTeacher.jFrame_pad.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (e.getSource() == jb_press) {
            if (!jt_pad.getText().isEmpty()) {
                MathTeacher.count_tot++;
                jt_pad.setText(input);
                if (input.equals(Integer.toString(m.getAnswer()))) {
                    MathTeacher.jl_answer.setText("Correct");
                    MathTeacher.jl_question.setText("Question " + m.getQuestion());
                    MathTeacher.count_corr++;
                } else {
                    MathTeacher.jl_answer.setText("Wrong");
                }
                MathTeacher.jTextField.setText("");
                jt_pad.setText("");
                input = "";
                MathTeacher.jl_record.setText(MathTeacher.count_corr + " correct out of " + MathTeacher.count_tot);
            }
        } else if (e.getSource() == jb_sign) {
            input = "-" + input;
            jt_pad.setText(input);
        } else if (e.getSource() == jb_clear) {
            input = "";
            jt_pad.setText(input);
        } else {
            input += actionCommand;
            jt_pad.setText(input);
        }

    }
}