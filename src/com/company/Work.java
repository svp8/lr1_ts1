package com.company;

import com.company.models.Client;
import com.company.models.Request;

import java.util.ArrayList;
import java.util.Scanner;

public class Work {
    private boolean flag=true;
    ArrayList<Client> clientArray;
    ArrayList<Request> requestArray;
    Scanner in;
    int choice;
    public void start(){
        clientArray=new ArrayList<>();
        requestArray=new ArrayList<>();
        clientArray.add(new Client(0,"Ivanov I.I.","lada","10"));
        clientArray.add(new Client(1,"Petrov I.I.","tesla","T"));
        requestArray.add(new Request(0,"04.04.2022","Сломалось колесо",1000,"10.04.2022",0));
        requestArray.add(new Request(1,"04.04.2021","Сломалось окно",11000,"10.04.2021",0));
        in=new Scanner(System.in);
        int number;
        while (flag){
            printMenu();
            choice=in.nextInt();
            switch(choice){
                case 1:
                    printClients();
                    break;
                case 2:
                    printRequests();
                    break;
                case 3:
                    System.out.println("Введите номер запроса ");
                    number=in.nextInt();
                    if(number<requestArray.size()){
                    updateRequest(number);}
                    break;
                case 4:
                    System.out.println("Введите номер запроса ");
                    number=in.nextInt();
                    if(number<requestArray.size()){
                        deleteRequest(number);}

                    break;
                case 6:
                    flag=false;
                    break;
                case 5:
                    createRequest();
                    break;
                default:
                    System.out.println("bruh");
                    break;
            }
        }

    }
    public void printMenu(){
        System.out.println("Выберите функцию: ");
        System.out.println("1. Вывести клиентов");
        System.out.println("2. Вывести запросы");
        System.out.println("3. Изменить запрос");
        System.out.println("4. Удалить запрос");
        System.out.println("5. Создать запрос");

        System.out.println("6. Выйти из программы");


    }
    public void printClients(){
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%5s %20s %7s %8s", "ID", "FIO", "Brand", "Model");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
        for(Client client:clientArray){
            System.out.printf("%5s %20s %7s %8s", client.getId(), client.getFIO(), client.getBrand(), client.getModel());
            System.out.println();
        }

    }
    public void printRequests(){
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%5s %13s %40s %6s %10s", "ID", "DateOfRequest", "Reason", "Price","DateOfTransfer");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
        for(Request req:requestArray){
            System.out.printf("%5s %13s %40s %6s %10s", req.getId(), req.getDateOfRequest(),req.getReason(),req.getPrice(),req.getDateOfTransfer());
            System.out.println();
        }

    }
    public void updateRequest(int number){
        System.out.println("Выберите функцию: ");
        System.out.println("1. Изменить дату запроса");
        System.out.println("2. Изменить причину");
        System.out.println("3. Изменить цену");
        System.out.println("4. Выйти");
        Request request=requestArray.get(number);
        String newValue;
        choice=in.nextInt();
        switch(choice){
            case 1:
                newValue=in.nextLine();
                System.out.println("Введите новое значение");
                newValue=in.nextLine();
                request.setDateOfRequest(newValue);
                break;
            case 2:
                newValue=in.nextLine();
                System.out.println("Введите новое значение");
                newValue=in.nextLine();
                request.setReason(newValue);
                break;
            case 3:
                System.out.println("Введите новое значение");
                int newPrice=in.nextInt();
                request.setPrice(newPrice);
                break;

            default:
                System.out.println("bruh");
                break;
        }
    }
    public void createRequest(){
        String temp;
        Request tempRequest=new Request();

        System.out.println("Введите id запроса");
        tempRequest.setId(in.nextInt());
        temp=in.nextLine();
        System.out.println("Введите дату запроса");
        tempRequest.setDateOfRequest(in.nextLine());
        temp=in.nextLine();
        System.out.println("Введите причину");
        tempRequest.setReason(in.nextLine());
        System.out.println("Введите цену");
        tempRequest.setPrice(in.nextInt());
        temp=in.nextLine();
        System.out.println("Введите дату передачи");
        tempRequest.setDateOfTransfer(in.nextLine());
        temp=in.nextLine();
        System.out.println("Введите id клиента");
        tempRequest.setClientId(in.nextInt());
        requestArray.add(tempRequest);
    }
    public void deleteRequest(int number){
        requestArray.remove(number);
    }

}
