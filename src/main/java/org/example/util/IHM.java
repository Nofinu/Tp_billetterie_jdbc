package org.example.util;

import model.Client;
import model.Lieu;
import service.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class IHM {

    private Scanner scanner;
    private ServiceEvenement serviceEvenement;
    private ServiceLieu serviceLieu;
    private ServiceCLient serviceCLient;
    private ServiceBillet serviceBillet;

    public IHM() {
        this.scanner = new Scanner(System.in);
    }

    public void start(){
        int entry;
        do{
            menu();
            entry = scanner.nextInt();
            scanner.nextLine();

            switch (entry){
                case 1 :
                    menuLieu(1);
                    break;
                case 4 :
                    menuEvenement(1);
                    break;
                case 7 :
                    menuClient(1);
                    break;

                case 2 :
                    menuLieu(2);
                    break;
                case 5 :
                    menuEvenement(2);
                    break;
                case 8 :
                    menuClient(2);
                    break;
                case 3 :
                case 6 :
                case 9 :
                case 12:
                    deleteMenu(entry);
                    break;
                case 10 :
                    showAllEvent();
                    break;
                case 11:
                    menuBillet();
                    break;
                case 13 :
                    showBillet();
                    break;
                case 14:
                    showAllCLient();
                    break;
                case 15:
                    showAllLieux();
                    break;
            }

        }while(entry !=0);

    }

    private void menu(){
        System.out.println("----- menu -----");
        System.out.println("1-- ajouter un lieu");
        System.out.println("2-- modifier un lieu");
        System.out.println("3-- supprimer un lieu");
        System.out.println("------------");
        System.out.println("4-- ajouter un evenement");
        System.out.println("5-- modifier un evenement");
        System.out.println("6-- supprimer un evenement");
        System.out.println("------------");
        System.out.println("7-- ajouter un client");
        System.out.println("8-- modifier un client");
        System.out.println("9-- supprimer un client");
        System.out.println("------------");
        System.out.println("10-- afficher tout les evenement disponible");
        System.out.println("11-- acheter un billet ");
        System.out.println("12-- annuler un billet");
        System.out.println("13-- afficher les billets d'un client");
        System.out.println("14-- afficher tout les clients");
        System.out.println("15-- afficher tout les lieux");
        System.out.println("-------------");
        System.out.println("0-- quitter");
    }

    private void menuLieu (int type){
        System.out.println("-------"+ (type == 1 ? "ajout" : "moddification" )+"d'un lieu -------");
        System.out.println("nom :");
        String nom = scanner.nextLine();
        System.out.println("adresse :");
        String adresse = scanner.nextLine();
        System.out.println("capacité :");
        int capacite = scanner.nextInt();
        scanner.nextLine();

        serviceLieu = new ServiceLieu();
        if(type ==1){
            if(serviceLieu.addLieu(nom,adresse,capacite)) {
                System.out.println("lieu ajouté");
            }
        }else {
            System.out.println("id :");
            int id =scanner.nextInt();
            scanner.nextLine();
            Lieu lieu = new Lieu(id,nom,adresse,capacite);
            if(serviceLieu.editLieu(lieu)){
                System.out.println("lieu modifié");
            };
        }
    }

    private  void menuClient (int type){
        System.out.println("------- "+(type == 1 ? "ajout" : "moddification" )+" ajout d'un evenement -------");
        System.out.println("nom :");
        String nom = scanner.nextLine();
        System.out.println("prenom :");
        String prenom = scanner.nextLine();
        System.out.println("email :");
        String email = scanner.nextLine();

        serviceCLient = new ServiceCLient();
        if(type == 1) {
            if(serviceCLient.addClient(nom,prenom,email)) {
                System.out.println("client ajouté");
            }
        }
        else{
            System.out.println("id :");
            int id =scanner.nextInt();
            scanner.nextLine();
            Client client = new Client(id,nom,prenom,email);
            if(serviceCLient.editCLient(client)) {
                System.out.println("client modifié");
            }
        }
    }

    private void menuEvenement (int type){
        System.out.println("-------"+(type == 1 ? "ajout" : "moddification" )+  "d'un lieu -------");
        System.out.println("nom :");
        String nom = scanner.nextLine();
        System.out.println("date format(dd-MM-yyyy) :");
        String dateString = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            date = new Date("01/01/2001");
        }

        System.out.println("heure :");
        String heure = scanner.nextLine();

        System.out.println("lieu (id) :");
        int idLieu = scanner.nextInt();
        scanner.nextLine();
        System.out.println("prix :");
        float prix = scanner.nextFloat();
        scanner.nextLine();

        serviceEvenement = new ServiceEvenement();

        if(type == 1) {
            if(serviceEvenement.addEvent(nom,date,heure,idLieu,prix)) {
                System.out.println("evenement ajouté");
            }
        }
        else{
            System.out.println("id :");
            int id =scanner.nextInt();
            scanner.nextLine();
            if(serviceEvenement.editEvenement(id,nom,date,heure,idLieu,prix));{
                System.out.println("evenement modifié");
            }
        }
    }

    public void menuBillet (){
        System.out.println("------- achat d'un billet -------");
        System.out.println("id du client :");
        int idCLient = scanner.nextInt();
        System.out.println("id de l'evenement");
        int idEvent = scanner.nextInt();
        scanner.nextLine();
        System.out.println("type de billet (vip/gold/standar): ");
        String type = scanner.nextLine().toLowerCase();
        TypeBillet typeBillet;
        switch (type){
            case "vip":
                typeBillet = TypeBillet.VIP;
                break;
            case "gold":
                typeBillet = TypeBillet.GOLD;
                break;
            default :
                typeBillet = TypeBillet.STANDARD;
                break;
        }

        serviceBillet = new ServiceBillet();

        if(serviceBillet.addBilletAction(idCLient,idEvent,typeBillet)){
            System.out.println("billet acheté");
        }
    }

    private void deleteMenu (int type){
        System.out.println("-------- supression "+ (type ==3?" de lieu" : type == 6?"d'evenement" : type == 9?"de client" : "de billet"));
        System.out.println("id :");
        int id = scanner.nextInt();
        scanner.nextLine();
       switch (type){
           case 3:
               serviceLieu = new ServiceLieu();
               if(serviceLieu.deleteLieu(id)){
                   System.out.println("lieu supprimé");
               }
               break;
           case 6:
               serviceEvenement = new ServiceEvenement();
               if(serviceEvenement.deleteEvenement(id)){
                   System.out.println("evenement supprimé");
               }
               break;
           case 9 :
               serviceCLient = new ServiceCLient();
               if(serviceCLient.deleteClient(id)){
                   System.out.println("client supprimé");
               }
               break;
           case 12 :
                serviceBillet = new ServiceBillet();
                if(serviceBillet.deleteBillet(id)){
                    System.out.println("billet annulé");
                }
               break;
       }
    }

    public void showAllEvent (){
        serviceEvenement = new ServiceEvenement();
        serviceEvenement.findAllEvenement().forEach(e-> System.out.println(e));
    }

    public void showBillet (){
        System.out.println("------ affichage des billets d'un client ----------");
        System.out.println("id du client :");
        int id = scanner.nextInt();
        scanner.nextLine();

        serviceBillet = new ServiceBillet();
        serviceBillet.showBilletAction(id).forEach(e -> System.out.println(e));
    }

    public void showAllCLient(){
        System.out.println("------ affichage des clients ------");
        serviceCLient = new ServiceCLient();
        serviceCLient.findAllClient().forEach(e-> System.out.println(e));
        System.out.println();
    }

    public void showAllLieux(){
        System.out.println("------ affichage des Lieux ------");
        serviceLieu = new ServiceLieu();
        serviceLieu.findAllLieu().forEach(e-> System.out.println(e));
        System.out.println();
    }



}


