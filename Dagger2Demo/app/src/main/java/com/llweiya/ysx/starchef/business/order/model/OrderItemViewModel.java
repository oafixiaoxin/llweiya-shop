package com.llweiya.ysx.starchef.business.order.model;

import java.util.ArrayList;
import java.util.List;

public class OrderItemViewModel {
    public String itemId = "";
    public String itemPicture = "";
    public String itemName = "";
    public String itemStatus = "";
    public String totalPrice = "";
    public List<FoodItemModel> goodsList = new ArrayList<>();
    public List<String> imageList = new ArrayList<>();
}
