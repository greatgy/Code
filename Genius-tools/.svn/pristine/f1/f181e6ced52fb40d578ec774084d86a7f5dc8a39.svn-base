require('./global');
var executor = require('./controller/MapperMybatisController');
var confDBTest = require('./resources/config/ProjTestDBTest');

var app = function() {};
app.prototype.startup = function(args) {
    executor.execute(confDBTest);
};
module.exports = new app();