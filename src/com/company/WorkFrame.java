package com.company;

import com.company.models.Client;
import com.company.models.Request;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WorkFrame {
    int width,height;
    ArrayList<Client> clientArray;
    ArrayList<Request> requestArray;
    String[][] dataClients;
    JTable tableClients;
    MyImage myImage;
    JFrame frame;
    JPanel panel;
    JLabel infoLabel;
    String infoColor="<html><font color=\"red\" size=\"6\">";
    WorkFrame(int width,int height){
        this.width=width;
        this.height=height;
        clientArray=new ArrayList<>();
        requestArray=new ArrayList<>();
        clientArray.add(new Client(0,"Ivanov I.I.","lada","10"));
        clientArray.add(new Client(1,"Petrov I.I.","tesla","T"));
        requestArray.add(new Request(0,"04.04.2022","Сломалось колесо",1000,"10.04.2022",0));
        requestArray.add(new Request(1,"04.04.2021","Сломалось окно",11000,"10.04.2021",0));
        myImage=new MyImage(width,height,"src/com/company/Images/back.jpg");
        infoLabel=new JLabel();
    }
    public void start(){

        frame=new JFrame("CarServiceDatabase");
        frame.setBounds(0,0,width,height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

        panel=new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,0,width,height);

        JPanel tablePanel=new JPanel();
        tablePanel.setBounds(width*1/2,height/10,width*7/16,height*9/10);

        frame.add(tablePanel);
        frame.add(panel);

        infoLabel.setBounds(40,40,400,400);
        panel.add(infoLabel);

        final DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("FIO");
        tableModel.addColumn("Model");
        tableModel.addColumn("Brand");
        tableModel.addTableModelListener(e -> {
            int row=e.getFirstRow();
            clientArray.get(row).setId(Integer.parseInt(tableModel.getValueAt(row,0).toString()));
            clientArray.get(row).setFIO(tableModel.getValueAt(row,1).toString());
            clientArray.get(row).setBrand(tableModel.getValueAt(row,2).toString());
            clientArray.get(row).setModel(tableModel.getValueAt(row,3).toString());
            infoUpdate(row);
        });
        for(int i=0;i<clientArray.size();i++){
            tableModel.insertRow(tableModel.getRowCount(), new Object[] {
                    clientArray.get(i).getId(),
                    clientArray.get(i).getFIO(),
                    clientArray.get(i).getModel(),
                    clientArray.get(i).getBrand()
            });
        }
        tableClients = new JTable(tableModel);
        JScrollPane scrollPane=new JScrollPane(tableClients);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        tablePanel.add(scrollPane);

        JButton buttonAddClient=new JButton("Добавит чела");
        buttonAddClient.setBounds(100,400,100,30);
        panel.add(buttonAddClient);
        buttonAddClient.addActionListener(e -> {
            createClient();
            tableModel.insertRow(tableModel.getRowCount(), new Object[] {
                   clientArray.get(clientArray.size()-1).getId(),
                   clientArray.get(clientArray.size()-1).getFIO(),
                   clientArray.get(clientArray.size()-1).getModel(),
                   clientArray.get(clientArray.size()-1).getBrand()
            });
        });
        JButton buttonDeleteClient=new JButton("Удалить чела");
        buttonDeleteClient.setBounds(100,500,100,30);
        panel.add(buttonDeleteClient);
        buttonDeleteClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteClient();
                tableModel.removeRow(tableModel.getRowCount()-1);
            }
        });

       Thread infoUpdater=new Thread(()->{
           int prev=0,next=0;
           while(true){
               try {
                   next=tableClients.getSelectedRow();
               if(next!=prev){
                   infoUpdate(next);
                   prev=next;
                   try {
                       java.lang.Thread.sleep(500);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               } catch (ArrayIndexOutOfBoundsException e) {}
       }});
       infoUpdater.start();

        myImage.setBounds(0,0,width,height);
        panel.add(myImage);

        frame.setVisible(true);
    }
    public void createClient(){
        Client tempClient=new Client();
        clientArray.add(tempClient);
    }
    public void deleteClient(){
        clientArray.remove(0);
    }
    public void infoUpdate(int n){
        Client client=clientArray.get(n);
        infoLabel.setText(infoColor+n+":"+client.getId());
        infoLabel.setText(infoLabel.getText()+ client.getFIO());
        infoLabel.setText(infoLabel.getText()+" "+ client.getBrand());
        infoLabel.setText(infoLabel.getText()+" "+client.getModel());
    }
}

