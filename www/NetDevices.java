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

import org.nmap4j.core.nmap.NMapExecutionException;
import org.nmap4j.core.nmap.NMapInitializationException;
import org.nmap4j.data.NMapRun;


public class NetDevices  extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

            if ("obtenerips".equals(action)) {
                obtenerips(args.getString(0), callbackContext);
                return true;
            }
            else {
                callbackContext.error("Incorrect action parameter: " + action);
                return false;
            }

    }
    private void obtenerips(String msg, CallbackContext callbackContext){
        // callbackContext.success("Hola");
        // String todo = "";

        // try {
        //     // Process proc = Runtime.getRuntime().exec("cat /proc/net/arp");
        //     // Process proc = Runtime.getRuntime().exec("nmap -sP -n 192.168.0.0/24");
        //     Process proc = Runtime.getRuntime().exec("nmap -n 192.168.0.0/24");

        //     BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        //     BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

        //     // read the output from the command
        //     String s = null;

        //     while ((s = stdInput.readLine()) != null) {
        //         String[] array = s.split("\\u0020"); //split que separa el arreglo
        //         String ar1=array[1]; //contiene la posicion 1 del arreglo (donde se encuentra la IP)
        //         String ar2=array[3];// contiene la posicion 3 del arreglo (donde se encuentra la MAC Address)
        //         String linea=array[1]+";"+array[3];
        //         todo = todo+"|"+s;
        //     }
        //     callbackContext.success(todo);
        // }
        // catch (IOException ex) {
        //     //System.err.println(ex);
        //     callbackContext.error(ex.getMessage());

        // } 
        try {
            String path = "/usr/local" ;
            
            Nmap4j nmap4j = new Nmap4j( path ) ;
            nmap4j.addFlags( "-sV -T5 -O -oX -" ) ;
            nmap4j.includeHosts( "localhost" ) ;
            nmap4j.execute() ;
            if( !nmap4j.hasError() ) {
                NMapRun nmapRun = nmap4j.getResult() ;
                String output = nmap4j.getOutput() ;
                String errors = nmap4j.getExecutionResults().getErrors() ;
                if( output == null ) {
                    callbackContext.error("Error nulo");
                }
                else if (errors == null ) {
                    callbackContext.error("Error errors");
                }
                else{
                    callbackContext.success(output);
                }
            }
        } catch (NMapInitializationException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            // fail() ;
            callbackContext.error(e.getMessage());
        } catch (NMapExecutionException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            // fail() ;
            callbackContext.error(e.getMessage());
        }
    } 

}