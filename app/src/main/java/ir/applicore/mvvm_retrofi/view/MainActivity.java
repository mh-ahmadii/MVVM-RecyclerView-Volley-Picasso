package ir.applicore.mvvm_retrofi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import ir.applicore.mvvm_retrofi.R;
import ir.applicore.mvvm_retrofi.databinding.ActivityMainBinding;
import ir.applicore.mvvm_retrofi.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        UserViewModel userViewModel = new UserViewModel(this);
        binding.setViewModel(userViewModel);
        binding.executePendingBindings();
    }
}