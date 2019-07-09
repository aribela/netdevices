

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

public class NetDevices  extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("obtenerips".equals(action)) {
            obtenerIps(args.getString(0), callbackContext);
            return true;
        }

        return false;
    }
    private void obtenerips(String msg, CallbackContext callbackContext){
        //FileWriter fichero=new FileWriter("/Users/ari/Desktop/f.txt");
        //BufferedWriter bfwriter = new BufferedWriter(fichero);
        String todo = "";

        try {
            Process proc = Runtime.getRuntime().exec("arp -a ");

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

            // read the output from the command
            String s = null;
            

            while ((s = stdInput.readLine()) != null) {
                String[] array = s.split("\\u0020"); //split que separa el arreglo
                String ar1=array[1]; //contiene la posicion 1 del arreglo (donde se encuentra la IP)
                String ar2=array[3];// contiene la posicion 3 del arreglo (donde se encuentra la MAC Address)
                String linea=array[1]+";"+array[3];
                todo = todo+"|"+linea;
                //fichero.write(todo);
                //bfwriter.newLine();

                //fichero.append("\r\n");

                // read any errors from the attempted command
                //while ((s = stdError.readLine()) != null) {
                    //System.err.println(s);
                //}
            }
            callbackContext.success(todo);
        }
        catch (IOException ex) {
            //System.err.println(ex);
            callbackContext.error("Error");
        } 
        //fichero.close();  
    } 

}