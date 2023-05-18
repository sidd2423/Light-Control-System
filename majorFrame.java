

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class majorFrame extends JFrame implements ActionListener {
    public Main main;

    majorFrame() throws IOException, InterruptedException {

        main = new Main();


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        JRadioButton Summerbutton = new JRadioButton("summer");
        JRadioButton Winterbutton = new JRadioButton("winter");

        Summerbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                try {
                    main.isSummer = true;
                    main.checkWork();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        Winterbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    main.isSummer = false;//
                    main.checkWork();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        this.add(Summerbutton);
        this.add(Winterbutton);

        ButtonGroup group = new ButtonGroup();
        group.add(Summerbutton);
        group.add(Winterbutton);


        Winterbutton.addActionListener(this);
        Summerbutton.addActionListener(this);


        this.add(Winterbutton);
        this.add(Summerbutton);

        this.pack();
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
