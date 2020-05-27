package com.llweiya.ysx.starchef.business.community.presenter;

import com.llweiya.ysx.starchef.business.community.interactor.CommunityInteractor;
import com.llweiya.ysx.starchef.business.community.model.FavoriteItemModel;
import com.llweiya.ysx.starchef.business.community.view.FavoriteView;
import com.llweiya.ysx.starchef.common.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class FavoritePresenter extends BasePresenter<CommunityInteractor, FavoriteView> {

    private static int pageSize = 10;

    public void getUserFavoriteItem(int pageNum) {
        List<FavoriteItemModel> retList = new ArrayList<>();
        for (int i = 0 ; i < 19 ; i++) {
            FavoriteItemModel itemModel = new FavoriteItemModel();
            itemModel.itemId = i;
            itemModel.threadTitle = "据说是一位日本老师傅亲述的句子";

            itemModel.userAvatar = "";
            itemModel.userNickname = "_tristan_yan";
            itemModel.likeCount = 55;
            if (i == 0 || i == 5 || i == 9 || i == 18) {
                itemModel.isLike = true;
                itemModel.threadImg = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590040526237&di=c56b428346d6b24be1a421a6ddd57033&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20170901%2Fe086d64751a44312866a81fb5f462326.jpeg";
            } else {
                itemModel.isLike = false;
                itemModel.threadImg = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1562673038836&di=821b65c5efe39f282e92509bc92666f9&imgtype=0&src=http%3A%2F%2Fimg.mp.sohu.com%2Fupload%2F20170811%2F1218825498ec49db8d1cbe621a8ae2b8_th.png";
            }
            retList.add(itemModel);
        }
        view.getDataSuccess(retList);
    }

}
