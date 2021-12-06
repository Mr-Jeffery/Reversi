package components;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//Event handling:
//a mouse is clicked
//the mouse is moved
//a character is entered
//Event source/object/Listener事件监听器：处理这个事情的过程

public abstract class BasicComponent extends JComponent {
    public BasicComponent() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                onMouseClicked();
            }
        });
    }

    public abstract void onMouseClicked();
}
