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

    //
    if ($("#shoppingCarModel").innerHTML == undefined) {
        var resultModelHTml = "";
        resultModelHTml += '<div id="shoppingCarModel" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"';
        resultModelHTml += '     aria-labelledby="mySmallModalLabel">';
        resultModelHTml += '    <div class="modal-dialog modal-lg">';
        resultModelHTml += '        <div id="result-content" class="modal-content">';
        resultModelHTml += '            <div class="modal-header">';
        resultModelHTml += '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span';
        resultModelHTml += '                        aria-hidden="true">&times;</span></button>';
        resultModelHTml += '                <h4 class="modal-title">Modal title</h4>';
        resultModelHTml += '            </div>';
        resultModelHTml += '            <div id="result-body" class="modal-body container-fluid">';
        resultModelHTml += '            </div>';
        resultModelHTml += '            <div class="modal-footer">';
        resultModelHTml += ' <button onclick="location=\'https://www.alipay.com/\'" type="button" class="btn btn-success">go to pay';
        resultModelHTml += '</button>';
        resultModelHTml += '                <button type="button" class="btn btn-primary" data-dismiss="modal">continue</button>';
        resultModelHTml += '            </div>';
        resultModelHTml += '        </div>';
        resultModelHTml += '    </div>';
        resultModelHTml += '</div>';
        $(bodyHtml).prepend(resultModelHTml);
        bindShoppingChart();
    } else {
        bindShoppingChart();
    }

    function bindShoppingChart() {
        $("#shopperChart").on("click", function () {
            loadOrderContent();
            $('#shoppingCarModel').modal();

        });
    }

    //load json data into model contextß
    function loadOrderContent() {
        var orderEntityListJsonObject = $("body").data("orderEntityListJsonObject");
        var orderFoodListJsonObject = $("body").data("orderFoodListJsonObject");
        var allPrice = $("body").data("allPrice");
        $("#result-body").empty();
        for (var i = 0; i < orderEntityListJsonObject.length; i++) {
            var currentOrderEntityJson = orderEntityListJsonObject[i];
            var content = "<div class='row'>";
            content += "<div class='col-md-3'> foodId :<br/>" + currentOrderEntityJson.foodId + '</div>';

            var foodEntity = getFoodEntity(currentOrderEntityJson.foodId);
            if (foodEntity) {
                content += "<div class='col-md-3'> foodId :<br/>" + foodEntity.foodName + '</div>';
            }

            content += "<div class='col-md-3'> orderCount   : X " + currentOrderEntityJson.orderCount + '</div>';
            content += "<div class='col-md-3'> date :<br/>" + currentOrderEntityJson.date + '</div><br/>';
            content += "</div>";
            $("#result-body").prepend(content);
        }
        $("#result-body").append("<div class='col-md-offset-8 col-md-4'>allPrice :" + allPrice + "</div>")
        //$("$result-body").text(orderEntityListJsonObject + '<br/>' + orderFoodListJsonObject);
    }

    function getFoodEntity(foodId) {
        var orderFoodListJsonObject = $("body").data("orderFoodListJsonObject");
        for (var i = 0; i < orderFoodListJsonObject.length; i++) {
            if (orderFoodListJsonObject[i].foodID == foodId) {
                return orderFoodListJsonObject[i];
            }
        }
    }

})

//fetch the data from server
var setShoppingChart = function () {
    $.getJSON("/GetOrderServlet", function (json) {
        console.log(json);
        var orderEntityListJsonObject = $.parseJSON(json.orderEntityList);
        var orderFoodListJsonObject = $.parseJSON(json.orderFoodList);
        var allPrice = $.parseJSON(json.allPrice);
        $("body").data("orderEntityListJsonObject", orderEntityListJsonObject);
        $("body").data("orderFoodListJsonObject", orderFoodListJsonObject);
        $("body").data("allPrice", allPrice);
        $("#numberCount").text(orderEntityListJsonObject.length);
    });
};