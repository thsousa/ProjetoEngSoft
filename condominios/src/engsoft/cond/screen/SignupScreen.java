package engsoft.cond.screen;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import engsoft.cond.control.SignupManager;
import engsoft.cond.model.Usuario;

public class SignupScreen extends Screen {

    /**
     *
     */
    private static final long serialVersionUID = 8183767463523397286L;

    public SignupScreen() {
        initScreen("", "", "", "", "", "");
    }

    public SignupScreen(String email, String nome) {
        initScreen(email, nome, "", "", "", "");
    }

    public SignupScreen(Usuario user) {
        initScreen(user.getEmail(), user.getNome(), user.getCpf(), user.getCnpj(), user.getTel1(), user.getTel2());
    }

    private void initScreen(String email, String nome, String cpf, String cnpj, String tel1, String tel2) {

        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setMinimumSize(getSize());
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Sistema de Condomínios - Cadastro", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 14));
        title.setAlignmentX(CENTER_ALIGNMENT);


        JLabel emailL = new JLabel("Email: ", JLabel.CENTER);
        emailL.setAlignmentX(CENTER_ALIGNMENT);

        final JTextField emailT = new JTextField();
        emailT.setMaximumSize(new Dimension(DEFAULT_WIDTH - 50, 30));
        emailT.setAlignmentX(CENTER_ALIGNMENT);
        emailT.setText(email);

        JLabel nomeL = new JLabel("Nome: ", JLabel.CENTER);
        nomeL.setAlignmentX(CENTER_ALIGNMENT);

        final JTextField nomeT = new JTextField();
        nomeT.setMaximumSize(new Dimension(DEFAULT_WIDTH - 50, 30));
        nomeT.setAlignmentX(CENTER_ALIGNMENT);
        nomeT.setText(nome);

        JLabel cpfL = new JLabel("CPF: ", JLabel.CENTER);
        cpfL.setAlignmentX(CENTER_ALIGNMENT);

        final JTextField cpfT = new JTextField();
        cpfT.setMaximumSize(new Dimension(DEFAULT_WIDTH - 50, 30));
        cpfT.setAlignmentX(CENTER_ALIGNMENT);
        cpfT.setText(cpf);

        JLabel cnpjL = new JLabel("CNPJ: ", JLabel.CENTER);
        cnpjL.setAlignmentX(CENTER_ALIGNMENT);

        final JTextField cnpjT = new JTextField();
        cnpjT.setMaximumSize(new Dimension(DEFAULT_WIDTH - 50, 30));
        cnpjT.setAlignmentX(CENTER_ALIGNMENT);
        cnpjT.setText(cnpj);

        JLabel tel1L = new JLabel("Telefone principal: ", JLabel.CENTER);
        tel1L.setAlignmentX(CENTER_ALIGNMENT);

        final JTextField tel1T = new JTextField();
        tel1T.setMaximumSize(new Dimension(DEFAULT_WIDTH - 50, 30));
        tel1T.setAlignmentX(CENTER_ALIGNMENT);
        tel1T.setText(tel1);

        JLabel tel2L = new JLabel("Telefone secundário: ", JLabel.CENTER);
        tel2L.setAlignmentX(CENTER_ALIGNMENT);

        final JTextField tel2T = new JTextField();
        tel2T.setMaximumSize(new Dimension(DEFAULT_WIDTH - 50, 30));
        tel2T.setAlignmentX(CENTER_ALIGNMENT);
        tel2T.setText(tel2);

        JButton signupButton = new JButton("Realizar cadastro");
        signupButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SignupManager.getInstance().doSignup(nomeT.getText(), emailT.getText(), cpfT.getText(), cnpjT.getText(), tel1T.getText(), tel2T.getText());
            }
        });
        signupButton.setAlignmentX(CENTER_ALIGNMENT);

        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(emailL);
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 10)));
        this.add(emailT);
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(nomeL);
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 10)));
        this.add(nomeT);
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(cpfL);
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 10)));
        this.add(cpfT);
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(cnpjL);
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 10)));
        this.add(cnpjT);
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(tel1L);
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 10)));
        this.add(tel1T);
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(tel2L);
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 10)));
        this.add(tel2T);
        this.add(Box.createRigidArea(new Dimension(DEFAULT_WIDTH, 20)));
        this.add(signupButton);
        this.addBackButton();
    }

}
