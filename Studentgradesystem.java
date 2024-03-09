import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Studentgradesystem extends JFrame implements ActionListener {
    private JLabel[] subjectLabels;
    private JTextField[] subjectFields;
    private JButton calculateButton;
    private JLabel totalMarksLabel, averagePercentageLabel, gradeLabel;

    public Studentgradesystem() {
        setTitle("Grade Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2, 10, 10));

        int numSubjects = 5; // Change this according to the number of subjects

        subjectLabels = new JLabel[numSubjects];
        subjectFields = new JTextField[numSubjects];

        for (int i = 0; i < numSubjects; i++) {
            subjectLabels[i] = new JLabel("Subject " + (i + 1) + " Marks:");
            subjectFields[i] = new JTextField();
            add(subjectLabels[i]);
            add(subjectFields[i]);
        }

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);
        add(calculateButton);

        totalMarksLabel = new JLabel("Total Marks: ");
        add(totalMarksLabel);

        averagePercentageLabel = new JLabel("Average Percentage: ");
        add(averagePercentageLabel);

        gradeLabel = new JLabel("Grade: ");
        add(gradeLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            int totalMarks = 0;
            int numSubjects = subjectFields.length;

            for (int i = 0; i < numSubjects; i++) {
                try {
                    totalMarks += Integer.parseInt(subjectFields[i].getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter valid marks (numbers only).");
                    return;
                }
            }

            double averagePercentage = (double) totalMarks / numSubjects;
            totalMarksLabel.setText("Total Marks: " + totalMarks);
            averagePercentageLabel.setText("Average Percentage: " + String.format("%.2f", averagePercentage) + "%");

            String grade = calculateGrade(averagePercentage);
            gradeLabel.setText("Grade: " + grade);
        }
    }

    private String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return "A+";
        } else if (averagePercentage >= 80) {
            return "A";
        } else if (averagePercentage >= 70) {
            return "B";
        } else if (averagePercentage >= 60) {
            return "C";
        } else if (averagePercentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        new Studentgradesystem();
    }
}
