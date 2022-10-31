import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Formula extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;


    private JTextField textFieldResult;

    private ButtonGroup radioButtons = new ButtonGroup();

    private Box hboxFormulaType = Box.createHorizontalBox();

    private int formulaId = 1;

    public double Form1(double x, double y, double z) {
        return 1; // вернем значения своего варианта
    }
    public double Form2(double x, double y, double z) {
        return 2; // так же свой вариант
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
    public Formula()
    {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH)/2,// это чтобы экран был по центру, без этого оно будет в
                (kit.getScreenSize().height - HEIGHT)/2); // левом верхнем углу

    }

}
