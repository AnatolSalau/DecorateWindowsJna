// Copyright 2020 Kalkidan Betre Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.example.decoratewindowsjna.demo;



import com.example.decoratewindowsjna.jna.ui.customjframe.CustomJFrame;
import com.example.decoratewindowsjna.jna.ui.customjframe.WindowFrameType;
import com.example.decoratewindowsjna.jna.ui.theme.DarkTheme;
import com.example.decoratewindowsjna.jna.ui.theme.Theme;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DemoApp {
   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            try {
               UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
               e.printStackTrace();
            }

            final Theme darkTheme = new DarkTheme();
            CustomJFrame frame = new CustomJFrame(darkTheme ,"Custom Decorated Window", WindowFrameType.NORMAL);
            frame.setMinimumSize(new Dimension(600, 400));
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            JFXPanel jfxPanel = new JFXPanel();
            frame.add(jfxPanel);

            Platform.runLater(() -> {
               WebView webView = new WebView();
               jfxPanel.setScene(new Scene(webView));
               webView.getEngine().load("http://www.stackoverflow.com/");
            });


            JMenuBar jMenuBar = new JMenuBar();
            JMenu fileMenu = new JMenu("File");
            JMenu editMenu = new JMenu("Edit");
            JMenu viewMenu = new JMenu("View");

            JMenuItem openMenu = new JMenuItem("Open");
            JMenuItem closeMenu = new JMenuItem("Close");
            fileMenu.add(openMenu);
            fileMenu.add(closeMenu);

            JMenuItem copyMenu = new JMenuItem("Copy");
            editMenu.add(copyMenu);

            JMenuItem toolsMenu = new JMenuItem("Tools");
            viewMenu.add(toolsMenu);

            jMenuBar.add(fileMenu);
            jMenuBar.add(editMenu);
            jMenuBar.add(viewMenu);
            frame.addUserControlsToTitleBar(jMenuBar);

            ImageIcon imageIcon = new ImageIcon("erythrocytes.png");
            frame.setIcon(imageIcon.getImage());

            frame.addJFrameCloseEventAdapter(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent e) {
                  //int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the app ?");
                  int response = 0;
                  if (response == JOptionPane.OK_OPTION) {
                         System.exit(0);
                  }
               }
            });


         }
      });
   }
}

