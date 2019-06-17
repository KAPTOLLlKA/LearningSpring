window.onload = function () {
    $.ajax({
        url: "/users/is_valid",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            token: getCookie("user_token")
        }),
        success: function() {
            document.getElementById("title").addEventListener("keydown", (e) => {
                if (e.key === 'Enter') {
                    addTopic();
                }
            });
        },
        error: function () {
            window.location = "/login.html";
        }
    });
};

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