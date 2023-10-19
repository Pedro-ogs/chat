import javax.swing.*;

public class ChatController {
    private ChatView gui;
    private Socket socket;

    public ChatController() throws Exception {
        this.socket = new Socket();
        initServer();
    }

    public void init() {
        gui = new ChatView(this);
        gui.setTitle("Conversa");
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(450, 350);
        gui.setLocationRelativeTo(null);
        gui.setContentPane(gui.getMainPanel());
        gui.setVisible(true);
    }

    private void initServer() throws Exception {
        if (socket == null){
            throw new Exception();
        }

        // Crie uma instância do Runnable
        Runnable runnable = new MyRunnable();

        // Crie uma instância de Thread e inicie-a com o Runnable
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void sendMsg(String minhaMsg){
        byte[] menssage = new byte[2048];

        if(minhaMsg.trim().length() == 0){
            //erro
        };

        menssage = minhaMsg.getBytes();
        socket.sendMsgChat(menssage);
    }

    public class MyRunnable implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("Thread esta rodando");
                String mensagem = socket.reciveMsgChat();
                gui.adicionarMensagem(mensagem);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

}
