window.onload = function () {
    document.getElementById("username").addEventListener("keydown", (e) => {
        checkEnterRegister(e);
    });
    document.getElementById("mail").addEventListener("keydown", (e) => {
        checkEnterRegister(e);
    });
    document.getElementById("password").addEventListener("keydown", (e) => {
        checkEnterRegister(e);
    });
    document.getElementById("re-type").addEventListener("keydown", (e) => {
        checkEnterRegister(e);
    });
    document.getElementById("about").addEventListener("keydown", (e) => {
        checkEnterRegister(e);
    });
};

function checkEnterRegister(e) {
    if (e.key === 'Enter') {
        register();
    }
}

function register() {
    let pass = $('#password').val();
    let re_pass = $('#re-type').val();

    if (pass === re_pass) {
        $.ajax({
            url: "/users/register",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                username: $('#username').val(),
                mail: $('#mail').val(),
                password: pass,
                about: $('#about').val()
            }),
            success: () => {
                window.location = "/login.html";
            },
            error: (jqXHR) => {
                $('#wrong').html(JSON.parse(jqXHR.responseText).message);
            }
        });
    } else {
        $('#wrong').html("Passwords don't match");
    }
}