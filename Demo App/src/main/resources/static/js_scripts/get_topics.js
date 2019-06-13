window.onload = function () {
    $.ajax({
        url: "/topics",
        type: "GET",
        headers: {"authorization": getCookie("user_token")},
        contentType: "application/json",
        success: function (collection) {
            for (let i = collection.length - 1; i >= 0; --i) {
                let node = document.createElement("div");
                node.setAttribute('style', "background-color: lightblue; color: black; text-align: justify; padding-left: 20px; margin: 20px");
                let title = document.createElement("h2");
                title.innerHTML = collection[i].title;
                let content = document.createElement("p");
                content.innerHTML = collection[i].content;
                let postedBy = document.createElement("p");
                postedBy.innerHTML = "Posted by: " + collection[i].postedBy;
                let postedAt = document.createElement("p");
                postedAt.innerHTML = "Posted at: " + collection[i].postedAt;
                node.appendChild(title);
                node.appendChild(content);
                node.appendChild(postedBy);
                node.appendChild(postedAt);
                document.getElementById('topics').appendChild(node);
            }
        },
        error: function (jqXHR) {
            window.location = "/error_page.html";
            $('#wrong').html(JSON.parse(jqXHR.responseText).message);
        }
    });
};