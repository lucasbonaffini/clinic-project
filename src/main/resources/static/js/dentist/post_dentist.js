window.addEventListener('load', function () {


    const form = document.querySelector('#add_dentist');


    form.addEventListener('submit', function (event) {


        const data = {
        enrollment : document.querySelector('#enrollment').value,
        lastname : document.querySelector('#lastname').value,
        name : document.querySelector('#name').value
        };


        const url = '/dentists';
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
        document.querySelector('#enrollment').value = "";
        document.querySelector('#lastname').value = "";
        document.querySelector('#name').value = "";

    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/dentist_list.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});
