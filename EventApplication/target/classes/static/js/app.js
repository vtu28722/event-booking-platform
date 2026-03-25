// Base API URL
const API_BASE = '/api';

// Utility: Show Toast Message
function showToast(message, type = 'success') {
    let container = document.getElementById('toast-container');
    if (!container) {
        container = document.createElement('div');
        container.id = 'toast-container';
        document.body.appendChild(container);
    }

    const toast = document.createElement('div');
    toast.className = `toast ${type}`;
    toast.innerText = message;
    container.appendChild(toast);

    setTimeout(() => {
        toast.classList.add('show');
    }, 10);

    setTimeout(() => {
        toast.classList.remove('show');
        setTimeout(() => toast.remove(), 300);
    }, 3000);
}

// Utility: Check if User is Logged In
async function checkAuth() {
    try {
        const response = await fetch(`${API_BASE}/auth/session`);
        if (response.ok) {
            const userName = await response.text();
            updateNavbar(true, userName);
            return true;
        } else {
            updateNavbar(false);
            return false;
        }
    } catch (e) {
        updateNavbar(false);
        return false;
    }
}

// Utility: Update Navbar based on Auth State
function updateNavbar(isLoggedIn, userName = '') {
    const navLinks = document.getElementById('nav-links');
    if (!navLinks) return;

    if (isLoggedIn) {
        navLinks.innerHTML = `
            <a href="/events.html">Events</a>
            <a href="/create-event.html">Create Event</a>
            <a href="/my-bookings.html">My Bookings</a>
            <a href="/profile.html">Profile (${userName})</a>
            <a href="#" onclick="logout(event)" class="nav-btn">Logout</a>
        `;
    } else {
        navLinks.innerHTML = `
            <a href="/events.html">Events</a>
            <a href="/login.html">Login</a>
            <a href="/register.html" class="nav-btn">Register</a>
        `;
    }
}

// Utility: Logout
async function logout(e) {
    e.preventDefault();
    try {
        const res = await fetch(`${API_BASE}/auth/logout`);
        if (res.ok) {
            showToast('Logged out successfully');
            setTimeout(() => window.location.href = '/', 1000);
        }
    } catch (err) {
        showToast('Logout failed', 'error');
    }
}

// Handle Form Submissions Generic
function handleFormSubmit(formId, apiEndpoint, successRedirect) {
    const form = document.getElementById(formId);
    if (!form) return;

    form.addEventListener('submit', async (e) => {
        e.preventDefault();
        const formData = new FormData(form);
        const data = Object.fromEntries(formData.entries());

        try {
            const res = await fetch(`${API_BASE}${apiEndpoint}`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(data)
            });

            if (res.ok) {
                showToast('Success!');
                if (successRedirect) {
                    setTimeout(() => window.location.href = successRedirect, 1500);
                }
            } else {
                const errData = await res.json();
                showToast(errData.error || 'Request failed', 'error');
            }
        } catch (err) {
            showToast('Network error', 'error');
        }
    });
}

// Ensure Auth runs on load
document.addEventListener('DOMContentLoaded', () => {
    checkAuth();
});
