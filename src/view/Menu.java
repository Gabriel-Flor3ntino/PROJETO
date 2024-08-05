package view;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.DatabaseManager;
import model.Aviao;
import model.Passageiro;
import model.Voo;

public class Menu extends JFrame {
    private JMenuBar barra = new JMenuBar();
    private JMenu prmt = new JMenu("Manter");
    private JMenu rsva = new JMenu("Reserva");
    private JMenu end = new JMenu("Sair");

    private JMenuItem prmt1 = new JMenuItem("Aeronave");
    private JMenuItem prmt2 = new JMenuItem("Voo");
    private JMenuItem prmt3 = new JMenuItem("Passageiro");

    private JMenuItem rsva1 = new JMenuItem("Reservar Assento");
    private JMenuItem rsva2 = new JMenuItem("Consultar Lugares Vazios");
    private JMenuItem rsva3 = new JMenuItem("Consultar Reservas Realizadas");
    private JMenuItem rsva4 = new JMenuItem("Cancelar Reserva");

    private JMenuItem end1 = new JMenuItem("Sair");

    private JPanel panelAeronave = new JPanel();
    private JPanel panelVoo = new JPanel();
    private JPanel panelReserva = new JPanel();

    private DatabaseManager dbManager;

    public Menu() throws SQLException {
        dbManager = new DatabaseManager();
        setupUI();
    }

    private void setupUI() {
        setJMenuBar(barra);
        barra.add(prmt);
        barra.add(rsva);
        barra.add(end);

        prmt.add(prmt1);
        prmt.add(prmt2);
        prmt.add(prmt3);

        rsva.add(rsva1);
        rsva.add(rsva2);
        rsva.add(rsva3);
        rsva.add(rsva4);

        end.add(end1);

        setLayout(new CardLayout());
        add(panelAeronave, "aeronave");
        add(panelVoo, "voo");
        add(panelReserva, "reserva");

        prmt1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPanel("aeronave");
                addAeronavePanel();
            }
        });

        prmt2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPanel("voo");
                addVooPanel();
            }
        });

        rsva1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPanel("reserva");
                addReservaPanel();
            }
        });

        rsva2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarLugaresVazios();
            }
        });

        rsva3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarReservasRealizadas();
            }
        });

        end1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setTitle("Reserva de Passagens Aéreas");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void showPanel(String panelName) {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), panelName);
    }

    private void addAeronavePanel() {
        panelAeronave.removeAll();
        panelAeronave.setLayout(new GridLayout(4, 2));

        JTextField modeloField = new JTextField();
        JTextField fileiraField = new JTextField();
        JTextField assentosField = new JTextField();

        JButton saveButton = new JButton("Salvar");

        panelAeronave.add(new JLabel("Modelo:"));
        panelAeronave.add(modeloField);
        panelAeronave.add(new JLabel("Fileiras:"));
        panelAeronave.add(fileiraField);
        panelAeronave.add(new JLabel("Assentos:"));
        panelAeronave.add(assentosField);
        panelAeronave.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String modelo = modeloField.getText();
                int fileiras = Integer.parseInt(fileiraField.getText());
                int assentos = Integer.parseInt(assentosField.getText());

                Aviao aviao = new Aviao(modelo, fileiras, assentos);
                try {
                    dbManager.getAviaoDao().create(aviao);
                    JOptionPane.showMessageDialog(null, "Aeronave salva com sucesso!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao salvar aeronave.");
                }
            }
        });

        panelAeronave.revalidate();
        panelAeronave.repaint();
    }

    private void addVooPanel() {
        panelVoo.removeAll();
        panelVoo.setLayout(new GridLayout(5, 2));

        JComboBox<Aviao> aviaoComboBox = new JComboBox<>();
        try {
            List<Aviao> avioes = dbManager.getAviaoDao().queryForAll();
            for (Aviao aviao : avioes) {
                aviaoComboBox.addItem(aviao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JTextField numeroVooField = new JTextField();
        JTextField dataField = new JTextField();
        JTextField horaField = new JTextField();

        JButton saveButton = new JButton("Salvar");

        panelVoo.add(new JLabel("Aeronave:"));
        panelVoo.add(aviaoComboBox);
        panelVoo.add(new JLabel("Número do Voo:"));
        panelVoo.add(numeroVooField);
        panelVoo.add(new JLabel("Data:"));
        panelVoo.add(dataField);
        panelVoo.add(new JLabel("Hora:"));
        panelVoo.add(horaField);
        panelVoo.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Aviao aviao = (Aviao) aviaoComboBox.getSelectedItem();
                int numeroVoo = Integer.parseInt(numeroVooField.getText());
                String data = dataField.getText();
                String hora = horaField.getText();

                Voo voo = new Voo(aviao, numeroVoo, data, hora);
                try {
                    dbManager.getVooDao().create(voo);
                    JOptionPane.showMessageDialog(null, "Voo salvo com sucesso!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao salvar voo.");
                }
            }
        });

        panelVoo.revalidate();
        panelVoo.repaint();
    }

    private void addReservaPanel() {
        panelReserva.removeAll();
        panelReserva.setLayout(new GridLayout(6, 2));

        JComboBox<Voo> vooComboBox = new JComboBox<>();
        try {
            List<Voo> voos = dbManager.getVooDao().queryForAll();
            for (Voo voo : voos) {
                vooComboBox.addItem(voo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JTextField nomeField = new JTextField();
        JTextField cpfField = new JTextField();
        JTextField fileiraField = new JTextField();
        JTextField assentoField = new JTextField();

        JButton saveButton = new JButton("Salvar");

        panelReserva.add(new JLabel("Voo:"));
        panelReserva.add(vooComboBox);
        panelReserva.add(new JLabel("Nome do Passageiro:"));
        panelReserva.add(nomeField);
        panelReserva.add(new JLabel("CPF do Passageiro:"));
        panelReserva.add(cpfField);
        panelReserva.add(new JLabel("Fileira:"));
        panelReserva.add(fileiraField);
        panelReserva.add(new JLabel("Assento:"));
        panelReserva.add(assentoField);
        panelReserva.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Voo voo = (Voo) vooComboBox.getSelectedItem();
                String nome = nomeField.getText();
                String cpf = cpfField.getText();
                int fileira = Integer.parseInt(fileiraField.getText());
                int assento = Integer.parseInt(assentoField.getText());

                Passageiro passageiro = new Passageiro(nome, cpf);
                try {
                    dbManager.getPassageiroDao().create(passageiro);
                    JOptionPane.showMessageDialog(null, "Reserva salva com sucesso!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao salvar reserva.");
                }
            }
        });

        panelReserva.revalidate();
        panelReserva.repaint();
    }

    private void consultarLugaresVazios() {
        JComboBox<Voo> vooComboBox = new JComboBox<>();
        try {
            List<Voo> voos = dbManager.getVooDao().queryForAll();
            for (Voo voo : voos) {
                vooComboBox.addItem(voo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int vooIndex = JOptionPane.showOptionDialog(null, vooComboBox, "Selecione o voo", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
        if (vooIndex >= 0 && vooIndex < vooComboBox.getItemCount()) {
            Voo voo = (Voo) vooComboBox.getItemAt(vooIndex);
            Aviao aviao = voo.getAeronave();
            StringBuilder lugaresVazios = new StringBuilder("Lugares vazios:\n");
            for (int i = 0; i < aviao.getFileira(); i++) {
                for (int j = 0; j < aviao.getAssentos(); j++) {
                    try {
                        List<Passageiro> passageiros = dbManager.getPassageiroDao().queryBuilder().where().eq("fileira", i).and().eq("assento", j).query();
                        if (passageiros.isEmpty()) {
                            lugaresVazios.append("Fileira: ").append(i).append(", Assento: ").append(j).append("\n");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            JOptionPane.showMessageDialog(null, lugaresVazios.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Voo não encontrado!");
        }
    }

    private void consultarReservasRealizadas() {
        JComboBox<Voo> vooComboBox = new JComboBox<>();
        try {
            List<Voo> voos = dbManager.getVooDao().queryForAll();
            for (Voo voo : voos) {
                vooComboBox.addItem(voo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int vooIndex = JOptionPane.showOptionDialog(null, vooComboBox, "Selecione o voo", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
        if (vooIndex >= 0 && vooIndex < vooComboBox.getItemCount()) {
            Voo voo = (Voo) vooComboBox.getItemAt(vooIndex);
            Aviao aviao = voo.getAeronave();
            StringBuilder reservasRealizadas = new StringBuilder("Reservas realizadas:\n");
            for (int i = 0; i < aviao.getFileira(); i++) {
                for (int j = 0; j < aviao.getAssentos(); j++) {
                    try {
                        List<Passageiro> passageiros = dbManager.getPassageiroDao().queryBuilder().where().eq("fileira", i).and().eq("assento", j).query();
                        for (Passageiro passageiro : passageiros) {
                            reservasRealizadas.append("Fileira: ").append(i).append(", Assento: ").append(j).append(", Passageiro: ").append(passageiro.getNome()).append("\n");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            JOptionPane.showMessageDialog(null, reservasRealizadas.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Voo não encontrado!");
        }
    }

    public static void main(String[] args) {
        try {
            new Menu();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}