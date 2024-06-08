package vn.edu.hcmuaf.fit.travie.user.adapter;

import static vn.edu.hcmuaf.fit.travie.core.shared.constant.AppConstant.INTENT_USER_PROFILE;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.hcmuaf.fit.travie.core.shared.enums.ProfileMenu;
import vn.edu.hcmuaf.fit.travie.databinding.ViewHolderProfileMenuBinding;
import vn.edu.hcmuaf.fit.travie.user.activity.ProfileDetailActivity;
import vn.edu.hcmuaf.fit.travie.user.model.UserProfile;

public class ProfileMenuAdapter extends RecyclerView.Adapter<ProfileMenuAdapter.ProfileMenuViewHolder> {
    ViewHolderProfileMenuBinding binding;
    Context context;

    UserProfile userProfile;
    private final List<ProfileMenu> profileMenuItems = ProfileMenu.getProfileMenuItems();

    public ProfileMenuAdapter(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
        notifyItemChanged(profileMenuItems.indexOf(ProfileMenu.PROFILE_DETAIL));
    }

    @NonNull
    @Override
    public ProfileMenuAdapter.ProfileMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        binding = ViewHolderProfileMenuBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ProfileMenuViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileMenuAdapter.ProfileMenuViewHolder holder, int position) {
        ProfileMenu profileMenu = profileMenuItems.get(position);
        holder.binding.menuTitleTxt.setText(profileMenu.getTitle());
        holder.binding.menuIcon.setImageDrawable(ContextCompat.getDrawable(context, profileMenu.getIcon()));
        holder.binding.cardView.setCardBackgroundColor(ContextCompat.getColor(context, profileMenu.getCardBackgroundColor()));

        holder.binding.menuItem.setOnClickListener(v -> {
            switch (profileMenu) {
                case PROFILE_DETAIL:
                    Intent intent = new Intent(context, ProfileDetailActivity.class);
                    intent.putExtra(INTENT_USER_PROFILE, userProfile);
                    context.startActivity(intent);
                    break;
                case CHANGE_PASSWORD:
                    break;
                case PAYMENT:
                    break;
                case HELP:
                    break;
                case LOGOUT:
                    break;
            }
        });
    }

    @Override
    public int getItemCount() {
        return profileMenuItems.size();
    }

    public static class ProfileMenuViewHolder extends RecyclerView.ViewHolder {
        ViewHolderProfileMenuBinding binding;

        public ProfileMenuViewHolder(ViewHolderProfileMenuBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
