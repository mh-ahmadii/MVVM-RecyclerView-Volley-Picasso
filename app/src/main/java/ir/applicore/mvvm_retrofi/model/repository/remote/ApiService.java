package ir.applicore.mvvm_retrofi.model.repository.remote;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ir.applicore.mvvm_retrofi.model.User;
import ir.applicore.mvvm_retrofi.viewmodel.UserViewModel;

public class ApiService {
    private static final String BASE_URL = "https://reqres.in/api/users?page=2";
    public List<UserViewModel> userViewModelList(Context context) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        List<UserViewModel> userViewModelList = new ArrayList<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, BASE_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int id = jsonObject.getInt("id");
                                String firstName = jsonObject.getString("first_name");
                                String lastName = jsonObject.getString("last_name");
                                String email = jsonObject.getString("email");
                                String avatar = jsonObject.getString("avatar");
                                User user = new User(id, firstName, lastName, email, avatar);
                                UserViewModel userViewModel = new UserViewModel(user);
                                userViewModelList.add(userViewModel);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        //mutableLiveData.setValue(userViewModelList);
        requestQueue.add(jsonObjectRequest);
        return userViewModelList;
    }
}











//        private static final String BASE_URL = "https://jsonplaceholder.typicode.com/comments";
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BASE_URL, null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//
//                        for (int i = 0; i < response.length(); i++) {
//                            try {
//                                JSONObject jsonObject = response.getJSONObject(i);
//                                int id = jsonObject.getInt("id");
//                                String firstName = jsonObject.getString("name");
//                                String lastName = jsonObject.getString("name");
//                                String email = jsonObject.getString("email");
//                                String avatar = jsonObject.getString("body");
//                                User user = new User(id, firstName, lastName, email, avatar);
////                              User user = new User(i, "" + i, "" + i, "" + i, "" + i);
////                                User user = new User(
////                                        jsonObject.getInt("id"),
////                                        jsonObject.getString("name"),
////                                        jsonObject.getString("name"),
////                                        jsonObject.getString(email),
////                                        jsonObject.getString("email"));
//                                UserViewModel userViewModel = new UserViewModel(user);
//                                userViewModelList.add(userViewModel);
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
////                            mutableLiveData.setValue(userViewModelList);
//                        }
//                    }
//
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
