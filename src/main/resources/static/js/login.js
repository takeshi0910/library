document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('loginForm');
    const emailInput = document.getElementById('email');
    const passwordInput = document.getElementById('password');
    const toggleIcon = document.getElementById('togglePassword');
    const errorMessage = document.getElementById('errorMessage');

    let isVisible = false;

    // ログイン処理
    form.addEventListener('submit', async function (e) {
        e.preventDefault(); // @submit.prevent の代わり

        const email = emailInput.value;
        const password = passwordInput.value;

        try {
            const response = await fetch('/api/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ email, password })
            });

            const data = await response.json();

            if (data.success) {
                window.location.href = '/';
            } else {
                errorMessage.textContent = data.message || '認証に失敗しました';
                errorMessage.style.display = 'block';
            }
        } catch (error) {
            console.error('通信エラー:', error);
            errorMessage.textContent = 'ログイン中にエラーが発生しました';
            errorMessage.style.display = 'block';
        }
    });
});