document.addEventListener('DOMContentLoaded', loadHandler);

async function loadHandler(event) {
    event.preventDefault();

    let response = await fetch('api/principal');
    let principal = await response.json();
    let parentDiv = document.getElementById("navbarPrincipalData");
    let div = document.createElement('div');
    div.className = "navbar-brand";
    div.innerHTML = '<b>' + principal.email + '</b>' + ' with roles: ' + principal.rolesString;
    parentDiv.insertBefore(div, parentDiv.firstChild);
}
