function getCookie(name) {
    let v = document.cookie.split('=');
    for (let i = 0; i < v.length; ++i) {
        if (v[i] === name) {
            return v[i + 1];
        }
    }
    return null;
}

function set_cookie(name, value, days) {
    let d = new Date;
    d.setTime(d.getTime() + 24*60*60*1000*days);
    document.cookie = name + "=" + value + ";path=/;expires=" + d.toDateString();
}

function delete_cookie(name) {
    set_cookie(name, '', -1);
}
