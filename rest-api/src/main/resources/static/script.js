document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('login');
    const registerForm = document.getElementById('register');

    const showRegisterLink = document.getElementById('showRegister');
    const showLoginLink = document.getElementById('showLogin');

    showRegisterLink.addEventListener('click', (event) => {
        event.preventDefault();
        loginForm.classList.add('hidden');
        registerForm.classList.remove('hidden');
    });

    showLoginLink.addEventListener('click', (event) => {
        event.preventDefault();
        registerForm.classList.add('hidden');
        loginForm.classList.remove('hidden');
    });
	
	registerForm.addEventListener('submit', async (event) => {
	    event.preventDefault();

	    const name = document.getElementById('registerName').value;
	    const age = document.getElementById('registerAge').value;
	    const address = document.getElementById('registerAddress').value;
	    const email = document.getElementById('registerEmail').value;
	    const password = document.getElementById('registerPassword').value;

	    try {
	        const response = await fetch('/api/clients', {
	            method: 'POST',
	            headers: { 'Content-Type': 'application/json' },
	            body: JSON.stringify({ name, age, address, email, password })
	        });

	        const result = await response.json();
	        if (response.ok) {
	            alert('Cadastro realizado com sucesso!');
	            document.getElementById('showLogin').click();
	        } else {
	            alert(result.message || 'Erro ao fazer cadastro');
	        }
	    } catch (error) {
	        console.error('Erro na requisição:', error);
	    }
	});
	
	loginForm.addEventListener('submit', async (event) => {
	    event.preventDefault();

	    const email = document.getElementById('loginEmail').value;
	    const password = document.getElementById('loginPassword').value;

	    try {
	        const response = await fetch('/api/clients/login', {
	            method: 'POST',
	            headers: { 'Content-Type': 'application/json' },
	            body: JSON.stringify({ email, password })
	        });

	        const result = await response.text();
	        if (response.ok) {
	            alert(result);
				window.location.href = "/login-success.html";
	        } else {
	            alert('Credenciais inválidas');
	        }
	    } catch (error) {
	        console.error('Erro na requisição:', error);
	    }
	});
});
