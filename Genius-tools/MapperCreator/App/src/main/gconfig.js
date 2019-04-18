/**
 * 全局的配置，直接使用gconfig调用，或global.gconfig
 */

var path = require('path');

var config = {
    "basePath": {
        "tmpl": path.resolve(__dirname, './', 'resources/tmpl'),
        "target": path.resolve(__dirname, '../../', 'target')
    }
};

module.exports = config;