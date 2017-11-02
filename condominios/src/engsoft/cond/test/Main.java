package engsoft.cond.test;

import java.util.ArrayList;

import engsoft.cond.control.DatabaseManager;
import engsoft.cond.model.AreaComum;
import engsoft.cond.model.Condominio;
import engsoft.cond.model.Usuario;

public class Main {

    public static void main(String[] args) {
//        Usuario u = new Usuario("a@a.com", "nome", "22222", "11111", "11-22-33", "44-55-66", "a");
//        DatabaseManager.getInstance().saveUser(u);
//        DatabaseManager.getInstance().clearTable("Usuario");
//        ArrayList<Usuario> userList = DatabaseManager.getInstance().getListOfUsers();
//        
//        System.out.println(userList);
//        Condominio c = new Condominio("SPV", "Rua 1");
//        AreaComum a1 = new AreaComum("Parquinho", "crianças");
//        AreaComum a2 = new AreaComum("Piscina", "molhada");
//        
//        c.addArea(a1);
//        c.addArea(a2);
//        
//        DatabaseManager.getInstance().saveEntity(a1);
//        DatabaseManager.getInstance().saveEntity(a2);
//        DatabaseManager.getInstance().saveEntity(c);
//        
//        ArrayList<Object> condList = DatabaseManager.getInstance().getListFromTable("Condominio");
//
//        System.out.println(condList);
        
         DatabaseManager.getInstance();
    }

}
