package chapter1.gameengine.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class JFrameContainer extends JFrame
{
    public JFrameContainer(String title, int width, int height)
    {
        setTitle(title);
        setPreferredSize(new Dimension(width,height));
    }

    public void init() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        requestFocus();
        setVisible(true);
        setResizable(false);

        Graphics g = getGraphics();
        Graphics2D g2 = (Graphics2D)g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        RenderingHints rh2 = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(rh);
        g2.setRenderingHints(rh2);
    }

    public Graphics getGraphics()
    {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null)
        {
            createBufferStrategy(3);
            bs = getBufferStrategy();
        }
        return bs.getDrawGraphics();
    }

    public void disposeGraphics()
    {
        BufferStrategy bs = getBufferStrategy();
        if (bs!=null)
        {
            bs.getDrawGraphics().dispose();
            bs.show();
        }


    }

}