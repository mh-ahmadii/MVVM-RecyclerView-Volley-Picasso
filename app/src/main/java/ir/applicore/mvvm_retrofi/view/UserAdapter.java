package ir.applicore.mvvm_retrofi.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ir.applicore.mvvm_retrofi.R;
import ir.applicore.mvvm_retrofi.databinding.UserRecyclerView;
import ir.applicore.mvvm_retrofi.viewmodel.UserViewModel;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.CustomViewHolder> {
    private List<UserViewModel> userViewModelList = new ArrayList<>();
    private LayoutInflater layoutInflater;

    public UserAdapter(List<UserViewModel> userViewModelList) {
        this.userViewModelList = userViewModelList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        UserRecyclerView userRecyclerView = DataBindingUtil.inflate(layoutInflater, R.layout.recycler_layout, parent, false);
        return new CustomViewHolder(userRecyclerView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.CustomViewHolder holder, int position) {
        holder.customBind(userViewModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return userViewModelList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private UserRecyclerView userRecyclerView;

        public CustomViewHolder(UserRecyclerView userRecyclerView) {
            super(userRecyclerView.getRoot());
            this.userRecyclerView = userRecyclerView;
        }

        public void customBind(UserViewModel userViewModel) {
            this.userRecyclerView.setRecycler(userViewModel);
            this.userRecyclerView.executePendingBindings();
        }
    }
}
