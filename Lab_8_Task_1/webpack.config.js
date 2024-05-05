const HtmlWebpackPlugin = require("html-webpack-plugin");
const path = require("path");

module.exports = { 
 mode: 'development', 
 entry: './src/index.js',
 output: {
   path: path.resolve(__dirname, 'dist'),
   filename: 'bundle.js',
 },
 resolve: {
   extensions: [ '.js' ],
 },
 module: {

 },
 plugins: [
   new HtmlWebpackPlugin({
     template: path.resolve(__dirname, "index.html")
   })
 ]
};