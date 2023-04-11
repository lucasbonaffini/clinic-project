window.addEventListener('load', function () {


    const form = document.querySelector('#update_dentist_form');
    form.addEventListener('submit', function (event) {
        let dentistEnrollment = document.querySelector('#enrollment').value;


        const formData = {
            enrollment: document.querySelector('#enrollment').value,
            lastname: document.querySelector('#lastname').value,
            name: document.querySelector('#name').value

        };


        const url = '/dentists';
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


    function findBy(enrollment) {
          const url = '/dentists'+"/"+enrollment;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let dentist = data;
              document.querySelector('#enrollment').value = dentist.enrollment;
              document.querySelector('#lastname').value = dentist.lastname;
              document.querySelector('#name').value = dentist.name;


              document.querySelector('#div_dentist_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }
