package com.jm.newvista.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import com.jm.newvista.R;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.GenreModel;
import com.jm.newvista.mvp.view.GenreView;
import com.jm.newvista.ui.activity.SearchResultActivity;
import com.jm.newvista.util.ApplicationUtil;
import com.jm.newvista.util.LanguageUtil;

import java.util.Locale;

public class GenrePresenter extends BasePresenter<GenreModel, GenreView> {
    private GenreModel genreModel;
    private GenreView genreView;

    public GenrePresenter() {
        genreModel = new GenreModel();
        super.BasePresenter(genreModel);
    }

    public void clickChipView(String chipText) {
        Context context = ApplicationUtil.getContext();
        Resources resources = ApplicationUtil.getContext().getResources();
        Configuration config = resources.getConfiguration();
        if (config.locale == Locale.CHINA) {
            Log.v("Locale", "China");
            String[] genresArray = context.getResources().getStringArray(R.array.genres_array);
            int index;
            for (index = 0; index < genresArray.length; index++) {
                if (chipText.equals(genresArray[index])) {
                    break;
                }
            }

            String[] rawGenresArray = LanguageUtil.getStringArrayToEnglish(R.array.genres_array);

            Intent intent = new Intent(context, SearchResultActivity.class);
            intent.putExtra("from", "Genre");
            intent.putExtra("genre", rawGenresArray[index]);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

            config.locale = Locale.CHINA;
            DisplayMetrics dm = resources.getDisplayMetrics();
            resources.updateConfiguration(config, dm);
        } else {
            Intent intent = new Intent(context, SearchResultActivity.class);
            intent.putExtra("from", "Genre");
            intent.putExtra("genre", chipText);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
