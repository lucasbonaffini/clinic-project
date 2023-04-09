window.addEventListener('load', function () {

    const form = document.querySelector('#add_appointment');


    form.addEventListener('submit', function (event) {


        const data = {
        patient_id: document.querySelector('#patient_id').value,
                dentist_id: document.querySelector('#dentist_id').value,
                date: document.querySelector('#date').value,
                description: document.querySelector('#description').value

        };


        const url = '/appointments';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {

                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong></strong> Successful dentist registration </div>'

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";

                 resetUploadForm();

            })
            .catch(error => {

                  let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> Error try again</strong> </div>'

                   document.querySelector('#response').innerHTML = errorAlert;
                   document.querySelector('#response').style.display = "block";

                   resetUploadForm();})
    });

    function resetUploadForm(){

        document.querySelector('#patient_id').value = "";
        document.querySelector('#dentist_id').value = "";
        document.querySelector('#date').value = "";
        document.querySelector('#description').value = "";

    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/appointment_list.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});
