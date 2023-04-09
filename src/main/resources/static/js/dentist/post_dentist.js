window.addEventListener('load', function () {

     //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
     //los datos que el usuario cargará del nuevo dentista
    const form = document.querySelector('#add_dentist');

    //Ante un submit del formulario se ejecutará la siguiente funcion
    form.addEventListener('submit', function (event) {

        //creamos un JSON que tendrá los datos del nuevo dentista
        const data = {
        enrollment : document.querySelector('#enrollment').value,
        lastname : document.querySelector('#lastname').value,
        name : document.querySelector('#name').value
        };

        //invocamos utilizando la función fetch la API dentistas con el método POST
        //que guardará al dentista que enviaremos en formato JSON
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
               //Si no hay ningun error se muestra un mensaje diciendo que el dentista
               //se agrego bien
                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong></strong> Successful dentist registration </div>'

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
                 //se dejan todos los campos vacíos por si se quiere ingresar otro dentista
                 resetUploadForm();

            })
            .catch(error => {
                 //Si hay algun error se muestra un mensaje diciendo que el dentista
                 //no se pudo guardar y se intente nuevamente
                  let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> Error try again</strong> </div>'

                   document.querySelector('#response').innerHTML = errorAlert;
                   document.querySelector('#response').style.display = "block";
                   //se dejan todos los campos vacíos por si se quiere ingresar otro dentista
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
