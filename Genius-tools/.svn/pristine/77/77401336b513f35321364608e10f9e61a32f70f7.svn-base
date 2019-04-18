var path = require('path');
var fs = require('fs');
var model = require('../model/MysqlModel');

var controller = function() {};

controller.prototype.execute = function(confDB) {
    createMapperFiles(confDB);
};

/**
 * 创建所有表对应的mapper文件
 * @param confDB
 */
function createMapperFiles(confDB) {
    confDB.tables.forEach(function(tablename){
    var template = getTmpl(path.resolve(gconfig.basePath.tmpl, confDB.tmpl + confDB.tmplDefaultExt));
    model.getData(confDB, tablename, function(data){
        var result = template(data);
        var targetFilePath = getTargetFilePath(confDB, tablename);
        mkdirSync(targetFilePath);
        fs.writeFileSync(targetFilePath, result, { flags: 'w', encoding: "utf-8"});
        console.log("success created: " + targetFilePath);
    });
});
}

/**
 * 获取模板
 * @param tmplpath
 * @returns {*}
 */
function getTmpl(tmplpath) {
    var tmplContent = fs.readFileSync(tmplpath, { flags: 'r', encoding: "utf-8"});
    return Handlebars.compile(tmplContent);
};

function getTargetFilePath(confDB, tableName) {
    var targetDirPath = path.resolve(gconfig.basePath.target, confDB.namespace.split(".").join("/"));
    var className = confDB.ClassNameDefaultFormatter.format($.capitalize(tableName));
    var targetFilePath = path.join(targetDirPath, className + confDB.targetFileDefaultExt);
    return targetFilePath;
};

/**
 * 创建某个文件的所有父级目录
 * @param filepath
 */
function mkdirSync(filepath) {
    var dir = path.dirname(filepath);
    if(!fs.existsSync(dir)) {
        mkdirSync(dir);
        return fs.mkdirSync(dir);
    }
};

module.exports = new controller();