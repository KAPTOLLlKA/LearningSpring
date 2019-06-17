window.onload = function () {
    $.ajax({
        url: "/users/is_valid",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            token: getCookie("user_token")
        }),
        success: function () {
            window.location = "/topics.html";
        },
        error: function () {
            document.getElementById("username").addEventListener("keydown", (e) => {
                checkEnterLogin(e);
            });
            document.getElementById("password").addEventListener("keydown", (e) => {
                checkEnterLogin(e);
            })
        }
    });
};

function checkEnterLogin(e) {
    if (e.key === 'Enter') {
        login();
    }
}

function login() {
    $.ajax({
        url: "/users/login",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            username: $('#username').val(),
            password: $('#password').val()
        }),
        success: function (token) {
            document.cookie = "user_token=" + token;
            window.location = "/topics.html";
        },
        error: function (jqXHR) {
            $('#wrong').html(JSON.parse(jqXHR.responseText).message);
        }
    });
}