/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.request.RequestObject;
import transfer.response.ResponseObject;

/**
 *
 * @author Mira
 */
public class CommunicationServer {
    private static CommunicationServer instanca;
    private Socket socket;

    public CommunicationServer() {
    }
   

    public static CommunicationServer getInstanca() {
        
        if(instanca == null)
            instanca = new CommunicationServer();
        return instanca;
    }
    
    public void connect(String IpAdress, int port) throws Exception{
        try {
            socket = new Socket(IpAdress, port);
            System.out.println("Client has connected to server");
        } catch (IOException ex) {
//            Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Don't exist server with those datas");
        }
    }
    
    
    public void sendRequest(RequestObject request) throws Exception{
    
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(request);
        } catch (IOException ex) {
//            Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("klijent.Klijent - linija 54 - Izbacen sa servera");
            try {
                socket.close();
            } catch (IOException ex1) {
                Logger.getLogger(CommunicationServer.class.getName()).log(Level.SEVERE, null, ex1);
                System.out.println("Error while closing socket");
            }
            throw new Exception("Connection with server has been lost. Try again!");
        }
    }
    
    public ResponseObject getResponse(){
        ResponseObject response = new ResponseObject();
        
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            response = (ResponseObject) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(CommunicationServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommunicationServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return response;
    }

    public Socket getSocket() {
        return socket;
    }
}
