package com.hz;

import com.hz.ui.ConnectUi;
import com.hz.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.lang.instrument.Instrumentation;
import java.util.function.Consumer;

/**
 * Hello world!
 *
 */
@Slf4j
public class App 
{

    public void loadingSystemConfig(){
        ConfigUtils.JAVA_HOME = System.getProperty("java.home");
    }

    public void show(){
        JFrame jpprof = new JFrame("Jpprof");
        ConnectUi connectUi = new ConnectUi();
        jpprof.add(connectUi.getPanel());
        jpprof.pack();
        jpprof.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jpprof.setVisible(true);
    }

    public static void main(String[] args) {
        App app = new App();
        //1.加载系统配置
        app.loadingSystemConfig();
        app.show();
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Tabbed Pane Example");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        JTabbedPane tabbedPane = new JTabbedPane();
//
//        // 创建标签页的内容面板
//        JPanel panel1 = new JPanel();
//        JPanel panel2 = new JPanel();
//        JPanel panel3 = new JPanel();
//
//        // 向每个面板添加一些组件
//        panel1.add(new JLabel("This is Panel 1"));
//        panel2.add(new JLabel("This is Panel 2"));
//        panel3.add(new JLabel("This is Panel 3"));
//
//        // 将面板添加到选项卡面板中
//        tabbedPane.addTab("Tab 1", panel1);
//        tabbedPane.addTab("Tab 2", panel2);
//        tabbedPane.addTab("Tab 3", panel3);
//
//        // 将选项卡面板添加到窗口的中间位置
//        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
//
//        frame.setSize(400, 300);
//        frame.setVisible(true);
//    }
}
