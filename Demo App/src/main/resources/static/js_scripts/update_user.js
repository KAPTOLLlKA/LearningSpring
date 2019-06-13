window.onload = function () {
    $.ajax({
        url: "/users/id",
        type: "GET",
        headers: {"authorization": getCookie("user_token")},
        contentType: "application/json",
        success: function (user) {
            $('#username_c').html("Current username: " + user.username);
            $('#mail_c').html("Current mail: " + user.mail);
            $('#about_c').html("About you: " + user.about);
        },
        error: function (jqXHR) {
            $('#wrong').html(JSON.parse(jqXHR.responseText).message);
        }
    })
};

function update_user() {
    let pass = $('#password').val();
    let re_pass = $('#re-type').val();

    if (pass === re_pass) {
        $.ajax({
            url: "/users/update",
            type: "POST",
            headers: {"authorization": getCookie("user_token")},
            contentType: "application/json",
            data: JSON.stringify({
                username: $('#username').val(),
                mail: $('#mail').val(),
                password: pass,
                about: $('#about').val()
            }),
            success: function () {
                window.location = "/topics.html";
            },
            error: function (jqXHR) {
                $('#wrong').html(JSON.parse(jqXHR.responseText).message);
            }
        })
    } else {
        $('#wrong').html("Passwords don't match");
    }
}