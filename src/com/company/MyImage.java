package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyImage extends JComponent {
    BufferedImage img;
    int width,height;
    MyImage(int width,int height,String source){
        this.width=width;
        this.height=height;
        try {
            img=ImageIO.read(new File(source));
            Graphics2D g2d =img.createGraphics();
            g2d.drawImage(img, 0, 0, width, height, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img,0,0,null);
    }
}
