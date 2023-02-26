document.addEventListener("DOMContentLoaded", loadUsersTable)

async function loadUsersTable() {
    let response = await fetch('api/users');
    let users = await response.json();

    let tbody = document.getElementById("usersTableBody");
    let tableRows = '';

    for (let user of users) {
        tableRows += '<tr>'
        tableRows += '<th scope="row"><span>' + user.id + '</span></th>'
        tableRows += '<td><span>' + user.username + '</span></td>'
        tableRows += '<td><span>' + user.secondName + '</span></td>'
        tableRows += '<td><span>' + user.age + '</span></td>'
        tableRows += '<td><span>' + user.email + '</span></td>'
        tableRows += '<td><span>' + user.rolesString + '</span></td>'
        tableRows += '<td>'
        tableRows += '<button type="button" class="btn btn-info" data-bs-toggle="modal"' +
            'data-bs-target="#editModal" onclick="editUserForm(' + user.id + ')">Edit</button>'
        tableRows += '</td>'
        tableRows += '<td>'
        tableRows += '<button type="button" class="btn btn-danger" data-bs-toggle="modal"' +
            'data-bs-target="#deleteModal" onclick="deleteUserForm(' + user.id + ')">Delete</button>'
        tableRows += '</td>'
        tableRows += '</tr>'
    }

    tbody.innerHTML = tableRows;
}

