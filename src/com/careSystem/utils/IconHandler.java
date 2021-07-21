package com.careSystem.utils;

import javax.swing.*;
import java.awt.*;

public class IconHandler {
    public static ImageIcon resizeIcon(String path) {
        ImageIcon newIconSrc = new ImageIcon(path);
        ImageIcon newIcon = new ImageIcon(newIconSrc.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        return newIcon;
    }
}
