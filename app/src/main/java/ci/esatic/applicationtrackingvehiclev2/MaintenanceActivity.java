package ci.esatic.applicationtrackingvehiclev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

import ci.esatic.applicationtrackingvehiclev2.services.CarburantReponse;
import ci.esatic.applicationtrackingvehiclev2.services.Constante;
import ci.esatic.applicationtrackingvehiclev2.services.JsonPlaceHolderApi;
import ci.esatic.applicationtrackingvehiclev2.services.MaintenanceResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MaintenanceActivity extends AppCompatActivity {

    private String myToken;
    private Integer valeur3;
    private TextView txtError;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private ImageView imageViewBack;
    private Button button1;
    private Button button2;

    private TextInputEditText textNumber;
    private TextInputEditText textStatus;
    private TextInputEditText textMontant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);

        SharedPreferences sharedPreferences = getSharedPreferences("Monstorage", Context.MODE_PRIVATE);
        myToken = sharedPreferences.getString("AuthToken", "valeurParDefaut");
        valeur3 = sharedPreferences.getInt("Chauffeur_vehicule_id", 0);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constante.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        this.imageViewBack = findViewById(R.id.left);

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

        this.textNumber = findViewById(R.id.NumeroFacture);
        this.button2 = findViewById(R.id.boutonAnnuler);
        this.textStatus = findViewById(R.id.status);
        this.textMontant = findViewById(R.id.montant);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    textNumber.setText("");
                    textStatus.setText("");
                    textMontant.setText("");
            }
        });

        this.button1 = findViewById(R.id.boutonValider);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = textNumber.getText().toString();
                String detail = textStatus.getText().toString();
                Integer mon = Integer.parseInt(textMontant.getText().toString());
                // valider
                ajouterMaintenance(myToken, num, detail, mon, valeur3);
            }
        });
    }

    private void ajouterMaintenance(String t, String num, String detail, Integer mon, Integer idv) {


        // Ajouter le token à l'en-tête
        String authorizationHeader = "Bearer " + myToken;

        Call<MaintenanceResponse> call = jsonPlaceHolderApi.maintenance(authorizationHeader, num, detail, mon, idv);

        call.enqueue(new Callback<MaintenanceResponse>() {
            @Override
            public void onResponse(Call<MaintenanceResponse> call, Response<MaintenanceResponse> response) {
                MaintenanceResponse reponse = response.body();

                if (!response.isSuccessful()){
                    txtError.setText("Erreur de connexion");
                    return;
                } else {
                    Toast.makeText(MaintenanceActivity.this, reponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MaintenanceResponse> call, Throwable t) {
                txtError.setText(t.getMessage());
            }
        });
    }
}