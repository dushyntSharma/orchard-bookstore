function formData() {
    var firstname = $("#firstname").val();
    var lastname = $("#address").val();
    var gender = $("#gender").val();

    console.log(firstname);
    console.log(lastname);
    console.log(gender);

    var fmdata = {
        firstname: firstname,
        lastname: lastname,
        gender: gender,
    }
    console.log(fmdata);
    $.ajax({
        url: "/bin/aemTestServlet",
        type: "post",
        data: { "data": JSON.stringify(fmdata) },
        success: function (data) {
            window.location.href = data;
        },
        error: function (data) {
            window.location.href = data;
        }
    });
}