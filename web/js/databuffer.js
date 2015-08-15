/**
 * Created by ZHANGJA4 on 8/14/2015.
 */
$(document).ready(function () {
    var bodyHtml = $("body");
    var statusMap = bodyHtml.data("statusMap");
    if (typeof(statusMap) == "undefined") {
        jQuery.get("/StatusServlet", {}, function(data) {
            console.log(data.statusMap);
            bodyHtml.data("statusMap", data);
        });
    }

    var foodType = bodyHtml.data("foodTypeMap");
    if (typeof(foodType) == "undefined") {
        jQuery.get("/FoodTypeServlet",{},function(data){
            console.log(data.foodTypeMap);
            bodyHtml.data("foodTypeMap", data);
            setFoodType();
        });
    } else {
        setFoodType();
    }


    function setFoodType() {
        var bodyHtml = $("body");
        var foodTypeMapJson = bodyHtml.data("foodTypeMap");
        var foodTypeArrayJson = foodTypeMapJson["foodTypeMap"];
        var foodList = $("#foodList");
        $(eval(foodTypeArrayJson)).each(function(index, item) {
            $(foodList).prepend("<li>" +
                "<a href=\"../FoodSerlvet?typeID=" + item.foodTypeID + "\">" + item.foodTypeName + "<\/a>" + "<\/li>");
        });
    }

})
