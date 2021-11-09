package tobe.vlc.lifiart;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.os.Vibrator;
import android.os.VibrationEffect;


import com.luciom.vlc.VlcDecoder;
import com.luciom.vlc.VlcDecoderError;
import com.luciom.vlc.VlcDecoderFactory;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
    private VlcDecoder mVlcDecoder = null;
    private WebView myWebView;
    private Boolean debug = true;
    private String current_code = "";

    private static final String lightList = "{\n" +
            "    \"AAA\":\"BBBB\",\n" +
            "    \"CCCC\":\"DDDD\",\n" +
            "    \"timestamp\":1563739549,\n" +
            "    \"09b3f\": \"https://dev.sfi.st/lifizonenet/lifizone/castel-del-monte/09b3f/\",\n" +
            "    \"009b3f\": \"https://dev.sfi.st/lifizonenet/lifizone/castel-del-monte/09b3f/\",\n" +
          //  "    \"01b94\": \"https://dev.sfi.st/lifizonenet/lifizone/castel-del-monte/01b94/\",\n" +
          //  "    \"001b94\": \"https://dev.sfi.st/lifizonenet/lifizone/castel-del-monte/01b94/\",\n" +
            "    \"ff89ab\": \"https://dev.sfi.st/lifizonenet/lifizone/barracco/ff89ab/\",\n" +
            "    \"011728\": \"https://dev.sfi.st/lifizonenet/lifizone/barracco/011728/\",\n" +
            "    \"11728\": \"https://dev.sfi.st/lifizonenet/lifizone/barracco/011728/\",\n" +
            "    \"34c46\": \"https://dev.sfi.st/lifizonenet/lifizone/castel-del-monte/34c46/\",\n" +
            "    \"034c46\": \"https://dev.sfi.st/lifizonenet/lifizone/castel-del-monte/34c46/\",\n" +
            "    \"11728\": \"https://dev.sfi.st/lifizonenet/lifizone/castel-del-monte/11728/\",\n" +
            "    \"001b94\": \"https://dev.sfi.st/lifizonenet/lifizone/castel-del-monte/11728/\",\n" +
            "    \"01b94\": \"https://dev.sfi.st/lifizonenet/lifizone/castel-del-monte/11728/\",\n" +
            "    \"011728\": \"https://dev.sfi.st/lifizonenet/lifizone/castel-del-monte/11728/\",\n" +
            "    \"0ea2b\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi01/\",\n" +
            "    \"00ea2b\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi01/\",\n" +
            "    \"03f9c8\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi02/\",\n" +
            "    \"3f9c8\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi02/\",\n" +
            "    \"01d99f\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi03/\",\n" +
            "    \"1d99f\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi03/\",\n" +
            "    \"01764e\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi04/\",\n" +
            "    \"1764e\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi04/\",\n" +
            "    \"034749\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi05/\",\n" +
            "    \"34749\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi05/\",\n" +
            "    \"02185b\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi06/\",\n" +
            "    \"2185b\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi06/\",\n" +
            "    \"01411d\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi07/\",\n" +
            "    \"1411d\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi07/\",\n" +
            "    \"0361c3\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi08/\",\n" +
            "    \"361e3\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi08/\",\n" +
            "    \"01e2e7\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi09/\",\n" +
            "    \"1e2e7\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi09/\",\n" +
            "    \"031623\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi10/\",\n" +
            "    \"31623\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi10/\",\n" +
            "    \"008e60\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi11/\",\n" +
            "    \"08e60\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi11/\",\n" +
            "    \"0092b6\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi12/\",\n" +
            "    \"092b6\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi12/\",\n" +
            "    \"038e56\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi13/\",\n" +
            "    \"38e56\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi13/\",\n" +
            "    \"00eb8c\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi14/\",\n" +
            "    \"0eb8c\": \"https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi14/\"\n" +

            " }";

    private static JSONObject obj = new JSONObject();
    private Object Vibrator;


    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }


    private final Handler.Callback vlcCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(final Message msg) {
            if (debug)
              //  Toast.makeText(getBaseContext(), "Caricamento...",
              //          Toast.LENGTH_LONG).show();
            if (msg.what == VlcDecoder.MsgWhat.STARTED.value && msg.obj instanceof VlcDecoder.VlcDecoderSource) {
                switch ((VlcDecoder.VlcDecoderSource) msg.obj) {
                    case AUDIO:
                        if (debug)
                            Toast.makeText(getBaseContext(), "AUDIO",
                                    Toast.LENGTH_LONG).show();
                        break;
                    case CAMERA_ANY:
                       if (debug)
                            //Toast.makeText(getBaseContext(), "CAM_ANY",
                            //        Toast.LENGTH_LONG).show();
                        break;
                    case CAMERA_BACK:
                        if (debug)
                            //Toast.makeText(getBaseContext(), "CAM_BACK",
                            //        Toast.LENGTH_LONG).show();
                        break;
                    case CAMERA_FRONT:
                        if (debug)
                            //Toast.makeText(getBaseContext(), "CAM_FRONT",
                            //        Toast.LENGTH_LONG).show();
                        break;
                    case AUTO:
                    default:
                        break;
                }

            } else if (msg.what == VlcDecoder.MsgWhat.STOPPED.value) {
                //if (debug)
                    //Toast.makeText(getBaseContext(), "STOPPED",
                     //       Toast.LENGTH_LONG).show();

            } else if (msg.what == VlcDecoder.MsgWhat.LOST.value) {
                //getSupportActionBar().setTitle("DISCONNECT");
                // Toast.makeText(getBaseContext(), "DISCONNESSO",
                //Toast.LENGTH_LONG).show();


            } else if (msg.what == VlcDecoder.MsgWhat.UID.value && msg.obj instanceof byte[]) {


                final byte[] filtered_uid = (byte[]) msg.obj;
                final StringBuilder sb_newUid = new StringBuilder("");
                for (int i = 0; i < filtered_uid.length; i++) {
                    sb_newUid.append(String.format("%02X", filtered_uid[i]));

                }
                //getSupportActionBar().setTitle("CONNECT");
                //Toast.makeText(getBaseContext(), "CONNESSO",
                //Toast.LENGTH_LONG).show();

                String id = sb_newUid.toString();
                id = id.toLowerCase();
                if(!current_code.equals(id)) {
                    current_code = id;
                    Toast.makeText(getBaseContext(), "Caricamento...", Toast.LENGTH_LONG).show();
                }
                System.out.println("********"+id);
                System.out.println("********OBJ: "+obj);
                if (obj.has(id)) {
                    try {
                        String url = obj.getString(id);
                        System.out.println("********"+id + " " + url);
                        myWebView.loadUrl(url);
                        stopVlc();
                        startVlc();
                        //myWebView.loadUrl("file:///android_asset/home.html?id=" + id);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                   if (debug)
                        Toast.makeText(getBaseContext(), sb_newUid.toString(),
                                Toast.LENGTH_LONG).show();
                }
            } else if (msg.what == VlcDecoder.MsgWhat.ERROR.value && msg.obj instanceof VlcDecoderError) {
                VlcDecoderError decError = (VlcDecoderError) msg.obj;

                System.out.println(decError.errorDescription);
                if(debug)
                    Toast.makeText(getBaseContext(), "NOT COMPATIBLE",
                            Toast.LENGTH_LONG).show();

            } else {
                return false;
            }
            return true;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //myToolbar.getOverflowIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        //setSupportActionBar(myToolbar);
        myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");
        // Change the URL to point to your own server
        //myWebView.loadUrl("https://ilmondonews.it/mauro/testpage/pag1.html");

        //SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        //String highScore = sharedPref.getString("lingua", "italiano");
        //myWebView.loadUrl("file:///android_asset/home.html?lingua="+highScore);
        myWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        myWebView.loadUrl("file:///android_asset/pages/home-enjoy.html");


        //LOAD LIGHTS


        try {
            obj = new JSONObject(lightList);
        } catch (JSONException e) {

            e.printStackTrace();
        }
        System.out.println("***** OBJ Prima di aggiorna:"+ obj);
        System.out.println("***** LIST Prima di aggiorna:"+ lightList);


        //****TEST LIFI*****

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED)

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 200);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO)
                == PackageManager.PERMISSION_DENIED)

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 200);

        aggiornalista();
    }

    private void aggiornalista() {
        new Jsontask().execute("https://lifizone.net/wp-json/lifizone/v1/lifipages-list");
        
    }

    /*** private void aggiornalista(){
        //caricare lista 1

         //JSONArray listaScaricata;
         //JSONObject obj;
         for (int i = 0; i < listaScaricata.length(); i++){
             JSONObject lampada = listaScaricata.getJSONObject(i);
             obj.put(lampada.getString("lifi_tag"),lampada.getString("url"));
         }

         //cariare lista 2 online https://dev.sfi.st/lifizonenet/wp-json/lifizone/v1/lifipages-list
         //append delle due liste
     }***/



    @Override
    public void onResume() {
        super.onResume();

        startVlc();
    }

    public void vibra() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            vibrator.vibrate(500);
        }
    }


    @Override
    public void onPause() {
        super.onPause();

        stopVlc();
    }

    private void startVlc() {
//		mVlcDecoder = VlcDecoderFactory.getDecoder(VlcDecoderSource.AUTO, getApplicationContext(), vlcCallback, OAUTH_TOKEN, GROUP_LOCATION);
        mVlcDecoder = VlcDecoderFactory.getDecoder(VlcDecoder.VlcDecoderSource.CAMERA_BACK,getApplicationContext(), vlcCallback);
        if (mVlcDecoder != null) {
            mVlcDecoder.start();
        }
    }


    private void stopVlc() {
        if (mVlcDecoder != null) {
            mVlcDecoder.stop();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // switch(item.getItemId()){
        // case R.id.italian_settings:
        //salvalingua("italiano");
        // myWebView.loadUrl("file:///android_asset/home.html?lingua="+"italiano");
        // break;
        // case R.id.english_settings:
        //salvalingua("inglese");
        //myWebView.loadUrl("file:///android_asset/home.html?lingua="+"inglese");
        //break;


        return super.onOptionsItemSelected(item);
        //}
        // public void salvalingua(String lingua){
        //SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedPref.edit();
        // editor.putString("lingua", lingua);
        // editor.apply();
        //}

    }

    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}

