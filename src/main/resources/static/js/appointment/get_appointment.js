
window.onload = function(){

    let url = '/appointments'

    const settings = {
        method : 'GET'
    }

    fetch(url,settings)
    .then(function(response){
        return response.json()
    })
    .then(function(data){
        console.log(data)

        renderizarTabla(data)

    })
    const form = document.querySelector('form');
        form.addEventListener('input', searchByEnrollment);

        function searchByEnrollment(event) {
          event.preventDefault(); // prevenir comportamiento predeterminado del formulario

          const inputEnrollment = document.querySelector('#inputEnrollment');
          const enrollment = inputEnrollment.value.trim();

          // Realizar solicitud de búsqueda
          let url = '/appointments/getByEnrollment?enrollment=' + enrollment;

          const settings = {
            method: 'GET'
          };

          fetch(url, settings)
            .then(function(response) {
              return response.json();
            })
            .then(function(data) {
              console.log(data);

              renderizarTabla(data);
            });
        }

    }

    function renderizarTabla(datos){

        let table = document.querySelector('#tableAppointments');
        table.innerHTML = "";



        datos.forEach(appointment => {


        let deleteButton = '<button' +
                                               ' id=' + '\"' + 'btn_delete_' + appointment.id + '\"' +
                                               ' type="button" onclick="deleteBy('+appointment.id+')" class="btn btn-danger btn_delete">' +
                                               '&times' +
                                               '</button>';

        let updateButton = '<button id="btn_id_' + appointment.id + '" type="button" onclick="findBy('+appointment.id+')" class="btn btn-info btn_id">' +
                                                  '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">' +
                                                      '<path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>' +
                                                      '<path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>' +
                                                  '</svg>' +
                                              '</button>';

                 table.innerHTML += `
        <tr>

       <td>${appointment.id}</td>
              <td>${appointment.dentist.lastname} / ${appointment.dentist.name}</td>
              <td>${appointment.patient.lastname} / ${appointment.patient.name}</td>
              <td>${appointment.date}</td>
              <td>${appointment.description}</td>
        <td> ${updateButton} </td>
       <td> ${deleteButton} </td>
    </tr>`
        });

    }
