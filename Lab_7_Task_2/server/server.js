const express = require('express');
const path = require('path');

const app = express();
const PORT = process.env.PORT || 3000;

app.use(express.static(path.join(__dirname)));

app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'index.html'));
});

app.get('/download/tours.json', (req, res) => {
  res.download(path.join(__dirname, 'tours.json'));
});

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
