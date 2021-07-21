package ir.applicore.mvvm_retrofi.viewmodel;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ir.applicore.mvvm_retrofi.BR;
import ir.applicore.mvvm_retrofi.model.User;
import ir.applicore.mvvm_retrofi.model.repository.remote.ApiService;
import ir.applicore.mvvm_retrofi.view.UserAdapter;

public class UserViewModel extends BaseObservable {
    private int id;
    private String firstName;
    private static final String TAG = "UserViewModel";
    private String lastName;
    private String email;
    private String avatar;

    private MutableLiveData<List<UserViewModel>> mutableLiveData = new MutableLiveData<>();

    public UserViewModel(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.avatar = user.getAvatar();
    }

    public UserViewModel(Context context) {
        getUsers(context);
    }

    public void getUsers(Context context) {
        ApiService apiService = new ApiService();
        List<UserViewModel> userViewModelList = apiService.userViewModelList(context);
        mutableLiveData.setValue(userViewModelList);
    }


    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
        notifyPropertyChanged(BR.avatar);
    }

    public void setMutableLiveData(MutableLiveData<List<UserViewModel>> mutableLiveData) {
        this.mutableLiveData = mutableLiveData;
    }

    @Bindable
    public int getId() {
        return id;
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    @Bindable
    public String getAvatar() {
        return avatar;
    }

    public MutableLiveData<List<UserViewModel>> getMutableLiveData() {
        return mutableLiveData;
    }

    @BindingAdapter("bind:recyclerUser")
    public static void recyclerUser(RecyclerView recyclerView, List<UserViewModel> userViewModelList) {
        UserAdapter userAdapter = new UserAdapter(userViewModelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(userAdapter);
    }

    @BindingAdapter("bind:imageURL")
    public static void loadAvatarImage(ImageView imageView, String imageURL) {
        Picasso.get().load(imageURL).into(imageView);
    }
}