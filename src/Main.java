import freshui.FreshUI;
import freshui.graphics.FButton;
import freshui.gui.Header;
import freshui.gui.input.Input;
import freshui.program.FreshProgram;
import freshui.util.FColor;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends FreshUI {

    final int SPACING = 15;
    final static Color GO_DEFAULT = new Color(74, 154, 53);
    final static Color GO_HOVER = FColor.darker(GO_DEFAULT, 0.85);
    final static Color INPUT_COLOR = new Color(155, 164, 160);
    final static Color ANSWER_COLOR = new Color(120, 182, 136);
    Input x,y,z,scale;
    FButton x2,y2,z2;
    FButton calculate;

    public void init(){
        setProgramName("Coordinate Dilator");
        setSize(400,315);
        setBackground(new Color(229, 229, 229));

        Header header = new Header(this.getWidth(), "Coordinate Dilator Tool", CENTER, this);
        add(header,0,0);
        header.setColor(new Color(83, 181, 211));

        x = new Input("X:",this);
        y = new Input("Y:",this);
        z = new Input("Z:",this);

        x.setColor(INPUT_COLOR);
        y.setColor(INPUT_COLOR);
        z.setColor(INPUT_COLOR);

        scale = new Input("Dilation Factor:",this);
        scale.setSize(230,30);
        scale.setColor(new Color(204, 176, 108));

        calculate = new FButton("Go",110,scale.getHeight());
        calculate.setColor(GO_DEFAULT);
        calculate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                calculate();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                calculate.setColor(GO_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                calculate.setColor(GO_DEFAULT);
            }
        });

        add(x, SPACING, header.getHeight()+SPACING*2);
        add(y, SPACING, x.getY()+x.getHeight()+SPACING);
        add(z, SPACING, y.getY()+y.getHeight()+SPACING);
        add(scale,SPACING, getHeight()-SPACING*3.5);
        add(calculate, getWidth()-calculate.getWidth()-SPACING,scale.getY());

        x2 = new FButton("X: ");
        y2 = new FButton("Y: ");
        z2 = new FButton("Z: ");

        x2.setColor(ANSWER_COLOR);
        y2.setColor(ANSWER_COLOR);
        z2.setColor(ANSWER_COLOR);
        x2.setSize(y.getWidth(), x.getHeight());
        y2.setSize(y.getWidth(), y.getHeight());
        z2.setSize(y.getWidth(), z.getHeight());

        add(x2, getWidth() - x2.getWidth() - SPACING*1.7, x.getY());
        add(y2, getWidth() - y2.getWidth() - SPACING*1.7, y.getY());
        add(z2, getWidth() - z2.getWidth() - SPACING*1.7, z.getY());

    }

    void calculate(){
        double X = Double.parseDouble(x.getInputText());
        double Y = Double.parseDouble(y.getInputText());
        double Z = Double.parseDouble(z.getInputText());

        double factor = 0;
        if(scale.getInputText().contains("/")){
            // get fraction details
            String fraction = scale.getInputText();
            double num = 1, den =1 , fracSlash = 0;

            int j = 0;
            StringBuffer sb = new StringBuffer();
            while(fraction.charAt(j) != '/'){
                sb.append(fraction.charAt(j++));
            }
            j++;

            StringBuffer sb2 = new StringBuffer();
            while(j<fraction.length()){
                sb2.append(fraction.charAt(j++));
            }

            num = Double.parseDouble(sb.toString());
            den = Double.parseDouble(sb2.toString());
            factor = num/den;
        } else {
            // get decimal details
            factor = Double.parseDouble(scale.getInputText());
        }

        x2.setText(""+X*factor);
        y2.setText(""+Y*factor);
        z2.setText(""+Z*factor);
    }

    public static void main(String[] args) {
        new Main().start();
    }
}