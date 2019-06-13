window.onload = function () {
    if (getCookie("user_token").length <= 1) {
        window.location = "/topics.html";
    }
};
//Fix this, add search panel, add button for returning to topics to user page and add_topic page.