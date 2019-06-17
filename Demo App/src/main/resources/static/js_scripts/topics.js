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
    let searchFor = $('#search').val().split(' ').join('+');
    if (searchFor.length > 0) {
        $.ajax({
            url: "/topics/search/" + searchFor,
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
    } else {
        load();
    }
}

function addTopics(collection) {
    $.ajax({
        url: "/users/id",
        type: "GET",
        headers: {"authorization": getCookie("user_token")},
        contentType: "application/json",
        success: function (user) {
            let buttonAdded = false;
            document.getElementById('topics').innerHTML = "";
            for (let i = collection.length - 1; i >= 0; --i) {
                let node = document.createElement("div");
                node.setAttribute("id", "div" + i);
                node.setAttribute('style', "background-color: lightblue; color: black; text-align: justify; padding-left: 20px; margin: 20px");

                let title = document.createElement("h2");
                title.setAttribute("id", "title" + i);
                title.innerHTML = collection[i].title;

                let content = document.createElement("p");
                content.setAttribute("id", "content" + i);
                content.innerHTML = collection[i].content;
                content.setAttribute('style', 'font-size: large');

                let posted = document.createElement("p");
                posted.setAttribute("id", "posted" + i);
                posted.innerHTML = "Posted by: " + collection[i].postedBy + "<br>Posted at: " + collection[i].postedAt;
                posted.setAttribute('style', 'font-size: medium');

                node.appendChild(title);
                node.appendChild(content);
                node.appendChild(posted);
                if (!buttonAdded && user.username === collection[i].postedBy) {
                    let edit = document.createElement("button");
                    edit.setAttribute("type", "button");
                    edit.addEventListener("click", () => {
                        editTopic(i);
                    });
                    edit.innerHTML = "Edit";
                    node.appendChild(edit);
                    node.appendChild(document.createElement("br"));
                    buttonAdded = true;
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

function editTopic(i) {
    let title = document.getElementById("title" + i).innerHTML;
    let content = document.getElementById("content" + i).innerHTML;
    let posted = document.getElementById("posted" + i).innerHTML;
    let divElem = document.getElementById("div" + i);
    divElem.innerHTML = "";

    let label = document.createElement("label");

    let titleField = document.createElement("input");
    titleField.setAttribute("id", "title" + i);
    titleField.setAttribute("placeholder", "Title");
    titleField.value = title;

    let contentArea = document.createElement("textarea");
    contentArea.setAttribute("id", "content" + i);
    contentArea.setAttribute("placeholder", "Content");
    contentArea.setAttribute("rows", "7");
    contentArea.setAttribute("cols", "70");
    contentArea.value = content;

    let postedP = document.createElement("p");
    postedP.setAttribute("id", "posted" + i);
    postedP.innerHTML = posted;

    let submitButton = document.createElement("button");
    submitButton.setAttribute("type", "button");
    submitButton.addEventListener("click", () => {
        submitTopicEdition(i);
    });
    submitButton.innerHTML = "Submit";

    let cancelButton = document.createElement("button");
    cancelButton.setAttribute("type", "button");
    cancelButton.addEventListener("click", () => {
        exitTopicEdition(i);
    });
    cancelButton.innerHTML = "Cancel";

    label.appendChild(titleField);
    label.appendChild(contentArea);
    label.appendChild(postedP);

    divElem.appendChild(label);
    divElem.appendChild(cancelButton);
    divElem.appendChild(submitButton);
    divElem.appendChild(document.createElement("br"));
    divElem.appendChild(document.createElement("br"));
}

function submitTopicEdition(i) {
    $.ajax({
        url: "/topics/update",
        type: "POST",
        data: JSON.stringify({
            title: document.getElementById("title" + i).value,
            content: document.getElementById("content" + i).value,
            postedBy: getCookie("user_token")
        }),
        headers: {"authorization": getCookie("user_token")},
        contentType: "application/json",
        success: function () {
            exitTopicEdition(i);
        }
    });
}

function exitTopicEdition(i) {
    $.ajax({
        url: "/topics",
        type: "GET",
        headers: {"authorization": getCookie("user_token")},
        contentType: "application/json",
        success: function (collection) {
            let title = collection[i].title;
            let content = collection[i].content;
            let posted = "Posted by: " + collection[i].postedBy + "<br>Posted at: " + collection[i].postedAt;
            let divElem = document.getElementById("div" + i);
            divElem.innerHTML = "";

            let titleH2 = document.createElement("h2");
            titleH2.setAttribute("id", "title" + i);
            titleH2.innerHTML = title;

            let contentP = document.createElement("p");
            contentP.setAttribute("id", "content" + i);
            contentP.setAttribute('style', 'font-size: large');
            contentP.innerHTML = content;

            let postedP = document.createElement("p");
            postedP.setAttribute("id", "posted" + i);
            postedP.setAttribute('style', 'font-size: medium');
            postedP.innerHTML = posted;

            //Appending children
            divElem.appendChild(titleH2);
            divElem.appendChild(contentP);
            divElem.appendChild(postedP);
            let edit = document.createElement("button");
            edit.setAttribute("type", "button");
            edit.addEventListener("click", () => {
                editTopic(i);
            });
            edit.innerHTML = "Edit";
            divElem.appendChild(edit);
            divElem.appendChild(document.createElement("br"));
            divElem.appendChild(document.createElement("br"));
        },
        error: function () {
            window.location = "/login.html";
        }
    });
}