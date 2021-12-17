import axios from 'axios'

function getContatos() {
    axios.get('http://localhost:8080/contatos')
    .then(response => console.log(response.data))
    .catch(error => console.log(error))
}

module.exports = {getContatos}