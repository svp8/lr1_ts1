package com.company;

import com.company.models.Client;
import com.company.models.Request;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WorkFrame {
    int width,height;
    ArrayList<Client> clientArray;
    ArrayList<Request> requestArray;
    JTable tableClients;
    JTable tableRequests;
    MyImage myImage;
    JFrame frame;
    JPanel panel;
    JLabel infoLabel;
    JLabel infoLabel1;
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
        infoLabel1=new JLabel();

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
        tablePanel.setBounds(width*1/2,height/10,width*7/16,height*9/20);

        JPanel tablePanelR=new JPanel();
        tablePanelR.setBounds(width*1/2,height/10+height*9/20+10,width*7/16,height*5/10);

        frame.add(tablePanel);
        frame.add(tablePanelR);
        frame.add(panel);

        infoLabel.setBounds(40,40,400,400);
        panel.add(infoLabel);

        infoLabel1.setBounds(40,400,400,400);
        panel.add(infoLabel1);

        final DefaultTableModel tableModel1 = new DefaultTableModel();
        tableModel1.addColumn("ID");
        tableModel1.addColumn("Reason");
        tableModel1.addColumn("Price");
        tableModel1.addColumn("Date of request");
        tableModel1.addColumn("Date of transfer");
        tableModel1.addColumn("Client id");
        tableModel1.addTableModelListener(e -> {
            int row=e.getFirstRow();
            requestArray.get(row).setId(Integer.parseInt(tableModel1.getValueAt(row,0).toString()));
            requestArray.get(row).setReason(tableModel1.getValueAt(row,1).toString());
            requestArray.get(row).setPrice(Integer.parseInt(tableModel1.getValueAt(row,2).toString()));
            requestArray.get(row).setDateOfRequest(tableModel1.getValueAt(row,3).toString());
            requestArray.get(row).setDateOfTransfer(tableModel1.getValueAt(row,4).toString());
            requestArray.get(row).setClientId(Integer.parseInt(tableModel1.getValueAt(row,5).toString()));
            infoUpdate1(row);
        });
        for(int i=0;i<requestArray.size();i++){
            tableModel1.insertRow(tableModel1.getRowCount(), new Object[] {
                   requestArray.get(i).getId(),
                    requestArray.get(i).getReason(),
                    requestArray.get(i).getPrice(),
                    requestArray.get(i).getDateOfRequest(),
                    requestArray.get(i).getDateOfTransfer(),
                    requestArray.get(i).getClientId()
            });
        }


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

        tableRequests = new JTable(tableModel1);
        JScrollPane scrollPane1=new JScrollPane(tableRequests);
        scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        tablePanelR.add(scrollPane1);

        JButton buttonAddClient=new JButton("Добавить клиента");
        buttonAddClient.setBounds(100,400,150,30);
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
        JButton buttonAddRequest=new JButton("Добавить запрос");
        buttonAddRequest.setBounds(250,400,150,30);
        panel.add(buttonAddRequest);
        buttonAddRequest.addActionListener(e -> {
            createRequest();
            tableModel1.insertRow(tableModel1.getRowCount(), new Object[] {
                    requestArray.get(requestArray.size()-1).getId(),
                    requestArray.get(requestArray.size()-1).getReason(),
                    requestArray.get(requestArray.size()-1).getPrice(),
                    requestArray.get(requestArray.size()-1).getDateOfRequest(),
                    requestArray.get(requestArray.size()-1).getDateOfTransfer(),
                    requestArray.get(requestArray.size()-1).getClientId()
            });
        });
        JButton buttonDeleteClient=new JButton("Удалить клиента");
        buttonDeleteClient.setBounds(100,500,150,30);
        panel.add(buttonDeleteClient);
        buttonDeleteClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteClient();
                tableModel.removeRow(tableClients.getSelectedRow());
            }
        });
        JButton buttonDeleteRequest=new JButton("Удалить запрос");
        buttonDeleteRequest.setBounds(250,500,150,30);
        panel.add(buttonDeleteRequest);
        buttonDeleteRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRequest();
                tableModel1.removeRow(tableRequests.getSelectedRow());
            }
        });
       Thread infoUpdater=new Thread(new Runnable(){
           public void run(){
               int prev=0,next=0;
               while(true){
                   try {
                       next=tableClients.getSelectedRow();
                       if(next!=-1){
                           if(next!=prev){
                               infoUpdate(next);
                               prev=next;
//                               try {
//                                   java.lang.Thread.sleep(500);
//                               } catch (InterruptedException e) {
//                                   e.printStackTrace();
//                               }
                           }
                       }
                       try {
                           java.lang.Thread.sleep(500);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }


                   } catch (ArrayIndexOutOfBoundsException e) {}
               }}
           }
           );
        Thread infoUpdater1=new Thread(new Runnable(){
            public void run(){
                int prev=0,next=0;
                while(true){
                    try {
                        next=tableRequests.getSelectedRow();
                        if(next!=-1){
                            if(next!=prev){
                                infoUpdate1(next);
                                prev=next;
//                               try {
//                                   java.lang.Thread.sleep(500);
//                               } catch (InterruptedException e) {
//                                   e.printStackTrace();
//                               }
                            }
                        }
                        try {
                            java.lang.Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                    } catch (ArrayIndexOutOfBoundsException e) {}
                }}
        }
        );
        infoUpdater1.start();
       infoUpdater.start();

        myImage.setBounds(0,0,width,height);
        panel.add(myImage);

        frame.setVisible(true);
    }
    public void createClient(){
        Client tempClient=new Client();
        clientArray.add(tempClient);
    }
    public void createRequest(){
        Request tempRequest=new Request();
        requestArray.add(tempRequest);
    }
    public void deleteClient(){
        clientArray.remove(tableClients.getSelectedRow());
    }
    public void deleteRequest(){
        System.out.println(tableRequests.getSelectedRow()+" test123");
        requestArray.remove(tableRequests.getSelectedRow());
    }
    public void infoUpdate(int n){
        System.out.println(n);
        Client client=clientArray.get(n);

        infoLabel.setText(infoColor+n+":"+client.getId());
        infoLabel.setText(infoLabel.getText()+ client.getFIO());
        infoLabel.setText(infoLabel.getText()+" "+ client.getBrand());
        infoLabel.setText(infoLabel.getText()+" "+client.getModel());
    }
    public void infoUpdate1(int n){
        System.out.println(n);
       Request request =requestArray.get(n);

        infoLabel1.setText(infoColor+n+":"+request .getId());
        infoLabel1.setText(infoLabel1.getText()+ request .getReason());
        infoLabel1.setText(infoLabel1.getText()+" "+ request .getPrice());
        infoLabel1.setText(infoLabel1.getText()+" "+request .getDateOfRequest());
        infoLabel1.setText(infoLabel1.getText()+" "+request .getDateOfTransfer());
        infoLabel1.setText(infoLabel1.getText()+" "+request .getClientId());

    }
}

