package com.jm.newvista.mvp.view;

import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.mvp.base.BaseView;

import java.util.List;

/**
 * Created by Johnny on 2/23/2018.
 */

public interface RandomPicksView extends BaseView {
    void onFinishPreparedRandomPicks(List<MovieEntity> randomPicks);
}
