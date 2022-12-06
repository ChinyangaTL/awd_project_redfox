const form = document.getElementById('form');
const firstName = document.getElementById('first-name');
const lastName = document.getElementById('last-name');
const email = document.getElementById('email');
const password = document.getElementById('password');

form.addEventListener('submit', (e) => {
  e.preventDefault();

  const firstNameValue = firstName.value;
  const lastNameValue = lastName.value;
  const emailValue = email.value;
  const passwordValue = password.value;

  if(firstNameValue === '') {
    setErrorFor(firstName, 'Field cannot be empty')
  }
  else {
    setSuccessFor(firstName)
  }
  if(lastNameValue === '') {
    setErrorFor(lastName, 'Field cannot be empty')
  }
  else {
    setSuccessFor(lastName)
  }
  if(emailValue === '') {
    setErrorFor(email, 'Field cannot be empty')
  }
  else if(!isEmail(emailValue)) {
    setErrorFor(email, 'Looks like this is not an email')
  }
  else {
    setSuccessFor(email)
  }
  if(passwordValue === '') {
    setErrorFor(password, 'Field cannot be empty')
  }
  else if(passwordValue.length < 7) {
    setErrorFor(password, 'Password must be at least 8 characters')
  }
  else {
    setSuccessFor(password)
    submitForm()
  }

})

function submitForm() {
  var http = new XMLHttpRequest();
  http.open("POST", "http://localhost:8080/redfox_war_exploded/ClientController", true);
  http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
  const params = `firstName=${document.getElementById("first-name").value}&surname=${document.getElementById("last-name").value}&email=${document.getElementById("email").value}&password=${document.getElementById("password").value}&confirmPassword=${document.getElementById("confirmPassword").value}`
  //var params = "firstName=" + <<get search value>>; // probably use document.getElementById(...).value
  http.send(params);
  http.onload = function() {
    alert(http.responseText);
  }
}

function setErrorFor(input, message) {
  const field = input.parentElement; //form-control
  const errorMessage = field.querySelector('.error-message');

  field.className = 'form-control show-error';
  errorMessage.innerText = message;
}

function setSuccessFor(input) {
  const field = input.parentElement;
  field.className = 'form-control success'
}


function isEmail(email) {
  return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email);
}
