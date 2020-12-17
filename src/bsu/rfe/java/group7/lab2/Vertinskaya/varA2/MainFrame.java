package bsu.rfe.java.group7.lab2.Vertinskaya.varA2;
//lab2 varA2
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 340;
    public double SUM = 0.0;

    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult;

    private ButtonGroup radioButtons = new ButtonGroup();
    private Box hBoxFormulaType = Box.createHorizontalBox();
    private int FormulaID = 1;

    public double calculate1 (double x, double y, double z) {
        if (x < 0) {
            JOptionPane.showMessageDialog(MainFrame.this,
                    "X не может быть отрицательным", " " +
                            "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
            return 0.0;
        }
        return (1/Math.sqrt(x) + Math.cos(Math.exp(y)) + Math.cos(Math.pow(z, 2))) /
                (Math.pow((Math.log(Math.pow((1+z), 2) + Math.sqrt(Math.exp(Math.cos(y)) + Math.pow(Math.sin(Math.PI * x), 2)))),1/3));
    }
    public double calculate2 (double x, double y, double z) {
        if (z < 0) {
            JOptionPane.showMessageDialog(MainFrame.this,
                    "Z не может быть отрицательным", " " +
                            "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
            return 0.0;
        }
        if (z == 1) {
            JOptionPane.showMessageDialog(MainFrame.this,
                    "Знаменатель дроби не должен равняться нулю", " " +
                            "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
            return 0.0;
        }
        return Math.pow((y + Math.pow(x, 3)), 1 / z) / Math.log(z);
    }
    private void addRadioButton(String buttonName, final int FormulaID){
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.FormulaID = FormulaID;
            }
        });
        radioButtons.add(button);
        hBoxFormulaType.add(button);
    }
    public MainFrame() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();

        setLocation((kit.getScreenSize().width - WIDTH) / 2,
                (kit.getScreenSize().height - HEIGHT / 2));
        hBoxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
        hBoxFormulaType.add(Box.createHorizontalGlue());
        hBoxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        JLabel labelForX = new JLabel("X: ");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel(("Y: "));
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel(("Z: "));
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        Box hBoxVariables = Box.createHorizontalBox();
        //hBoxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));
        //hBoxVariables.add(Box.createHorizontalGlue());
        hBoxVariables.add(labelForX);
        hBoxVariables.add(Box.createHorizontalStrut(7));
        hBoxVariables.add(textFieldX);
        hBoxVariables.add(Box.createHorizontalGlue());
        hBoxVariables.add(Box.createHorizontalStrut(7));
        hBoxVariables.add(labelForY);
        hBoxVariables.add(Box.createHorizontalStrut(7));
        hBoxVariables.add(textFieldY);
        //hBoxVariables.add(Box.createHorizontalStrut(7));
        hBoxVariables.add(Box.createHorizontalGlue());
        hBoxVariables.add(labelForZ);
        hBoxVariables.add(Box.createHorizontalStrut(7));
        hBoxVariables.add(textFieldZ);
        //hBoxVariables.add(Box.createHorizontalGlue());
        JButton buttonCalc = new JButton("Вычислить");
        JLabel labelForResult = new JLabel("Результат");
        textFieldResult = new JTextField("0", 15);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
        Box hBoxResult = Box.createHorizontalBox();
        hBoxResult.add(Box.createHorizontalGlue());
        hBoxResult.add(labelForResult);
        hBoxResult.add(Box.createHorizontalStrut(10));
        hBoxResult.add(textFieldResult);
        hBoxResult.add(Box.createHorizontalGlue());
        hBoxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));


        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (FormulaID == 1)
                        result = calculate1(x, y, z);
                    else
                        result = calculate2(x, y, z);
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonSum = new JButton("M+");
        buttonSum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());

                    if (FormulaID==1)
                        SUM += calculate1(x, y, z);
                    else
                        SUM += calculate2(x, y, z);
                    textFieldResult.setText(Double.toString(SUM));
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonClearSum = new JButton("MC");
        buttonClearSum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                SUM=0.0;
                textFieldResult.setText("0");
            }});

        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });
        Box hBoxButtons = Box.createHorizontalBox();
        hBoxButtons.add(Box.createHorizontalGlue());
        hBoxButtons.add(buttonCalc);
        hBoxButtons.add(Box.createHorizontalStrut(15));
        hBoxButtons.add(buttonReset);
        hBoxButtons.add(Box.createHorizontalStrut(15));
        hBoxButtons.add(buttonSum);
        hBoxButtons.add(Box.createHorizontalStrut(15));
        hBoxButtons.add(buttonClearSum);
        hBoxButtons.add(Box.createHorizontalGlue());
        hBoxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hBoxFormulaType);
        contentBox.add(hBoxVariables);
        contentBox.add(hBoxButtons);
        contentBox.add(hBoxResult);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        System.out.println("omg it works");
    }
}






