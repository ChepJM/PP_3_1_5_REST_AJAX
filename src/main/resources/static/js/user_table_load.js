document.addEventListener("DOMContentLoaded", loadUserTable)
// document.getElementById("v-pills-user-tab").addEventListener("click", clickHandler)

async function loadUserTable() {
    let response = await fetch('api/principal');
    let user = await response.json();
    let tbody = document.getElementById("userTableBody");


    let tr = document.createElement('tr');
    tr.innerHTML = '<th scope="row"><span>' + user.id + '</span></th>'
    tr.innerHTML += '<td><span>' + user.username + '</span></td>'
    tr.innerHTML += '<td><span>' + user.secondName + '</span></td>'
    tr.innerHTML += '<td><span>' + user.age + '</span></td>'
    tr.innerHTML += '<td><span>' + user.email + '</span></td>'
    tr.innerHTML += '<td><span>' + user.rolesString + '</span></td>'

    tbody.innerHTML = tr.innerHTML;
}