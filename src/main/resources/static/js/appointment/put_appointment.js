window.addEventListener('load', function () {


    const form = document.querySelector('#update_appointment_form');
    form.addEventListener('submit', function (event) {
        let appointmentId = document.querySelector('#id').value;


        const formData = {
            id: document.querySelector('#id').value,
            patient_id : document.querySelector('#patient_id').value,
            dentist_id : document.querySelector('#dentist_id').value,
            date : document.querySelector('#date').value,
            description : document.querySelector('#description').value

        };


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


              document.querySelector('#div_appointment_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }
