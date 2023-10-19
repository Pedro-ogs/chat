import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatView extends JFrame{
    private JPanel mainPanel;
    private JTextArea textAreaChat;
    private JButton btnSend;
    private JTextField textMsg;
    private JPanel topJPanel;
    private ChatController controller;

    public ChatView(ChatController controller){
        this.controller = controller;

        //JScrollPane scrollPane = new JScrollPane(textAreaChat);
        //mainPanel.add(scrollPane);

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String minhaMsg = textMsg.getText();
                controller.sendMsg(minhaMsg);
                textAreaChat.setText(textAreaChat.getText() + String.format("[Eu]: %s\n",minhaMsg));
                textMsg.setText("");
            }
        });
    }

    public void adicionarMensagem(String mensagem) {
        textAreaChat.setText(textAreaChat.getText() + String.format("[Elu]: %s\n",mensagem));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
