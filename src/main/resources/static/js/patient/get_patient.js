
window.onload = function(){

    let url = '/patients'

    const settings = {
        method : 'GET'
    }

    fetch(url,settings)
    .then(function(response){
        return response.json()
    })
    .then(function(data){
        console.log(data)

        renderTable(data)

    })

    const form = document.querySelector('form');
    form.addEventListener('input', searchByLastname);

    function searchByLastname(event) {
      event.preventDefault();

      const inputLastname = document.querySelector('#inputLastname');
      const lastname = inputLastname.value.trim();


      let url = '/patients/getLastnameLike?lastname=' + lastname;

      const settings = {
        method: 'GET'
      };

      fetch(url, settings)
        .then(function(response) {
          return response.json();
        })
        .then(function(data) {
          console.log(data);

          renderTable(data);
        });
    }
    }

    function renderTable(datos){

        let table = document.querySelector('#tablePatients');
        table.innerHTML = "";



        datos.forEach(patient => {


        let deleteButton = '<button' +
                                               ' id=' + '\"' + 'btn_delete_' + patient.id + '\"' +
                                               ' type="button" onclick="deleteBy('+patient.id+')" class="btn btn-danger btn_delete">' +
                                               '&times' +
                                               '</button>';

        let updateButton = '<button id="btn_id_' + patient.id + '" type="button" onclick="findBy('+patient.id+')" class="btn btn-info btn_id">' +
                                                  '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">' +
                                                      '<path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>' +
                                                      '<path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>' +
                                                  '</svg>' +
                                              '</button>';

                 table.innerHTML += `
        <tr>

       <td>${patient.id}</td>
              <td>${patient.lastname}</td>
              <td>${patient.name}</td>
              <td>${patient.dni}</td>
              <td>${patient.address.street}</td>
              <td>${patient.address.number}</td>
              <td>${patient.address.city}</td>
       <td> ${updateButton} </td>
       <td> ${deleteButton} </td>
    </tr>`
        });

    }
