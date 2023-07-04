const jsonData = [
  {
    "id": 1,
    "type": "Embraer E190",
    "airlineName": "Southwest Airlines",
    "noOfPassengers": 121
  },
  {
    "id": 2,
    "type": "Boeing 747",
    "airlineName": "Lufthansa",
    "noOfPassengers": 400
  },
  {
    "id": 3,
    "type": "Bombardier Challenger 300",
    "airlineName": "Delta Air Lines",
    "noOfPassengers": 40
  },
  {
    "id": 4,
    "type": "Boeing 747",
    "airlineName": "Delta Air Lines",
    "noOfPassengers": 467
  },
  {
    "id": 5,
    "type": "Boeing 747",
    "airlineName": "British Airways",
    "noOfPassengers": 130
  },
  {
    "id": 6,
    "type": "Airbus A320",
    "airlineName": "Emirates",
    "noOfPassengers": 331
  },
  {
    "id": 7,
    "type": "Cessna 172",
    "airlineName": "United Airlines",
    "noOfPassengers": 143
  },
  {
    "id": 8,
    "type": "Embraer E190",
    "airlineName": "Delta Air Lines",
    "noOfPassengers": 48
  },
  {
    "id": 9,
    "type": "Airbus A320",
    "airlineName": "Delta Air Lines",
    "noOfPassengers": 438
  },
  {
    "id": 10,
    "type": "Embraer E190",
    "airlineName": "Delta Air Lines",
    "noOfPassengers": 83
  },
  {
    "id": 11,
    "type": "Cessna 172",
    "airlineName": "American Airlines",
    "noOfPassengers": 163
  },
  {
    "id": 12,
    "type": "Airbus A320",
    "airlineName": "Singapore Airlines",
    "noOfPassengers": 301
  }
];

const endpointUrl = 'http://localhost:8080/aircraft/addAircraft/';

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