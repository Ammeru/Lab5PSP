import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

class NameList extends JFrame {
    private List<String> lastNames = new ArrayList<>();
    private List<String> firstNames = new ArrayList<>();
    private List<String> middleNames = new ArrayList<>();

    private JTextField inputField;
    private JCheckBox lastNameCheckbox, firstNameCheckbox, middleNameCheckbox;
    private JButton addButton, showButton;

    public NameList() {
        setTitle("Управление списками");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inputField = new JTextField(20);
        addButton = new JButton("Добавить");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToLists();
            }
        });

        lastNameCheckbox = new JCheckBox("Фамилии");
        firstNameCheckbox = new JCheckBox("Имена");
        middleNameCheckbox = new JCheckBox("Отчества");

        showButton = new JButton("Показать");
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showResult();
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Введите ФИО:"));
        panel.add(inputField);
        panel.add(addButton);
        panel.add(new JLabel("Выберите, что показывать:"));
        panel.add(lastNameCheckbox);
        panel.add(firstNameCheckbox);
        panel.add(middleNameCheckbox);
        panel.add(showButton);

        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    private void addToLists() {
        String[] fullName = inputField.getText().split("\\s+");

        if (fullName.length == 3) {
            lastNames.add(fullName[0]);
            firstNames.add(fullName[1]);
            middleNames.add(fullName[2]);
            JOptionPane.showMessageDialog(this, "Данные добавлены успешно.");
        } else {
            JOptionPane.showMessageDialog(this, "Введите ФИО в правильном формате (Фамилия Имя Отчество).", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

        inputField.setText("");
    }

    private void showResult() {
        List<String> result = new ArrayList<>();

        if (lastNameCheckbox.isSelected()) {
            result.addAll(lastNames);
        }
        if (firstNameCheckbox.isSelected()) {
            result.addAll(firstNames);
        }
        if (middleNameCheckbox.isSelected()) {
            result.addAll(middleNames);
        }

        String resultString = String.join("\n", result);
        JOptionPane.showMessageDialog(this, resultString, "Результат", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NameList().setVisible(true);
            }
        });
    }
}
