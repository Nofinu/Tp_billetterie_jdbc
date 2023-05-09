package model;

import dao.BilletDAO;
import dao.ClientDao;
import dao.EvenementDAO;
import dao.LieuDAO;
import jdk.jshell.spi.ExecutionControl;
import model.Lieu;
import org.example.util.DatabaseManager;
import org.w3c.dom.ls.LSOutput;
import service.*;

import java.security.PrivateKey;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class IHM {

    private Scanner scanner;
    private Connection connection;

    private LieuDAO lieuDAO;
    private EvenementDAO evenementDAO;
    private ClientDao clientDao;
    private BilletDAO billetDAO;

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
                case 4 :
                case 7 :
                    ServiceGeneral.addAction(entry);
                    break;
                case 2 :
                case 5 :
                case 8 :
                    ServiceGeneral.editAction(entry);
                    break;
                case 3 :
                case 6 :
                case 9 :
                case 12:
                    ServiceGeneral.deleteAction(entry);
                    break;
                case 10 :
                    ServiceGeneral.showAllEventAction();
                    break;
                case 11:
                    ServiceBillet.addBilletAction();
                    break;
                case 13 :
                    ServiceBillet.showBilletAction();
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
        System.out.println("-------------");
        System.out.println("0-- quitter");
    }

}


