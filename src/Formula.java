import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Formula extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JLabel formulaImageLabel = new JLabel();
    private JLabel memoryTextLabel = new JLabel("MEM:");

    private JLabel memoryTextLabel1 = new JLabel("0");
    private JLabel memoryTextLabel2 = new JLabel("0");
    private JLabel memoryTextLabel3 = new JLabel("0");


    private int activeMemoryCell = 0;
    private double memoryCells[] = new double[3];
    private JLabel resultLabel = new JLabel();

    private JTextField textFieldResult;

    private ButtonGroup radioButtons = new ButtonGroup();

    private Box hboxFormulaType = Box.createHorizontalBox();

    private int formulaId = 1;

    public double Form1(double x, double y, double z) {
        drawFormula("https://chart.googleapis.com/chart?cht=tx&chl=sin(ln(y)%2Bsin(pi*y^2))(x^2%20%2B%20sin(z)%2Bexp(cos(z)))^{1/4}");
        return Math.sin(Math.log(y)+Math.sin(3.14*y*y))*Math.pow(x*x+Math.sin(z)+Math.exp(Math.cos(z)),1/4);
    }

    public double Form2(double x, double y, double z) {
        drawFormula("https://chart.googleapis.com/chart?cht=tx&chl=(cos(exp(x))%20%2Bln(1%2By)^2%2B%20sqrt(e^{cos(x)}%20%2B%20sin(pi*z)^2)%2Bsqrt(1/x)%2Bcos(y^2))^{sin(z)}");

        return Math.pow(Math.cos(Math.exp(x))+Math.log((1+y)*(1+y))+Math.sqrt(Math.exp(Math.cos(x))+Math.sin(3.14*z)*Math.sin(3.14*z))+
                Math.sqrt(1/x)+Math.cos(y*y),Math.sin(z));
    }

    // Вспомогательный метод для добавления кнопок на панель
    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Formula.this.formulaId = formulaId;
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }

    public Formula() {
        super("дюк123");

        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("Справка");
        menuBar.add(fileMenu);
        JButton nextMenu = new JButton("О программе");
        fileMenu.add(nextMenu);

        ImageIcon img= new ImageIcon("кот.png");
        nextMenu.addActionListener(new ActionListener() {
         @Override
        public void actionPerformed(ActionEvent e) {
             JOptionPane.showMessageDialog(Formula.this,
                     "Уласовец Ксения Игоревна 2 курс 8 группа",
                     "Информация", JOptionPane.INFORMATION_MESSAGE, img );
          }
   });

                setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH) / 2,// это чтобы экран был по центру, без этого оно будет в
                (kit.getScreenSize().height - HEIGHT) / 2); // левом верхнем углу



        JRadioButton rbMem1 = new JRadioButton("1");
        JRadioButton rbMem2 = new JRadioButton("2");
        JRadioButton rbMem3 = new JRadioButton("3");

        rbMem1.setSelected(true);

        rbMem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Formula.this.activeMemoryCell = 0;
            }
        });

        rbMem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Formula.this.activeMemoryCell = 1;
            }
        });

        rbMem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Formula.this.activeMemoryCell = 2;
            }
        });

        ButtonGroup memButtonGroup = new ButtonGroup();
        memButtonGroup.add(rbMem1);
        memButtonGroup.add(rbMem2);
        memButtonGroup.add(rbMem3);

        JButton buttonMemoryPlus = new JButton("M+");
        buttonMemoryPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int activeCell = Formula.this.activeMemoryCell;
                memoryCells[activeCell] += Double.parseDouble(resultLabel.getText());
                updateMemoryLabels();
            }
        });

        JButton buttonMemoryMinus = new JButton("M-");
        buttonMemoryMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int activeCell = Formula.this.activeMemoryCell;
                memoryCells[activeCell] -= Double.parseDouble(resultLabel.getText());
                updateMemoryLabels();
            }
        });

        JButton buttonMemoryClear = new JButton("MC");
        buttonMemoryClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int activeCell = Formula.this.activeMemoryCell;
                memoryCells[activeCell] = 0;
                updateMemoryLabels();

            }
        });





        addRadioButton("Формула 1", 1); // добавление радиокнопок под наши формулы
        addRadioButton("Формула 2", 2);

        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);

       // JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 6);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
     //   JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 6);       // отводим место для полей ввода значений
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
      //  JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 6);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());


        Box hboxMemory = Box.createHorizontalBox();
        hboxMemory.add(Box.createHorizontalGlue());
        hboxMemory.add(memoryTextLabel);
        hboxMemory.add(rbMem1);
        hboxMemory.add(rbMem2);
        hboxMemory.add(rbMem3);
        hboxMemory.add(buttonMemoryPlus);
        hboxMemory.add(buttonMemoryMinus);
        hboxMemory.add(buttonMemoryClear);
        hboxMemory.add(Box.createHorizontalGlue());

        Box hboxImage = Box.createHorizontalBox();
        hboxImage.add(formulaImageLabel);
        hboxImage.add((Box.createVerticalStrut(20)));

        Box hboxVariables = Box.createHorizontalBox();
      //  hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(40));
      //  hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(40));
       // hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);


        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0", 10);
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalGlue());

        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;

                    if (formulaId == 1) {
                        result = Form1(x, y, z);
                    }
                    else {
                        result = Form2(x, y, z);
                    }
                    labelForResult.setText(result.toString());

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Formula.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
                catch(ArithmeticException exception) {
                    JOptionPane.showMessageDialog(Formula.this,
                            "Ошибка в арифметике", "Деление на ноль",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");

            }
        });
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(
                BorderFactory.createLineBorder(Color.GREEN));
// Связать области воедино в компоновке BoxLayout

        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalStrut(5));
        contentBox.add(hboxImage);
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxMemory);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }

    private void updateMemoryLabels() {
        memoryTextLabel1.setText(Double.toString(memoryCells[0]));
        memoryTextLabel2.setText(Double.toString(memoryCells[1]));
        memoryTextLabel3.setText(Double.toString(memoryCells[2]));
    }

    private void drawFormula(String strUrl) {
        URL url = null;
        try {
            url = new URL(strUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        BufferedImage image = null;
        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        formulaImageLabel.setIcon(new ImageIcon(image));
    }
}
