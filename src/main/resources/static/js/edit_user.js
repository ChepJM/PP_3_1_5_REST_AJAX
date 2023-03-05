async function editUserForm(id) {

    const response = (await fetch('api/user/' + id));
    const user = await response.json();
    const form = document.getElementById("editModalForm");
    form.addEventListener("submit", editUser);
    const fields = ["hiddenId", "id", "username", "secondName", "age", "email", "password", "roleList"];

    console.log(form.elements);
    console.log(user);
    for (const key of fields) {
        if (key === "roleList") {
            for (let option of form.elements[key].options) {
                for (const role of user.roleList) {
                    if (option.value === role["roleName"]) {
                        option.selected = true;
                    }
                }
            }
        } else {
            form.elements[key].setAttribute('value', user[key]);
        }
    }
    form.elements["hiddenId"].setAttribute('value', user["id"]);
}

async function editUser(event) {
    event.preventDefault();
    const form = event.currentTarget;
    const formData = new FormData(form);
    let userObject = {}
    formData.forEach((value, key) => {
        if (key !== 'roleList') {
            userObject[key] = value;
        } else {
            console.log(value);
            if (!Array.isArray(userObject[key])) {
                userObject[key] = [];
                userObject[key].push(value);
            } else {
                userObject[key].push(value);
            }
        }
    });
    console.log(userObject);
    const jsonUser = JSON.stringify(userObject)
    console.log(jsonUser)
    await fetch(
        'api/user',
        {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
                Accept: "application/json"
            },
            body: jsonUser
        }
    );

    $('#closeEditForm').click();
    await loadUsersTable();
}

async function resetEditModalFunc() {
    const form = document.getElementById("editModalForm");
    for (let option of form.elements["roleList"].options) {
        option.selected = false;
    }
}