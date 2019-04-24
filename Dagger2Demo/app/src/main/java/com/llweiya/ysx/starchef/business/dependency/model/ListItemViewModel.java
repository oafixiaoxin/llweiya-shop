package com.llweiya.ysx.starchef.business.dependency.model;

import java.util.List;

/**
 * Created by ysx on 2018/10/21.
 */

public class ListItemViewModel implements ITest {

    private List<TestModel> content;

    public List<TestModel> getContent() {
        return content;
    }

    public void setContent(List<TestModel> content) {
        this.content = content;
    }

    @Override
    public int getItemType() {
        return ITest.TYPE_LIST;
    }
}
