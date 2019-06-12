function login() {
    $.ajax({
        url: "/users/login",
        type: "POST",
        contentType: 'application/json',
        data: JSON.stringify({
            username: $('#username').val(),
            password: $('#password').val()
        }),
        success: function (token) {
            document.cookie = 'user_token' + token;
            window.location = "/users";
        },
        error: function (jqXHR) {
            if (jqXHR.status === 401) {
                $('#wrong').show();
            }
        }
    });
}