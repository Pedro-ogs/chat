import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatView extends JFrame{
    private JPanel mainPanel;
    private JTextArea textAreaChat;
    private JButton btnSend;
    private JTextField textMsg;
    private JPanel topJPanel;
    private JPanel chatPanel;
    private JPanel messagePanel;
    private ChatController controller;

    public ChatView(ChatController controller){
        this.controller = controller;
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String minhaMsg = textMsg.getText();
                controller.sendMsg(minhaMsg);
                textAreaChat.setText(textAreaChat.getText() + String.format("[Eu]: %s\n",minhaMsg));
                textMsg.setText("");
            }
        });

        chatPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        messagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    }

    public void adicionarMensagem(String mensagem) {
        textAreaChat.setText(textAreaChat.getText() + String.format("[Elu]: %s\n",mensagem));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
