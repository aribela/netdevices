package com.framelova;

import org.apache.cordova.CordovaPlugin;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.io.FileWriter;
import java.net.InetAddress;
import java.io.BufferedWriter;
import java.io.File;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import android.content.Context;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class NetDevices  extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

            if ("obtenerips".equals(action)) {
                obtenerips(args.getString(0), callbackContext);
                return true;
            }
            else if("urlValidator".equals(action)){
                urlValidator("http://192.168.0.27/cm?cmnd=Power%20On");
                return true;
            }
            else {
                callbackContext.error("Incorrect action parameter: " + action);
                return false;
            }

    }
    private void obtenerips(String msg, CallbackContext callbackContext){
        // callbackContext.success("Hola");
        String todo = "";

        try {
            Process proc = Runtime.getRuntime().exec("cat /proc/net/arp");
            // Process proc = Runtime.getRuntime().exec("nmap -sP -n 192.168.0.0/24");
            // Process proc = Runtime.getRuntime().exec("nmap -n 192.168.0.0/24");

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

            // read the output from the command
            String s = null;

            while ((s = stdInput.readLine()) != null) {
                String[] array = s.split("\\u0020"); //split que separa el arreglo
                String ar1=array[1]; //contiene la posicion 1 del arreglo (donde se encuentra la IP)
                String ar2=array[3];// contiene la posicion 3 del arreglo (donde se encuentra la MAC Address)
                String linea=array[1]+";"+array[3];
                todo = todo+"|"+s;
            }
            callbackContext.success(todo);
        }
        catch (IOException ex) {
            //System.err.println(ex);
            callbackContext.error(ex.getMessage());

        } 
        
    } 

    public void urlValidator(String url){
        /*validación de url*/
        try {
            new URL(url).toURI();
            callbackContext.success("Url valida");
        }
        catch (URISyntaxException exception) {
            callbackContext.error(ex.getMessage());
        }
        catch (MalformedURLException exception) {
            callbackContext.error(ex.getMessage());
        }
    }

}