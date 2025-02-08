const socket = new SockJS('/websocket');  // Establish WebSocket connection to the server
const stompClient = Stomp.over(socket);

stompClient.connect({}, () => {
    console.log('Connected to WebSocket');

    stompClient.subscribe('/topic/credit_notification_sender', (message) => {
        console.log('Received message:', message.body);

        const data = JSON.parse(message.body);

        fetchApplicationData(data.referenceId);
    });
});

function fetchApplicationData(referenceId) {
    fetch(`http://localhost:8081/applications/${referenceId}`)
        .then(response => response.json())
        .then(application => {
            createApplicationCard(application);
        })
        .catch(error => console.error('Error fetching application data:', error));
}

function createApplicationCard(application) {
    const card = document.createElement('div');
    card.classList.add('application-card');
    card.style.border = '1px solid #ccc';
    card.style.padding = '10px';
    card.style.margin = '10px';
    card.style.borderRadius = '5px';
    card.style.width = '300px';

    const amount = document.createElement('h3');
    amount.textContent = `Amount: $${application.amount.toFixed(2)}`;
    card.appendChild(amount);

    const purpose = document.createElement('p');
    purpose.textContent = `Purpose: ${application.purpose}`;
    card.appendChild(purpose);

    const term = document.createElement('p');
    term.textContent = `Term: ${application.term} months`;
    card.appendChild(term);

    const status = document.createElement('p');
    status.textContent = `Status: ${application.applicationStatus}`;
    card.appendChild(status);

    document.getElementById('application-cards-container').appendChild(card);
}
