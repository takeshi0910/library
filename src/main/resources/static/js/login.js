document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('loginForm');
    const emailInput = document.getElementById('email');
    const passwordInput = document.getElementById('password');
    const toggleIcon = document.getElementById('togglePassword');
    const errorMessage = document.getElementById('errorMessage');

    let isVisible = false;

    form.addEventListener('submit', async function (e) {
        e.preventDefault();

        const email = emailInput.value;
        const password = passwordInput.value;

        try {
            const response = await axios.post('/api/login', {
                email,
                password
            });

            const data = response.data;

            if (data.success) {
                window.location.href = '/';
            } else {
                errorMessage.textContent = data.message || '認証に失敗しました';
                errorMessage.style.display = 'block';
            }
        } catch (error) {
            console.error('通信エラー:', error);
            if (error.response?.status === 401) {
                errorMessage.textContent = error.response.data.message || '認証に失敗しました';
            } else {
                errorMessage.textContent = 'ログイン中にエラーが発生しました';
            }
            errorMessage.style.display = 'block';
        }
    });
});