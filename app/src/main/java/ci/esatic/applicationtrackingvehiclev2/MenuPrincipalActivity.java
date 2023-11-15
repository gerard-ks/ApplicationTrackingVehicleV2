package ci.esatic.applicationtrackingvehiclev2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import ci.esatic.applicationtrackingvehiclev2.services.ChauffeurResponse;
import ci.esatic.applicationtrackingvehiclev2.services.Constante;
import ci.esatic.applicationtrackingvehiclev2.services.JsonPlaceHolderApi;
import ci.esatic.applicationtrackingvehiclev2.services.LocalisationResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenuPrincipalActivity extends AppCompatActivity implements LocationListener {

    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private TextView txtError;
    double latitude;
    double longitude;

    Integer valeur3;

    String myToken;
    private LocationManager locationManager;
    private static final int PERMS_CALL_ID = 1234;

    private TextView textView1;
    private TextView textView2;

    private ImageButton imageButton1;
    private ImageButton imageButton2;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        this.txtError = findViewById(R.id.TxtError);
        this.textView1 = findViewById(R.id.textNom);
        this.textView2 = findViewById(R.id.textNumero);
        this.imageButton1 = findViewById(R.id.imageButton1);
        this.imageButton2 = findViewById(R.id.imageButton2);
        this.button = findViewById(R.id.button);

        SharedPreferences sharedPreferences = getSharedPreferences("Monstorage", Context.MODE_PRIVATE);
        String valeur1 = sharedPreferences.getString("Username", "valeurParDefaut");
        myToken = sharedPreferences.getString("AuthToken", "valeurParDefaut");
        String valeur2 = sharedPreferences.getString("Immatriculation", "valeurParDefaut");
        valeur3 = sharedPreferences.getInt("Chauffeur_vehicule_id", 0);

        textView1.setText("Nom : " + valeur1);
        textView2.setText("Numero Vehicule : " + valeur2);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constante.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuPrincipalActivity.super.getOnBackPressedDispatcher().onBackPressed();
            }
        });

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MaintenanceActivity.class);
                startActivity(intent);
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CarburantActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPermissions();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(locationManager != null)
        {
            locationManager.removeUpdates(this);
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        envoyerLocalisation(myToken, longitude, latitude, valeur3);
    }

    private void envoyerLocalisation(String token, double longitude, double latitude, Integer valeur) {
        Map<String, String> fields = new HashMap<>();
        fields.put("longitude", String.valueOf(longitude));
        fields.put("latitude", String.valueOf(latitude));
        fields.put("chauffeur_vehicule_id", String.valueOf(valeur));
        // Ajouter le token à l'en-tête
        String authorizationHeader = "Bearer " + token;

        Call<LocalisationResponse> call = jsonPlaceHolderApi.localisation(authorizationHeader, fields);
        call.enqueue(new Callback<LocalisationResponse>() {
            @Override
            public void onResponse(Call<LocalisationResponse> call, Response<LocalisationResponse> response) {
                if (!response.isSuccessful()) {
                    txtError.setText("Erreur de connexion");
                } else {
                    LocalisationResponse reponse = response.body();
                    Toast.makeText(MenuPrincipalActivity.this, reponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LocalisationResponse> call, Throwable t) {
                txtError.setText("ERREUR LORS DE L'ENVOI DE LA LOCALISATION");
            }
        });
    }


    private void checkPermissions()
    {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, PERMS_CALL_ID);
            return;
        }

        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);
        }

        if (locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER))
        {
            locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 10000, 0, this);
        }

        if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
        {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0, this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMS_CALL_ID){
            checkPermissions();
        }
    }
}