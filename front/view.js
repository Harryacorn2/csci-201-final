const ENDPOINT = "/api/view";
const PERIOD = 1000;  // milliseconds per poll 

// updates the DOM using the update data
function show(data) {
}

// requests the view endpoint to update the DOM
function update() {
    fetch(ENDPOINT + "?" + new URLSearchParams({
        room: 0,
    }))
    .then(resp => resp.json())
    .then(show);
}

// polls for updates
function poll() {
    setTimeout(poll, PERIOD);
    update();
}

// start polling
poll();
