async function deleteUserForm(id) {

    const response = (await fetch('api/user/' + id));
    const user = await response.json();
    const form = document.getElementById("deleteModalForm");
    form.addEventListener("submit", deleteUser);
    const fields = ["id", "username", "age", "secondName", "email", "roleList"];

    for (const key of fields) {
        if (key === "roleList") {
            for (let option of form.elements[key].options) {
                for (const role of user.roleList) {
                    console.log(role["roleName"]);
                    if (option.value === role["roleName"]) {
                        option.selected = true;
                    }
                }
            }
        } else {
            form.elements[key].setAttribute('value', user[key]);
        }
    }


}

async function deleteUser(event) {
    event.preventDefault();
    const form = event.currentTarget;

    await fetch(
        'api/user/' + form.elements["id"].value,
        {
            method: 'DELETE',
            headers: {
                "Content-Type": "application/json",
                Accept: "application/json"
            }
        }
    );

    $('#closeDeleteForm').click();
    await loadUsersTable();
}

async function resetDeleteModalFunc() {
    const form = document.getElementById("deleteModalForm");
    for (let option of form.elements["roleList"].options) {
        option.selected = false;
    }
}