function getToken() {
    const cookieParts = document.cookie.split('=');
    return cookieParts[1];
}