import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;

public class Socket {

    private String ipCliente;
    private int serverPort;

    public Socket(){
        this.serverPort = 6969;
    }

    public String reciveMsgChat() throws Exception {
        try{
            java.net.ServerSocket tomadaServidor = new java.net.ServerSocket(serverPort);
            System.out.println("Aguardando conexão");
            java.net.Socket tomadaCliente = tomadaServidor.accept();
            System.out.println("Conectado");
            setIpCliente(String.valueOf(tomadaCliente.getInetAddress()));

            InputStream bufferInput = tomadaCliente.getInputStream();

            byte[] textRecive = new byte[2048];

            bufferInput.read(textRecive);
            String message = new String(textRecive).trim();

            return message;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "";
        }
    }

    public void sendMsgChat(byte[] menssagem){
        try{
            java.net.Socket tomadaCliente = new java.net.Socket(ipCliente,6970);

            OutputStream bufferSaida = tomadaCliente.getOutputStream();
            bufferSaida.write(menssagem);
            bufferSaida.flush();

        } catch (UnknownHostException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    public void setIpCliente(String ipCliente){
        this.ipCliente = ipCliente.replace("/", "");
    }

}
