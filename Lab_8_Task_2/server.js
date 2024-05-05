// Install express server
const express = require('express');
const path = require('path');

const app = express();
const PORT = process.env.PORT || 8080;

app.use(express.static('./dist'));

app.get('/', (req, res) =>
    res.sendFile('index.html', { root: 'dist/' }),
);

app.get('/download', (req, res) => {
    const file = path.join(__dirname, 'data.json');
    res.download(file);
});

app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});
