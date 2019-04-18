var mysql = require('mysql');
var async = require('async');

var model = function() {};
model.prototype.getData = function(confDB, table, cbk) {
    var conn = mysql.createConnection(confDB.mysql.conn);
    conn.connect();
    async.series(getHandlers(conn, table), function(err, results) {

        //var data = { "name": "Alan", "hometown": "Somewhere, TX", "kids": [{"name": "Jimmy", "age": "12"}, {"name": "Sally", "age": "4"}]};
        //cbk(data);

        var data = {};
        data.table = table;
        data.namespace = confDB.namespace;
        data.allfields = {};
        data.className = $.capitalize(table);

        var fields = getFields(results);

        var ids = getIds(fields);
        data.ids = ids;

        var update = getUpdate(fields);
        data.allfields.update = update;

        var base = getBaseColums(fields);
        data.allfields.base = base;

        var other = getOtherColumns(fields, base, update);
        data.allfields.other = other;
        //console.log(data);
        cbk(data);
        conn.end();
    });
};

module.exports = new model();

// 获取所有的 id 字段
function getIds(fields){
    var ids = {};
    if($.contains(fields, "uid")){
        ids.uid = "uid";
    }
    if($.contains(fields, "oid")){
        ids.uid = "oid";
    }
    return ids;
}

// 获取所有的字段
function getFields(results){
    var desc = results.desc;
    var fields = new Array();
    $.forEach(desc, function(colum){
        fields.push(colum.Field)
    });
    return fields;
}

// 组织 othercolums
function getOtherColumns(fields, base, update){
    var other = new Array();
    $.forEach(fields, function(item){
        if(!$.contains(base, item) && item != update){
            other.push(item);
        }
    });
    return other;
}

// 判断 updatetime 是否存在
function getUpdate(fields){
    var updatetime = "updatetime";
    if($.contains(fields, updatetime) >= 0){
        return update = updatetime;
    }
}

// 查找 basecolumns 的字段
function getBaseColums(fields){
    var base = new Array();
    var basecolumns = ["uid", "oid", "createtime"];
    $.forEach(basecolumns, function(columname){
        if($.contains(fields, columname)){
            if(columname == "oid"){
                if(!$.contains(fields, "uid")){
                    base.push(columname);
                }
            }else{
                base.push(columname);
            }

        }
    });
    return base;
}

function getHandlers(conn, table){
    return {
        desc: function(callback) {
            conn.query('desc `' + table + '`', function(err, rows, fields) {
                if (err) throw err;
                //console.log("I am in desc handler");
                callback(null, rows);
            })
        }
    };
}
