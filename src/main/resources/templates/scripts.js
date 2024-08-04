function moveCard(event, targetColumn) {
    const card = event.target.closest('.card');
    const target = document.querySelector(`.${targetColumn}`);
    
    if (targetColumn === 'done') {
        event.target.remove();
    } else {
        event.target.setAttribute('onclick', `moveCard(event, '${targetColumn === 'todo' ? 'doing' : 'done'}')`);
    }

    target.appendChild(card);
}

document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('addTodoForm');
    const backButton = document.getElementById('backButton');

    form.addEventListener('submit', (event) => {
        event.preventDefault(); // Prevent form from submitting normally

        const title = document.getElementById('title').value;
        const username = document.getElementById('username').value;
        const priority = document.querySelector('input[name="priority"]:checked').value;

        // Create the data object to send to the server
        const todoData = {
            title: title,
            username: username,
            priority: priority,
            // registrationDate will be handled by the server
        };

        // Simulate form submission
        // Replace this with actual server request code
        console.log('Submitting data:', todoData);

        // Reset the form after submission
        form.reset();

        // Redirect to the main page
        window.location.href = 'main.html'; // Change 'main.html' to your actual main page URL
    });

    backButton.addEventListener('click', () => {
        window.location.href = 'main.html'; // Change 'main.html' to your actual main page URL
    });
});