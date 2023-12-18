import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class RestaurantMenu extends JFrame {
    private JComboBox<String> categoryComboBox;
    private JTextArea menuTextArea;
    private JButton loadButton;
    private JButton saveButton;

    private ArrayList<String> categories;
    private ArrayList<String> menuData;

    public RestaurantMenu() {
        setTitle("Электронное меню для ресторана");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        categories = new ArrayList<>();
        menuData = new ArrayList<>();

        categoryComboBox = new JComboBox<>();
        menuTextArea = new JTextArea(10, 30);
        loadButton = new JButton("Загрузить меню");
        saveButton = new JButton("Сохранить меню");

        // Добавляем элементы категорий
        categories.add("Завтрак");
        categories.add("Обед");
        categories.add("Ужин");

        // Добавляем обработчики событий
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadMenuData();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveMenuData();
            }
        });

        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMenuData();
            }
        });

        // Добавляем элементы управления
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Категория:"));
        panel.add(categoryComboBox);
        panel.add(loadButton);
        panel.add(saveButton);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(menuTextArea), BorderLayout.CENTER);

        // Инициализация
        for (String category : categories) {
            categoryComboBox.addItem(category);
        }

        // Закрытие окна
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    private void loadMenuData() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                menuData.clear();
                menuTextArea.setText(""); // Очищаем текстовую область перед загрузкой новых данных
                String line;
                while ((line = reader.readLine()) != null) {
                    menuData.add(line);
                    menuTextArea.append(line + "\n"); // Добавляем каждую строку в текстовую область
                }
                updateMenuData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveMenuData() {
        // Обновляем menuData на основе данных в текстовой области
        updateMenuData();

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
                for (String line : menuData) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateMenuData() {
        menuData.clear();

        String[] lines = menuTextArea.getText().split("\\n");
        for (String line : lines) {
            menuData.add(line);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RestaurantMenu().setVisible(true);
            }
        });
    }
}
