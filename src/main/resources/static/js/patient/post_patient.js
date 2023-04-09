window.addEventListener('load', function () {


    const form = document.querySelector('#add_patient');


    form.addEventListener('submit', function (event) {


        const data = {
        lastname: document.querySelector('#lastname').value,
                name: document.querySelector('#name').value,
                dni: document.querySelector('#dni').value,
                address: {
                    street: document.querySelector('#street').value,
                    number: document.querySelector('#number').value,
                    city: document.querySelector('#city').value
                }
        };


        const url = '/patients';
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

        document.querySelector('#lastname').value = "";
        document.querySelector('#name').value = "";
        document.querySelector('#dni').value = "";

                    document.querySelector('#street').value = "";
                    document.querySelector('#number').value = "";
                    document.querySelector('#city').value = "";

    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/patient_list.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});
