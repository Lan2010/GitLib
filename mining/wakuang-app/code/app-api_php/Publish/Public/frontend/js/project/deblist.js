$(function () {
    var getRequest = function () {
        var borrowIncome = $("#borrowIncome").val();
        var borrowLimit = $("#borrowLimit").val();
        window.location.href = "/Project/debelist?limit=" + borrowLimit + "&income=" + borrowIncome;
    };
    $("#search_Div a[href='javascript:void(0);']").click(function () {
        var selectValue = $(this).attr('value');
        var selectType = "#" + $(this).parent().attr('value');
        $(selectType).val(selectValue);
        getRequest();
    });
});


