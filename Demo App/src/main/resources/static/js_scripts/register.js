function register() {
    let pass = $('#password').val();
    let re_pass = $('#re-type').val();

    if (pass === re_pass) {
        $.ajax({
            url: "/users/register",
            type: "POST",
            contentType: 'application/json',
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
                if (jqXHR.status === 406) {
                    $('#taken').show();
                    $('#pass').hide();
                    $('#mandatory').hide();
                } else if (jqXHR.status === 418) {
                    $('#mandatory').show();
                    $('#pass').hide();
                    $('#taken').hide();
                }
            }
        });
    } else {
        $('#pass').show();
        $('#taken').hide();
        $('#mandatory').hide();
    }
}