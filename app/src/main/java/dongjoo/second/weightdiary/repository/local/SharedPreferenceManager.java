package dongjoo.second.weightdiary.repository.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import dongjoo.second.weightdiary.common.entity.History;
import dongjoo.second.weightdiary.common.entity.User;


public class SharedPreferenceManager {
    private Context context;

    private SharedPreferences sharedUserPreferences;
    private SharedPreferences sharedHistoryPreferences;

    public SharedPreferenceManager(Context context) {
        this.context = context;

        if (sharedUserPreferences == null) {
            sharedUserPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        }

        if (sharedHistoryPreferences == null) {
            sharedHistoryPreferences = context.getSharedPreferences("histories", Context.MODE_PRIVATE);
        }
    }


    public User getUser() {
        Gson gson = new Gson();
        String json = sharedUserPreferences.getString("user", "");

        User user = gson.fromJson(json, User.class);

        return user;
    }

    public void setUser(User user) {
        SharedPreferences.Editor editor = sharedUserPreferences.edit();

        Gson gson = new Gson();
        String stringUser = gson.toJson(user);
        editor.putString("user", stringUser).apply();
        editor.commit();
    }

    public void removeUser() {
        SharedPreferences.Editor editor = sharedUserPreferences.edit();
        editor.remove("user").apply();
        editor.commit();
    }

    public ArrayList<History> getHistories() {
        Gson gson = new Gson();
        String json = sharedUserPreferences.getString("histories", "");
        Type type = new TypeToken<ArrayList<History>>() {}.getType();
        ArrayList<History> histories = gson.fromJson(json, type);

        return histories;
    }

    public void setHistory(History history) {
        SharedPreferences.Editor editor = sharedUserPreferences.edit();

        Gson gson = new Gson();

        String json = sharedUserPreferences.getString("histories", "");
        Type type = new TypeToken<ArrayList<History>>() {}.getType();
        ArrayList<History> histories = gson.fromJson(json, type);

        if(histories == null) {
            histories = new ArrayList<>();
        }
        histories.add(history);

        String stringHistories = gson.toJson(histories);
        editor.putString("histories", stringHistories).apply();
        editor.commit();
    }

    public void removeHistory(int index) {
        SharedPreferences.Editor editor = sharedUserPreferences.edit();
        Gson gson = new Gson();

        String json = sharedUserPreferences.getString("histories", "");
        Type type = new TypeToken<ArrayList<History>>() {}.getType();
        ArrayList<History> histories = gson.fromJson(json, type);

        histories.remove(index);

        String stringHistories = gson.toJson(histories);
        editor.putString("histories", stringHistories).apply();
        editor.commit();
    }

    public void removeAllHistory(List<History> removeHistories) {
        SharedPreferences.Editor editor = sharedUserPreferences.edit();
        Gson gson = new Gson();

        String json = sharedUserPreferences.getString("histories", "");
        Type type = new TypeToken<ArrayList<History>>() {}.getType();
        ArrayList<History> histories = gson.fromJson(json, type);

        histories.removeAll(removeHistories);

        String stringHistories = gson.toJson(histories);
        editor.putString("histories", stringHistories).apply();
        editor.commit();
    }

    public void removeHistories() {
        SharedPreferences.Editor editor = sharedUserPreferences.edit();
        editor.remove("histories").apply();
        editor.commit();
    }

}
