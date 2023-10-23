import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;

public class Socket {

    private String ipCliente;
    public int serverPort;

    public Socket(){
        this.serverPort = 6969;
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
