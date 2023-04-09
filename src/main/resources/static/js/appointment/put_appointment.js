window.addEventListener('load', function () {

    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado del estudiante
    const formulario = document.querySelector('#update_appointment_form');
    formulario.addEventListener('submit', function (event) {
        let appointmentId = document.querySelector('#id').value;

        //creamos un JSON que tendrá los datos del estudiante
        //a diferencia de un estudiante nuevo en este caso enviamos el id
        //para poder identificarlo y modificarlo para no cargarlo como nuevo
        const formData = {
            id: document.querySelector('#id').value,
            patient_id : document.querySelector('#patient_id').value,
            dentist_id : document.querySelector('#dentist_id').value,
            date : document.querySelector('#date').value,
            description : document.querySelector('#description').value

        };

        //invocamos utilizando la función fetch la API estudiantes con el método PUT
        //que modificará al estudiante que enviaremos en formato JSON
        const url = '/appointments';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => response.json())

    })
 })

    //Es la funcion que se invoca cuando se hace click sobre el id de un estudiante del listado
    //se encarga de llenar el formulario con los datos del estudiante
    //que se desea modificar
    function findBy(id) {
          const url = '/appointments'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let appointments = data;
              document.querySelector('#id').value = appointments.id;
              document.querySelector('#dentist_id').value = appointments.dentist.enrollment
              document.querySelector('#patient_id').value = appointments.patient.dni
              document.querySelector('#date').value = appointments.date;
              document.querySelector('#description').value = appointments.description;

            //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_appointment_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }
