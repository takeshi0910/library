// イベントハンドラ登録：ページ読み込み時と検索ボタン押下時に検索処理を実行
window.addEventListener('DOMContentLoaded', function () {
  // ページ読み込み時に検索実行
  searchBooks();

  // 検索ボタン押下時に再検索
  document.getElementById('searchBtn').addEventListener('click', function () {
    $('#bookTable').DataTable().ajax.reload();
  });
});

// 書籍検索メソッド本体
function searchBooks() {
    $('#bookTable').DataTable({
        dom: 'lrtip',
        retrieve: true,
        paging: true,
        searching: true,
        processing: true,
        serverSide: true,
        ordering: true,
        ajax: fetchBooks,
        columns: [
            { data: 'title' },
            { data: 'author' },
            { data: 'genreName' },
            { data: 'isbn' }
        ],
         language: {
             url: '/js/i18n/ja.json'
         }
    });
}

// axiosによるデータ取得
function fetchBooks(data, callback) {
  const payload = buildPayload(data);

  axios.post('/api/books/search', payload)
    .then(response => {
      callback(response.data); // DataTablesに結果を渡す
    })
    .catch(error => {
      console.error('検索失敗:', error);
      callback({
        data: [],
        recordsTotal: 0,
        recordsFiltered: 0,
        draw: data.draw
      });
    });
}

// 検索フォームの入力値を全て取得してシリアライズ化(JSON形式に変換）
function getSearchFormData() {
    const formArray = $('#searchForm').serializeArray();
    const formData = {};
    formArray.forEach(item => {
        formData[item.name] = item.value;
    });
    return formData;
}

// 検索処理に用いるpayloadを作成する。payloadは検索値とDataTablesの情報を保持する。
function buildPayload(data) {
    const SearchFormData = getSearchFormData();
    return {
        ...SearchFormData,
        draw: data.draw,
        start: data.start,
        length: data.length,
        order: data.order,
        columns: data.columns
    };
}

// 本をクリックしたら詳細画面へ　※管理者権限のみ
function attachRowClickHandlers() {
    const role = document.body.dataset.role;
    if (role !== 'ADMIN') return;

    document.querySelectorAll('#result tr[data-id]').forEach(row => {
        row.addEventListener('click', () => {
            window.location.href = `/books/form?id=${row.dataset.id}`;
        });
    });
}