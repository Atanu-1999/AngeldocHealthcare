package com.User.angeldochealthcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.User.angeldochealthcare.R;
import com.User.angeldochealthcare.response.Banner_Response;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class Banner_Adapter extends PagerAdapter {

    private Context Mcontext;
    private List<Banner_Response.Result> theSlideItemsModelClassList;
    String bannerImage = "";

    public Banner_Adapter(Context Mcontext, List<Banner_Response.Result> theSlideItemsModelClassList) {
        this.Mcontext = Mcontext;
        this.theSlideItemsModelClassList = theSlideItemsModelClassList;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) Mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View sliderLayout = inflater.inflate(R.layout.banner_layout, null);

        ImageView featured_image = sliderLayout.findViewById(R.id.image_view);
        bannerImage = theSlideItemsModelClassList.get(position).getLink();
        if (!Objects.equals(bannerImage, "")) {
            Picasso.with(Mcontext)
                    .load(theSlideItemsModelClassList.get(position).getLink())
                    .into(featured_image);
        } else {
            Picasso.with(Mcontext)
                    .load(R.drawable.slider_one)
                    .into(featured_image);
        }
        container.addView(sliderLayout);
        return sliderLayout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return 0;

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}
