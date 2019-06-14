window.onload = function () {
    load();
    document.getElementById("search").addEventListener("keydown", (e) => {
        if (e.key === 'Enter') {
            if ($('#search').val().length > 0) {
                searchTopic();
            } else {
                load();
            }
        }
    });
};

function load() {
    $.ajax({
        url: "/topics",
        type: "GET",
        headers: {"authorization": getCookie("user_token")},
        contentType: "application/json",
        success: function (collection) {
            addTopics(collection);
        },
        error: function () {
            window.location = "/login.html";
        }
    });
}

function searchTopic() {
    $.ajax({
        url: "/topics/search/" + $('#search').val().split(' ').join('+'),
        type: "GET",
        headers: {"authorization": getCookie("user_token")},
        contentType: "application/json",
        success: function (collection) {
            addTopics(collection);
        },
        error: function () {
            window.location = "/login.html";
        }
    })
}

function addTopics(collection) {
    $.ajax({
        url: "/users/id",
        type: "GET",
        headers: {"authorization": getCookie("user_token")},
        contentType: "application/json",
        success: function (user) {
            document.getElementById('topics').innerHTML = "";
            for (let i = collection.length - 1; i >= 0; --i) {
                let node = document.createElement("div");
                node.setAttribute('style', "background-color: lightblue; color: black; text-align: justify; padding-left: 20px; margin: 20px");
                let title = document.createElement("h2");
                title.innerHTML = collection[i].title;
                let content = document.createElement("p");
                content.innerHTML = collection[i].content;
                content.setAttribute('style', 'font-size: large');
                let postedBy = document.createElement("p");
                postedBy.innerHTML = "Posted by: " + collection[i].postedBy + "<br>Posted at: " + collection[i].postedAt;
                postedBy.setAttribute('style', 'font-size: medium');
                node.appendChild(title);
                node.appendChild(content);
                node.appendChild(postedBy);
                if (user.username === collection[i].postedBy) {
                    let edit = document.createElement("button");
                    edit.setAttribute("type", "button");
                    edit.addEventListener("click", () => {
                        editTopic(collection[i]);
                    });
                    edit.innerHTML = "Edit";
                    node.appendChild(edit);
                    node.appendChild(document.createElement("br"));
                }
                node.appendChild(document.createElement("br"));
                document.getElementById('topics').appendChild(node);
            }
        },
        error: function () {
            window.location = "/login.html";
        }
    });
}

function editTopic(topic) {

}