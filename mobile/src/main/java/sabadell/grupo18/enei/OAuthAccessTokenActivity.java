package sabadell.grupo18.enei;

import java.io.IOException;
import java.net.URLDecoder;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Execute the OAuthRequestTokenTask to retrieve the request, and authorize the request.
 * After the request is authorized by the user, the callback URL will be intercepted here.
 *
 */
public class OAuthAccessTokenActivity extends Activity {

    private SharedPreferences prefs;
    private Autorizador oAuth2Helper;

    private String redirectURI = "http://localhost";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.prefs = PreferenceManager.getDefaultSharedPreferences(this);

        try {
            oAuth2Helper = new Autorizador();
        }catch (IOException e){

        }
        webview = new WebView(this);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setVisibility(View.VISIBLE);
        webview.getSettings().setDomStorageEnabled(true);
        setContentView(webview);

        String authorizationUrl = oAuth2Helper.getAuthorizationUrl();

        handled=false;

        webview.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url,Bitmap bitmap)  {

            }
            @Override
            public void onPageFinished(final WebView view, final String url)  {

                if (url.startsWith("http://localhost")) {
                    webview.setVisibility(View.INVISIBLE);

                    if (!handled) {
                        new ProcessToken(url,oAuth2Helper).execute();
                    }
                } else {
                    webview.setVisibility(View.VISIBLE);
                }
            }

        });

        webview.loadUrl(authorizationUrl);
    }

    private WebView  webview;

    boolean handled=false;
    private boolean hasLoggedIn;

    @Override
    protected void onResume() {
        super.onResume();
        if (hasLoggedIn) {
            finish();
        }
    }


    private class ProcessToken extends AsyncTask<Uri, Void, Void> {

        String url;
        boolean startActivity=false;


        public ProcessToken(String url,Autorizador oAuth2Helper) {
            this.url=url;
        }

        @Override
        protected Void doInBackground(Uri...params) {


            if (url.startsWith(redirectURI)) {
                //Log.i(Constants.TAG, "Redirect URL found" + url);
                handled=true;
                try {
                    if (url.indexOf("code=")!=-1) {
                        String authorizationCode = extractCodeFromUrl(url);

                        //Log.i(Constants.TAG, "Found code = " + authorizationCode);

                        oAuth2Helper.retrieveAndStoreAccessToken(authorizationCode);
                        startActivity=true;
                        hasLoggedIn=true;

                    } else if (url.indexOf("error=")!=-1) {
                        startActivity=true;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
               //not doing nothing with uri
            }
            return null;
        }

        private String extractCodeFromUrl(String url) throws Exception {
            String encodedCode = url.substring(redirectURI.length()+7,url.length());
            return URLDecoder.decode(encodedCode,"UTF-8");
        }

        @Override
        protected void onPreExecute() {

        }


        /**
         * When we're done and we've retrieved either a valid token or an error from the server,
         * we'll return to our original activity
         */
        @Override
        protected void onPostExecute(Void result) {
            if (startActivity) {
                //Log.i(Constants.TAG," ++++++++++++ Starting mainscreen again");
                startActivity(new Intent(OAuthAccessTokenActivity.this,MainActivity.class));
                finish();
            }

        }

    }
}
