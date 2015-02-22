package sabadell.grupo18.enei;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.util.store.MemoryDataStoreFactory;

//import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.io.IOException;

/**
 * Created by Oscar on 21/02/2015.
 * IBHD
 */
public class Autorizador {
    private final String CLIENT_ID = "CLI2898747608739vtnwzgvblfawpbbwqbxbagthynhncykfevaaudqk";
    private final String CLIENT_SECRET = "C4ligul4s";

    private final String UserID = "UserSabadell";

    //Guarda los scopes
    private Collection<String> scopes = new ArrayList<String>();

    /** Global instance of the HTTP transport. */
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();

    private final DataStore dataStore;

    private AuthorizationCodeFlow flow;

    public Autorizador() throws IOException{
        String[] vector = {"read"};
        Collections.addAll(this.scopes, vector);
        this.dataStore = MemoryDataStoreFactory.getDefaultInstance().getDataStore("0");
        this.flow = new AuthorizationCodeFlow.Builder(
                BearerToken.authorizationHeaderAccessMethod(),
                HTTP_TRANSPORT,
                JSON_FACTORY,
                new GenericUrl("https://developers.bancsabadell.com/AuthServerBS/oauth/token"),
                new ClientParametersAuthentication(CLIENT_ID, CLIENT_SECRET),
                CLIENT_ID,
                "https://developers.bancsabadell.com/AuthServerBS/oauth/authorize"
        ).setCredentialDataStore(this.dataStore).build();
    }
/*
    public static void Autorizar{
        AuthorizationCodeFlow acf =
        acf.loadCredential("");
        //ifnot authorized
        acf.newAuthorizationUrl();
        //Obtenemos el code
        //Usar code para obtener
        acf.newTokenRequest("AUTORIZATIONCODE");
        //Para guardar y obtener las credenciales para acceder a recursos
        acf.createAndStoreCredential()


    }*/

    public String getAuthorizationUrl() {
        //TODO control de scopes por variables
        String authorizationUrl = flow.newAuthorizationUrl().setRedirectUri("http://localhost").setScopes(this.scopes).build();
        return authorizationUrl;
    }

    public void retrieveAndStoreAccessToken(String authorizationCode) throws IOException {
        TokenResponse tokenResponse = flow.newTokenRequest(authorizationCode).setScopes(scopes).setRedirectUri("http://localhost").execute();
        //Log.i(Constants.TAG, "Found tokenResponse :");
       // Log.i(Constants.TAG, "Access Token : " + tokenResponse.getAccessToken());
        //Log.i(Constants.TAG, "Refresh Token : " + tokenResponse.getRefreshToken());
        flow.createAndStoreCredential(tokenResponse, UserID);
    }

    public String executeApiCall() throws IOException {
        return HTTP_TRANSPORT.createRequestFactory(flow.loadCredential(UserID)).buildGetRequest(new GenericUrl("https://developers.bancsabadell.com/ResourcesServerBS/oauthservices/v1.0.0/cuentasvista")).execute().parseAsString();
    }



}
