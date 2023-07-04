const jsonData = [{
        "id": 1,
        "code": "YHZ",
        "name": "Halifax Stanfield International Airport",
        "cityId": 10
    },{
        "id": 2,
        "code": "YWG",
        "name": "Winnipeg James Armstrong Richardson International Airport",
        "cityId": 8
    },{
        "id": 3,
        "code": "YQB",
        "name": "Québec City Jean Lesage International Airport",
        "cityId": 7
    },{
        "id": 4,
        "code": "YHM",
        "name": "John C. Munro Hamilton International Airport",
        "cityId": 9
    },{
        "id": 5,
        "code": "YUL",
        "name": "Montréal-Pierre Elliott Trudeau International Airport",
        "cityId": 3
    },{
        "id": 6,
        "code": "YVR",
        "name": "Vancouver International Airport",
        "cityId": 2
    },{
        "id": 7,
        "code": "YYJ",
        "name": "Victoria International Airport",
        "cityId": 11
    },{
        "id": 8,
        "code": "YEG",
        "name": "Edmonton International Airport",
        "cityId": 6
    },{
        "id": 9,
        "code": "YYZ",
        "name": "Toronto Pearson International Airport",
        "cityId": 1
    },{
        "id": 10,
        "code": "YOW",
        "name": "Ottawa International Airport",
        "cityId": 5
    },{
        "id": 11,
        "code": "YYC",
        "name": "Calgary International Airport",
        "cityId": 4
    },{
        "id": 12,
        "code": "YQR",
        "name": "Regina International Airport",
        "cityId": 12
    }
]

const endpointUrl = 'http://localhost:8080/airport/addAirport/';

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