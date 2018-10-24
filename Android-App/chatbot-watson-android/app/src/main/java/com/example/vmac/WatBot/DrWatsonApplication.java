package com.example.vmac.WatBot;

import android.app.Application;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

public class DrWatsonApplication extends Application {

    public static DiseasesJson diseasesJson;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDiseasesJson();
    }

    public static String WORKSPACE_ID = WorkspaceIds.TB;
    public static String currentDiseaseName = Diseases.TB;

    public static class WorkspaceIds {
//        public static String IRON = "6e8cfded-d07d-4df9-8ebd-c193306c32c5";
        public static String IRON = "0d61ba5d-5f73-4700-b331-9ea9ae300a48";
//        public static String TB = "d6822c8b-9c1b-460c-8271-0d12b6502a6b";
        public static String TB = "5e5465b9-e8e7-4b85-b63d-b62a0708432d";
//        public static String TB = "51a3b753-4a13-4e2a-a847-389eb89e5a94";
    }

    public static class Diseases {
        public static String IRON_DEFICIENCY = "iron_deficiency";
        public static String TB = "tuberculosis";
    }

    public static void setWorkspaceId(String workspaceId) {
        WORKSPACE_ID = workspaceId;
    }

    public static void setCurrentDiseaseName(String diseaseName) {
        currentDiseaseName = diseaseName;
    }

    private void setupDiseasesJson() {
        Gson gson = new Gson();
        String json = null;
        try {
            InputStream inputStream = getAssets().open("diseases.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        diseasesJson = gson.fromJson(json, DiseasesJson.class);
    }
}
