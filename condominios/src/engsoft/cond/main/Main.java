package engsoft.cond.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import engsoft.cond.control.DatabaseManager;
import engsoft.cond.control.LoginManager;
import engsoft.cond.control.MainManager;

public class Main {

    private final static Log LOGGER = LogFactory.getLog(Main.class);

    public static void main(String[] args) {

        LOGGER.info("Iniciando aplicativo");
        DatabaseManager.getInstance();
        LOGGER.info("Banco de dados inicializado");

        if (MainManager.CLEAR_DB) {
        	LOGGER.info("Removendo todos os dados...");
        	DatabaseManager.getInstance().clearAllTables();
        }

        if (MainManager.POP_DB) {
        	LOGGER.info("Inserindo dataset para testes...");
        	DatabaseManager.getInstance().populate();
        }

        if (MainManager.DEBUG){
        	LOGGER.info("Executando em modo debug, autenticação desabilitada");
        }

        JFrame mainFrame;

        mainFrame = new JFrame("Sistema de Condomínios");
        Dimension monitor = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 800;
        int height = 600;

        mainFrame.setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(true);

        mainFrame.setMinimumSize(new Dimension(width, height));
        mainFrame.setMaximumSize(new Dimension(width, height));
        mainFrame.setPreferredSize(new Dimension(width, height));
        mainFrame.setLocation((monitor.width - mainFrame.getWidth())/2, (monitor.height - mainFrame.getHeight())/2);
        mainFrame.pack();

        mainFrame.setVisible(true);

        MainManager.getInstance(mainFrame).changeScreen(LoginManager.getInstance().getLoginScreen());

    }

}
