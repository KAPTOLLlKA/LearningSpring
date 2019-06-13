function addTopic() {
    $.ajax({
        url: "/topics/add",
        type: "POST",
        headers: {"authorization": getCookie("user_token")},
        contentType: "application/json",
        data: JSON.stringify({
            title: $('#title').val(),
            content: $('#content').val(),
            postedBy: getCookie("user_token")
        }),
        success: function () {
            window.location = "/topics.html";
        },
        error: function (jqXHR) {
            window.location = "/error_page.html";
            $('#wrong').html(JSON.parse(jqXHR.responseText).message);
        }
    });
}