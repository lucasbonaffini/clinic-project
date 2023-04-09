function deleteBy(enrollment) {
           //con fetch invocamos a la API de dentistas con el método DELETE
           //pasandole el id en la URL
          const url = '/dentists/'+ enrollment;
          const settings = {
              method: 'DELETE'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {

                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong></strong> dentist deleted </div>'

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";



            })
            .catch(error => {
                 //Si hay algun error se muestra un mensaje diciendo que el dentista
                 //no se pudo guardar y se intente nuevamente
                  let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> Error try again</strong> </div>'

                   document.querySelector('#response').innerHTML = errorAlert;
                   document.querySelector('#response').style.display = "block";

                   ;})


}
