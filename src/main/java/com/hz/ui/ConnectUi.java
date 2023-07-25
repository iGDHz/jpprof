package com.hz.ui;

import com.hz.utils.CommandUtils;
import com.hz.utils.ProcessUtils;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
@Slf4j
public class ConnectUi {
    private JPanel parentPanel;
    private JTable threadTable;
    private JRadioButton localRadioButton;
    private JRadioButton remoteRadioButton;
    private JTextField token;
    private JButton connectButton;
    private JButton exitButton;
    private JTextField userName;
    private JTextField remoteProcess;

    private boolean isRemote;
    public ConnectUi() {
        isRemote = false;
        localRadioButton.setSelected(true);
        loadingThread();
        connectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        remoteRadioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                remoteRadioButton.setSelected(true);
                localRadioButton.setSelected(false);
                isRemote = true;
            }
        });
        localRadioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                remoteRadioButton.setSelected(false);
                localRadioButton.setSelected(true);
                isRemote = false;
            }
        });
    }

    /**
     * 加载当前JVM运行的所有线程信息
     */
    public void loadingThread(){
        String jps = ProcessUtils.findJps();
        List<String> processList;
        try {
             processList = CommandUtils.runNative(new String[]{jps, "-l"});
        } catch (IOException e) {
            log.error("Loading Thread Error!");
            throw new RuntimeException(e);
        }
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("pid");
        tableModel.addColumn("线程");
        threadTable.setModel(tableModel);
        for (String process : processList) {
            String[] split = process.split("\\s+");
            tableModel.addRow(split);
        }
    }
    public JPanel getPanel(){
        return parentPanel;
    }
}
