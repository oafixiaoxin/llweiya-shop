package com.llweiya.ysx.starchef.business.community.model;

import java.util.ArrayList;
import java.util.List;

public class CommunityMainCourseModel implements ICommunitySectionType {
    public String title = "";
    public int totalCount = 0;
    public List<UserCourseViewModel> userCourseList = new ArrayList<>();

    @Override
    public int getItemType() {
        return TYPE_RECOMMENDED;
    }
}
