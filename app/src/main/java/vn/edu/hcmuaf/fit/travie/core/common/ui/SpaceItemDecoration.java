package vn.edu.hcmuaf.fit.travie.core.common.ui;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private final int space;
    private final int orientation;

    public SpaceItemDecoration(int space) {
        this.space = space;
        this.orientation = RecyclerView.VERTICAL;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (orientation == RecyclerView.VERTICAL) {
            outRect.top = space;
            outRect.bottom = space;
        } else {
            outRect.left = space;
            outRect.right = space;
        }
    }
}
