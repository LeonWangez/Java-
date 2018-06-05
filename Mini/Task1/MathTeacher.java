import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
/**
 *
 * it will display an arithmetic question.
 *      When answer the question , and program will be told if the answer is correct or not.
 */
public class MathTeacher extends JFrame implements ActionListener {
    JTextField jTextField;
    JLabel jl_question, jl_answer;
    JButton jb_check;
    int flag;

    int arg1 ;
    int arg2 ;
    public static void main(String[] args) {
        MathTeacher m = new MathTeacher();
        m.Gui();
    }

    /**
     *
     * The Initialization of GUI interface
     */
//The method of initializing the GUI interface
    public void Gui() {
        //Initialize the GUI component
        JPanel first_line = new JPanel();
        first_line.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 33));
        jTextField = new JTextField(13);
        //call the method getQuestion to get the question
        jl_question = new JLabel("Question "+getQuestion());
        first_line.add(jTextField);


        JPanel sec_line = new JPanel();
        sec_line.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 28));
        jb_check = new JButton("Press for answer");
        jb_check.addActionListener(this);
        jl_answer = new JLabel();
        sec_line.add(jb_check);

        JPanel jp_whole = new JPanel();
        jp_whole.setLayout(new GridLayout(2,2));
        jp_whole.add(first_line);
        jp_whole.add(jl_question);
        jp_whole.add(sec_line);
        jp_whole.add(jl_answer);

        JFrame jFrame = new JFrame("MathTeacher");
        jFrame.setLayout(new BorderLayout());
        jFrame.add(jp_whole, BorderLayout.CENTER);
        jFrame.setSize(400, 200);
        jFrame.setLocation(400, 200);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    /**
     *
     * @return the expression of random question generated
     */
    public String getQuestion() {
        Random random = new Random();
        int arg1 = random.nextInt(10) + 1;
        int arg2 = random.nextInt(10) + 1;
        //pass the value to the global variety
        this.arg1=arg1;
        this.arg2=arg2;
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
                return arg1 + "/" + arg2;
        }
        return null;
    }

    /**
     *
     * @return the answer of question
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
                if (Integer.parseInt(jTextField.getText()) == (getAnswer())) {
                    jl_answer.setText("Correct");
                    jTextField.setText("");
                    jl_question.setText("Question "+getQuestion());
                } else
                    jl_answer.setText("Wrong");
                    jTextField.setText("");
            } else {

            }

        }
    }
}
