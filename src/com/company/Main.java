package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;  // Import the Scanner class
import java.io.FileWriter;

public class Main {
    static ArrayList<Laptop> LaptopList = new ArrayList<Laptop>();
    static boolean runMenu = true;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        readLaptops();
        while(runMenu){
        menu(sc);}
    }

    public static void menu(Scanner sc){

        int option = 0;
        System.out.println("Welkom bij TechGrounds Laptops, selecteer één van de volgende opties:\n" +
                "1. Weergeef alle producten.\n" +
                "2. Weergeef alle producten gesorteerd op merknaam van a → z.\n" +
                "3. Weergeef alle producten gesorteerd op merknaam van z ← a.\n" +
                "4. Weergeef alle producten gesorteerd op prijs van hoog naar laag.\n" +
                "5. Weergeef alle producten gesorteerd op prijs van laag naar hoog.\n" +
                "6. Voeg een product toe.\n" +
                "7. Verwijder een product.\n" +
                "8. Beëindig de applicatie.");
        option = sc.nextInt();
        switch (option){
                case 1:
                    showLaptops();
                    break;
                case 2:
                    showLaptopsAZ();
                    break;
                case 3:
                    showLaptopsZA();
                    break;
                case 4:
                    showLaptopsPrijsHL();
                    break;
                case 5:
                    showLaptopsPrijsLH();
                    break;
                case 6:
                    addLaptop();
                    break;
                case 7:
                    removeLaptop();
                    break;
                case 8:
                    saveChanges();
                        System.out.println("Goodbye!");
                    runMenu=false;
                    sc.close();
                    break;
                default:
                    System.out.println("Verkeerde input, kies één van de mogelijkheden uit het menu.");


            }
    }
    public static void readLaptops(){
        try {
//            String basePath = new File("").getAbsolutePath();
//            System.out.println(basePath);

            File myObj = new File("laptops.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(" ");
                LaptopList.add(new Laptop(data[0], data[1], Integer.parseInt(data[2].replace("$", ""))));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file could not be found on the system.");
            e.printStackTrace();
        }
    }
    public static void showLaptops(){
        int i =0;
        for(Laptop laptop : LaptopList){
            i++;
            System.out.println(i + ". " + laptop.toString());
        }
    }

    public static void showLaptopsAZ(){
        ArrayList<Laptop> LaptopListAZ = LaptopList;
        Collections.sort(LaptopListAZ,
                (o1, o2) -> o1.getMerk().compareTo(o2.getMerk()));
        for(Laptop laptop : LaptopListAZ){
            System.out.println(laptop.toString());
        }
    }
    public static void showLaptopsZA(){
        ArrayList<Laptop> LaptopListZA = LaptopList;
        Collections.sort(LaptopListZA,
                (o1, o2) -> o2.getMerk().compareTo(o1.getMerk()));
        for(Laptop laptop : LaptopListZA){
            System.out.println(laptop.toString());
        }
    }
    public static void showLaptopsPrijsHL(){
        ArrayList<Laptop> LaptopListPrijsHL = LaptopList;
        Collections.sort(LaptopListPrijsHL,
                (o1, o2) -> o2.getPrijs() - (o1.getPrijs()));
        for(Laptop laptop : LaptopListPrijsHL){
            System.out.println(laptop.toString());
        }
    }
    public static void showLaptopsPrijsLH(){
        ArrayList<Laptop> LaptopListPrijsLH = LaptopList;
        Collections.sort(LaptopListPrijsLH,
                (o1, o2) -> o1.getPrijs()-(o2.getPrijs()));
        for(Laptop laptop : LaptopListPrijsLH){
            System.out.println(laptop.toString());
        }
    }

    public static void addLaptop(){
        Scanner sc = new Scanner(System.in);
        Laptop laptop = new Laptop();
        System.out.println("Wat is het merk van de laptop?");
        laptop.setMerk(sc.nextLine());
        System.out.println("Wat is het model van de laptop?");
        laptop.setType(sc.nextLine());
        System.out.println("Wat is de prijs van de laptop?");
        laptop.setPrijs(sc.nextInt());
        LaptopList.add(laptop);
    }
    public static void removeLaptop(){
        Scanner sc = new Scanner(System.in);
        showLaptops();
        System.out.println("Voer het getal in van de laptop die verwijderd moet worden.\n");
        LaptopList.remove(sc.nextInt()-1);
    }

    public static void saveChanges(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Wil je de aanpassingen opslaan?\n(yes/no/)\n");
        if(sc.nextLine().contains("yes")){
            try {
                FileWriter writer = new FileWriter("laptops.txt");
                for(Laptop laptop : LaptopList){

                    writer.write(laptop.toString() + "\n");
                }
                writer.close();
                System.out.println("Wijzigingen succesvol opgeslagen!");
            } catch (IOException e) {
                System.out.println("Error! Wijzigingen niet opgeslagen.");
                e.printStackTrace();
            }
        }
    }
}
