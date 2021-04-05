const ENDPOINT = "/api/view";
const PERIOD = 1000;  // milliseconds per poll 
const STREAM = document.querySelector("#stream");
let latest_id = -1;

// adds a message to the DOM
function add_message(msg) {
    const row = STREAM.insertRow();
    const timecell = row.insertCell();
    timecell.appendChild(document.createTextNode(msg.time));
    const bodycell = row.insertCell();
    bodycell.appendChild(document.createTextNode(msg.body));
}

// updates page state from data
function update(data) {
    data.forEach(msg => {
        if (msg.id > latest_id) {
            add_message(msg);
            latest_id = msg.id;
        }
    });
}

// requests the view endpoint to update the DOM
function request() {
    fetch(ENDPOINT + "?" + new URLSearchParams({
        room: 0,
        from: latest_id,
    }))
    .then(resp => resp.json())
    .then(update);
}

// polls for updates
function poll() {
    setTimeout(poll, PERIOD);
    request();
}

// start polling
//poll();
