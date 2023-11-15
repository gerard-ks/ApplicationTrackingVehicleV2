package ci.esatic.applicationtrackingvehiclev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationListener;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import ci.esatic.applicationtrackingvehiclev2.services.ChauffeurResponse;
import ci.esatic.applicationtrackingvehiclev2.services.Constante;
import ci.esatic.applicationtrackingvehiclev2.services.JsonPlaceHolderApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {

    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private TextInputEditText username;
    private TextInputEditText password;
    private Button bouton;

    private TextView txtError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.username = findViewById(R.id.textUsername);
        this.password = findViewById(R.id.textPassword);
        this.bouton = findViewById(R.id.boutonValider);
        this.txtError = findViewById(R.id.TxtError);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constante.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u = username.getText().toString().trim();
                String p = password.getText().toString().trim();

                loginChauffeur(u, p);
            }
        });
    }

    private void loginChauffeur(String username, String password) {

        Map<String, String> fields = new HashMap<>();
        fields.put("nomUtilisateur", username);
        fields.put("motPasse", password);

        Call<ChauffeurResponse> call = jsonPlaceHolderApi.login(fields);

        call.enqueue(new Callback<ChauffeurResponse>() {
            @Override
            public void onResponse(Call<ChauffeurResponse> call, Response<ChauffeurResponse> response) {
                //Integer status;
                ChauffeurResponse reponse = response.body();

                if (!response.isSuccessful()){
                    txtError.setText("Erreur de connexion");
                    return;
                } else {
                    SharedPreferences sharedPreferences = getSharedPreferences("Monstorage", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("AuthToken", reponse.getToken());
                    editor.putString("Username", reponse.getChauffeur().getNom() + " "+ reponse.getChauffeur().getPrenom());
                    editor.putInt("IdChauffeur", reponse.getChauffeur().getId());
                    editor.putString("Immatriculation", reponse.getImmatriculation());
                    editor.putInt("IdVehicule", reponse.getIdVehicule());
                    editor.putInt("Chauffeur_vehicule_id", reponse.getIdConduite());

                    editor.apply();

                    Intent intent = new Intent(MainActivity.this, MenuPrincipalActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ChauffeurResponse> call, Throwable t) {
                System.out.println(t);
                System.out.println(call);
                txtError.setText(t.getMessage());
            }
        });
    }


}