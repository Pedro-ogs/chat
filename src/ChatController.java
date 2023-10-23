import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;

public class ChatController {
    private ChatView gui;
    private Socket socket;
    private java.net.ServerSocket tomadaServidor;
    private java.net.Socket tomadaCliente;
    public ChatController() throws Exception {
        this.socket = new Socket();
    }

    public void init() throws Exception {
        gui = new ChatView(this);
        gui.setTitle("Conversa");
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(450, 350);
        gui.setLocationRelativeTo(null);
        gui.setContentPane(gui.getMainPanel());
        gui.setVisible(true);
        initServer();
    }

    private void initServer() throws Exception {
        if (socket == null){
            throw new Exception();
        }

        tomadaServidor = new java.net.ServerSocket(socket.serverPort);
        System.out.println("Aguardando conexão");
        tomadaCliente = tomadaServidor.accept();
        System.out.println("Conectado");
        socket.setIpCliente(String.valueOf(tomadaCliente.getInetAddress()));

        // Crie uma instância de Thread e inicie-a
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        byte[] textReceive = new byte[2048];

                        InputStream bufferInput = tomadaCliente.getInputStream();
                        //Guardando a msg recebida dentro da variavel textRecive
                        bufferInput.read(textReceive);
                        String message = new String(textReceive).trim();

                        addMessage(message);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        };
        thread.start();
    }

    private void addMessage(String message) {
        this.gui.adicionarMensagem(message);
    }

    public void sendMsg(String minhaMsg){
        byte[] menssage = new byte[2048];

        if(minhaMsg.trim().length() == 0){
            //erro
        };

        menssage = minhaMsg.getBytes();
        socket.sendMsgChat(menssage);
    }

}
