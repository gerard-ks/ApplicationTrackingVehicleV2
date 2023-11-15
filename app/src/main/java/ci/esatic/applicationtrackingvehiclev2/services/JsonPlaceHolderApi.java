package ci.esatic.applicationtrackingvehiclev2.services;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.*;
public interface JsonPlaceHolderApi {

    @FormUrlEncoded
    @POST("login")
    Call<ChauffeurResponse> login(@FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("localisation")
    Call<LocalisationResponse> localisation(@Header("Authorization") String authorization, @FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("carburant")
    Call<CarburantReponse> carburant(
            @Header("Authorization") String authorization,
            @Field("numeroFacture") String numeroFacture,
            @Field("nombreLitre") Double nombreLitre,
            @Field("montantCarburant") Integer montantCarburant,
            @Field("chauffeur_vehicule_id") Integer chauffeur_vehicule_id
    );

    @FormUrlEncoded
    @POST("maintenance")
    Call<MaintenanceResponse> maintenance(@Header("Authorization") String authorization,
                                          @Field("numeroFacture") String numeroFacture,
                                          @Field("reparation") String reparation,
                                          @Field("montantMaintenance") Integer montantMaintenance,
                                          @Field("chauffeur_vehicule_id") Integer vehicule_id
                                          );

}
