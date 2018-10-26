$(function() {
    tabClick("0");
});
function tabClick(pageIndex) {
    getMoreAnnouncementList(pageIndex);

}

function getMoreAnnouncementList(pageIndex) {
    var data = {"p": pageIndex}
    $.ajax({
        type: "post",
        url: "/Mobile/Account/getMoreAnnouncementList",
        data: data,
        dataType: "json",
        beforeSend: function(XMLHttpRequest) {

        },
        success: function(data) {
            if (data.status.status == 1) {
                $("#pageIndex").val(data.status.data.pageIndex)
                $("#uncollected").append(data.status.data.html);
            } else {
                $("#wxMore").hide();
                $("#wxLoad").hide();
                $("#wxNo").show();
            }
        },
        complete: function(XMLHttpRequest, textStatus) {

        },
        error: function() {

        }
    });



}
