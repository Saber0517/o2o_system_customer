/**
 * 用于缓存所有必要数据，在每次刷新页面的时候
 * Created by ZHANGJA4 on 8/14/2015.
 */
$(document).ready(function () {
    var bodyHtml = $("body");
    var statusMap = bodyHtml.data("statusMap");
    if (typeof(statusMap) == "undefined") {
        jQuery.get("/StatusServlet", {}, function (data) {
            console.log(data.statusMap);
            bodyHtml.data("statusMap", data);
        });
    }

    var foodType = bodyHtml.data("foodTypeMap");
    if (typeof(foodType) == "undefined") {
        jQuery.get("/FoodTypeServlet", {}, function (data) {
            console.log(data.foodTypeMap);
            bodyHtml.data("foodTypeMap", data);
            setFoodType();
        });
    } else {
        setFoodType();
    }

    var userEntity = bodyHtml.data("userEntity");
    if (typeof(userEntity) == "undefined") {
        jQuery.get("/GetUserDataServlet", {}, function (data) {
            console.log(data);
            console.log("userName: " + data.userName);
            bodyHtml.data("userEntity", data);
            setUserName();
            getOrder(data.userID);
        });
    } else {
        setUserName();
        getOrder(userEntity.userID);
    }

    function getOrder(userID) {
        var orderEntitys = bodyHtml.data("orderEntity");
        if (typeof(orderEntitys) == "undefined") {
            jQuery.get("/GetOrderServlet", {"userID": userID}, function (data) {
                console.log(data);
                //console.log("userName: " + data.userName);
                bodyHtml.data("orderEntity", data);

                //
            });
        } else {
            ///
        }
    }


    function setUserName() {
        var bodyHtml = $("body");
        var userEntity = bodyHtml.data("userEntity");
        $("#username").text(userEntity.userName);

    }

    function setFoodType() {
        var bodyHtml = $("body");
        var foodTypeMapJson = bodyHtml.data("foodTypeMap");
        var foodTypeArrayJson = foodTypeMapJson["foodTypeMap"];
        var foodList = $("#foodList");
        $(eval(foodTypeArrayJson)).each(function (index, item) {
            $(foodList).prepend("<li>" +
                "<a href=\"../FoodSerlvet?typeID=" + item.foodTypeID + "\">" + item.foodTypeName + "<\/a>" + "<\/li>");
        });
    }


})
