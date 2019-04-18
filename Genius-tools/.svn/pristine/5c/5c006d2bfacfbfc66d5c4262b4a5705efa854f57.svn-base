/**
 * 全局变量的设置，比如设置global.gconfig、global.$等
 */
global.gconfig = require('./gconfig');
global.$ = getLodash();
global.Handlebars = getHandlebars();

/**
 * 删除字符串两边的空白
 */
String.prototype.trim = function() {
    return this.replace(/^\s*|\s*$/g, "");
};
/**
 * 字符串格式化
 * var template1="我是{0}，今年{1}了";
 * var template2="我是{name}，今年{age}了";
 * var result1=template1.format("loogn",22);
 * var result2=template2.format({name:"loogn",age:22});
 * 两个结果都是"我是loogn，今年22了"
 *
 */
String.prototype.format = function(args) {
    var result = this;
    if (arguments.length > 0) {
        if (arguments.length == 1 && typeof (args) == "object") {
            for (var key in args) {
                if(args[key]!=undefined){
                    var reg = new RegExp("({" + key + "})", "g");
                    result = result.replace(reg, args[key]);
                }
            }
        }
        else {
            for (var i = 0; i < arguments.length; i++) {
                if (arguments[i] != undefined) {
                    var reg = new RegExp("({[" + i + "]})", "g");
                    result = result.replace(reg, arguments[i]);
                }
            }
        }
    }
    return result;
};

/**
 * 封装lodash
 */
function getLodash() {
    var _ = require('lodash');
    _.mixin({ 'capitalize': function (str) {
        return str.charAt(0).toUpperCase() + str.slice(1).toLowerCase();
    } });
    return _;
}

function getHandlebars() {
    var Handlebars = require('Handlebars');
    Handlebars.registerHelper('brace', function(arg) {
        return '{' + arg + '}';
    });
    return Handlebars;
}