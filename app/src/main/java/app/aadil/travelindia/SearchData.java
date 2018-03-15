package app.aadil.travelindia;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class SearchData {
    private static SearchData data = null;
    private static HashMap<String, List<String>> db;

    private SearchData() {
        db = new HashMap<String, List<String>>();
    }

    public static SearchData getInstance() {
        if(data == null)
            data = new SearchData();
        return data;
    }

    public static void addCity(String City, List<String> Places) {
        List<String> Places_Copy = new ArrayList<>();
        Places_Copy.addAll(Places);
        db.put(City, Places_Copy);
    }

    public static List<String> getCityPlaces(String City) {
        return db.get(City);
    }

    public static List<String> getAllPlaces() {
        List<String> AllPlaces = new ArrayList<>();

        for(String City : db.keySet()) {
            List<String> places = db.get(City);
            for(String place : places)
                AllPlaces.add(place + ", " + City);
        }

        return AllPlaces;
    }

    public Task<QuerySnapshot> getPlacesFromFirestore() {
        FirestoreService firestoreService = FirestoreService.getInstance();
        return firestoreService.getPlaces();
    }
}