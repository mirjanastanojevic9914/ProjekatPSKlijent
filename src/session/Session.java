/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Mira
 */
public class Session {
    private static Session instance;
    private Map<String, Object> map;
    private Socket socket;
    
    private Session() {
        map = new HashMap<>();
    }
    
    public static Session getInstance(){
        if (instance == null) instance = new Session();
        return instance;
    }

     public void put(String key, Object value) {
        map.put(key, value);
    }
    
    public Object get(String key) {
        return map.get(key);
    }
    
    public void remove(String key) {
        map.remove(key);
    }
    
    
    public Map<String, Object> getMap() {
        return map;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
