package com.company;

import com.company.models.Client;
import com.company.models.Request;

import javax.swing.*;
import javax.swing.table.TableModel;
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
        dataClients = new String[100][4];
    }
    public void start(){

        frame=new JFrame("CarServiceDatabase");
        frame.setBounds(0,0,width,height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        panel=new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        updateClientTable();
        JButton buttonAddClient=new JButton("Добавит чела");
        buttonAddClient.setBounds(400,400,100,30);
        panel.add(buttonAddClient);
        buttonAddClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createClient();
            }
        });
        JButton buttonDeleteClient=new JButton("Удалить чела");
        buttonDeleteClient.setBounds(700,400,100,30);
        panel.add(buttonDeleteClient);
        buttonDeleteClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteClient();
            }
        });


        myImage.setBounds(0,0,width,height);
        panel.add(myImage);

        frame.setVisible(true);
    }
    public void createClient(){
        String temp;
        Client tempClient=new Client();
        tempClient.setFIO("kekkekekuuuraaaaas");
        clientArray.add(tempClient);
        updateClientTable();
    }
    public void deleteClient(){
        clientArray.remove(0);
        updateClientTable();
    }
    public void updateClientTable(){
        for(int k=0;k<100;k++){
            dataClients[k][0]=" ";
            dataClients[k][1]=" ";
            dataClients[k][2]=" ";
            dataClients[k][3]=" ";
        }
        int i=0;
        for(Client client:clientArray){
            dataClients[i][0]=Integer.toString(client.getId());
            dataClients[i][1]=client.getFIO();
            dataClients[i][2]=client.getBrand();
            dataClients[i][3]=client.getModel();
            i++;
        }
        String[] columnNames = {"id", "Fio", "Brand", "Model"};
        tableClients = new JTable(dataClients, columnNames);
        panel.add(tableClients);
        tableClients.setBounds(10,200,300,300);
    }
}
