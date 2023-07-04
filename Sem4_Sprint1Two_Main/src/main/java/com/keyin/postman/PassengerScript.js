const jsonData = [{
  "id": 1,
  "firstName": "Garik",
  "lastName": "Highman",
  "phoneNumber": "873-815-0991",
  "aircraftIdsList": [1],
  "airportIdsList": [1],
}, {
  "id": 2,
  "firstName": "Jonah",
  "lastName": "Lavin",
  "phoneNumber": "360-169-2782",
  "aircraftIdsList": [2],
  "airportIdsList": [2],
}, {
  "id": 3,
  "firstName": "Gae",
  "lastName": "Philpotts",
  "phoneNumber": "681-806-4669",
  "aircraftIdsList": [3,4],
  "airportIdsList": [3,1],
}, {
  "id": 4,
  "firstName": "Carla",
  "lastName": "Biaggi",
  "phoneNumber": "858-620-2986",
  "aircraftIdsList": [4],
  "airportIdsList": [4],
}, {
  "id": 5,
  "firstName": "Shayla",
  "lastName": "Parmenter",
  "phoneNumber": "960-630-4919",
  "aircraftIdsList": [5],
  "airportIdsList": [5],
}, {
  "id": 6,
  "firstName": "Guendolen",
  "lastName": "Inchboard",
  "phoneNumber": "712-622-1660",
  "aircraftIdsList": [6,10],
  "airportIdsList": [6],
}, {
  "id": 7,
  "firstName": "Aurelie",
  "lastName": "Ackerman",
  "phoneNumber": "584-356-2191",
  "aircraftIdsList": [7,8,3],
  "airportIdsList": [7,1,6],
}, {
  "id": 8,
  "firstName": "Emmalynne",
  "lastName": "Stelljes",
  "phoneNumber": "825-392-6610",
  "aircraftIdsList": [8],
  "airportIdsList": [8],
}, {
  "id": 9,
  "firstName": "Obediah",
  "lastName": "Noell",
  "phoneNumber": "298-938-0067",
  "aircraftIdsList": [9],
  "airportIdsList": [9],
}, {
  "id": 10,
  "firstName": "Ole",
  "lastName": "Heliar",
  "phoneNumber": "860-819-3578",
  "aircraftIdsList": [10],
  "airportIdsList": [10],
}, {
  "id": 11,
  "firstName": "Sammie",
  "lastName": "Nowick",
  "phoneNumber": "354-921-4083",
  "aircraftIdsList": [11,9],
  "airportIdsList": [11,3],
}, {
  "id": 12,
  "firstName": "Alyosha",
  "lastName": "McIlhatton",
  "phoneNumber": "567-804-6698",
  "aircraftIdsList": [12],
  "airportIdsList": [12],
}]

const endpointUrl = 'http://localhost:8080/passenger/addPassenger/';

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