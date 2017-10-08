import com.sun.org.apache.xpath.internal.SourceTree;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by stasPC on 12.04.2017.
 */
public class PortScanner {
    public PortScanner(){}
    void portScanner(int start, int end, String ip){
        for (int portNumber = start; portNumber <= end; portNumber++) {
            try {
                InetAddress address = InetAddress.getByName(ip);
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(address, portNumber), 200);
                socket.close();
                System.out.println("Port " + portNumber + " is open");
            } catch (Exception e) {
                System.out.println("Port " + portNumber + " is closed");
            }
            }
    }
}
