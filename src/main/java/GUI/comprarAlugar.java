package GUI;

import DAO.AlugarFilmesDAO;
import DAO.ComprarFilmesDAO;
import DAO.FilmesDAO;
import DAO.TipoPagamentoDAO;
import Persistence.AlugarFilmes;
import Persistence.ComprarFilmes;
import Persistence.Filmes;
import Persistence.TipoPagamento;
import Persistence.Users;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.swing.JOptionPane;
import service.CalculoAluguel;

public class comprarAlugar extends javax.swing.JFrame {

    private final Users userLogado;

    public comprarAlugar(Users u) {
        this.userLogado = u;
        initComponents();

        preencherComboBoxPagamento();
        preencherComboBoxFilmes();

        txtPreco.setEnabled(false);
    }

    public void preencherComboBoxPagamento() {
        try {
            var DAO = new TipoPagamentoDAO();
            List<TipoPagamento> pagamentos = DAO.listar();

            System.out.println("Pagamentos encontrados: " + pagamentos.size());

            for (TipoPagamento p : pagamentos) {
                System.out.println("Adicionando: " + p.getNome());
                cbPagamento.addItem(p);
            }
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public void preencherComboBoxFilmes() {
        try {
            var DAO = new FilmesDAO();
            List<Filmes> filmes = DAO.listar();

            System.out.println("Filmes econtrandos: " + filmes.size());

            for (Filmes f : filmes) {
                System.out.println("Adicionando: " + f.getTitulo());
                cbFilmes.addItem(f);
            }
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void acaoBotaoComprar() {
        if (rbComprar.isSelected()) {
            lbInicio.setEnabled(false);
            lbDevolucao.setEnabled(false);
            txtDataAluguel.setEnabled(false);
            txtDataDevolucao.setEnabled(false);
            txtDataCompra.setEnabled(true);
        }
    }

    public void acaoBotaoAlugar() {
        if (rbAlugar.isSelected()) {
            lbInicio.setEnabled(true);
            lbDevolucao.setEnabled(true);
            txtDataAluguel.setEnabled(true);
            txtDataDevolucao.setEnabled(true);
            txtDataCompra.setEnabled(false);
        }
    }

    public boolean tratandoErros() {

        if (!rbComprar.isSelected() && !rbAlugar.isSelected()) {
            JOptionPane.showMessageDialog(this,
                    "Selecione uma das opções (ALUGAR/COMPRAR) para continuar");
            return false;
        }

        if (rbAlugar.isSelected()) {
            if (txtDataAluguel.getText().equals("  /  /    ")) {
                JOptionPane.showMessageDialog(this,
                        "Informe a data de inicio da compra!");
                txtDataAluguel.requestFocus();
                return false;
            }
            if (txtDataDevolucao.getText().equals("  /  /    ")) {
                JOptionPane.showMessageDialog(this, "Informe a data de devolução!");
                txtDataDevolucao.requestFocus();
                return false;
            }
        }
        return true;
    }

    public void calcularPreco() {
        try {
            Filmes filmeSelecionado = (Filmes) cbFilmes.getSelectedItem();
            if (filmeSelecionado == null) {
                return;
            }

            double valor = 0;

            if (rbAlugar.isSelected()) {
                String dataInicio = txtDataAluguel.getText().trim();
                String dataDevolucao = txtDataDevolucao.getText().trim();

                if (dataInicio.length() < 10 || dataDevolucao.length() < 10) {
                    return;
                }
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate inicio = LocalDate.parse(dataInicio, formatter);
                    LocalDate devolucao = LocalDate.parse(dataDevolucao, formatter);

                    var calculo = new CalculoAluguel();

                    valor = calculo.calcularValor(
                            filmeSelecionado.getPrecoAluguel(),
                            inicio,
                            devolucao
                    );

                    txtPreco.setText(String.valueOf(valor));

                } catch (Exception e) {
                    System.out.println("ERRO: " + e);
                }

            } else if (rbComprar.isSelected()) {
                valor = filmeSelecionado.getPrecoCompra();
                txtPreco.setText(String.valueOf(valor));
            }
        } catch (Exception e) {
            System.out.println("ERRO: " + e);
        }
    }

    public void exibirCompra() {
        TipoPagamento pagamentoSelecionado = (TipoPagamento) cbPagamento.getSelectedItem();
        if (rbComprar.isSelected()) {
            JOptionPane.showMessageDialog(this, "Filme comprado com sucesso!\n"
                    + "\nFilme: " + cbFilmes.getSelectedItem()
                    + "\nData da compra: " + txtDataCompra.getText()
                    + "\nForma de pagamento: " + pagamentoSelecionado
                    + "\nPreço: " + txtPreco.getText());
        } else if (rbAlugar.isSelected()) {
            JOptionPane.showMessageDialog(this, "Filme alugado com sucesso!\n"
                    + "\nFilme: " + cbFilmes.getSelectedItem()
                    + "\nInicio: " + txtDataAluguel.getText()
                    + "\nDevolução: " + txtDataDevolucao.getText()
                    + "\nForma de pagamento: " + pagamentoSelecionado
                    + "\nPreço: " + txtPreco.getText());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgComprarAlugar = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        rbComprar = new javax.swing.JRadioButton();
        rbAlugar = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        cbPagamento = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbFilmes = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        lbInicio = new javax.swing.JLabel();
        lbDevolucao = new javax.swing.JLabel();
        txtDataAluguel = new javax.swing.JFormattedTextField();
        txtDataCompra = new javax.swing.JFormattedTextField();
        btnPagar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtDataDevolucao = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPreco = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(7, 14, 19));

        bgComprarAlugar.add(rbComprar);
        rbComprar.setForeground(new java.awt.Color(255, 255, 255));
        rbComprar.setText("COMPRAR");
        rbComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbComprarActionPerformed(evt);
            }
        });

        bgComprarAlugar.add(rbAlugar);
        rbAlugar.setForeground(new java.awt.Color(255, 255, 255));
        rbAlugar.setText("ALUGAR");
        rbAlugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAlugarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(101, 22, 30));
        jLabel1.setText("ALUGAR/COMPRAR");

        cbPagamento.setBackground(new java.awt.Color(255, 255, 255));
        cbPagamento.setForeground(new java.awt.Color(0, 0, 0));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("SELECIONE UM FILME");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("FORMA DE PAGAMENTO");

        cbFilmes.setBackground(new java.awt.Color(255, 255, 255));
        cbFilmes.setForeground(new java.awt.Color(0, 0, 0));
        cbFilmes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFilmesActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("SELECIONE A OPÇÃO DESEJADA");

        lbInicio.setForeground(new java.awt.Color(255, 255, 255));
        lbInicio.setText("INICIO");

        lbDevolucao.setForeground(new java.awt.Color(255, 255, 255));
        lbDevolucao.setText("DEVOLUÇAO");

        txtDataAluguel.setBackground(new java.awt.Color(255, 255, 255));
        txtDataAluguel.setForeground(new java.awt.Color(0, 0, 0));
        try {
            txtDataAluguel.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataAluguel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDataAluguelKeyReleased(evt);
            }
        });

        txtDataCompra.setBackground(new java.awt.Color(255, 255, 255));
        txtDataCompra.setForeground(new java.awt.Color(0, 0, 0));
        try {
            txtDataCompra.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        btnPagar.setBackground(new java.awt.Color(101, 22, 30));
        btnPagar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnPagar.setForeground(new java.awt.Color(255, 255, 255));
        btnPagar.setText("PAGAR");
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("DATA DA COMPRA");

        txtDataDevolucao.setBackground(new java.awt.Color(255, 255, 255));
        txtDataDevolucao.setForeground(new java.awt.Color(0, 0, 0));
        try {
            txtDataDevolucao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataDevolucao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDataDevolucaoKeyReleased(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("PREÇO");

        txtPreco.setBackground(new java.awt.Color(255, 255, 255));
        txtPreco.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(rbComprar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbAlugar)
                .addGap(129, 129, 129))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(lbInicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbDevolucao)
                .addGap(163, 163, 163))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(46, 93, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(272, 272, 272))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(cbFilmes, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1))
                                        .addComponent(jLabel3))
                                    .addGap(251, 251, 251))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDataAluguel, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(98, 98, 98)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtDataDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(260, 260, 260)
                            .addComponent(txtDataCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(311, 311, 311)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(342, 342, 342)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbComprar)
                    .addComponent(rbAlugar))
                .addGap(59, 59, 59)
                .addComponent(jLabel4)
                .addGap(33, 33, 33)
                .addComponent(jLabel2)
                .addGap(12, 12, 12)
                .addComponent(cbFilmes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbInicio)
                    .addComponent(lbDevolucao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDataAluguel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDataCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(btnPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rbComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbComprarActionPerformed
        acaoBotaoComprar();
        calcularPreco();
    }//GEN-LAST:event_rbComprarActionPerformed

    private void rbAlugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAlugarActionPerformed
        acaoBotaoAlugar();

    }//GEN-LAST:event_rbAlugarActionPerformed

    private void cbFilmesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFilmesActionPerformed
        calcularPreco();
    }//GEN-LAST:event_cbFilmesActionPerformed

    private void txtDataAluguelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDataAluguelKeyReleased
        calcularPreco();
    }//GEN-LAST:event_txtDataAluguelKeyReleased

    private void txtDataDevolucaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDataDevolucaoKeyReleased
        calcularPreco();
    }//GEN-LAST:event_txtDataDevolucaoKeyReleased

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        if (tratandoErros()) {
            calcularPreco();

            Filmes filme = (Filmes) cbFilmes.getSelectedItem();
            TipoPagamento pagamento = (TipoPagamento) cbPagamento.getSelectedItem();

            if (filme == null || pagamento == null || userLogado == null) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
                return;
            }

            try {
                if (rbComprar.isSelected()) {
                    ComprarFilmes compra = new ComprarFilmes();
                    compra.setUsers(userLogado);
                    compra.setFilmes(filme);
                    compra.setTipoPagamento(pagamento);
                    compra.setDataCompra(LocalDate.now());
                    compra.setValor(Double.parseDouble(txtPreco.getText()));

                    var dao = new ComprarFilmesDAO();
                    dao.salvarCompra(compra);

                    TipoPagamento pagamentoSelecionado = (TipoPagamento) cbPagamento.getSelectedItem();

                    JOptionPane.showMessageDialog(this, "Filme comprado com sucesso!\n"
                            + "\nFilme: " + cbFilmes.getSelectedItem()
                            + "\nData da compra: " + txtDataCompra.getText()
                            + "\nForma de pagamento: " + pagamentoSelecionado
                            + "\nPreço: " + txtPreco.getText());

                    txtPreco.setText("");

                } else if (rbAlugar.isSelected()) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate dataInicio = LocalDate.parse(txtDataAluguel.getText(), formatter);
                    LocalDate dataDevolucao = LocalDate.parse(txtDataDevolucao.getText(), formatter);

                    AlugarFilmes alugar = new AlugarFilmes();
                    alugar.setUsers(userLogado);
                    alugar.setFilmes(filme);
                    alugar.setTipoPagamento(pagamento);
                    alugar.setDataInicio(dataInicio);
                    alugar.setDataDevolucao(dataDevolucao);
                    alugar.setValor(Double.parseDouble(txtPreco.getText()));
                    alugar.setStatus("ativo");

                    var dao = new AlugarFilmesDAO();
                    dao.salvarAluguel(alugar);

                    TipoPagamento pagamentoSelecionado = (TipoPagamento) cbPagamento.getSelectedItem();

                    JOptionPane.showMessageDialog(this, "Filme alugado com sucesso!\n"
                            + "\nFilme: " + cbFilmes.getSelectedItem()
                            + "\nInicio: " + txtDataAluguel.getText()
                            + "\nDevolução: " + txtDataDevolucao.getText()
                            + "\nForma de pagamento: " + pagamentoSelecionado
                            + "\nPreço: " + txtPreco.getText());
                    txtPreco.setText("");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "ERRO ao salvar: " + e.getMessage());
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_btnPagarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(comprarAlugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(comprarAlugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(comprarAlugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(comprarAlugar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new comprarAlugar(new Users()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgComprarAlugar;
    private javax.swing.JButton btnPagar;
    private javax.swing.JComboBox cbFilmes;
    private javax.swing.JComboBox cbPagamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbDevolucao;
    private javax.swing.JLabel lbInicio;
    private javax.swing.JRadioButton rbAlugar;
    private javax.swing.JRadioButton rbComprar;
    private javax.swing.JFormattedTextField txtDataAluguel;
    private javax.swing.JFormattedTextField txtDataCompra;
    private javax.swing.JFormattedTextField txtDataDevolucao;
    private javax.swing.JTextField txtPreco;
    // End of variables declaration//GEN-END:variables
}
