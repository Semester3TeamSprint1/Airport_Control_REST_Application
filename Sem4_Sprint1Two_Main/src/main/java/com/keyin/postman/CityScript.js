const jsonData = [{
  "id": 1,
  "name": "Toronto",
  "province": "ON"
}, {
  "id": 2,
  "name": "Vancouver",
  "province": "BC"
}, {
  "id": 3,
  "name": "Montreal",
  "province": "QC"
}, {
  "id": 4,
  "name": "Calgary",
  "province": "AB"
}, {
  "id": 5,
  "name": "Ottawa",
  "province": "ON"
}, {
  "id": 6,
  "name": "Edmonton",
  "province": "AL"
}, {
  "id": 7,
  "name": "Quebec City",
  "province": "QC"
}, {
  "id": 8,
  "name": "Winnipeg",
  "province": "MB"
}, {
  "id": 9,
  "name": "Hamilton",
  "province": "ON"
}, {
  "id": 10,
  "name": "Halifax",
  "province": "NS"
}, {
  "id": 11,
  "name": "Victoria",
  "province": "BC"
}, {
  "id": 12,
  "name": "Regina",
  "province": "SK"
}]

const endpointUrl = 'http://localhost:8080/city/addCity/';

function sendPostRequest(data) {
  pm.sendRequest({
    url: endpointUrl,
    method: 'POST',
    header: {
      'Content-Type': 'application/json'
    },
    body: {
      mode: 'raw',
      raw: JSON.stringify(data)
    }
  }, (err, res) => {
    if (err) {
      console.error(err);
      return;
    }

    console.log(res.json());
  });
}

// Loop through each object in the array and send a POST request
jsonData.forEach((data) => {
  sendPostRequest(data);
});