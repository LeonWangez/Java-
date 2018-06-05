import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
/**
 *
 * @Description: it will display an arithmetic question.
 *      When answer the question , and program will be told if the answer is correct or not.
 * @auther: Gao Jianyu
 * @date: 2018/6/4
 */
public class MathTeacher extends JFrame implements ActionListener {
    JTextField jTextField;
    JLabel jl_question, jl_answer, jl_record;
    JButton jb_check;
    int flag;
    // record the total number and correct number of click
    int count_tot=0, count_corr=0;
    int arg1;
    int arg2;

    public static void main(String[] args) {
        MathTeacher m = new MathTeacher();
        m.Gui();
    }
    /**
     *
     * @Description: The Initialization of GUI interface
     *
     * @param:  null
     * @auther: Gao Jianyu
     * @date: 2018/6/4
     * @return void
     */
    //The method of initializing the GUI interface
    public void Gui() {
        //Initialize the GUI component
        JPanel first_line = new JPanel();
        first_line.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 33));
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
        sec_line.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 28));
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

        jl_record = new JLabel(count_corr+" correct out of "+ count_tot, JLabel.CENTER);


        JFrame jFrame = new JFrame("MathTeacher");
        jFrame.setLayout(new BorderLayout());
        jFrame.add(jp_whole, BorderLayout.CENTER);
        jFrame.add(jl_record,BorderLayout.SOUTH);
        jFrame.setSize(400, 200);
        jFrame.setLocation(400, 200);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
    /**
     *
     * @Description: Get the random question
     *
     * @param: null
     * @auther: Gao Jianyu
     * @date: 2018/6/4
     * @return void
     */
    public String getQuestion() {
        Random random = new Random();
        int arg1 = random.nextInt(10) + 1;
        int arg2 = random.nextInt(10) + 1;
        //pass the value to the global variety
        this.arg1 = arg1;
        this.arg2 = arg2;
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
                    this.arg1 = arg1;
                }
                return arg1 + "/" + arg2;
        }
        return null;
    }
    /**
     *
     * @Description: get the answer of the random question
     *
     * @param:
     * @auther: Gao Jianyu
     * @date: 2018/6/4
     * @return void
     */
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
            if (!jTextField.getText().isEmpty()) {
                count_tot++;
                if (Integer.parseInt(jTextField.getText()) == (getAnswer())) {
                    jl_answer.setText("Correct");
                    jl_question.setText("Question " + getQuestion());
                    count_corr++;
                } else
                    jl_answer.setText("Wrong");
                jTextField.setText("");
                jl_record.setText(count_corr+" correct out of "+ count_tot);
            } else {
//                jl_record.setText(count_corr+" correct out of "+ count_tot);

            }

        }
    }
}
