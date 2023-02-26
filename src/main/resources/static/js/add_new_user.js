document.getElementById("addUserForm").addEventListener("submit", addUser)

async function addUser(event) {
    event.preventDefault();

    let addForm = event.currentTarget;
    const formData = new FormData(addForm);
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
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
                Accept: "application/json"
            },
            body: jsonUser
        }
    );

    addForm.reset();
    await loadUsersTable();
    document.getElementById("nav-home-tab").setAttribute("class", "nav-link active");
    document.getElementById("nav-profile-tab").setAttribute("class", "nav-link");
    document.getElementById("nav-home").setAttribute("class", "tab-pane fade active show");
    document.getElementById("nav-profile").setAttribute("class", "tab-pane fade");


}