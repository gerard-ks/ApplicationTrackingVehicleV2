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
import ci.esatic.applicationtrackingvehiclev2.services.ChauffeurResponse;
import ci.esatic.applicationtrackingvehiclev2.services.Constante;
import ci.esatic.applicationtrackingvehiclev2.services.JsonPlaceHolderApi;
import ci.esatic.applicationtrackingvehiclev2.services.LocalisationResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarburantActivity extends AppCompatActivity {

    private TextView txtError;

    private String myToken;
    private int myIdVehicule;
    private Integer valeur3;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private ImageView imageViewBack;
    private Button button1;
    private Button button2;
    private TextInputEditText textNumber;
    private TextInputEditText textLitre;
    private TextInputEditText textMontant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carburant);

        SharedPreferences sharedPreferences = getSharedPreferences("Monstorage", Context.MODE_PRIVATE);
        myToken = sharedPreferences.getString("AuthToken", "valeurParDefaut");
        myIdVehicule = sharedPreferences.getInt("IdVehicule", 0);
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
        this.textLitre = findViewById(R.id.NombreLitre);
        this.textMontant = findViewById(R.id.montant);


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textNumber.setText("");
                textLitre.setText("");
                textMontant.setText("");
            }
        });

        this.button1 = findViewById(R.id.boutonValider);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = textNumber.getText().toString();
                Double li = Double.parseDouble(textLitre.getText().toString());
                Integer mon = Integer.parseInt(textMontant.getText().toString());

                // valider
                ajouterCarburant(myToken, num, li, mon, valeur3);
            }
        });
    }

    private void ajouterCarburant(String t, String num, Double detail, Integer mon, Integer idv) {

        // Ajouter le token à l'en-tête
        String authorizationHeader = "Bearer " + myToken;

        Call<CarburantReponse> call = jsonPlaceHolderApi.carburant(authorizationHeader, num, detail, mon, idv);

        call.enqueue(new Callback<CarburantReponse>() {
            @Override
            public void onResponse(Call<CarburantReponse> call, Response<CarburantReponse> response) {
                CarburantReponse reponse = response.body();

                if (!response.isSuccessful()){
                    txtError.setText("Erreur de connexion");
                    return;
                } else {
                    Toast.makeText(CarburantActivity.this, reponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CarburantReponse> call, Throwable t) {
                txtError.setText(t.getMessage());
            }
        });
    }


}