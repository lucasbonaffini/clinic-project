window.addEventListener('load', function () {

    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado del estudiante
    const formulario = document.querySelector('#update_patient_form');
    formulario.addEventListener('submit', function (event) {
        let patientId = document.querySelector('#id').value;

        //creamos un JSON que tendrá los datos del estudiante
        //a diferencia de un estudiante nuevo en este caso enviamos el id
        //para poder identificarlo y modificarlo para no cargarlo como nuevo
        const formData = {
            id: document.querySelector('#id').value,
            lastname: document.querySelector('#lastname').value,
            name: document.querySelector('#name').value,
            dni: document.querySelector('#dni').value,
            address: {
              street: document.querySelector('#street').value,
              number: document.querySelector('#number').value,
              city: document.querySelector('#city').value
            }

        };

        //invocamos utilizando la función fetch la API estudiantes con el método PUT
        //que modificará al estudiante que enviaremos en formato JSON
        const url = '/patients';
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
          const url = '/patients'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let patient = data;
              document.querySelector('#id').value = patient.id;
              document.querySelector('#lastname').value = patient.lastname;
              document.querySelector('#name').value = patient.name;
              document.querySelector('#dni').value = patient.dni;
              document.querySelector('#street').value = patient.address.street;
              document.querySelector('#number').value = patient.address.number;
              document.querySelector('#city').value = patient.address.city;


            //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_patient_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }
