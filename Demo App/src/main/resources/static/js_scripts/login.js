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