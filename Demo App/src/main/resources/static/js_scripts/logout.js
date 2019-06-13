function logout() {
    $.ajax({
        url: "/users/logout",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            token: getCookie("user_token")
        }),
        success: function () {
            delete_cookie("user_token");
            window.location = "/login.html";
        },
        error: function (jqXHR) {
            $('#wrong').html(JSON.parse(jqXHR.responseText).message);
        }
    });
}