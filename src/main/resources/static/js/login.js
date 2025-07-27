const { createApp } = Vue;

createApp({
  data() {
    return {
      email: '',
      password: '',
      errorMessage: ''
    };
  },
  methods: {
    async handleLogin() {
      try {
        const response = await axios.post('/api/login', {
          email: this.email,
          password: this.password
        });

        if (response.data.status === 'success') {
          console.log("success");
          window.location.href = '/';
        } else {
          console.log("failed");
          this.errorMessage = response.data.message || 'ログインに失敗しました';
        }
      } catch (error) {
        if (error.response && error.response.status === 401) {
          this.errorMessage = '認証に失敗しました';
        } else {
          this.errorMessage = '通信エラーが発生しました';
        }
      }
    }
  }
}).mount('#app');