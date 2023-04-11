window.addEventListener('load', function () {


    const form = document.querySelector('#update_patient_form');
    form.addEventListener('submit', function (event) {
        let patientId = document.querySelector('#id').value;


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



              document.querySelector('#div_patient_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }
